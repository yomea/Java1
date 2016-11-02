<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="youth.hong.shopping.*" %>
<%
	String register = request.getParameter("action");
	if(register != null && register.trim().equals("register")) {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		if(username == null || password == null || password2 == null || phone == null || address == null) {
			out.println("错误的注册方式!");
			return;
		} else if(!password.equals(password2)) {
			out.println("两次密码的输入不一致！！！");
			return;
		}
		username = new String(username.getBytes("ISO-8859-1"),"utf-8");
		password = new String(password.getBytes("ISO-8859-1"),"utf-8");
		phone = new String(phone.getBytes("ISO-8859-1"),"utf-8");
		address = new String(address.getBytes("ISO-8859-1"),"utf-8");
		User user = new User(username, password, phone, address);
		user.save();
		
		response.sendRedirect("firstpage.jsp");
		
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<script language=JavaScript src="script/regcheckdata.js"></script>
</head>
<body>

	<form name="form" action="register.jsp" method="post" onSubmit="return checkdata()">
		<input type="hidden" name="action" value="register" />
			<table width="750" align="center" border="2">
				<tr>
					<td colspan="2" align="center">用户注册</td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td>
						<input type=text name="username" size="30" maxlength="10">
					</td>
				</tr>
				<tr>
					<td>密码：</td>
					<td>
						<input type=password name="password" size="15" maxlength="12">
					</td>
				</tr>
				<tr>
					<td>密码确认</td>
					<td>
						<input type=password name="password2" size="15" maxlength="12">
					</td>
				</tr>
				<tr>
				<tr>
					<td>电话</td>
					<td>
						<input type=text name="phone" size="15" maxlength="12">
					</td>
				</tr>
				<tr>
					<td>地址</td>
					<td>
						<textarea rows="12" cols="80" name="address"></textarea>
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td>
						<input type="submit" value="提交">
						<input type="reset" value="重置">
					</td>
				</tr>
				
			</table>
		</form>
</body>
</html>