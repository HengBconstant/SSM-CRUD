package com.sc.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.crud.bean.Department;
import com.sc.crud.dao.DepartmentMapper;

@Service
public class DeptService {
	@Autowired
	private DepartmentMapper departmentMapper;
	
	public List<Department> getDept(){
		return departmentMapper.selectByExample(null);
	}
 	
}
