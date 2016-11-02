<%@page import="youth.hong.shopping.product.ProductMgr"%>
<%@page import="youth.hong.shopping.product.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String strId = request.getParameter("id");
	int id = 0;
	if (strId == null) {
		out.println("error");
		return;
	} else {
		try {
			id = Integer.parseInt(strId);
		} catch (NumberFormatException e) {
			out.println("error");
			return;
		}
	}
	Product p = ProductMgr.getInstance().getProduct(id);
%>
<%
	int buyCount = 1;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = function() {
		
		
		var add = document.getElementById("add");
		var reduce = document.getElementById("reduce");
		var countNo = document.getElementById("countNo");
		var buy = document.getElementById("buy");
		
		add.onclick = function() {
			var countValue = parseInt(countNo.innerHTML);
			countNo.innerHTML = countValue + 1;
		}
		reduce.onclick = function() {
			var countValue = parseInt(countNo.innerHTML);
			if(countValue > 1) {
				
			countNo.innerHTML = countValue - 1;
			}
		}
		buy.onclick = function() {
			buy.href = "buy.jsp?id=<%=p.getId()%>&count=" + parseInt(countNo.innerHTML);
		}
	}
</script>
</head>
<body bgcolor="#73747374;">
	<div style="width: 600px; margin: 0 auto; text-align: center;">
		<img alt="product" src="shoppingfiles/2016032516441214.jpg" /><br>
		<span><%=p.getName()%></span><br> <span
			style='text-decoration: <%=session.getAttribute("User") != null ? "line-through" : "none"%>'>市场价：<%=p.getNormalPrice() %></span>
		<br> <span style='text-decoration: <%=session.getAttribute("User") == null ? "line-through" : "none"%>'>会员价：<%=p.getMemberPrice()%></span><br> 购买数量:<span
			id="reduce"
			style="cursor: pointer; display: inline-block; width: 10px; height: 10px; border: 1px solid; line-height: 10px; background: blue;">-</span><span
			style="display: inline-block; width: 5px; height: 5px; margin: 0 20px"
			id="countNo">1</span><span id="add"
			style="cursor: pointer; display: inline-block; width: 10px; height: 10px; border: 1px solid; line-height: 10px; background: blue;">+</span>
		<br> <span><a id="buy"
			href="">我买了</a></span>
	</div>
</body>
</html>