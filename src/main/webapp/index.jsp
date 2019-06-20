<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- 以服务器的根路径为基准 
	http：//localhost：3306（服务器）
	/crud （项目名）  + 
-->
<script src="${APP_PATH }/static/jquery/jquery.min.js"
	type="text/javascript"></script>
<link href="${APP_PATH }/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${APP_PATH }/static/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
</head>
<body>
欢迎老板：<shiro:principal></shiro:principal>
注销用户登陆：<a href="${APP_PATH }/logout">点我</a><br>
<shiro:hasRole name="admin">
	<a href="adminRole.jsp">进入admin页面</a><br>
</shiro:hasRole>
<shiro:hasRole name="user">
	<a href="userRole.jsp">进入user页面</a>
</shiro:hasRole>
<!-- 添加员工的模态框 -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">员工添加</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">EmpName</label>
		    <div class="col-sm-10">
		      <input type="email" class="form-control" id="empName" placeholder="EmpName" name="empName">
		      <span class="help-block"></span>
		    </div>
		    
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">email</label>
		    <div class="col-sm-10">
		      <input type="email" class="form-control" id="email" placeholder="email" name="email">
		      <span class="help-block"></span>
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">gender</label>
		    <div class="col-sm-10">
				<div class="radio">
				  <label>
				    <input type="radio" name="gender" id="optionsRadios1" value="M" checked>男
				  </label>
				  <label>
				    <input type="radio" name="gender" id="optionsRadios2" value="F">女	
				  </label>
				</div>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">dept</label>
		    <div class="col-sm-4">
		    	<select class="form-control" name="dId">
				</select>
		    </div>
		  </div>
		
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
      </div>
    </div>
  </div>
</div>
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id ="empAddBtn">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table " id="emp_table">
					<thead>
						<tr>
						<th>#</th>
						<th>empName</th>
						<th>gender</th>
						<th>email</th>
						<th>dept</th>
						<th>操纵</th>
					</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<!-- 分页条 -->
		<div class="row">

			<!-- 分页__文字 -->
			<div class="col-md-6" id="page_info">
				
			</div>
			<!-- 分页_条 -->
			<div class="col-md-6" id="page_nav">
				
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function(){
			to_page(1);
		})
		
		function to_page(pn){
			$.ajax({
				url:"${APP_PATH}/emps",
				type:"get",
				data:"pn="+pn,
				success:function(result){
					data_of_table(result);
					data_of_pageInfo(result);
					data_of_pageNav(result);
				}
			})
		}
		
		function data_of_table(result){
			$("#emp_table tbody").empty();
			var emps =  result.extend.pageInfo.list;
			$.each(emps,function(index,item){
				var empId = $("<td></td>").append(item.empId);
				var empName = $("<td></td>").append(item.empName);
				var gender = $("<td></td>").append(item.gender=="M"?"男":"女");
				var email = $("<td></td>").append(item.email);
				var department = $("<td></td>").append(item.department.deptName);
					var updateMenu = $("<button></button>").addClass("btn btn-primary btn-sm")
										.append($("<span></span>").addClass("glyphicon glyphicon-pencil ")).append("编辑");
					var delMenu =  $("<button></button>").addClass("btn btn-danger btn-sm")
					.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
				var menu = $("<td></td>").append(updateMenu).append(" ").append(delMenu);
				$("<tr></tr>")
					.append(empId)
					.append(empName)
					.append(gender)
					.append(email)
					.append(department)
					.append(menu)
					.appendTo("#emp_table tbody");
			})
		}
		/* 分页信息 */
		function data_of_pageInfo(result){
			$("#page_info").empty();
			$("#page_info").append("当前第"+result.extend.pageInfo.pageNum+"页, 总共"+result.extend.pageInfo.pages+"页，共"+result.extend.pageInfo.total+"条记录");
		}
		/* 解释分页条 */
		function data_of_pageNav(result){
			$("#page_nav").empty();
			var ul = $("<ul></ul>").addClass("pagination");
			var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
			var upLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href","#"));
			if(result.extend.pageInfo.hasPreviousPage == false){
				firstPageLi.addClass("disabled");
				upLi.addClass("disabled");
			}else{
				firstPageLi.click(function(){
					to_page(1);
				});
				upLi.click(function(){
					to_page(result.extend.pageInfo.pageNum-1);
				});
			}
			var nextLi = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href","#"));
			var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
			
			if(result.extend.pageInfo.hasNextPage == false){
				lastPageLi.addClass("disabled");
				nextLi.addClass("disabled");
			}else{
				nextLi.click(function(){
					to_page(result.extend.pageInfo.pageNum+1);
				});
				lastPageLi.click(function(){
					to_page(999999999);
				});
			}
			ul.append(firstPageLi).append(upLi);
			$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
				var pageLi = $("<li></li>").append($("<a></a>").attr("href","#").append(item));
				if(result.extend.pageInfo.pageNum == item){
					pageLi.addClass("active");
				}
				pageLi.click(function(){
					to_page(item);
				})
				ul.append(pageLi);
			})
			ul.append(nextLi).append(lastPageLi);
			var nav = $("<nav></nav>").append(ul);
			$("#page_nav").append(nav);	
		}
		
		$("#empAddBtn").click(function() {
			$("#empAddModal form")[0].reset();
			$("#empAddModal").find("*").removeClass("has-error has-success");
			$("#empAddModal").find("error-block").text("");
			//先提取出部门下拉框的
			getDept();
			$("#empAddModal").modal();
		})
		
		function getDept() {
			$.ajax({
				url:"getDept",
				type:"GET",
				success:function(data){
//{"code":200,"msg":"处理成功","extend":{"dept":[{"deptId":1,"deptName":"研发部"},{"deptId":2,"deptName":"测试部"},{"deptId":3,"deptName":"市场部门"}]}}
					$.each(data.extend.dept,function(){
						$("#empAddModal select").empty();
						var deptData = $("<option></option>").attr("value",this.deptId).append(this.deptName);
						$("#empAddModal select").append(deptData);
					});
				}
			});
		}
		
		/* 表单格式信息验证 */
		function validata_form() {
			var empName = $("#empName").val();
			var empName_reg = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]+$)/;
			if(!empName_reg.test(empName)){
				validata_form_error("#empName","no","用户名格式错误！重写。");
				return false;
			}else {
				validata_form_error("#empName","yes","");
			}
			var email = $("#email").val();
			var email_reg = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
			if(!email_reg.test(email)){
				validata_form_error("#email","no","邮箱格式错误！重写。");
				return false;
			}else{
				validata_form_error("#email","yes","");
			}
			return true;
			
		}
		
		/* 验证表单信息时候，提示错误信息 */
		function validata_form_error(dom,status,error_massage){
			$(dom).parent().removeClass("has-error has-success");
			$(dom).next("span").text("");
			if(status == "no"){
				$(dom).parent().addClass("has-error");
				$(dom).next("span").text(error_massage);	
			}else if(status == "yes"){
				$(dom).parent().addClass("has-success");
				$(dom).next("span").text(error_massage);	
			}
		}
		/* 判断姓名重复 */
		$("#empName").blur(function () {
			var empName = this.value;
			$.ajax({
				url:"${APP_PATH}/regEmpName",
				type:"POST",
				data : "empName="+empName,
				success:function(data){
					if(data.code==200){
						validata_form_error("#empName","yes","用户名可以用");
						$("#emp_save_btn").attr("ajax-va","yes");
					}else if(data.code==400){
						validata_form_error("#empName","no",data.extend.errorMsg);
						$("#emp_save_btn").attr("ajax-va","no");
					}
				}
			});
		})
		
		$("#emp_save_btn").click(function(){
			if(!validata_form()){
				return false;
			};
			
			if($(this).attr("ajax-va")=="no"){
				return false;
			}
			
			$.ajax({
				url:"${APP_PATH}/saveEmp",
				type:"post",
				data:$("#empAddModal form").serialize(),
				success:function(data){
					$('#empAddModal').modal('hide');
					to_page(99999999);
				}
			});
		});
	</script>
</body>
</html>