package com.security.service.am;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.dao.am.AccountDao;
import com.security.model.am.AccountDTO;

@Service("mysqlUserService")
public class CustomUserDetailsServiceImpl implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {
	
	private static final Logger log = Logger.getLogger(CustomUserDetailsServiceImpl.class);
	
	private static final int ACCOUNT_DELETE_FLAG_DELETED = 1;
	
	@Autowired  
	private AccountDao accountDao;

	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {

		String email = token.getName();
		log.info("CustomUserDetailsServiceImpl | loadUserDetails() | Loading user details whose E-mail is: "+email);
		List<AccountDTO> accountList = accountDao.findByDeleteFlagNotAndLoginUid(ACCOUNT_DELETE_FLAG_DELETED, email);
		
		if(accountList.size() == 0) {
			throw new UsernameNotFoundException("User " + email + " was not found in the database");
		}
		
		AccountDTO account = accountList.get(0);
		
		return account;
	}

}
