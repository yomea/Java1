<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="youth.hong.shopping.product.Product"%>
<%@page import="youth.hong.shopping.product.ProductMgr"%>
<%@page import="youth.hong.shopping.Category"%>
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
	String action = request.getParameter("action");
	if(action != null && action.equals("complexsearch")) {
		request.setCharacterEncoding("utf-8");
		String strLowNormalPrice = request.getParameter("lownormalprice");
		String strLowMemberPrice = request.getParameter("lowmemberprice");
		String strHighNormalPrice = request.getParameter("highnormalprice");
		String strHighMemberPrice = request.getParameter("highmemberprice");
		String strStartDate = request.getParameter("startdate");
		String strEndDate = request.getParameter("enddate");
		String strCategoryId = request.getParameter("categoryid");
		String keyword = request.getParameter("keyword");
		double lowNormalPrice = 0;
		double lowMemberPrice = 0;
		double highNormalPrice = 0;
		double highMemberPrice = 0;
		Date startDate = null;
		Date endDate = null;
		int categoryId = 0;
		int[] idArray = null;
		if(strCategoryId != null && !strCategoryId.trim().equals("0")) {
			idArray = new int[1];
			try {
				
				idArray[0] = categoryId = Integer.parseInt(strCategoryId);
			} catch(NumberFormatException e) {
				idArray = null;
			}
		}
		if(strLowNormalPrice == null) {
			strLowNormalPrice = "-1";
		} else {
			try {
				lowNormalPrice = Double.parseDouble(strLowNormalPrice);
			} catch(NumberFormatException e) {
				lowNormalPrice = -1;
			}
			if(lowNormalPrice < 0) {
				lowNormalPrice = -1;
			}
		}
		
		if(strHighNormalPrice == null) {
			strHighNormalPrice = "-1";
		} else {
			try {
				highNormalPrice = Double.parseDouble(strHighNormalPrice);
			} catch(NumberFormatException e) {
				highNormalPrice = -1;
			}
			if(highNormalPrice < 0) {
				highNormalPrice = -1;
			}
		}
		
		if(strLowMemberPrice == null) {
			strLowMemberPrice = "-1";
		} else {
			try {
				lowMemberPrice = Double.parseDouble(strLowMemberPrice);
			} catch(NumberFormatException e) {
				lowMemberPrice = -1;
			}
			if(lowMemberPrice < 0) {
				lowMemberPrice = -1;
			}
		}
		
		if(strHighMemberPrice == null) {
			strHighMemberPrice = "-1";
		} else {
			try {
				highMemberPrice = Double.parseDouble(strHighMemberPrice);
			} catch(NumberFormatException e) {
				highMemberPrice = -1;
			}
			if(highMemberPrice < 0) {
				highMemberPrice = -1;
			}
		}
		
		if(strStartDate == null || strStartDate.trim().equals("")) {
			startDate = null;
			
		} else {
			
			startDate = Timestamp.valueOf(strStartDate);
		}
		
		if(strEndDate == null || strEndDate.trim().equals("")) {
			endDate = null;
			
		} else {
			
			endDate = Timestamp.valueOf(strEndDate);
		}
		List<Product> products = new ArrayList<Product>();
				int pageCount = ProductMgr.getInstance().findProduct(products, idArray, keyword, lowNormalPrice, highNormalPrice, lowMemberPrice, highMemberPrice, startDate, endDate, pageValue, PAGE_SIZE);
		 
		%>
			<table>
				<tr>
					<td>名称</td>
					<td>详情</td>
					<td>市场价</td>
					<td>会员价</td>
					<td>上架时间</td>
					<td>categoryId</td>
					
				</tr>
				
				<% for(Product p : products) { %>
					<tr>
						<td><%=p.getName() %></td>
						<td><%=p.getDescr() %></td>
						<td><%=p.getNormalPrice() %></td>
						<td><%=p.getMemberPrice() %></td>
						<td><%=p.getPdate() %></td>
						<td><%=p.getCategoryid() %></td>
					</tr>
				<%} %>
				
			</table>
				<center>
					<span>第<%=pageValue %>页</span><br>
					<a href="productsearch.jsp?pageNo=<%=pageValue-1 %>&action=<%=action %>&keyword=<%=keyword%>&lownormalprice=<%=lowNormalPrice%>&highnormalprice=<%=highNormalPrice%>&lowmemberprice=<%=lowMemberPrice%>&highmemberprice=<%=highMemberPrice%>&categoryid=<%=categoryId%>">上一页</a>
					<a href="productsearch.jsp?pageNo=<%=pageValue+1 > pageCount ? pageValue : pageValue + 1 %>&action=<%=action %>&keyword=<%=keyword%>&lownormalprice=<%=lowNormalPrice%>&highnormalprice=<%=highNormalPrice%>&lowmemberprice=<%=lowMemberPrice%>&highmemberprice=<%=highMemberPrice%>&categoryid=<%=categoryId%>">下一页</a>
				</center>
	
		<% 
		
	} else if(action != null && action.trim().equals("simplesearch2")) {
		String[] strCategoryId = request.getParameterValues("categoryid");
		String keyword = request.getParameter("keyword");
		int[] arr = null;
		String categoryIds = "";
		if(strCategoryId == null || strCategoryId.length == 0) {
			strCategoryId = null;
		} else {
			arr = new int[strCategoryId.length];
			for(int i = 0; i < strCategoryId.length; i++) {
				try {
					categoryIds += "&categoryid="+strCategoryId[i];
					arr[i] = Integer.parseInt(strCategoryId[i]);
				} catch(NumberFormatException e) {
					out.println("error!!!");
					return;
				}
			}
			if(keyword == null) {
				keyword = "";
			}
			List<Product> products = new ArrayList<Product>();
			int pageCount = ProductMgr.getInstance().findProduct(products, arr, keyword, -1, -1, -1, -1, null, null, pageValue, PAGE_SIZE);
	 
	%>
		<table>
			<tr>
				<td>名称</td>
				<td>详情</td>
				<td>市场价</td>
				<td>会员价</td>
				<td>上架时间</td>
				<td>categoryId</td>
				
			</tr>
			
			<% for(Product p : products) { %>
				<tr>
					<td><%=p.getName() %></td>
					<td><%=p.getDescr() %></td>
					<td><%=p.getNormalPrice() %></td>
					<td><%=p.getMemberPrice() %></td>
					<td><%=p.getPdate() %></td>
					<td><%=p.getCategoryid() %></td>
				</tr>
			<%} %>
			
		</table>
			<center>
				<span>第<%=pageValue %>页</span><br>
				<a href="productsearch.jsp?pageNo=<%=pageValue-1 %>&action=<%=action %>&keyword=<%=keyword%><%=categoryIds%>">上一页</a>
				<a href="productsearch.jsp?pageNo=<%=pageValue+1 > pageCount ? pageValue : pageValue + 1 %>&action=<%=action %>&keyword=<%=keyword%><%=categoryIds%>">下一页</a>
			</center>

	<% 
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
<style type="text/css">
form{ margin-top:50px;}
</style>
</head>
<body>
 <form name="simplesearch" action="" method="post">
 	<label>简单搜索</label><br />
 	<input type="hidden" name="action" value="simplesearch" />
 	<input type="text" name="keyword" />
 	<input type="submit" value="提交" />
 </form>
 
 <form name="simplesearch2" action="productsearch.jsp" method="post">
 	<label>简单搜索2</label><br />
 	<input type="hidden" name="action" value="simplesearch2" />
 	
 		<% for(Category c : categories) {
 				if(c.getCno() == 0) {
 			%>
 				<input name="categoryid" type="checkbox" value="<%=c.getId() %>"><%=c.getName() %><br>
 			<%}else { %>
 				<%=c.getName() %><br>
 			<%} }%>
 	<input type="text" name="keyword" />
 	<input type="submit" value="提交" />
 </form>

 <form name="complexsearch" action="productsearch.jsp" method="post">
 <input type="hidden" name="action" value="complexsearch">
 <label>复杂搜索</label>
 	<table>
 		<tr>
 			<td>category</td>
 			<td><select name="categoryid" >
 			<option value="0">所有类</option>
 			<% for(Category c : categories) {
 				String preStr = "";
 				for(int i = 1; i < c.getGrade(); i++) {
 					preStr += "***";
 				}
 			%>
 				<option value="<%=c.getId() %>"><%=preStr + c.getName() %></option>
 			<%} %>
 			</select></td>
 		</tr>
 		<tr>
 			<td>关键字</td>
 			<td><input type="text" name="keyword" /></td>
 		</tr>
 		
 		<tr>
 			<td>市场价</td>
 			<td>from:<input name="lownormalprice" />&nbsp;&nbsp;
 			to:<input name="highnormalprice" />
 			</td>
 		</tr>
 		
 		<tr>
 			<td>会员价</td>
 			<td>from:<input name="lowmemberprice" />&nbsp;&nbsp;
 			to:<input name="highmemberprice" /></td>
 		</tr>
 		
 		<tr>
 			<td>时间</td>
 			<td>from:<input name="startdate" />&nbsp;&nbsp;
 			to:<input name="enddate" /></td>
 		</tr>
 		
 		
 		
 		<tr>
 			<td colspan="2"><input type="submit" value="提交" /></td>
 		</tr>
 		
 	</table>
 </form>
</body>
</html>