<%@page import="youth.hong.shopping.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String strId = request.getParameter("id");
	int id = 0;
	Category c = null;
	if(strId == null) {
		out.println("修改失败！");
		return;
	} else {
		try {
			id = Integer.parseInt(strId.trim());
		} catch(NumberFormatException e) {
			out.println("修改失败！");
			return;
		}
		c = Category.getCategory(id);
		String action = request.getParameter("action");
		if(action != null && action.trim().equals("modify")) {
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
				Category.moify(id, name, descr);
				out.println("option success");
			}
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
	<form name="categoryadd" action="modifycategory.jsp?id=<%= id %>" method="post">
 <input type="hidden" name="action" value="modify">
 	<table>
 		<tr>
 			<td>类别</td>
 			<td><input type="text" name="name" value="<%=c.getName() %>" /></td>
 		</tr>
 		<tr>
 			<td>类别描述</td>
 			<td><textarea rows="5" cols="50" name="descr"><%=c.getDescr() %></textarea></td>
 		</tr>
 		<tr>
 			<td colspan="2"><input type="submit" value="提交" /></td>
 		</tr>
 		
 	</table>
 </form>
</body>
</html>