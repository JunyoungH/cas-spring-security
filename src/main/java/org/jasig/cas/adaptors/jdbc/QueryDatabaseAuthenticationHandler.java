/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.cas.adaptors.jdbc;

import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.FailedLoginException;
import javax.validation.constraints.NotNull;

/**
 * Class that if provided a query that returns a password (parameter of query
 * must be username) will compare that password to a translated version of the
 * password provided by the user. If they match, then authentication succeeds.
 * Default password translator is plaintext translator.
 *
 * @author Scott Battaglia
 * @author Dmitriy Kopylenko
 * @author Marvin S. Addison
 *
 * @since 3.0
 */
public class QueryDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(QueryDatabaseAuthenticationHandler.class);

    @NotNull
    private String sql;
    
    public static final int ACTIVE = 1;
    private int passwordValidateDays;

    /** {@inheritDoc} */
    @Override
    protected final HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {
    	
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        	
        final String username = credential.getUsername();
        final String rawPassword = credential.getPassword();

        try {
            final Map<String, Object> account = getJdbcTemplate().queryForMap(this.sql, username);
            String dbPassword= (String)account.get("encrypted_password");
            
            if((Integer)account.get("status") != ACTIVE) {
            	throw new AccountLockedException("Email: "+username+", this account has been locked.");
            }           
            
          //TODO: Get value "90" from cas.properties file
            if(isPasswordExpired((Date)account.get("password_last_updated"), this.passwordValidateDays)) {
            	throw new CredentialExpiredException("Password has expired.");
            };
            
            if (!encoder.matches(rawPassword, dbPassword)) {
                throw new FailedLoginException("Password does not match value on record.");
            }
        } catch (final IncorrectResultSizeDataAccessException e) {
            if (e.getActualSize() == 0) {
                throw new AccountNotFoundException(username + " not found with SQL query");
            } else {
                throw new FailedLoginException("Multiple records found for " + username);
            }
        } catch (final DataAccessException e) {
            throw new PreventedException("SQL exception while executing query for " + username, e);
        }
                
        return createHandlerResult(credential, new SimplePrincipal(username), null);
         
    }
    

	/**
     * @param passwordLastUpdatedDate The last updated date of account password
     * @param passwordValidateDays    After this period the password will consider as expired (Unit: days)
     */
    private boolean isPasswordExpired(Date passwordLastUpdateDate, int passwordValidateDays) {
    	Calendar passwordLastUpdated = Calendar.getInstance();
  
    	passwordLastUpdated.setTime(passwordLastUpdateDate);
    	passwordLastUpdated.add(Calendar.DATE, passwordValidateDays);
    	Date currentTimestamp = new Date();

    	if(currentTimestamp.getTime() > passwordLastUpdated.getTime().getTime()) {
    		return true;
    	}else {
    		return false;
    	}
    }
       
    
    /**
     * @param sql The sql to set.
     */
    public void setSql(final String sql) {
        this.sql = sql;
    }

	public void setPasswordValidateDays(int passwordValidateDays) {
		this.passwordValidateDays = passwordValidateDays;
	}
    
}
