package com.sc.crud.test;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.aspectj.apache.bcel.util.ClassPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sc.crud.bean.Emplovee;
import com.sc.crud.dao.DepartmentMapper;
import com.sc.crud.dao.EmploveeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class TestMapper {
	@Autowired
	DepartmentMapper departmentMapper;   
	@Autowired
	EmploveeMapper emploveeMapper;
	@Autowired
	SqlSession sqlSession;
	@Test
	public void testcrud() {
		EmploveeMapper mapper = sqlSession.getMapper(EmploveeMapper.class);
		for(int i=1;i<=500;i++) {
			String name = UUID.randomUUID().toString().substring(0, 9);
			mapper.insertSelective(new Emplovee(null, name+i, "M", i+"@163.com", 1));
		}
		System.out.println(ClassPath.getClassPath());
		
	}
}
