<%@page import="youth.hong.shopping.OrderMgr"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="youth.hong.shopping.User"%>
<%@page import="youth.hong.shopping.SalesOrder"%>
<%@page import="youth.hong.shopping.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Cart cart = (Cart)session.getAttribute("cart");
	User user = (User)session.getAttribute("User");
	if(user == null) {
		user = new User();
		user.setId(-1);
	}
	String addr = request.getParameter("addr");
	if(addr == null || addr.trim().equals("")) {
		out.println("地址错误!");
		return;
	}
	request.setCharacterEncoding("utf-8");
	addr = new String(addr.getBytes("ISO-8859-1"),"utf-8");
	if(cart != null) {
		SalesOrder so = new SalesOrder();
		so.setUser(user);
		so.setAddr(addr);
		so.setoDate(new Timestamp(System.currentTimeMillis()));
		so.setCart(cart);
		so.setStatus(0);
		OrderMgr.getIntance().saveOrder(so);
		session.removeAttribute("cart");
	}
%>
<center>欢迎再来！</center>