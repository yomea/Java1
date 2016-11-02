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
<script>
	var buyCount = document.getElementById("buycount");
	function check() {
		if(buyCount.value == "" || buyCount.value == null) {
			alert("please input count!");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<form style="text-align:center" name="haha" action="modifycount.jsp" method="post" onsubmit="return check()">
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
			<td><input type="text" size="5" value="<%=cartItem.getCount() %>" id="buycount" name="p<%=cartItem.getProductId() %>"/></td>
			<td><%=cartItem.getCount()*cartItem.getPrice() %></td>
		</tr>
		<%} %>
	</table>
	<p style="text-align:center">总计<%=cart.getTotalPrice() %></p>
	<input type="submit" value="修改购买数量" />
	<input type="button" value="确认订单" onclick="document.location.href='confirm.jsp';" />
</form>
</body>
</html>