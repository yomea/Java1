<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	    int id = 0;
	    int rootid = 0;
	    String strId = request.getParameter("id");
	    String strRootId = request.getParameter("rootid");
	    if(strId == null || strRootId == null) {
		    out.println("操作被拒绝！请通过正确路径进入");
	    	return;
	    } else if(strId.trim()==""||strRootId.trim()==""){
	    	out.println("操作被拒绝！请通过正确路径进入");
	    	return;
	    } else {
	    	id = Integer.parseInt(request.getParameter("id")); 
		    rootid = Integer.parseInt(request.getParameter("rootid"));
	    }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
div{ width:600px; margin:0 auto; text-align:center;}
input{ margin:10px;}
</style>
<script type="text/javascript">


	function check() {
		if(document.oneForm.title.value == "") {
			alert("标题不允许为空！");
			return false;
		} else if( document.oneForm.cont.value == ""){
			alert("内容不允许为空！");
			return false;
		} else {
			return true;
		}
		
	}


</script>
</head>
<body>
	<div>
		<form name="oneForm" method="get" action="SaveInto.jsp" onsubmit="return check()">
			<input type="hidden" name="id" value="<%=id %>" />
			<input type="hidden" name="rootid" value="<%=rootid %>" />
			<input type="text" name="title" /><br />
			<textarea name="cont"></textarea><br />
			<input type="submit" value="submit" />
		</form>
	</div>
</body>
</html>