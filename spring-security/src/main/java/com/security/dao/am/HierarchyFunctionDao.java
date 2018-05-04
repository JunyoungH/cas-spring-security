package com.security.dao.am;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.model.am.HierarchyFunctionDTO;


@Repository
public interface HierarchyFunctionDao extends JpaRepository<HierarchyFunctionDTO, Long>{

	List<HierarchyFunctionDTO> findByOrganizationAliasDTO_HierarchyLevel(Integer hierarchyLevel);

}
