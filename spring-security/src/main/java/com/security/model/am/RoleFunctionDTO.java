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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="am_role_function" )
public class RoleFunctionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4782518059880768999L;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "role_function_id", columnDefinition="bigint", unique = true, nullable=false)
	private Long roleFunctionId;
	


/* Constructor (You can create an constructor for default value setting) */
	
	public RoleFunctionDTO(){
		
	}

	
/* Relationship */
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="role_id")
    private RoleDTO roleDTO; // Foreign key to table am_role
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="function_id")
    private FunctionDTO functionDTO; // Foreign key to table am_function

	
/* Getter and Setter */
	

	public Long getRoleFunctionId() {
		return roleFunctionId;
	}


	public void setRoleFunctionId(Long roleFunctionId) {
		this.roleFunctionId = roleFunctionId;
	}


	public RoleDTO getRoleDTO() {
		return roleDTO;
	}


	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}


	public FunctionDTO getFunctionDTO() {
		return functionDTO;
	}


	public void setFunctionDTO(FunctionDTO functionDTO) {
		this.functionDTO = functionDTO;
	}
	

}
