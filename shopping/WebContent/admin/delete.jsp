<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="youth.hong.shopping.*" %>
<%@ include file="_sessioncheck.jsp" %>
<%
	String strId = request.getParameter("id");
	int id = 0;
	if(strId == null) {
		out.println("操作失败！");
		return;
	} else {
		try {
			id = Integer.parseInt(strId.trim());
		} catch(NumberFormatException e) {
			out.println("操作失败!");
			return;
		}
		UserManager.delete(id);
		response.setHeader("refresh", "1;url=userlist.jsp");
	}
%>
<center>删除成功！</center>