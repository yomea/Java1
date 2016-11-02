<%@page import="youth.hong.shopping.CartItem"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cart" class="youth.hong.shopping.Cart" scope="session"></jsp:useBean>
<%
	List<CartItem> items = cart.getItem();
	int count = 0;
	for(CartItem ci : items) {
		String strCount = request.getParameter("p" + ci.getProductId());
		if(strCount != null) {
			try {
				count = Integer.parseInt(strCount);
				ci.setCount(count);
				out.println("<center>修改成功</center>");
			} catch(NumberFormatException e) {
				out.println("error");
				
			}
		}
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
p{ text-align:center;}
</style>
<script>
window.onload = function() {
	var count = document.getElementById("count");
	var value = 5; 
	count.innerHTML = value;
	 function interval() {
		count.innerHTML = --value;
		if(value == 0) {
			clearInterval(cl);
			document.location.href="buydetail.jsp";
		}
	}
	var cl = setInterval(interval,1000); 
}
</script>
</head>
<body>
<div class="main">
	
	<p id="count"></p>
</div>
</body>
</html>