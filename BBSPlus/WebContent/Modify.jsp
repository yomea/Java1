<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,youth.hong.bbs.*" %>
<%	String action = request.getParameter("action");
	int id = 0;
	int rootId = 0;
	int reId = 0;
	int reRootId = 0;
	ResultSet rs = null;
	Article a = null;
	if(action != null && action.trim().equals("true")) {
		String title = request.getParameter("title");
		String cont = request.getParameter("cont");
		String strId = request.getParameter("id");
		String strRootId = request.getParameter("rootid");
		if(strId == null || strRootId == null) {
			out.println("错误的访问方式！");
			return;
		} else {
			try {
				id = Integer.parseInt(strId.trim());
				rootId = Integer.parseInt(strRootId.trim());
			} catch(NumberFormatException e) {
				out.println("错误修改方式！");
				return;
			}
			if(cont == null || title == null) {
				out.println("修改失败!");
				return;
			} else {
				Connection conn = DB.getConn();
				String sql = "update article set cont='" + cont + "',titlt='" + title + " where id=" + id;
				//String pSql = "update article set cont=?,title=? where id=?";
				System.out.println(sql);
				Statement stmt = DB.getStmt(conn);
				DB.executeUpdate(stmt, sql);
				
				
				conn.close();
				
				response.sendRedirect("article.jsp");
				return;
			}
		}
} else {
	String strReId = request.getParameter("id");
	String strReRootId = request.getParameter("rootId");
	if(strReId == null || strReRootId == null) {
		out.println("错误!");
		return;
	} else {
		try {
			reId = Integer.parseInt(strReId.trim());
			reRootId = Integer.parseInt(strReRootId.trim());
			Connection conn = DB.getConn();
			Statement stmt = DB.getStmt(conn);
			rs = DB.getRs(stmt, "select * from article where id=" + reId);
			if(!rs.next()) return;
			a = new Article();
			a.initArticle(rs);
		} catch(NumberFormatException e) {
			out.println("错误修改方式！");
			return;
		}
	}
}

%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Java|Java世界_中文论坛|ChinaJavaWorld技术论坛 : 初学java遇一难题！！望大家能帮忙一下 ...</title>
<meta http-equiv="content-type" content="text/html; charset=GBK">
<link rel="stylesheet" type="text/css" href="images/style.css" title="Integrated Styles">
<script language="JavaScript" type="text/javascript" src="images/global.js"></script>
<link rel="alternate" type="application/rss+xml" title="RSS" href="http://bbs.chinajavaworld.com/rss/rssmessages.jspa?threadID=744236">
<style type="text/css">
div{ width:600px; margin:0 auto; text-align:center;}
input{ margin:10px;}
</style>
<link href="fckeditor/sample.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
	<script type="text/javascript">

window.onload = function()
{
	// Automatically calculates the editor base path based on the _samples directory.
	// This is usefull only for these samples. A real application should use something like this:
	// oFCKeditor.BasePath = '/fckeditor/' ;	// '/fckeditor/' is the default value.
	//var sBasePath = document.location.pathname.substring(0,document.location.pathname.lastIndexOf('_samples')) ;
	var sBasePath = "<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/fckeditor/" %>";
	//alert(sBasePath);
	var oFCKeditor = new FCKeditor( 'cont' ) ;
	oFCKeditor.BasePath	= sBasePath ;
	oFCKeditor.ReplaceTextarea() ;
}

	</script>
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
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td width="40%"><img src="images/header-stretch.gif" alt="" border="0" height="57" width="100%">
     	</td>
      <td width="1%"><img src="images/header-right.gif" alt="" height="57" border="0"></td>
    </tr>
  </tbody>
</table>
<br>
<div id="jive-flatpage">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr valign="top">
        <td width="99%"><p class="jive-breadcrumbs"> <a href="http://bbs.chinajavaworld.com/index.jspa">首页</a> &#187; <a href="http://bbs.chinajavaworld.com/forumindex.jspa?categoryID=1">ChinaJavaWorld技术论坛|Java世界_中文论坛</a> &#187; <a href="http://bbs.chinajavaworld.com/category.jspa?categoryID=2">Java 2 Platform, Standard Edition (J2SE)</a> &#187; <a href="http://bbs.chinajavaworld.com/forum.jspa?forumID=20&amp;start=0">Java语言*初级版</a> </p>
        </td>
        </tr>
        </tbody>
        </table>
        <div>
		<form name="oneForm" method="get" action="Modify.jsp" onsubmit="return check()">
			<input type="hidden" name="action" value="true" />
			<input type="hidden" name="id" value="<%=reId %>" />
			<input type="hidden" name="rootid" value="<%=reRootId %>" />
			<input type="text" name="title" value="<%=a.getTitle() %>" />
			<textarea name="cont" rows="10" cols="80" ><%=a.getCont() %></textarea><br />
			<input type="submit" value="submit" />
		</form>
	</div>
</body>
</html>