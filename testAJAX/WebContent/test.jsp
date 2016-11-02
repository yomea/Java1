<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.setContentType("text/xml");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("pagram", "no-cache");
	response.setDateHeader("Expires", 0); 
	//out.println("{\"name\":\"Amy\",\"age\":21}");
	out.println("<?xml version='1.0' encoding='UTF-8'?> <msg>hello!</msg>");
%>