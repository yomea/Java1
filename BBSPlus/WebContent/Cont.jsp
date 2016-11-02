<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,youth.hong.bbs.*" %>
<% 	int id = 0;
	String strId = request.getParameter("id");
	if(strId == null ) {
		out.println("拒绝访问！");
		return;
	} else {
		try {
			id = Integer.parseInt(strId.trim());
			
		} catch (NumberFormatException e) {
			out.println("内容格式错误！拒绝访问！");
			return;
		}
	}
	
	Connection conn = DB.getConn();
	Statement stmt = DB.getStmt(conn);
	String sql = "select * from article where id = " + id;
	ResultSet rs = DB.getRs(stmt, sql);
	rs.next();
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>