<%@page import="youth.hong.shopping.Cart"%>
<%@page import="youth.hong.shopping.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	User u = (User)session.getAttribute("User");
	Cart c = (Cart)session.getAttribute("cart");
	if(u != null) {
		session.setAttribute("User", null);
		session.setAttribute("cart", null);
		response.sendRedirect("firstpage.jsp");
	} else {
		response.sendRedirect("firstpage.jsp");
	}
	
%>