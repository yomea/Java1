<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"  %>
<%request.setCharacterEncoding("utf-8"); %>
<%	int id = 0;
	String strId = request.getParameter("id");
	if(strId == null ) {
	    out.println("操作被拒绝！请通过正确路径进入");
		return;
	} else if(strId.trim()==""){
		out.println("操作被拒绝！请通过正确路径进入");
		return;
	} else {
		id = Integer.parseInt(request.getParameter("id")); 
	   
	}
	String title = request.getParameter("title");
	String cont = request.getParameter("cont");
	if(title == null || cont == null) {
		out.println("请求被拒绝");
		return;
	} else if(title.trim()==""||cont.trim()=="") {
		out.println("发表失败");
		return;
	}
	cont = cont.replaceAll("[\n]", "<br />");
	int rootid = Integer.parseInt(request.getParameter("rootid"));
%>
<%  Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbs","root","root");
  	PreparedStatement pstmt = conn.prepareStatement("insert into article values (null,?,?,?,?,now(),0)");
  	conn.setAutoCommit(false);
	pstmt.setInt(1, id);
	pstmt.setInt(2, rootid);
	pstmt.setString(3,title);
	pstmt.setString(4, cont);
	pstmt.executeUpdate();
	Statement stmt = conn.createStatement();
	stmt.executeUpdate("update article set isleaf = 1 where id = " + id);
	System.out.println(cont);
	response.sendRedirect("ShowArticleTree.jsp");
	conn.commit();
	conn.setAutoCommit(true);
	conn.close();
	pstmt.close(); 
	stmt.close();
	
%>
