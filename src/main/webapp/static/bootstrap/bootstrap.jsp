<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath()+"/";

out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
out.println("<link href='"+path+"bootstrap/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>");
out.println("<link href='"+path+"bootstrap/css/bootstrap-theme.min.css' rel='stylesheet' type='text/css'/>");
out.println("<script type='text/javascript' src='"+path+"bootstrap/js/jquery.min.js'></script>");
out.println("<script type='text/javascript' src='"+path+"bootstrap/js/bootstrap.min.js'></script>");
%>