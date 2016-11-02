<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=
	(String)request.getAttribute("test")

%>
<%=
	request.getParameter("hello")

%>
<%
	
	String[] ha = request.getParameterValues("ha");
	if(ha != null) {
		
		for(int i = 0; i < ha.length; i++) {
			out.print(ha[i] + "<br>");
		}
	}

%>

字符集：<%=request.getCharacterEncoding() %><br>
accept.jsp的物理地址：<%=request.getRealPath("accept.jsp") %><br>
请求的长度：<%=request.getContentLength() %><br>
上下虚拟路径：<%=request.getContextPath() %><br>
获得客户端地址：<%=request.getRemoteAddr() %><br>
获得客户端端口：<%=request.getRemotePort() %><br>
获得服务主机的名字：<%=request.getServerName() %><br>
获取session里的值：<%=session.getAttribute("session") %>
获取session的id：<%=session.getId() %>

<%
	out.println("<h1>hello，大家好，我是jsp内置的out对象，你要是不用flush把我强制输出，即使我的代码在response的打印流前面我也不会输出在它的前面</h1>");
	//out.flush();
	response.getWriter().println("<h1>大家好我是response生成的打印流</h1>");
	//注意哦，如果你不把out.flush()注释掉我会出异常的哟！
	//response.sendRedirect("testRequest.jsp");
	//request.getRequestDispatcher("testRequest.jsp").forward(request, response);
%>
</body>
</html>