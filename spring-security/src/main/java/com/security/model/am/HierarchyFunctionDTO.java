package com.security.model.am;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="am_hierarchy_function" )
public class HierarchyFunctionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6322579242183377024L;

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "hierarchy_function_id", columnDefinition="bigint", unique = true, nullable=false)
	private Long hierarchyFunctionId;
	


/* Constructor (You can create an constructor for default value setting) */
	
	public HierarchyFunctionDTO(){
		
	}

	
/* Relationship */
	
	@ManyToOne
    @JoinColumn(name="alias_id")
    private OrganizationAliasDTO organizationAliasDTO; // Foreign key to table am_organization_alias
	
	@ManyToOne
    @JoinColumn(name="function_id")
    private FunctionDTO functionDTO; // Foreign key to table am_function

	
/* Getter and Setter */
	

	public Long getHierarchyFunctionId() {
		return hierarchyFunctionId;
	}


	public void setHierarchyFunctionId(Long hierarchyFunctionId) {
		this.hierarchyFunctionId = hierarchyFunctionId;
	}


	public OrganizationAliasDTO getOrganizationAliasDTO() {
		return organizationAliasDTO;
	}


	public void setOrganizationAliasDTO(OrganizationAliasDTO organizationAliasDTO) {
		this.organizationAliasDTO = organizationAliasDTO;
	}


	public FunctionDTO getFunctionDTO() {
		return functionDTO;
	}


	public void setFunctionDTO(FunctionDTO functionDTO) {
		this.functionDTO = functionDTO;
	}
	

}
