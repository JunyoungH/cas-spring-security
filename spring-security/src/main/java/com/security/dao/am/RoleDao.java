package com.security.dao.am;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.model.am.RoleDTO;


@Repository
public interface RoleDao extends JpaRepository<RoleDTO, Long>{

	RoleDTO findByRoleId(Long roleId)throws Exception;

}
