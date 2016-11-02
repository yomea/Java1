<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="youth.hong.shopping.*" %>
<%
	String action = request.getParameter("action");
	
	String username = null;
	String phone = null;
	String address = null;
	User user = (User)session.getAttribute("User");
	if(user == null) {
		response.sendRedirect("firstpage.jsp");
		return;
	} 
		username = user.getUsername();
		phone = user.getPhone();
		address = user.getAddress();
		if(action != null && "modify".equals(action)) {
		String Musername = request.getParameter("username");
		String Mphone = request.getParameter("phone");
		String Maddress = request.getParameter("address");
		if(Musername == null || Mphone == null || Maddress == null) {
			out.println("修改失败！");
			response.setHeader("refresh", "1;url=modify.jsp");
			return;
		} else if(Musername.trim().equals("") || Mphone.trim().equals("") || Maddress.trim().equals("")) {
			out.println("修改失败！");
			response.setHeader("refresh", "1;url=modify.jsp");
			return;
		} else {
			Musername = new String(Musername.getBytes("ISO-8859-1"),"utf-8");
			Maddress = new String(Maddress.getBytes("ISO-8859-1"),"utf-8");
			//System.out.println(Musername);
			User.userModify(user.getId(), Musername, Mphone, Maddress);
			out.println("修改成功！");
			response.setHeader("refresh", "1;url=firstpage.jsp");
			return;
			}
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

	<form name="form" action="modify.jsp" method="post" onSubmit="return checkdata()">
		<input type="hidden" name="action" value="modify" />
			<table width="750" align="center" border="2">
				<tr>
					<td colspan="2" align="center">用户修改</td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td>
						<input type=text name="username" size="30" maxlength="10" value="<%=username%>">
					</td>
				</tr>
				
				
				<tr>
				<tr>
					<td>电话</td>
					<td>
						<input type=text name="phone" size="15" maxlength="12" value="<%=phone %>">
					</td>
				</tr>
				<tr>
					<td>地址</td>
					<td>
						<textarea rows="12" cols="80" name="address"><%=address %></textarea>
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