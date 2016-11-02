<%@page import="java.util.List"%>
<%@page import="youth.hong.shopping.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="_sessioncheck.jsp" %>
<%
	List<Category> categories = Category.getCategories();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="0" align="center">
	<tr>
		<th>id</th>
		<th>pid</th>
		<th>名称</th>
		<th>描述</th>
		<th>isleaf</th>
		<th>等级</th>
		<th>添加子类</th>
		<th>处理</th>
		<th>修改</th>
		<th>添加产品</th>
	</tr>
	<% for(Category c : categories) {
			String preStr = "";
			for(int i = 1; i < c.getGrade(); i++) {
				preStr += "***";
			}
	%>
		<tr>
		<td ><%=c.getId() %></td>
		<td><%=c.getPid() %></td>
		<td><%=preStr + c.getName() %></td>
		<td><%=c.getDescr() %></td>
		<td><%=c.getCno() %></td>
		<td><%=c.getGrade() %></td>
		<td><a href="addsoncategory.jsp?id=<%=c.getId()%>&grade=<%=c.getGrade()%>">添加</a></td>
		<td><a href="deletecategory.jsp?id=<%=c.getId()%>&pid=<%=c.getPid()%>">删除</a></td>
		<td><a href="modifycategory.jsp?id=<%=c.getId()%>">修改</a></td>
		<td><a href="productadd.jsp?id=<%=c.getId()%>"><%=c.getCno() == 0 ? "添加": "" %></a></td>
	</tr>
	<%} %>
</table>
</body>
</html>