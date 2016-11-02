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
		int pageCount = ProductMgr.getInstance().getPageCount(PAGE_SIZE);
		if(pageValue > pageCount) {
			pageValue = pageCount;
		}
		
	}
%>
<%
	List<Product> products = ProductMgr.getInstance().getProduct(pageValue,PAGE_SIZE);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>名称</td>
			<td>详情</td>
			<td>市场价</td>
			<td>会员价</td>
			<td>上架时间</td>
			<td>categoryId</td>
			<td>修改</td>
			<td>删除</td>
			
		</tr>
		
		<% for(Product p : products) { %>
			<tr>
				<td><%=p.getName() %></td>
				<td><%=p.getDescr() %></td>
				<td><%=p.getNormalPrice() %></td>
				<td><%=p.getMemberPrice() %></td>
				<td><%=p.getPdate() %></td>
				<td><%=p.getCategory().getName() %></td>
				<td><a href="productmodify.jsp?id=<%=p.getId()%>">修改</a></td>
				<td><a href="productdelete.jsp?id=<%=p.getId()%>">删除</a></td>
			</tr>
		<%} %>
		
	</table>
		<center>
			<span>第<%=pageValue %>页</span><br>
			<a href="productlist.jsp?pageNo=<%=pageValue-1 %>">上一页</a>
			<a href="productlist.jsp?pageNo=<%=pageValue+1 %>">下一页</a>
		</center>
</body>
</html>