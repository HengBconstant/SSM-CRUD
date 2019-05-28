package com.sc.crud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sc.crud.bean.Emplovee;
import com.sc.crud.bean.Msg;
import com.sc.crud.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		//在查询之前调用
		PageHelper.startPage(pn, 10);
		List<Emplovee> list = employeeService.getAll();
		//连续显示的页数
		PageInfo pageInfo = new PageInfo<>(list,5);
		return Msg.success().add("pageInfo", pageInfo);
	}
	
	//@RequestMapping("/emps")
	public String getEmp(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model) {
		//在查询之前调用
		PageHelper.startPage(pn, 10);
		List<Emplovee> list = employeeService.getAll();
		//连续显示的页数
		PageInfo pageInfo = new PageInfo<>(list,5);
		model.addAttribute("pageInfo", pageInfo);
		return "list";
	}
	@RequestMapping(value="/saveEmp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(Emplovee emp) {
		employeeService.saveEmp(emp);
		return Msg.success();
	}
	/**
	 * ajax验证 格式
	 * @param empName
	 * @return
	 */
	@RequestMapping(value="/regEmpName",method=RequestMethod.POST)
	@ResponseBody
	public Msg regEmpName(@RequestParam(name="empName")String empName) {
		
		String reg = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]+$)";
		
		boolean c = empName.matches(reg);
		if (!c) {
			return Msg.fail().add("errorMsg", "名字不能用，换一个吧 草！");
		}
		
		boolean b = employeeService.regEmpName(empName);
		if(b) {
			return Msg.success();
		}
		return Msg.fail();
	}
	
}
