<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="accept.jsp" method="get">
<input type="submit" value="submit">
<input type="text" name="hello">
<input type="checkbox" name="ha" value="苹果">
<input type="checkbox" name="ha" value="橙子">
<input type="checkbox" name="ha" value="香蕉">
<input type="checkbox" name="ha" value="雪梨">
<input type="checkbox" name="ha" value="葡萄">
</form>
<%	
	request.setAttribute("test", "test");
	session.setAttribute("session", "session");
	session.invalidate();
	//pageContext.forward("accept.jsp");
	pageContext.getOut().println("hello");
%>
</body>
</html>