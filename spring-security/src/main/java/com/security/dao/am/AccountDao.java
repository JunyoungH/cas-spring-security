package com.security.dao.am;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.model.am.AccountDTO;


@Repository
public interface AccountDao extends JpaRepository<AccountDTO, Long>{

	AccountDTO findByAccountId(Long accountId);
	
	List<AccountDTO> findByLoginUid(String loginUid);
	
	List<AccountDTO> findByDeleteFlagNotAndLoginUid(int deleteFlag, String loginUid);

	List<AccountDTO> findByDeleteFlagNotAndLoginUidAndAccountIdNotIn(int deleteFlag, String loginUid, List<Long> accounts);

	List<AccountDTO> findByLoginUidAndStatus(String loginUid, int accountStatus);

	List<AccountDTO> findByRoleDTO_RoleId(Long roleId);
	
}
