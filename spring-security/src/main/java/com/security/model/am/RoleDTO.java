package com.security.model.am;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="am_role" )
public class RoleDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6909331672114166947L;

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "role_id", columnDefinition="bigint", unique = true, nullable=false)
	private Long roleId;
	
	@Column(name = "name", columnDefinition="varchar(50)", nullable=false)
	private String name;
	
//	@Transient
//	private String ;

/* Constructor (You can create an constructor for default value setting) */
	
	public RoleDTO(){
		
	}

	
/* Relationship */
	@JsonIgnore
	@OneToMany(mappedBy="roleDTO", cascade=CascadeType.PERSIST)
    private List<AccountDTO> accountList = new ArrayList<AccountDTO>();
	
	
	@OneToMany(mappedBy="roleDTO", cascade = CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<RoleFunctionDTO> roleFunctionList = new ArrayList<RoleFunctionDTO>();
	
/* Getter and Setter */
	

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AccountDTO> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<AccountDTO> accountList) {
		this.accountList = accountList;
	}


	public List<RoleFunctionDTO> getRoleFunctionList() {
		return roleFunctionList;
	}

	public void setRoleFunctionList(List<RoleFunctionDTO> roleFunctionList) {
		this.roleFunctionList = roleFunctionList;
	}

}
