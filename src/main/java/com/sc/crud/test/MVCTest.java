package com.sc.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.sc.crud.bean.Emplovee;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MVCTest {
	@Autowired
	WebApplicationContext context;
	
	//虚拟mvc请求
	MockMvc mockMvc;
	@Before
	public void initMokmvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testPage() throws Exception {
		//模拟请求拿到返回值
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
		//请求成功后请求域中会有pageinfo 我们可以取出pageinfo进行验证
		MockHttpServletRequest request = result.getRequest();
		PageInfo pi = (PageInfo)request.getAttribute("pageInfo");
		System.out.println("当前页码"+pi.getPageNum());
		System.out.println("总页数"+pi.getPages());
		System.out.println("总条数"+pi.getTotal());
		System.out.println("在页面需要连续现实的野马数量");
		int[] nums = pi.getNavigatepageNums();
		for(int i:nums) {
			System.out.println(" "+i);
		}
		//获取员工信息
		List<Emplovee> list = pi.getList();
		for(Emplovee e : list) {
			System.out.println(e.getEmpId()+""+e.getEmpName());
		}
	}
}
