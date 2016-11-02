<%@page import="youth.hong.shopping.User"%>
<%@page import="youth.hong.shopping.product.Product"%>
<%@page import="youth.hong.shopping.product.ProductMgr"%>
<%@page import="youth.hong.shopping.CartItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cart" class="youth.hong.shopping.Cart" scope="session"></jsp:useBean>
<%
	String strId = request.getParameter("id");
	String strCount = request.getParameter("count");
	int count = 0;
	int id = 0;
	if(strId == null || strCount == null) {
		out.println("error");
		return;
	} else {
		try {
			id = Integer.parseInt(strId.trim());
			count = Integer.parseInt(strCount.trim());
		} catch(NumberFormatException e) {
			out.println("error");
			return;
		}
		
		
	}
%>
<%	
	User user = (User)session.getAttribute("User");
	Product p = ProductMgr.getInstance().getProduct(id);
	CartItem ci = new CartItem();
	ci.setProductId(id);
	ci.setName(p.getName());
	if(user != null) {
		ci.setPrice(p.getMemberPrice());
		
	} else {
		ci.setPrice(p.getNormalPrice());
	}
	ci.setCount(count);
	cart.addItem(ci);
	response.sendRedirect("buydetail.jsp");
%>
