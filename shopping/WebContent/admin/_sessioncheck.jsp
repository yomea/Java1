<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null || !admin.trim().equals("true")) {
	response.sendRedirect("login.jsp");
		
} 
%>