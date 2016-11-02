<%@page import="java.util.List"%>
<%@page import="youth.hong.shopping.*"%>
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
	SalesOrder so = OrderMgr.getIntance().getSalesOrderById(id);
	List<SalesItem> items = OrderMgr.getIntance().getSalesItems(so);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>下单人：<%=so.getUser().getUsername() %></center><br>
<table style="text-align:center;" align="center" >
	<tr>
		<th>商品名</th>
		<th>商品价格</th>
		<th>购买数量</th>
		
	</tr>
	<% for(SalesItem c : items) {
			
	%>
		<tr>
		<td ><%=c.getProduct().getName() %></td>
		<td><%=c.getUnitPrice() %></td>
		<td><%=c.getpCount() %></td>
		
	</tr>
	<%} %>
</table>
</body>
</html>