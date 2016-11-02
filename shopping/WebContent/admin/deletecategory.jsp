<%@page import="youth.hong.shopping.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String strId = request.getParameter("id");
	String strPid = request.getParameter("pid");
	int id = 0;
	int pid = 0;
	if(strId == null || strPid == null) {
		out.println("错误的操作！");
		return;
	} else {
		try {
			id = Integer.parseInt(strId.trim());
			pid = Integer.parseInt(strPid.trim());
		} catch(NumberFormatException e) {
			out.println("错误！");
			return;
		}
		
		Category.delete(id);
		Category.setLeaf(pid);
		response.sendRedirect("categorylist.jsp");
	}
%>
