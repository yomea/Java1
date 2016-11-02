<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,youth.hong.bbs.*" %>
<%	String action = request.getParameter("action");
	String title = request.getParameter("title");
	String cont = request.getParameter("cont");
	if(action != null && action.trim().equals("true")) {
		if(title == null || cont == null) {
			out.println("非正常提交");
			return;
		} else {
			Connection conn = DB.getConn();
			String sql = "insert into article values (null,?,?,?,?,now(),0)";
			PreparedStatement pStmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			conn.setAutoCommit(false);
			pStmt.setInt(2,-1);
			pStmt.setInt(1,0);
			pStmt.setString(3,title);
			pStmt.setString(4, cont);
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				int key = rs.getInt(1);
				Statement stmt = DB.getStmt(conn);
				stmt.executeUpdate("update article set rootid=" + key + " where id=" + key);
			}
			conn.commit();
			conn.setAutoCommit(true);
			pStmt.close();
			conn.close();
		}
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
<!--

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

-->
</script>
</head>
<body>
<div>
		<form name="oneForm" method="get" action="post.jsp" onsubmit="return check()">
			<input type="hidden" name="action" value="true" />
			<input type="text" name="title" /><br />
			<textarea name="cont"></textarea><br />
			<input type="submit" value="submit" />
		</form>
	</div>
</body>
</html>