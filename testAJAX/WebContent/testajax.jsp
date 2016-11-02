<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var request;
function check() {
	/* var request = new XMLHttpRequest();
	request.open("GET", "test.jsp?number=" + document.getElementById("test").value);
	request.send();
	request.onreadystatechange = function() {
		if (request.readyState===4) {
			if (request.status===200) { 
				document.getElementById("searchResult").innerHTML = request.responseText;
			} else {
				alert("发生错误：" + request.status);
			}
		} 
	} */
	 var test = document.getElementById("test");
	var url = "test.jsp?id=" + escape(test.value);
	//alert("sdfg");
	if(window.XMLHttpRequest) {
		request = new XMLHttpRequest();//alert("sdfg");
	} else if(window.ActiveXObject) {
		request = new ActiveXObject("Microsoft.XMLHTTP");
	}
	request.open("GET",url);//alert("sdfg");
	request.send();//alert("sdfg");	
	//只要状态发生改变都会触发这个函数。
	request.onreadystatechange = function() {
		if(request.readyState === 4) {
			if(request.status === 200) {
				/* var test = '{"name":"Amy", "age":20}';
				var t = JSON.parse(test);
				alert(t.name); */
				/* var jsona = JSON.parse(request.responseText);
				
				alert(jsona.name); */
				alert(request.responseXML);
				//alert(request.responseXML.getElementsByTagName("msg")[0].childNodes[0].nodeValue); */
			} else {
				alert("error");
			}
		}
	} 
}


</script>
</head>
<body>
	
		<input type="text" id="test" name="test" onblur="check()"/><br>
		
	<p id="ha">xdcv</p>
	
</body>
</html>