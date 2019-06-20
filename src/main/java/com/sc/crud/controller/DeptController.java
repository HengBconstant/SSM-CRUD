package com.sc.crud.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sc.crud.bean.Department;
import com.sc.crud.bean.Msg;
import com.sc.crud.service.DeptService;

@Controller
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("/getDept")
	@ResponseBody
	private Msg getDept() {
		List<Department> dept = deptService.getDept();
		return Msg.success().add("dept", dept);
	}
}
