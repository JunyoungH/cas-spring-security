package com.security.dao.am;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.model.am.RoleFunctionDTO;


@Repository
public interface RoleFunctionDao extends JpaRepository<RoleFunctionDTO, Long> {

	List<RoleFunctionDTO> findByRoleDTO_RoleId(Long roleId)throws Exception;

	void deleteByRoleDTO_RoleId(Long roleId)throws Exception;

}
