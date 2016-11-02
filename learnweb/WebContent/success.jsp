<%@page import="learnweb.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String username = request.getParameter("username");
	String password = request.getParameter("passwordtest");
	if(username != null && password != null) {
		if("admin".equals(username) && "admin".equals(password)) {
			session.setAttribute("username", "admin");
			
		}else {
			response.sendRedirect("unsuccess.jsp");
		}
	} else {
		response.sendRedirect("unsuccess.jsp");
	}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	欢迎您！<%=session.getAttribute("username") %>
	<%-- <jsp:useBean id="User" class="learnweb.User" scope="session"></jsp:useBean> --%>
	
	<%-- <jsp:setProperty name="User" property="*" />
	<jsp:setProperty property="username" name="User"/>
	<jsp:setProperty property="username" name="User" value="root"/>
	<jsp:setProperty property="password" name="User" param="passwordtest"/> --%>
	
	<%-- <jsp:getProperty property="username" name="User"/><br />
	<jsp:getProperty property="password" name="User"/><br /> --%>
	<%=((User)session.getAttribute("User")).getUsername() %>
	<%=((User)session.getAttribute("User")).getPassword() %>
</body>
</html>