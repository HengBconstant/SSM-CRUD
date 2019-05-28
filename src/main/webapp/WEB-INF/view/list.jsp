<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
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
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>
					SSM-CRUD
					<h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table ">
					<tr>
						<th>#</th>
						<th>empName</th>
						<th>gender</th>
						<th>email</th>
						<th>dept</th>
						<th>操纵</th>
					</tr>
					<c:forEach items="${pageInfo.list }" var="emp">
						<tr>
							<th>${emp.empId }</th>
							<th>${emp.empName }</th>
							<th>${emp.gender=="M"?"男":"女" }</th>
							<th>${emp.email }</th>
							<th>${emp.department.deptName }</th>
							<th>操纵</th>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<!-- 分页条 -->
		<div class="row">

			<!-- 分页文字信息 -->
			<div class="col-md-6">
				当前第几页：${pageInfo.pageNum }，总共${pageInfo.pages }页，${pageInfo.total }条记录
			</div>
			<!-- 分页条信息 -->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<c:if test="${!pageInfo.isFirstPage }">
							<li><a href="${APP_PATH }/emps?pn=1" aria-label="Previous"> <span
								aria-hidden="true">首页</span>
							</a></li>
						</c:if>
						<c:if test="${!pageInfo.isFirstPage }">
							 <li>
						      <a href="${APP_PATH }/emps?pn=${pageInfo.pageNum-1 }" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						</c:if>
						
						<c:forEach items="${pageInfo.navigatepageNums }" var="page_Info">
							<c:if test="${page_Info == pageInfo.pageNum }">
								<li class="active"><a href="#">${page_Info }</a></li>
							</c:if>
							<c:if test="${page_Info != pageInfo.pageNum }">
								<li><a href="${APP_PATH }/emps?pn=${page_Info }">${page_Info }</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${!pageInfo.isLastPage }">
							<li>
						      <a href="${APP_PATH }/emps?pn=${pageInfo.pageNum+1 }" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
						</c:if>
						<c:if test="${!pageInfo.isLastPage }">
							<li><a href="${APP_PATH }/emps?pn=${pageInfo.pages}" aria-label="Next"> <span
									aria-hidden="true">末页</span>
							</a></li>
						</c:if>	
					</ul>
				</nav>
			</div>
		</div>

	</div>
</body>
</html>