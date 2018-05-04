package com.security.dao.am;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.model.am.FunctionDTO;


@Repository
public interface FunctionDao extends JpaRepository<FunctionDTO, Integer>{

	FunctionDTO findOne(Integer functionId);

	FunctionDTO findByFunctionName(String functionName);
	
}
