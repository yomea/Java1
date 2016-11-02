<%@page import="java.util.ArrayList"%>
<%@page import="youth.hong.shopping.SalesOrder"%>
<%@page import="youth.hong.shopping.OrderMgr"%>
<%@page import="java.util.List"%>
<%@page import="youth.hong.shopping.product.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="_sessioncheck.jsp" %>
<%!
	private static final int PAGE_SIZE = 3;
%>
<%	
	
	String pageNo = request.getParameter("pageNo");
	int pageValue = 1;
	if(pageNo != null) {
		try {
			pageValue = Integer.parseInt(pageNo);
			
		} catch(NumberFormatException e) {
			out.println("error");
			return;
		}
		if(pageValue < 1) {
			pageValue = 1;
		}
		
		
	}
%>
<%
	List<SalesOrder> orders = new ArrayList<SalesOrder>();
	int pageCount = OrderMgr.getIntance().getOrders(orders, pageValue, PAGE_SIZE);
	if(pageValue > pageCount) {
		pageValue = pageCount;
	}
	
%>

<%
	String[] strStatus = {"未处理","已处理","作废"};
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table style="text-align:center;" align="center">
		<tr>
			<td>订单ID</td>
			<td>客户名</td>
			<td>客户电话</td>
			<td>地址</td>
			<td>订单时间</td>
			<td>订单状态</td>
			
			
		</tr>
		
		<% for(SalesOrder so : orders) { %>
			<tr>
				<td><%=so.getId() %></td>
				<td><%=so.getUser().getUsername() %></td>
				<td><%=so.getUser().getPhone() %></td>
				<td><%=so.getAddr() %></td>
				<td><%=so.getoDate() %></td>
				<td><%=strStatus[so.getStatus()] %></td>
				<td><a href="ordermodify.jsp?id=<%=so.getId()%>">修改状态</a></td>
				<td><a href="orderdetail.jsp?id=<%=so.getId() %>">订单明细</a></td>
				
				<td><a href="orderdelete.jsp?id=<%=so.getId()%>">删除</a></td>
			</tr>
		<%} %>
		
	</table>
		<center>
			<span>第<%=pageValue %>页</span><br>
			<a href="orderlist.jsp?pageNo=<%=pageValue-1 %>">上一页</a>
			<a href="orderlist.jsp?pageNo=<%=pageValue+1 > pageCount ? pageCount : pageValue + 1 %>">下一页</a>
		</center>
</body>
</html>