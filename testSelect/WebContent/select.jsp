<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function testSelect() {
		
		 if(document.test.a.selectedIndex == 1) {
			document.test.b.length = 2;
			/* document.test.b.options[0].text = "Ann";
			document.test.b.options[0].value = 2; */
			
			document.test.b.options[1].text = "hong";
			document.test.b.options[1].value = 2;
		}
		 
	}
</script>
</head>
<body>
	<form action="" name="test">
		<select name="a" onchange="testSelect()">
			<option>Amy</option>
			<option>youth</option>
		</select>
		
		<select name="b">
			<option>Amy</option>
		</select>	
	</form>
</body>
</html>