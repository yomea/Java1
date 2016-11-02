<%@page import="youth.hong.shopping.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="_sessioncheck.jsp" %>
<%
	String action = request.getParameter("action");
	if(action != null && action.equals("add")) {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String descr = request.getParameter("descr");
		//System.out.println(name);
		if(name == null || descr == null) {
			out.println("error");
			return;
		} else {
			name = new String(name.getBytes("ISO-8859-1"),"utf-8");
			descr = new String(descr.getBytes("ISO-8859-1"),"utf-8");
			Category.addRootCategory(name, descr);
			out.println("option success");
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form name="categoryadd" action="categoryadd.jsp" method="post">
 <input type="hidden" name="action" value="add">
 	<table>
 		<tr>
 			<td>类别</td>
 			<td><input type="text" name="name" /></td>
 		</tr>
 		<tr>
 			<td>类别描述</td>
 			<td><textarea rows="5" cols="50" name="descr"></textarea></td>
 		</tr>
 		<tr>
 			<td cols="2"><input type="submit" value="提交" /></td>
 		</tr>
 		
 	</table>
 </form>
</body>
</html>