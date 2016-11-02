<%@page import="youth.hong.shopping.CategoryDAO"%>
<%@page import="youth.hong.shopping.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String strId = request.getParameter("id");
	String strGrade = request.getParameter("grade");
	int grade = 0;
	int id = 0;
	if(strId == null || strGrade == null) {
		out.println("error!");
		return;
	} else {
		try {
			id = Integer.parseInt(strId.trim());
			grade = Integer.parseInt(strGrade.trim());
		} catch(NumberFormatException e) {
			out.println("error!");
			return;
		}
		String action = request.getParameter("action");
		if(action != null && action.equals("addson")) {
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
				Category.add(id, grade, name, descr);
				CategoryDAO.update(id);
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
<form name="categoryadd" action="addsoncategory.jsp?id=<%=id %>&grade=<%=grade %>" method="post">
 <input type="hidden" name="action" value="addson">
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
 			<td colspan="2"><input type="submit" value="提交" /></td>
 		</tr>
 		
 	</table>
 </form>
</body>
</html>