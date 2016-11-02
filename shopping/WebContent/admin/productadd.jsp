<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="youth.hong.shopping.product.Product"%>
<%@page import="youth.hong.shopping.product.ProductMgr"%>
<%@page import="youth.hong.shopping.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="_sessioncheck.jsp" %>
<%
	String action = request.getParameter("action");
	String strId = request.getParameter("id");
	int id = -1;
	if(strId != null) {
		try {
			id = Integer.parseInt(strId);
		} catch(NumberFormatException e) {
			out.println("你干哈？");
			return;
		}
	}
	if(action != null && action.equals("productadd")) {
		request.setCharacterEncoding("utf-8");
		String strNormalPrice = request.getParameter("normalprice");
		String strMemberPrice = request.getParameter("memberprice");
		String strCategoryId = request.getParameter("categoryid");
		String name = request.getParameter("name");
		String descr = request.getParameter("descr");
		double normalPrice = 0;
		double memberPrice = 0;
		int categoryId = 0;
		//System.out.println(name);
		if(name == null || descr == null || strNormalPrice == null || strMemberPrice == null || strCategoryId == null) {
			out.println("error");
			return;
		} else {
			try {
				normalPrice = Double.parseDouble(strNormalPrice);
				memberPrice = Double.parseDouble(strMemberPrice);
				categoryId = Integer.parseInt(strCategoryId);
			} catch(NumberFormatException e) {
				out.println("error");
				return;
			}
			Category c = Category.getCategory(categoryId);
			if(c.getCno() == 1) {
				out.println("非叶子节点，不能添加产品!");
				return;
			}
			name = new String(name.getBytes("ISO-8859-1"),"utf-8");
			descr = new String(descr.getBytes("ISO-8859-1"),"utf-8");
			Product p = new Product();
			p.setName(name);
			p.setDescr(descr);
			p.setCategoryid(categoryId);
			p.setMemberPrice(memberPrice);
			p.setNormalPrice(normalPrice);
			p.setPdate(new Timestamp(System.currentTimeMillis()));
			
			ProductMgr.getInstance().addProduct(p);
			out.println("option success");
		}
	}
%>
<%
	List<Category> categories = Category.getCategories();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form name="productadd" action="productadd.jsp" method="post">
 <input type="hidden" name="action" value="productadd">
 	<table>
 		<tr>
 			<td>名称</td>
 			<td><input type="text" name="name" /></td>
 		</tr>
 		
 		<tr>
 			<td>描述</td>
 			<td><textarea name="descr" ></textarea></td>
 		</tr>
 		
 		<tr>
 			<td>市场价</td>
 			<td><input type="text" name="normalprice" /></td>
 		</tr>
 		
 		<tr>
 			<td>会员价</td>
 			<td><input type="text" name="memberprice" /></td>
 		</tr>
 		
 		<tr>
 			<td>categoryId</td>
 			<td>
 			<select name="categoryid">
 			<%
 				for(Category c : categories) {
 					if(c.getCno()==0) {
 			%>
 			
 			<option value="<%=c.getId() %>"  <%=id == c.getId() ? "selected" : "" %> ><%=c.getName() %> </option>
 			
 			<%} }%>
 			</select>
 			</td>
 		</tr>
 		
 		<tr>
 			<td colspan="2"><input type="submit" value="提交" /></td>
 		</tr>
 		
 	</table>
 </form>
</body>
</html>