<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="youth.hong.shopping.*" %>
<%
	String register = request.getParameter("action");
	if(register != null && register.trim().equals("register")) {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		if(username == null || password == null || password2 == null || phone == null || address == null) {
			out.println("错误的注册方式!");
			return;
		} else if(!password.equals(password2)) {
			out.println("两次密码的输入不一致！！！");
			return;
		}
		username = new String(username.getBytes("ISO-8859-1"),"utf-8");
		password = new String(password.getBytes("ISO-8859-1"),"utf-8");
		phone = new String(phone.getBytes("ISO-8859-1"),"utf-8");
		address = new String(address.getBytes("ISO-8859-1"),"utf-8");
		User user = new User(username, password, phone, address);
		user.save();
		
		response.sendRedirect("firstpage.jsp");
		
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0229)http://member.shopin.net/ssoRegister?domainName=www.shopin.net&return_url=http://www.shopin.net/ddLogReturn/index.html&sign=302c02143663de168a87918eeebebd75c9bfab43c480cc1702145b41bd03c5a2e17102b019ad6cdd71ec46d3c05e&regist_from= -->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
<title>注册</title>
<link href="./registerfiles/reset.css" rel="stylesheet" type="text/css">
<link href="./registerfiles/layout.css" rel="stylesheet" type="text/css">
<link href="./registerfiles/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" async="" src="./registerfiles/pixel.php"></script><script type="text/javascript" src="./registerfiles/qc_loader.js" data-appid="101154842" data-redirecturi="http%3A%2F%2Fmember.shopin.net%2FqqLoginForOuth2%2Findex" charset="utf-8"></script><script src="./registerfiles/qc-1.0.1.js" type="text/javascript" data-appid="101154842" data-redirecturi="http%3A%2F%2Fmember.shopin.net%2FqqLoginForOuth2%2Findex" charset="utf-8"></script>
<link href="./registerfiles/login.css" rel="stylesheet" type="text/css">
<style>
.S_mark1{background:url(http://images.shopin.net/s/images/2014xsqq/popbox.png) 0 0 no-repeat;width:632px;height:292px;text-align:center;font:600 22px/30px 'microsoft yahei';color:#515151}
.S_mark1 h2{font:600 24px/40px 'microsoft yahei';color:#d02b56;margin-bottom:28px}
.S_mark1 .qd{font:600 27px/30px 'microsoft yahei';color:#d02a56}
.S_mark1 .close{background:url(http://images.shopin.net/s/images/2014xsqq/popClose.gif) 0 0 no-repeat;width:18px;height:18px;position:absolute;top:25px;right:25px;overflow:hidden;text-indent:-999em}
.S_mark1 input{background:url(http://images.shopin.net/s/images/2014xsqq/c_btn.png) no-repeat;width:195px;height:81px;border:none;cursor:pointer;font:500 24px/40px 'microsoft yahei';color:#fff;margin:0 30px}
.S_mark1 input.login{background-position:0 0}
.S_mark1 input.reg{background-position:0 100%;color:#cf1f4d}
.bg3{background:url(http://images.shopin.net/s/images/2014xsqq/hong2.png) 180px 100% no-repeat;padding:82px 0 0;height:199px}
.S_mark1{text-align:center;position:absolute;top:200px;left:350px;overflow:hidden;}
#hclose{text-align:left;position:absolute;top:10px;right:10px;overflow:hidden;font-size:14px;}
</style>
</head>

<body class="bgc-login">
<a href="javascript:void(0);" id="open_g"></a>
<div id="topNav">
  <div class="in">
        <p class="topLogin fl">您好！欢迎来到上品折扣 
        </p><ul class="TopQuick fr">
            <li><a href="http://member.shopin.net/myshopin/orders.html" target="_blank">我的上品</a></li>
            <li class="separator"></li>
            <li><a href="http://www.shopin.net/help/newreg.html" target="_blank">新手上路</a><a href="http://www.shopin.net/help/customer.html" target="_blank">客服</a><a href="http://www.shopin.net/help/customer.html" target="_blank">帮助</a></li>
            <li class="separator"></li>
            <li class="public pr">关注我们<a title="新浪微博" target="_blank" href="http://weibo.com/shopinnet" class="tSina"></a><a title="腾讯微博" target="_blank" href="http://t.qq.com/shangpinzhekou" class="tQq"></a><a href="javascript:void(0);" class="tWeixin" title="上品微信"></a><div class="tCode undis pa fb tc"><img width="180" height="180" src="./registerfiles/img_code1.gif"><p>公司微信号为：shopin2000</p></div></li>
        </ul>
	</div>
</div><!--顶部登录&快速导航结束-->
<div class="in oz">
	<h1 class="lg-reg"><a href="http://www.shopin.net/" target="_blank">上品折扣网</a></h1>
</div>
<div class="box-login">
	<div class="tabBd h-reg oz">
	<!--全渠道会员-->
	<form id="mregister_form" action="http://member.shopin.net/ssoRegister" method="post" class="item">
	  <input type="hiddeen" name="action" value="register" />
		<div class="oz">
			<span style="display:block; text-align:center; color:red; font-weight:bold;"></span>
			<div class="left">
			    <div class="sp-item"><span class="sp-label"><i>*</i> Email地址：</span>
					<input id="email" name="email" type="text" class="i-text" onfocus="clearMsg(this)" onblur="validateEmail()">
					<div id="emailmsg"></div>
				</div>
				
				<div class="sp-item"><span class="sp-label"><i>*</i> 密  码：</span>
					<input id="password" name="password" type="password" class="i-text" onfocus="clearMsg(this)" onkeyup="checkPassword()" onblur="checkPassword()">
					<div id="pwdmsg"></div>
				</div>
				<div class="sp-item"><span class="sp-label"><i>*</i> 确认密码：</span>
					<input id="confirmPassword" name="confirmPassword" type="password" class="i-text" onfocus="clearMsg(this)" onblur="validateConfirmPassword()">
					<div id="conpwdmsg"></div>
				</div>
				
			
				
				
				<div class="mob-item">
					<div class="sp-item"><span class="sp-label"> 手机号：</span>
						<input id="mobile" name="mobile" type="text" class="i-text" onfocus="clearMobileMsg(this)">
						<div id="mobilemsg"></div>
					</div>
					 
					
					
				</div>
				<div id="mcheck" class="sp-item form-meta oz">
					<input name="" type="checkbox" value="" class="checkbox" checked="checked">
					<a href="http://www.shopin.net/help/privacy.html" target="_blank"><label>同意上品折扣网服务协议</label></a>
				</div>
				<div class="sp-item form-meta">
					<input name="" type="button" class="btn-reg" onclick="doMRegister()">
				</div>
			</div>
			<div class="right">
				<h2>您已是上品折扣会员</h2>
				<div class="mt15">
					<input name="" type="button" class="btn-green" value="立即登录 &gt;&gt;" onclick="loginonclick()">
				</div>
				<div class="coagent mt20 oz">
					<dl>
						<dt>无需注册，即可登录</dt>
						<dd id="qqLoginBtn"><a href="javascript:;" onclick="return window.open(&#39;https://graph.qq.com/oauth2.0/authorize?client_id=101154842&amp;response_type=token&amp;scope=all&amp;redirect_uri=http%3A%2F%2Fmember.shopin.net%2FqqLoginForOuth2%2Findex&#39;, &#39;oauth2Login_10397&#39; ,&#39;height=525,width=585, toolbar=no, menubar=no, scrollbars=no, status=no, location=yes, resizable=yes&#39;);"><img src="./registerfiles/Connect_logo_1.png" alt="QQ登录" border="0"></a></dd>
						<script type="text/javascript">
						   //调用QC.Login方法，指定btnId参数将按钮绑定在容器节点中
						   QC.Login({
						       //btnId：插入按钮的节点id，必选
						       btnId:"qqLoginBtn",    
						       //用户需要确认的scope授权项，可选，默认all
						       scope:"all",
						       //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
						       size: "C_S"
						   }, function(reqData, opts){//登录成功
						       //根据返回数据，更换按钮显示状态方法
						       var dom = document.getElementById(opts['btnId']),
						       _logoutTemplate=[
						            //头像
						            '<span><img src="{figureurl}" class="{size_key}"/></span>',
						            //昵称
						            '<span>{nickname}</span>',
						            //退出
						            '<span><a href="javascript:QC.Login.signOut();">退出</a></span>'    
						       ].join("");
						       dom && (dom.innerHTML = QC.String.format(_logoutTemplate, {
						           nickname : QC.String.escHTML(reqData.nickname), //做xss过滤
						           figureurl : reqData.figureurl
						       }));
						   }, function(opts){//注销成功
						        // alert('QQ登录 注销成功');
						   }
						);
						</script>
						<dd><a class="xl-icon" href="http://member.shopin.net/sinaLog?domainName=www.shopin.net&return_url=http://www.shopin.net/ddLogReturn/index.html&sign=302c02143663de168a87918eeebebd75c9bfab43c480cc1702145b41bd03c5a2e17102b019ad6cdd71ec46d3c05e">新浪微博</a></dd>
						<dd><a class="zfb-icon" href="http://member.shopin.net/aliLogin?domainName=www.shopin.net&return_url=http://www.shopin.net/ddLogReturn/index.html&sign=302c02143663de168a87918eeebebd75c9bfab43c480cc1702145b41bd03c5a2e17102b019ad6cdd71ec46d3c05e">支付宝</a></dd>
						<dd><a class="icon-139" href="http://member.shopin.net/139Transfer?domainName=www.shopin.net&return_url=http://www.shopin.net/ddLogReturn/index.html&sign=302c02143663de168a87918eeebebd75c9bfab43c480cc1702145b41bd03c5a2e17102b019ad6cdd71ec46d3c05e">139邮箱</a></dd>
					</dl>
				</div>
			</div>
		</div>
	</form>
	</div>
</div>

<div class="S_mark1 pr undis" id="idPop_g" style="position: fixed; z-index: 1001;">
<div class="bg3">
<a href="javascript:goreturn();" id="hclose">关闭</a>
<h2>注册成功！恭喜您获得50元购物券1张</h2>
<p class="f18">您可到“我的上品”→“<a href="http://member.shopin.net/myshopin/coupon.html" target="_blank">我的购物券</a>”中查询</p>
</div>
</div>

<!--活动广告开始-->
<!--<div style="padding-top:10px;padding-left:70px"><a href="http://www.shopin.net/help/2014member0805.html" target="_blank"><img src="http://member.shopin.net/images/20149.1.jpg" ></a></div>
<div style="padding-top:15px;padding-left:70px"><a href="http://www.shopin.net/help/memberstart.html" target="_blank"><img src="http://member.shopin.net/images/2014-06-12.jpg" ></a></div>-->
<!--活动广告结束-->
<script src="./registerfiles/jquery-1.7.2.min.js"></script> 
<script src="./registerfiles/tab.js"></script> 

 <script src="./registerfiles/index.js"></script>
<script>
$(".tWeixin").hover(function(){
	$(".tCode").show();
	},function(){
		$(".tCode").hide();
	});
</script>
<script type="text/javascript">
	$(function(){
	  var rpass=$("#rpass").val();
	  if(rpass!="" && rpass==1){
	    $("#idPop_g").show();
	  }
    });
			
    function goreturn(){
     var rurl=$("#return").val();
    
    	window.location.href=rurl;
    }



		function clearMsg(obj) {
			$(obj).html("");
		}
		function clearMobileMsg(obj) {
			$(obj).html("");
			$("#mobilemsg").removeClass("msg-box");
			$("#mobilemsg").html("<i></i>");
		}
		function loginonclick(){
		  window.location.href="http://member.shopin.net/login.html";
		}
	//全渠道会员
	function validateEmail() {
			var email = $("#email").val();
			if(email == '') {
				 $("#emailmsg").addClass("msg-box");
				 $("#emailmsg").html("<i></i>邮箱地址不能为空");
				return false;
			}
			var valid = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(email);
			if(!valid) {
				 $("#emailmsg").addClass("msg-box");
				 $("#emailmsg").html("<i></i>邮箱地址格式不正确");
				return false;
			}
			if(email!=''){
			return $.ajax({
				url : '/queryExists',
				type : 'post',
				async:false,
				dataType : 'json',
				data : {
					'email' : email
				},
				timeout : 30000,
				error : function() {
					return false;
				},
				success : function(response) {
					var result = response.result;
					if(result == "1") {
						if(response.isExists == "1") {
							$("#emailmsg").addClass("msg-box");
				            $("#emailmsg").html("<i></i>该邮箱已存在，请更换或<a href='javascript:void(0)' onClick='loginonclick()'>登录</a>");
							
							return false;
						} else {
						$("#emailmsg").removeClass("msg-box");
			            $("#emailmsg").html("<i></i>");
							return true;
						}
					}
				}
			});
			}else{
			  return true;
			}
			
		}	
		
		
   //密码验证
		
		function checkPassword() {
			var password = $('#password').val();
			if(password == '' || password.length < 6) {
			
				$("#pwdmsg").addClass("msg-box");
				$("#pwdmsg").html("<i></i>请输入6-16位字符，密码最好包括数字，大小写字母，特殊符号！");
				return false;
			} else{
			    $("#pwdmsg").removeClass("msg-box");
			    $("#pwdmsg").html("<i></i>");
			    return true;
			}			
		}
		
		
		function validateConfirmPassword() {
			var password = $('#password').val();
			var confirmPassword = $('#confirmPassword').val();
			if(password != confirmPassword) {
			
			    $("#conpwdmsg").addClass("msg-box");
				$("#conpwdmsg").html("<i></i>确认密码必须和密码相同");
				return false;
			} else {
				 $("#conpwdmsg").removeClass("msg-box");
			     $("#conpwdmsg").html("<i></i>");
				return true;
			}
		}
		
		
		
		function validateMobile() {
			var mobile = $('#mobile').val();
				var valid=/^1[3,4,5,7,8]+\d{9}$/.test(mobile);
				if(!valid) {
					 $("#mobilemsg").addClass("msg-box");
				     $("#mobilemsg").html("<i></i>手机号码格式不正确");
				     $(".btn-msg").attr("disabled", false);
				   $(".btn-reg").attr("disabled", false);
					return false;
				} 
			
				$("#mobilemsg").removeClass("msg-box");
				$("#mobilemsg").html("<i></i>");
				return true;
		}
		function validatecode() {
			var code = $('#mobilecode').val();
			if(code==''|| code.length < 6){
					 $("#mobilecodemsg").addClass("msg-box");
				     $("#mobilecodemsg").html("<i></i>验证码输入错误");
				     $(".btn-msg").attr("disabled", false);
				   $(".btn-reg").attr("disabled", false);
					return false;
			
			}
			 $("#mobilecodemsg").removeClass("msg-box");
			 $("#mobilecodemsg").html("<i></i>");
			 return true;
		}
		
		function validateVerifyCode() {
			var verifyCode = $('#verifyCode').val();
			if(verifyCode == '') {
			    $("#verifycodemsg").addClass("msg-box");
				$("#verifycodemsg").html("<i></i>验证码不能为空");
				$(".btn-msg").attr("disabled", false);
				   $(".btn-reg").attr("disabled", false);
				return false;
			} else {
				return $.ajax({
					url : '/validateVerifyCode',
					type : 'post',
					async:false,
					dataType : 'json',
					data : {
						'j_captcha_response' : verifyCode
					},
					error : function() {
						$("#verifycodemsg").addClass("msg-box");
						$("#verifycodemsg").html("<i></i>验证码不正确");
						$(".btn-msg").attr("disabled", false);
				   $(".btn-reg").attr("disabled", false);
						return false;
					},
					success : function(response) {
						if(response.isValidate) {
							 $("#verifycodemsg").removeClass("msg-box");
			     				$("#verifycodemsg").html("<i></i>");
							return true;
						}else{
							$("#verifycodemsg").addClass("msg-box");
						$("#verifycodemsg").html("<i></i>验证码不正确");
						$(".btn-msg").attr("disabled", false);
				   $(".btn-reg").attr("disabled", false);
							return false;
						}
						
					}
				});
			}
		}
		
		function refreshVerifyCode() {
			$('#verifyCodeImg').attr('src', '/jcaptcha?' + Math.round(Math.random() * 100000));
		}
		
		function sendcodes(){
		     if ($(".btn-msg").attr("disabled")) {
							return;
				}
	           $(".btn-msg").attr("disabled", "disabled"); 
			  var text=$(".btn-msg").val();
			  if(text!="获取短信验证码"){
			  		return;
			  }
			  
			  
			 if(!validateVerifyCode()) {
						return;
			 }
		      
			   
			if(!validateMobile()) {
				return;
			}
		    
			var mobile = $('#mobile').val();
			$.ajax({
				url : '/sendRegMobileCode',
				type : 'post',
				async : false,
				dataType : 'json',
				data : {
					'fromSystem' : 'web',
					'mobile' : mobile
				},
				error : function() {
					$(".btn-msg").attr("disabled", false);
					return false;
				},
				success : function(response) {
					var exits=response.isExits;
					if(exits=="1"){
						 $("#mobilemsg").addClass("msg-box");
					     $("#mobilemsg").html("<i></i>手机号已存在，请更换");
					      $(".btn-msg").attr("disabled", false);
						return false;
					}
					
					 $("#mobilemsg").removeClass("msg-box");
					 $("#mobilemsg").html("<i></i>");
					
					var result = response.result;
					if(result == "1") {
					    $("#mobilecodemsg").removeClass("msg-box");
			            $("#mobilecodemsg").html("<i></i>");
			            
						$(".btn-msg").val("120秒后重新获取");
								
		                  setTimeout(countDown, 1000);
		                  return true;
					} 
					if(result == "0"){
						$("#mobilecodemsg").addClass("msg-box");
					    $("#mobilecodemsg").html("<i></i>系统繁忙,请稍候再试");
					    $(".btn-msg").attr("disabled", false);
					    return false;
				}
				if(result == "-1"){
						$("#mobilecodemsg").addClass("msg-box");
					    $("#mobilecodemsg").html("<i></i>获取验证码次数过多，请24h后重试。");
					    $(".btn-msg").attr("disabled", false);
					    return false;
				}
				}
				
			});
			
		}
		var time = 120;
		function countDown() {
			time--;
			$(".btn-msg").val(time + '秒后重新获取');
			if (time == 0) {
			           time = 120;
			$(".btn-msg").val("获取短信验证码");
			 $(".btn-msg").attr("disabled", false);
			} else {
			  setTimeout(countDown, 1000);
			}
		} 
		
		
		
		
		
		function doMRegister() {
			var mobile = $('#mobile').val();
			if(!validateEmail()) {
				return;
			}
			if(!checkPassword()) {
				return;
			}
			if(!validateConfirmPassword()) {
				return;
			}
			if(mobile!=null&&mobile!=''){
				if(!validateMobile()) {
					return;
				}
				if(!validatecode()) {
					return;
				}
			}
			if(!validateVerifyCode()) {
				return;
			}
			if(!$('#mcheck input:checkbox').eq(0).attr("checked")) {
				alert('请选择“同意上品折扣网服务协议”');
				return;
			}
			pyRegisterCvt("");
			$('#mregister_form').get(0).submit();
		}
	//品友注册时推送代码	
	function pyRegisterCvt(_orderno){
		var w=window,d=document,e=encodeURIComponent;
		var b=location.href,c=d.referrer,f,g=d.cookie,h=g.match(/(^|;)\s*ipycookie=([^;]*)/),i=g.match(/(^|;)\s*ipysession=([^;]*)/);
		if (w.parent!=w){f=b;b=c;c=f;};u='//stats.ipinyou.com/cvt?a='+e('83s.tPs.13-Yk5dZ_znSe16v7c3yP0')+'&c='+e(h?h[2]:'')+'&s='+e(i?i[2].match(/jump\%3D(\d+)/)[1]:'')+'&u='+e(b)+'&r='+e(c)+'&rd='+(new Date()).getTime()+'&OrderNo='+e(_orderno)+'&e=';
		(new Image()).src=u;
	}
</script>
<script type="text/javascript">
//从页面收集OpenAPI必要的参数。get_user_info不需要输入参数，因此paras中没有参数
var paras = {};
var nickname = "";
//用JS SDK调用OpenAPI
QC.api("get_user_info", paras)
	//指定接口访问成功的接收函数，s为成功返回Response对象
	.success(function(s){
		//成功回调，通过s.data获取OpenAPI的返回数据
		//alert("获取用户信息成功！当前用户昵称为："+s.data.nickname);
		nickname = s.data.nickname;
	})
	//指定接口访问失败的接收函数，f为失败返回Response对象
	.error(function(f){
		//失败回调
		//alert("获取用户信息失败！");
	})
	//指定接口完成请求后的接收函数，c为完成请求返回Response对象
	.complete(function(c){
		//完成请求回调
		if(QC.Login.check()){//如果已登录
		QC.Login.getMe(function(openId, accessToken){
			//alert(["当前登录用户的", "openId为："+openId, "accessToken为："+accessToken].join("\n"));
			$.ajax({
				url : 'http://member.shopin.net/qqLogReturnOuth2/index.json',
				type : 'post',
				dataType : 'json',
				data : {
				domainName:'www.shopin.net',
				return_url:'http://www.shopin.net/ddLogReturn/index.html',
				openid:openId,
				accessToken:accessToken,
				sign:'302c02143663de168a87918eeebebd75c9bfab43c480cc1702145b41bd03c5a2e17102b019ad6cdd71ec46d3c05e',
				nickname:nickname
				},
				timeout : 30000,
				error : function() {
			        return;
				},
				complete : function(response) {
				//alert("调用函数ajaxresponse.return_url成功返回"+response);
				var jsonData = eval("("+response.responseText+")");
				var url = jsonData.substring(jsonData.indexOf("http"),jsonData.indexOf("shopin-sso-qq")+13);
				//alert("url====="+url);
				window.location.href = url;
				QC.Login.signOut();
		  		return;
				}
			});
			
		});
		//这里可以调用自己的保存接口
		
		}
		//alert("获取用户信息完成！");
	});
</script>
<script type="text/javascript">
if(QC.Login.check()){//如果已登录
	QC.Login.getMe(function(openId, accessToken){
		//alert(["当前登录用户的", "openId为："+openId, "accessToken为："+accessToken].join("\n"));
	});
	//这里可以调用自己的保存接口
	//...
}
</script>
<script type="text/javascript">
		(function(){
			try {
				var viz = document.createElement("script");
				viz.type = "text/javascript";
				viz.async = true;
				viz.src = ("https:" == document.location.protocol ?"https://ssl.vizury.com" : "http://serv3.vizury.com")+ "/analyze/pixel.php?account_id=VIZVRM871";
				var s = document.getElementsByTagName("script")[0];
				s.parentNode.insertBefore(viz, s);
				viz.onload = function(){
					try {
						pixel.parse();
					} catch (i) {}
				};
				viz.onreadystatechange = function() {
				if (viz.readyState == "complete" || viz.readyState == "loaded") {
					try {
						pixel.parse();
					} catch (i) {}
				}
				};
			} catch (i) {}
		})();
</script>
<script src="./registerfiles/mask.js"></script>

<iframe src="./registerfiles/PMProxy.html" style="width: 0px; height: 0px; display: none; overflow: hidden;"></iframe></body></html>