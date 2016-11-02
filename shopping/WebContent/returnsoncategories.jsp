<%@page import="java.util.List"%>
<%@page import="youth.hong.shopping.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String strId = request.getParameter("id");
	int id = 0;
	if(strId != null) {
		try {
			id = Integer.parseInt(strId.trim());
		} catch(NumberFormatException e) {
			out.println("error");
			return;
		}
		List<Category> categories = Category.getCategories();
		%>
			<option selected="selected" value="-1" >-所有子商品-</option>
		<%
		for(int i = 0; i < categories.size(); i++) {
			Category c = categories.get(i);
			if(c.getPid() == id) {
				%>
				<option value="<%=c.getId() %>"><%=c.getName() %></option>
				<%
			}
		}
	}
%>