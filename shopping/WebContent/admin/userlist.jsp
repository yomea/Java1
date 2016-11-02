<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="youth.hong.shopping.*"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="_sessioncheck.jsp" %>
<%
	String pageNo = request.getParameter("pageNo");
	int pageValue = 1;
	int startPage = 0;
	int pageSize = 3;
	if(pageNo == null) {
		pageValue = 1;
	} else {
		try {
			pageValue = Integer.parseInt(pageNo.trim());
			
		} catch(NumberFormatException e) {
			out.println("error!");
			return;
		}
		
		
	}
	int pageCtrlSize = 3;
	int pageCount = UserManager.getPageCount()[0];
	
	List<User> users = UserManager.getUsers(pageValue);
	pageValue = UserManager.getPageValue();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.pageCtrl {
	text-align:center;
	margin-bottom:20px;
	
}

span {
	display:inline-block;
	width:100px;
	background-color:#738456;
	text-align:center;
	border-right:2px solid black;
}
</style>
</head>

<body>
	<div class="pageCtrl">
		<span>共有<%=pageCount %>页</span>
		<span>第<%=pageValue %>页</span>
		<a href="userlist.jsp?pageNo=<%=pageValue-1%>"><span style="background-color:<%=pageValue == 1 ? "red" : "none"%>">上一页</span></a>
		<%	
			int pageCtrlValue = pageValue;
			int pageCD = 0;
			if(pageCount <= pageCtrlSize) {
				pageCD = pageCount;
				pageCtrlValue = 1;
				
			} else {
				pageCD = pageValue + pageCtrlSize - 1;
			}
			if(pageCD > pageCount) {
				pageCD = pageCount;
				pageCtrlValue = pageCount - (pageCtrlSize - 1);
			}
			for(int i = pageCtrlValue; i <= pageCD; i++) { %>
			<a href="userlist.jsp?pageNo=<%=i%>"><span style="background-color:<%=pageValue == i ? "red" : "none"%>"><%=i %></span></a>
		<%} %>
		<a href="userlist.jsp?pageNo=<%=pageValue+1%>"><span style="background-color:<%=pageValue == pageCount ? "red" : "none"%>">下一页</span></a>
		<br>
	</div>
	<table border="1" align="center">
		<tr>
		<th>用户名</th>
		<th>电话</th>
		<th>地址</th>
		<th>注册时间</th>
		<th>处理</th>
		</tr>
		<%
			for(User user : users) {
		%>
			<tr>
				<td><%=user.getUsername() %></td>
				<td><%=user.getPhone() %></td>
				<td><%=user.getAddress() %></td>
				<td><%=user.getRdate() %></td>
				<td><a href="delete.jsp?id=<%=user.getId()%>">删</a></td>
			</tr>
		<%} %>
	</table>
	
</body>
</html>