package com.sc.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.crud.bean.Emplovee;
import com.sc.crud.bean.EmploveeExample;
import com.sc.crud.bean.EmploveeExample.Criteria;
import com.sc.crud.dao.EmploveeMapper;

@Service
public class EmployeeService {
	
	@Autowired
	EmploveeMapper emploveeMapper;
	
	public List<Emplovee> getAll() {
		// TODO Auto-generated method stub
		return emploveeMapper.selectByExampleWithDept(null);
		
	}

	public void saveEmp(Emplovee emp) {
		// TODO Auto-generated method stub
		emploveeMapper.insertSelective(emp);
	}

	public boolean regEmpName(String empName) {
		EmploveeExample emploveeExample = new EmploveeExample();
		Criteria createCriteria = emploveeExample.createCriteria();
		createCriteria.andEmpNameEqualTo(empName);
		long l = emploveeMapper.countByExample(emploveeExample);
		return l==0;
	}

}
