<%@page import="youth.hong.user.User"%>
<%@page import="youth.hong.dao.PasswordNotCorrectException"%>
<%@page import="youth.hong.dao.UsernameNotFoundException"%>
<%@page import="youth.hong.userMgr.UserMgr"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	if(username == null || username.equals("")) {
		out.println("请输入用户名");
		response.setHeader("refresh", "1;url=login.jsp");
		return;
	} else if(password == null || password.equals("")) {
		out.println("请输入密码");
		response.setHeader("refresh", "1;url=login.jsp");
		return;
	} else {
%>
		<jsp:useBean id="user" class="youth.hong.user.User" scope="page"></jsp:useBean>
		<jsp:setProperty property="*" name="user"/>
	<%	
		try {
			/* User u = new User();
			u.setUsername(username);
			u.setPassword(password); */
			UserMgr.getUm().checkUser(user);
		} catch(UsernameNotFoundException e) {
			out.println("用户名不存在!");
			response.setHeader("refresh", "1;url=login.jsp");
			return;
		} catch(PasswordNotCorrectException e) {
			out.println("密码不正确!!");
			response.setHeader("refresh", "1;url=login.jsp");
			return;
		}
	}
%>