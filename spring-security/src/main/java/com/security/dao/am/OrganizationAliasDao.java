package com.security.dao.am;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.model.am.OrganizationAliasDTO;


@Repository
public interface OrganizationAliasDao extends JpaRepository<OrganizationAliasDTO, Long>{

	List<OrganizationAliasDTO> findByHierarchyLevel(int heirarchyLevel);

	OrganizationAliasDTO findByAliasId(Long valueOf);

	List<OrganizationAliasDTO> findByHierarchyLevelBetween(int fromHierarchyLevel, int toHierarchyLevel);

}
