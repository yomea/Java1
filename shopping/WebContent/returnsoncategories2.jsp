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
		StringBuilder str = new StringBuilder();
		int count = 1;
		for(int i = 0; i < categories.size(); i++) {
			Category c = categories.get(i);
			if(c.getPid() == id) {
				str.append("document.forms['search'].b.options[" + count + "].text = '" + c.getName() + "';\n"); 
				str.append("document.forms['search'].b.options[" + count + "].value = " + c.getId() + ";\n"); 
				count++;
			}
		}
		str.insert(0, "document.forms['search'].b.length = " + count + ";\n");
		out.write(str.toString());
	}
%>