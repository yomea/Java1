<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="_sessioncheck.jsp" %>
<html>
<script type="text/javascript">
	state = 0 ;
	menuState = 0;
	mainState = 0;
</script>

<head>
<title>E_shopping管理平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<frameset rows="29,*" border="0" cols="*">
  <frame name="top" scrolling="NO" noresize src="top.html">
  <frameset cols="20%,*"  border="0"  rows="*" scrolling="NO" name="mleft">
    <frame src="menu.html" frameborder=NO border="0" scrolling="NO" >
    <frameset rows="20,100%,*" name="content"   cols="*">
      <frame src="title.html"  noresize scrolling="NO" name="mtitle">
      <frame src=""   name="main" marginwidth="0" marginheight="0" scrolling="YES">
      <frame src=""   name="detail">
    </frameset>
  </frameset>
</frameset>
<noframes>
</noframes>
</html>