package com.security.model.am;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="am_function" )
public class FunctionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6796631145609195662L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "function_id", columnDefinition="int", unique = true, nullable=false)
	private Integer functionId;
	
	@Column(name = "function_name", columnDefinition="varchar(45)", nullable=false)
	private String functionName;
	
	
//	@Transient
//	private String ;

/* Constructor (You can create an constructor for default value setting) */
	
	public FunctionDTO(){
		
	}

	
/* Relationship */
	
	@OneToMany(mappedBy="functionDTO", cascade = CascadeType.ALL)
	private List<RoleFunctionDTO> roleFunctionList = new ArrayList<RoleFunctionDTO>();
	
	@OneToMany(mappedBy="functionDTO", cascade = CascadeType.ALL)
	private List<HierarchyFunctionDTO> hierarchyFunctionList = new ArrayList<HierarchyFunctionDTO>();
	
/* Getter and Setter */
	

	public Integer getFunctionId() {
		return functionId;
	}



	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}



	public String getFunctionName() {
		return functionName;
	}



	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}



	public List<RoleFunctionDTO> getRoleFunctionList() {
		return roleFunctionList;
	}



	public void setRoleFunctionList(List<RoleFunctionDTO> roleFunctionList) {
		this.roleFunctionList = roleFunctionList;
	}



	public List<HierarchyFunctionDTO> getHierarchyFunctionList() {
		return hierarchyFunctionList;
	}



	public void setHierarchyFunctionList(
			List<HierarchyFunctionDTO> hierarchyFunctionList) {
		this.hierarchyFunctionList = hierarchyFunctionList;
	}


}
