<%@page import="youth.hong.shopping.User"%>
<%@page import="youth.hong.shopping.CartItem"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cart" class="youth.hong.shopping.Cart" scope="session"></jsp:useBean>
<%
	List<CartItem> items = cart.getItem();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<p><% 
	User u = (User)session.getAttribute("User");
	if(u == null) {
		out.println("您尚未登陆，是否<a href='firstpage.jsp?flag=true'>登陆</a>，否则按市场价售卖商品！！！");
	}
%></p>
<form style="text-align:center" name="haha" action="order.jsp" method="post" onsubmit="return check()">
	<table align="center">
		<tr>
			<td>商品ID</td>
			<td>商品名称</td>
			<td>商品<%=session.getAttribute("User") != null ? "会员" : "普通" %>单价</td>
			<td>商品数量</td>
			<td>总价</td>
		</tr>
		<% for(CartItem cartItem : items) { %>
		<tr>
			<td><%=cartItem.getProductId() %></td>
			<td><%=cartItem.getName() %></td>
			<td><%=cartItem.getPrice() %></td>
			<td><%=cartItem.getCount() %></td>
			<td><%=cartItem.getTotalPrice() %></td>
		</tr>
		<%} %>
	</table>
	<p style="text-align:center">总计<%=cart.getTotalPrice() %></p>
	<textarea name="addr"><%=u != null ? u.getAddress() : "" %></textarea>
	<br>
	<input type="submit" value="确认下单" />
</form>

</body>
</html>