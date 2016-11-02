<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.setHeader("Cache-Control", "no-store");//HTTP1.1
	response.setHeader("pragam", "no-cache");//HTTP1.0
	response.setDateHeader("Expires", 0);
	response.getWriter().write("<msg>hello</msg>");
%>