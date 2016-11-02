<%@page import="youth.hong.shopping.SalesOrder"%>
<%@page import="youth.hong.shopping.OrderMgr"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String strId = request.getParameter("id");
int id;
if(strId == null) {
	out.println("error");
	return;
} else {
	try {
		id = Integer.parseInt(strId);
	} catch(NumberFormatException e) {
		out.println("error");
		return;
	}
}
%>


<%
	String action = request.getParameter("action");
	if(action != null && action.trim().equals("modify")) {
		String strS = request.getParameter("status");
		int status = 0;
		if(strS == null) {
			out.println("error!");
			return;
		} else {
			try {
				status = Integer.parseInt(strS);
			} catch(NumberFormatException e) {
				out.println("error");
				return;
			}
		}
		OrderMgr.getIntance().modify(id, status);
	}
%>

<%
	SalesOrder so = OrderMgr.getIntance().getSalesOrderById(id);
	String[] strStatus = {"未处理","已处理","作废"};
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(so.getUser().getUsername() == null) { %>
<center>下单人：普通用户</center><br>
<%} else {%>
<center>下单人：<%=so.getUser().getUsername() %></center><br>
<%} %>
<form action="ordermodify.jsp" style="text-align:center;">
<input type="hidden" name="action" value="modify" />
<input type="hidden" name="id" value="<%=id %>" />
<span>订单状态：</span>
<select name="status">
<%for(int i = 0; i < 3; i++) { %>
	<option value="<%=i %>" <%=(i == so.getStatus() ? "selected" : "")%>><%=strStatus[i] %></option>
<% }%>
</select>
<input type="submit" value="修改" />
</form>
</body>
</html>