<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
 input{margin:10px 0;}
 .hello{width:100%;height:100%;top:0;left:0;background-color:green;position:fixed;}
 .hi{text-align:center;margin:0 auto;}
</style>
<script type="text/javascript">
 window.onload=function() {
	 var input = document.getElementsByTagName("input");
	 var error = document.getElementById("error");
	 for(var i=0; i<input.length; i++) {
		 input[i].onfocus=function() {
			 error.innerHTML = "";
		 }
	 }
	 
 }
</script>
</head>
<body bgcolor="green">
	<table class="hello">
		<tr>
			<td>
				<form name="login" action="login.jsp" method="get">
					<input type="hidden" name="action" value="post" />
					<table class="hi">
					<caption>管理员登陆界面</caption>
					<tbody>
					<tr><td>用户名：</td><td><input type="text" name="username" value="admin" size="9" /></td> </tr>
					<tr><td>密码：</td><td><input type="password" name="password" value="admin" size="10" /></td> </tr>
					<%	if(request.getParameter("action") != null) {
						
						String username = request.getParameter("username");
						String password = request.getParameter("password");
						if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
						session.setAttribute("admin", "true");
						response.sendRedirect("ShowArticleTree.jsp");
						
						} else { %>
							<tr><td colspan="2" id="error">用户名或密码错误！</td></tr>
					<% 
					 	}
						
					%>
					<% }%>
					<tr><td colspan="2"><input type="submit" value="登陆" /></td> </tr>
					</tbody>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>