<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%	
	if(request.getParameter("action") != null && request.getParameter("action").trim().equals("post") ) {
	
		String title = request.getParameter("title");
		String cont = request.getParameter("cont");
		if(title == null || cont == null) {
			out.println("请求被拒绝");
			return;
		} else if(title.trim()==""||cont.trim()=="") {
			out.println("发表失败");
			return;
		}
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbs", "root", "root");
		String sql = "insert into article values (null,0,?,?,?,now(),0)";
		/* Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select max(id) from article");
		rs.next();
		int i = rs.getInt(1); */
		PreparedStatement pStmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		pStmt.setInt(1, -1);
		pStmt.setString(2, title);
		pStmt.setString(3,cont);
		pStmt.executeUpdate();
		
		ResultSet rs = pStmt.getGeneratedKeys();
		rs.next();
		int key = rs.getInt(1);
		rs.close();
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate("update article set rootid=" + key + " where id=" + key);
		System.out.println("update article set rootid=" + key + " where id=" + key);
		stmt.close();
		conn.close();
		pStmt.close();
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
div{ width:600px; margin:0 auto; text-align:center;}
input{ margin:10px;}
</style>
<script type="text/javascript">


	function check() {
		if(document.oneForm.title.value == "") {
			alert("标题不允许为空！");
			return false;
		} else if( document.oneForm.cont.value == ""){
			alert("内容不允许为空！");
			return false;
		} else {
			return true;
		}
		
	}


</script>
</head>
<body>
<div>
		<form name="oneForm" method="get" action="WriterArticle.jsp" onsubmit="return check()">
			<input type="hidden" name="action" value="post" />
			<input type="text" name="title" /><br />
			<textarea name="cont"></textarea><br />
			<input type="submit" value="submit" />
		</form>
	</div>
</body>
</html>