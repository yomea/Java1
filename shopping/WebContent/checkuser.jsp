<%@page import="youth.hong.shopping.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("utf-8");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	

	
	if(username == null || password == null) {
		response.sendRedirect("firstpage.jsp");
	} else {
		User user = null;
		try {
			username = new String(username.getBytes("ISO-8859-1"),"utf-8");
			user = User.checkUser(username, password);
			
		} catch(UserNameNotFoundException e) {
			out.println("用户不存在！");
			response.setHeader("refresh", "1;url=firstpage.jsp");
			return;
		} catch(PasswordNotCurrectException e) {
			out.println("密码错误！");
			response.setHeader("refresh", "1;url=firstpage.jsp");
			return;
		}
		session.setAttribute("User", user);
		out.println("登录成功！");
		String flag = request.getParameter("flag");
		
		if(flag == null || !flag.trim().equals("true")){
		response.setHeader("refresh", "1;url=firstpage.jsp");
			
		} else {
			
			response.sendRedirect("confirm.jsp");
		}
		
	}
	
%>