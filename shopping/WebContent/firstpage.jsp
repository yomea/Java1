<%@page import="youth.hong.shopping.Category"%>
<%@page import="youth.hong.shopping.product.Product"%>
<%@page import="youth.hong.shopping.product.ProductMgr"%>
<%@page import="youth.hong.shopping.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>

<%-- <%!//������������һ
	private String createJavaScript(List<Category> categories, Category topCategory) {
		StringBuffer str = new StringBuffer();
		int count = 1;
		for(Category c : categories) {
			if(c.getPid() == topCategory.getId()) {
				str.append("document.forms['search'].b.options[" + count + "].text = '" + c.getName() + "';\n"); 
				str.append("document.forms['search'].b.options[" + count + "].value = " + c.getId() + ";\n"); 
				count++;
			}
			
		}
		str.insert(0, "document.forms['search'].b.length = " + count + ";\n");
		str.insert(0,"if(document.forms['search'].a.options[document.forms['search'].a.selectedIndex].value == " + topCategory.getId() + "){\n");
		str.append("}\n");
		return str.toString();
}
%> --%>


<%
	int count = 20;
	/* String st = ""; */
	List<Product> products = ProductMgr.getInstance().getLatestProduct(count);
	String flag = request.getParameter("flag");
	List<Category> categories = Category.getCategories();
	List<Category> topCategories = new ArrayList<Category>();
	for(Category c : categories) {
		if(c.getGrade() == 1) {
			topCategories.add(c);
			/* st += createJavaScript(categories,c); */
		}
	}
%>

<html>
<head>
<me content="zh-cn"></me>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�̳� - ���Ͼ�Ʒ��Ա��</title>
<link href="images/new.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="images/new.css" rel="stylesheet" type="text/css">
<style type="text/css">
body { width:1000px; margin:0 auto; }
</style>
<script type="text/javascript">
     function check() {
  	  
 if(document.loginform.username.value == "") {
	                                	  alert("�û���������Ϊ�գ�");
	                                	  return false;
	                                  }
 else if(document.loginform.password.value == "") {
	                                	  alert("���벻����Ϊ�գ�");
	                                	  return false;
	                                  }
                                  }
     function grade1() {
    	// alert();
    	var request = new XMLHttpRequest();
    	//var url = "returnsoncategories.jsp?id=" + (document.forms["search"].a.options[document.forms["search"].a.selectedIndex].value);
    	//var url = "ReturnOption?id=" + (document.forms["search"].a.options[document.forms["search"].a.selectedIndex].value);
    	var url = "returnsoncategories2.jsp?id=" + (document.forms["search"].a.options[document.forms["search"].a.selectedIndex].value);
    	request.open("GET",url);
    	request.send();
    	request.onreadystatechange = function() {
    		if(request.readyState === 4) {
    			if(request.status === 200) {
    				var context = request.responseText;
    				 //alert(context);
    				 eval(context);//�������˶�̬����javascript���롣
    				//alert(context.length); 
    				//��������������
    				//document.forms['search'].b.innerHTML = context;
    			}
    		}
    	}
    	 
     }
    <%--  function grade2() {
    	 if(document.search.a.selectedIndex == 0) {
    		 document.search.b.selectedIndex = 0;
    	 }
    	 <%=st %>
     } --%>

 </script>
</head>
<body onLoad="MM_preloadImages('images/00-2.gif','images/01-2.gif','images/02-2.gif','images/03-2.gif','images/04-2.gif','images/05-2.gif','images/06-2.gif','images/07-2.gif','images/08-2.gif','images/09-2.gif')">
<script src="images/piaochuang.js"></script>
<table align="left" border="0" cellpadding="0" cellspacing="0" width="980">
  <tbody>
    <tr>
      <td><!--��ʾͷ����Ϣ����-->
        <table style="border-collapse: collapse;" border="0" cellpadding="0" cellspacing="0" width="100%">
          <tbody>
            <tr>
              <td width="10"></td>
              <td width="135"><a href="thinkshop.cn.htm"></a></td>
              <td width="50"></td>
              <td width="120"><a target="_blank" href="index_bat.php.htm"><img src="images/dazong.gif" border="0" height="47" width="90"></a></td>
              <td width="120"><a target="_blank" href="happybirthday.php.htm"><img src="images/shenri.gif" border="0" height="47" width="95"></a></td>
              <td width="120"><a target="_blank" href="brandshop.php.htm"><img src="images/pingpai.gif" border="0" height="47" width="96"></a></td>
              <td width="120"><a target="_blank" href="powered by Discuz!.htm"><img src="images/luntan.gif" border="0" height="47" width="95"></a></td>
              <td width="120"><a target="_blank" href="gc.htm"><img src="images/thinkcard.gif" border="0" height="47" width="90"></a></td>
              <td align="right" valign="bottom"><map name="FPMap_inctop">
                  <area href="point_exg.php.htm" shape="rect" coords="13,2,81,16" target="_blank">
                  <area href="my_thinkshop.php.htm" shape="rect" coords="92, 1, 158, 15" target="_blank">
                  <area href="extend.php.htm" shape="rect" coords="170, 0, 233, 16" target="_blank">
                </map>
                <img src="images/top_right.gif" usemap="#FPMap_inctop" border="0" height="22" width="238"></td>
            </tr>
          </tbody>
        </table></td>
    </tr>
    <tr>
      <td height="61"><table  width="100%">
          <tbody>
            <tr>
              <td width="6"><img src="images/left.gif" height="35" width="6"></td>
              <td align="left" background="images/topbg.gif" valign="middle" width="897"><table align="left"  width="713">
                  <tbody>
                    <tr class="style1" align="center">
					
                      <td align="left">
                     
					  		<a href="">
								<!--<img src="images/00.gif" name="Image0" border="0">-->
								<span style="color:#FFFFFF"></span>
							</a>
						
					  </td>
					  
                    </tr>
                  </tbody>
                </table></td>
              <td rowspan="2" align="right" width="77"><a href="shoppingpricebuy.php.htm" target="_blank"><img src="images/gouwu_new.gif" border="0" height="61" width="77"></a></td>
            </tr>
            <tr>
              <td colspan="2"><table border="0" cellpadding="0" cellspacing="0" width="100%">
                  <tbody>
                    <tr>
                      <td rowspan="2" bgcolor="#ededed" width="1"><img src="images/line.gif" height="1" width="1"></td>
                      <td bgcolor="#ffffff" height="1" width="850"><img src="images/line.gif" height="1" width="1"></td>
                    </tr>
                    <tr>
                      <td background="images/topbg2.gif" height="25"><table style="border-collapse: collapse;" class="twoji" border="0" cellpadding="0" cellspacing="0" width="100%">
                          <tbody>
                          
	                            <tr id="pma_" style="display: none;">
	                              <td style="position: relative; left: 0pt;" height="25" width="100%">
	                              	&nbsp; 
	                              
								  </td>	                              	
	                            </tr>
                        
                          </tbody>
                        </table></td>
                    </tr>
                  </tbody>
                </table></td>
            </tr>
          </tbody>
        </table></td>
    </tr>
    <tr>
      <td bgcolor="#dadce8" height="1"><img src="images/line.gif" height="1" width="1"></td>
    </tr>
    <tr>
      <td><table border="0" cellpadding="0" cellspacing="0" width="980">
          <tbody>
            <tr>
              <td bgcolor="#ededed" width="1"><img src="images/line.gif" height="1" width="1"></td>
              <td background="images/topbg3.gif" height="31" width="123">��</td>
              <td align="center" background="images/topbg3.gif" width="28"><img src="images/zoom.gif" height="31" width="20"></td>
             
              
              <td background="images/topbg3.gif" valign="middle" width="521"><input name="Bsearch" value="Y" type="hidden">
                <table  width="100%">
                  <tbody>
                    <tr>
                      <td align="left" valign="middle" width="83%">
                      <form name="search" action="" method="post" style="position:absolute; left:650px; top:125px;">
	                      	<select name="a" style="font-size: 9pt; color: rgb(85, 85, 85);" onchange="grade1()">
	                          <option selected="selected" value="-1">--������Ʒ--</option>
	                          <%
	                          	for(Category category : topCategories) {
	                          %>
	                          <option value="<%=category.getId() %>"><%=category.getName() %></option>
	                          <%
	                          	}
	                          %>
	                        </select>
	                        
	                        <select name="b" style="font-size: 9pt; color: rgb(85, 85, 85);">
	                        	<option selected="selected" value="0" >-��������Ʒ-</option>
	                        
	                        </select>
                        </form>
                        <input name="searchname" style="font-size: 9pt; color: rgb(85, 85, 85);" onFocus="if(this.value=='����������Ҫ���ҵ���Ʒ����'){this.value='';}" onBlur="if(this.value==''){this.value='����������Ҫ���ҵ���Ʒ����';}" value="����������Ҫ���ҵ���Ʒ����" size="25" maxlength="30" type="text">
                        <input name="searchcode" style="font-size: 9pt; color: rgb(85, 85, 85);" onFocus="if(this.value=='��Ʒ���'){this.value='';}" onBlur="if(this.value==''){this.value='��Ʒ���';}" value="��Ʒ���" size="8" maxlength="6" type="text">
                        <input src="images/go.gif" style="position: relative; top: 2px;" border="0" height="17" type="image" width="20">
                        &nbsp;<a href="index_search.php.htm"><img src="images/gaoji.gif" style="position: relative; top: 3px;" border="0" height="19" width="66"></a> </td>
                      <td align="left" valign="middle" width="17%"></td>
                    </tr>
                  </tbody>
                </table></td>
              <td align="right" background="images/topbg3.gif" width="306">
              	<!--
              	<iframe name="buysta" src="images/buysta.htm" frameborder="0" height="14" scrolling="no" width="100%"></iframe>
              	-->
              	</td>
              <td bgcolor="#ededed" width="1"></td>
            </tr>
          </tbody>
        </table></td>
    </tr>
    <tr>
      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tbody>
            <tr>
              <td valign="top" width="190"><table border="0" cellpadding="0" cellspacing="0" width="100%">
                  <tbody>
                    <tr>
                      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
                          <tbody>
                            <tr>
                              <td onMouseOver="on_trview('0','')" align="center" height="40"><img src="images/login.gif" height="39" width="190"></td>
                            </tr>
                          </tbody>
                        </table></td>
                    </tr>
                    <tr>
                      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
                          <tbody>
                            <tr>
                              <td rowspan="3" bgcolor="#aab3d5" width="1"><img src="images/line.gif" height="1" width="1"></td>
                              <td width="176"><img src="images/loginbg1.gif" height="19" width="188"></td>
                              <td rowspan="3" bgcolor="#aab3d5" width="1"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                            <tr>
                              <td align="center" bgcolor="#e2e4f0"><table border="0" cellpadding="0" cellspacing="0" width="95%">
                              <% 
                              	User user = (User)session.getAttribute("User");
                              	if(user == null) {
                              %>
                                  <form name="loginform" method="post" action="checkuser.jsp?flag=<%=((flag != null && flag.trim().equals("true")) ? "true" : "false") %>" onsubmit="return check()">
                                  
                                  <tbody>
                                    <tr>
                                      <td align="left" height="25" width="73%">��Ա�ţ�
                                        <input id="test" name="username" size="10" style="font-size: 12px;" type="text" onblur="test()"></td>
                                      <td rowspan="2" width="27%">
                                      	<input src="images/down.gif" name="B1" border="0" height="45" type="image" width="45" ></td>
                                    </tr>
                                    <tr>
                                      <td align="left">�ܡ��룺
                                        <input name="password" size="10" style="font-size: 11px;" type="password"></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2" height="30"><p align="center">[<a href="register.jsp">���û�ע��</a>] &nbsp;[<a href="passwdview.php.htm" onClick="js_callpage(href);return false">��������</a>]</p></td>
                                    </tr>
                                  </tbody>
                                 </form>
                                  <%} else { %>
                                  
                                  <tbody>
                                    <tr>
                                      <td align="left" height="25" width="73%">��Ա�ţ�<%=user.getUsername() %>
                                        </td>
                                        <td><a href="modify.jsp">�޸���Ϣ</a></td>
                                        </tr>
                                        <tr>
                                        <td><a href="buydetail.jsp">�ҵĹ��ﳵ</a></td>
                                        <td><a href="unlogin.jsp">ע��</a></td>
                                      </tr>
                                  </tbody>
                                  
                                  <%} %>
                                </table></td>
                            </tr>
                            <tr>
                              <td bgcolor="#aab3d5" height="1"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                          </tbody>
                        </table></td>
                    </tr>
                    <tr>
                      <td><img src="images/tel.gif" height="87" width="190"></td>
                    </tr>
                    <tr>
                      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
                          <tbody>
                            <tr>
                              <td colspan="3"><img src="images/news.gif" height="32" width="190"></td>
                            </tr>
                            <tr>
                              <td rowspan="3" bgcolor="#aab3d5" width="1"><img src="images/line.gif" height="1" width="1"></td>
                              <td align="center" bgcolor="#e2e4f0"><table bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="90%">
                                  <tbody>
                                    <tr>
                                      <td align="left"> ��<a class="twoji" target="_blank" href="http://www.thinkshop.cn/sales_info.php?slid=86"><font color="#cc0000">�����ʰ칫Ϊ��,�����ɱ�<br>
                                        &nbsp;Я����(04.20-04.26)</font></a>&nbsp;<img src="images/hot_gif.gif"><br>
                                        ��<a class="twoji" target="_blank" href="http://www.thinkshop.cn/sales_info.php?slid=85">��Ʒ�ֳ�,���Բ���Ʒ�ʣ�</a><br>
                                        ��<a class="twoji" target="_blank" href="http://www.thinkshop.cn/sales_info.php?slid=84">������ʵ�ò���,��������..</a><br>
                                        ��<a class="twoji" target="_blank" href="http://www.thinkshop.cn/sales_info.php?slid=83">���޹ذ�3m���ӵƹذ�����..</a><br>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td align="center" height="10" valign="top"><a href="http://www.thinkshop.cn/sales_info.php?ef=list"><img src="images/more.gif" border="0" height="5" width="158"></a></td>
                                    </tr>
                                  </tbody>
                                </table></td>
                              <td rowspan="3" bgcolor="#aab3d5" width="1"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                            <tr>
                              <td bgcolor="#e2e4f0" height="8"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                            <tr>
                              <td bgcolor="#aab3d5" height="1"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                          </tbody>
                        </table></td>
                    </tr>
                    <tr>
                      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
                          <tbody>
                            <tr>
                              <td colspan="3"><img src="images/jingcai.gif" height="32" width="190"></td>
                            </tr>
                            <tr>
                              <td rowspan="3" bgcolor="#aab3d5" width="1"><img src="images/line.gif" height="1" width="1"></td>
                              <td align="center" bgcolor="#e2e4f0"><table bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="90%">
                                  <tbody>
                                    <tr>
                                      <td class="twoji" align="left">��<a class="twoji" href="http://www.thinkshop.cn/extend.php?type=file&amp;fileid=85" target="_blank">�������Ϊ2006�����ʦ��..</a><br>
                                        ��<a class="twoji" href="http://www.thinkshop.cn/extend.php?type=file&amp;fileid=84" target="_blank">3M�ֻ�����Ĥ,�ر�İ�����..</a><br>
                                        ��<a class="twoji" href="http://www.thinkshop.cn/extend.php?type=file&amp;fileid=83" target="_blank">���崺���α������й�����..</a><br>
                                        ��<a class="twoji" href="http://www.thinkshop.cn/extend.php?type=file&amp;fileid=82" target="_blank">���300M 802.11n����·��</a><br>
                                        ��<a class="twoji" href="http://www.thinkshop.cn/extend.php?type=file&amp;fileid=81" target="_blank">Pre-N 2���������·����</a><br>
                                        ��<a class="twoji" href="http://www.thinkshop.cn/extend.php?type=file&amp;fileid=79" target="_blank">��ɽ����ɱ��U����װ����</a><br>
                                        ��<a class="twoji" href="http://www.thinkshop.cn/extend.php?type=file&amp;fileid=78" target="_blank">������Ƶת�������˵��</a><br>
                                        ��<a class="twoji" href="http://www.thinkshop.cn/extend.php?type=file&amp;fileid=77" target="_blank">��������಩ʿϵ�н���</a><br></td>
                                    </tr>
                                    <tr>
                                      <td align="center" height="10" valign="top"><a href="http://www.thinkshop.cn/extend.php?type=file"><img src="images/more.gif" border="0" height="5" width="158"></a></td>
                                    </tr>
                                  </tbody>
                                </table></td>
                              <td rowspan="3" bgcolor="#aab3d5" width="1"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                            <tr>
                              <td bgcolor="#e2e4f0" height="8"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                            <tr>
                              <td bgcolor="#aab3d5" height="1"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                          </tbody>
                        </table></td>
                    </tr>
                    <tr>
                      <td bgcolor="#6874bf" valign="top"><input name="qid" value="7" type="hidden">
                        <input value="Y" name="Bsubmit" type="hidden">
                        <table style="border-collapse: collapse;" bordercolorlight="#FF0000" bordercolordark="#FF0000" border="0" bordercolor="#111111" cellpadding="0" cellspacing="0">
                          <script>function windowOpener0(){controlWindow=window.open('','thinkshop','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=100,height=100');}</script>
                          <form action="question.php" method="post" onSubmit="windowOpener0()" target="thinkshop">
                          </form>
                          <tbody>
                            <tr>
                              <td colspan="5"><img src="images/wen_top.gif" height="17" width="190"></td>
                            </tr>
                            <tr>
                              <td rowspan="2" bgcolor="#aab3d5" width="1"><img src="images/line.gif" height="1" width="1"></td>
                              <td rowspan="2" class="wenjuan" width="10">&nbsp;</td>
                              <td class="twoji" bgcolor="#ffffff" height="24" width="168">�����ڹ�������ʹ���ֻ��շ�����ʱ����������͵�������ֻ���Ļ�����кθо���</td>
                              <td rowspan="2" class="wenjuan" width="10">&nbsp;</td>
                              <td rowspan="2" bgcolor="#aab3d5" width="1"><img src="images/line.gif" height="1" width="1"></td>
                            </tr>
                            <tr>
                              <td bgcolor="#ffffff" height="80"><input value="22" name="qd_serial" type="radio">
                                �ǳ����������Ϊ<br>
                                <input value="23" name="qd_serial" type="radio">
                                ����ν���������<br>
                                <input value="24" name="qd_serial" type="radio">
                                ���⹲���ҵ���Ϣ<br>
                                &nbsp;&nbsp;&nbsp;
                                <input src="images/tijiao.gif" border="0" height="20" type="images" width="60">
                                <a href="http://www.thinkshop.cn/question.php?qid=7&amp;Bsubmit=Y" onClick="js_callpage(href);return false"><img src="images/chongxie.gif" border="0" height="20" width="60"></a></td>
                            </tr>
                            <tr>
                              <td colspan="5"><img src="images/wen_up.gif" height="30" width="190"></td>
                            </tr>
                          </tbody>
                        </table></td>
                    </tr>
                  </tbody>
                </table></td>
              <td onMouseOver="on_trview('0','')" width="10"></td>
              <td valign="top" width="780"><map name="FPMap_brand">
                  <area coords="5, 6, 65, 29" shape="rect" href="http://www.thinkshop.cn/index_pma.php?pma_code=21">
                  <area coords="75, 6, 147, 28" shape="rect" href="http://www.thinkshop.cn/brand_belkin.php">
                  <area coords="157, 7, 187, 29" shape="rect" href="http://www.thinkshop.cn/brand_3m.php">
                  <area coords="194, 6, 277, 30" shape="rect" href="http://www.thinkshop.cn/brand_logitech.php">
                  <area coords="353, 6, 417, 28" shape="rect" href="http://www.thinkshop.cn/brandshop.php?brand=%C5%B7%C4%B7%C1%FA">
                  <area coords="423, 5, 499, 29" shape="rect" href="http://www.thinkshop.cn/brandshop.php?brand=%CF%A3%B8%F1%CB%AE%C6%BF">
                  <area coords="507, 5, 597, 28" shape="rect" href="http://www.thinkshop.cn/brandshop.php?brand=%C8%F0%CA%BF%BE%FC%B5%B6">
                  <area coords="607, 6, 690, 28" shape="rect" href="http://www.thinkshop.cn/brandshop.php?brand=%B1%B4%BF%CB%C2%FC%B2%A9%CA%BF">
                  <area coords="696, 4, 774, 29" shape="rect" href="http://www.thinkshop.cn/index_catalog.php?pma_code=8&amp;cat_code=228">
                  <area coords="283, 6, 349, 28" shape="rect" href="http://www.thinkshop.cn/brandshop.php?brand=%C8%F1%BD%DD%CD%F8%C2%E7">
                </map>
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                  <tbody>
                    <tr>
                      <td onMouseOver="on_trview('0','')" height="8"></td>
                    </tr>
                    <tr>
                      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
                          <tbody>
                            <tr>
                              <td bgcolor="#4352af" width="519"><table style="border-collapse: collapse;" border="0" bordercolor="#111111" cellpadding="0" cellspacing="0" height="266" width="519">
                                  <tbody>
                                    <tr>
                                      <td width="100%"><a style="left: 518px ! important; top: 0px ! important;" class="abp-objtab visible ontop" href="images/index_banner.swf"></a>
                                        </td>
                                    </tr>
                                  </tbody>
                                </table></td>
                              <td valign="top" width="260"><table bgcolor="#4352af" border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
                                  <tbody>
                                    <tr>
                                      <td align="right"><img src="images/newp_top.gif" height="38" width="257"></td>
                                    </tr>
                                    <tr>
                                      <td align="center" bgcolor="#4352af" height="98" valign="top"><table border="0" cellpadding="0" cellspacing="0" width="242">
                                          <tbody>
                                            <tr>
                                              <td align="left" height="118" valign="middle"><span class="white">��<a href="http://www.thinkshop.cn/index_pi.php?productcode=GT1325" class="white">��ʿ���� ��ʿ��0.7100��</a><br>
                                                ��<a href="http://www.thinkshop.cn/index_pi.php?productcode=GT1323" class="white">3M ָʾ��ǩ͸����������װ(��/��)</a><br>
                                                ��<a href="http://www.thinkshop.cn/index_pi.php?productcode=GT1319" class="white">�¹���������ʿ��ϴȥ�ۼ�100��*2װ</a><br>
                                                ��<a href="http://www.thinkshop.cn/index_pi.php?productcode=BR0472" class="white">��������������Сӥ+��������ʽ���..</a><br>
                                                ��<a href="http://www.thinkshop.cn/index_pi.php?productcode=GT1318" class="white">ŷķ��905�Ʋ���</a><br>
                                                ��<a href="http://www.thinkshop.cn/index_pi.php?productcode=BR0470" class="white">����������ʽAV������(��ɫ)</a><br>
                                                </span></td>
                                            </tr>
                                          </tbody>
                                        </table></td>
                                    </tr>
                                    <tr>
                                      <td align="center" bgcolor="#4352af"><table background="images/242-98.gif" border="0" cellpadding="0" cellspacing="0" width="242">
                                          <tbody>
                                            <tr>
                                              <td align="center" height="98" width="138"><table style="border-collapse: collapse;" border="0" bordercolor="#111111" cellpadding="0" cellspacing="0" width="90%">
                                                  <tbody>
                                                    <tr>
                                                      <td align="left" height="40"><strong><a href="" onClick="return false"><span id="img_title" onClick="javascript:goUrl()"></span></a></strong></td>
                                                    </tr>
                                                    <tr>
                                                      <td align="left"><p class="white"><span id="img_price1"></span><br>
                                                          <span id="img_price2"></span></p></td>
                                                    </tr>
                                                  </tbody>
                                                </table></td>
                                              <td align="center" height="98" valign="middle" width="104"><span onClick="javascript:goUrl()" style=""> <img src="images/br0465.gif" id="imgInit" style="border-color: black; color: rgb(0, 0, 0);" name="imgInit" border="0" height="90" width="75"></span></td>
                                            </tr>
                                            
                                          </tbody>
                                        </table></td>
                                    </tr>
                                    <tr>
                                      <td align="right"><img src="images/newp_up.gif" height="12" width="260"></td>
                                    </tr>
                                  </tbody>
                                </table></td>
                            </tr>
                          </tbody>
                        </table></td>
                    </tr>
                    <tr>
                      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
                          <tbody>
                            <tr>
                              <td width="584" valign="top"><table border="0" cellpadding="0" cellspacing="0" width="100%">
                                  <tbody>
                                    <tr>
                                      <td width="10"></td>
                                      <td width="553" valign="top">
									  <%
									  	for(int i=0; i < products.size(); i++) {
									  		Product p = products.get(i);
									  %>
									  <div style="display:inline-block; margin-left:20px;">
										 <a href="productdetail.jsp?id=<%=p.getId() %>"><img alt="product" src="shoppingfiles/2016032516441214.jpg" width="90" height="90" /><br>
										  <span><%=p.getName() %></span><br>
										  <span style="text-decoration:line-through;">�г��ۣ�<%=p.getNormalPrice() %></span>	<br>
										   <span>��Ա�ۣ�<%=p.getMemberPrice() %></span>	</a>						  
									  </div>
									  <%} %>
									  <table border="0" cellpadding="0" cellspacing="0" width="553">
                                          <tbody>
                                          
                                         
	                                            <tr>
	                                              <td width="47%">
	                                              	<a href="">
	                                              	
	                                              	</a>
	                                              </td>
	                                              <td width="7%"></td>
	                                              <td width="46%">
	                                              	<a href="">
	                                              		
	                                              	</a>
	                                              </td>
	                                            </tr>
	                                            <tr>
	                                              <td class="twoji">
	                                              
	                                              <td>��</td>
	                                              <td class="twoji">
	                                              
	                                              </td>
	                                            </tr>
                                         
                                          </tbody>
                                        </table>
                                      </td>
                                      <td width="20"></td>
                                    </tr>
                                  </tbody>
                              </table></td>
                              <td align="right" valign="top" width="196"><table border="0" cellpadding="0" cellspacing="0" width="196">
                                  <tbody>
                                    <tr>
                                      <td><img src="images/paihang_top.gif" height="88" width="196"></td>
                                    </tr>
                                    <tr>
                                      <td align="center" background="images/paihang_bg.gif"><table style="border-collapse: collapse;" border="0" cellpadding="0" cellspacing="0" width="170">
                                          <tbody>
                                            <tr>
                                              <td height="25"><img src="images/tou_02.gif"><a href="http://www.thinkshop.cn/index_pi.php?productcode=GT1025" class="twoji" title="��������ʽ����">��������ʽ����</a></td>
                                            </tr>
                                            <tr>
                                              <td height="2"><img src="images/xian.gif" height="1" width="170"></td>
                                            </tr>
                                            <tr>
                                              <td height="25"><img src="images/tou_03.gif"><a href="http://www.thinkshop.cn/index_pi.php?productcode=GT0855" class="twoji" title="3M ��Ļ�����������װ">3M ��Ļ�����������װ</a></td>
                                            </tr>
                                            <tr>
                                              <td height="2"><img src="images/xian.gif" height="1" width="170"></td>
                                            </tr>
                                            <tr>
                                              <td height="25"><img src="images/tou_02.gif"><a href="http://www.thinkshop.cn/index_pi.php?productcode=GT0856" class="twoji" title="3M �ɵ��ڱʼǱ�����-��ʽ">3M �ɵ��ڱʼǱ�����-��ʽ</a></td>
                                            </tr>
                                            <tr>
                                              <td height="2"><img src="images/xian.gif" height="1" width="170"></td>
                                            </tr>
                                            <tr>
                                              <td height="25"><img src="images/tou_02.gif"><a href="http://www.thinkshop.cn/index_pi.php?productcode=BR0235" class="twoji" title="�������ص�Դ(���ؽ��������� 300W)">�������ص�Դ(���ؽ���..</a></td>
                                            </tr>
                                            <tr>
                                              <td height="2"><img src="images/xian.gif" height="1" width="170"></td>
                                            </tr>
                                            <tr>
                                              <td height="25"><img src="images/tou_02.gif"><a href="http://www.thinkshop.cn/index_pi.php?productcode=GT0794" class="twoji" title="3M ˼������(������Ļ)IT��װ">3M ˼������(������Ļ)IT..</a></td>
                                            </tr>
                                            <tr>
                                              <td height="2"><img src="images/xian.gif" height="1" width="170"></td>
                                            </tr>
                                            <tr>
                                              <td height="25"><img src="images/tou_02.gif"><a href="http://www.thinkshop.cn/index_pi.php?productcode=BR0010" class="twoji" title="������ȫ��ͨ����������">������ȫ��ͨ����������</a></td>
                                            </tr>
                                            <tr>
                                              <td height="2"><img src="images/xian.gif" height="1" width="170"></td>
                                            </tr>
                                            <tr>
                                              <td height="25"><img src="images/tou_02.gif"><a href="http://www.thinkshop.cn/index_pi.php?productcode=BR0236" class="twoji" title="�������ص�Դ(���ؽ��������� 150W�¿�)">�������ص�Դ(���ؽ���..</a></td>
                                            </tr>
                                            <tr>
                                              <td height="2"><img src="images/xian.gif" height="1" width="170"></td>
                                            </tr>
                                            <tr>
                                              <td height="25"><img src="images/tou_02.gif"><a href="http://www.thinkshop.cn/index_pi.php?productcode=BR0402" class="twoji" title="3Mˬ�������+3M˼������(���)">3Mˬ�������+3M˼������..</a></td>
                                            </tr>
                                            <tr>
                                              <td height="2"><img src="images/xian.gif" height="1" width="170"></td>
                                            </tr>
                                            <tr>
                                              <td height="25"><img src="images/tou_02.gif"><a href="http://www.thinkshop.cn/index_pi.php?productcode=BR0013" class="twoji" title="������ʼǱ�USB2.0 2��PCMCIA ���俨">������ʼǱ�USB2.0 2��P..</a></td>
                                            </tr>
                                            <tr>
                                              <td height="2"><img src="images/xian.gif" height="1" width="170"></td>
                                            </tr>
                                            <tr>
                                              <td height="25"><img src="images/tou_02.gif"><a href="http://www.thinkshop.cn/index_pi.php?productcode=BR0357" class="twoji" title="������ʼǱ����Ա����ڵ���(14��)">������ʼǱ����Ա����ڵ�..</a></td>
                                            </tr>
                                          </tbody>
                                        </table></td>
                                    </tr>
                                    <tr>
                                      <td><img src="images/paihang_up.gif" height="17" width="196"></td>
                                    </tr>
                                    <tr>
                                      <td height="5"></td>
                                    </tr>
                                    <tr>
                                      <td><a title="�̳���ļУ԰����𱬽����У�����ҵ�ļ��飡" target="_blank" href="http://www.thinkshop.cn/sales/campus"><img src="images/fcthink2.gif" border="0" height="66" width="194"></a></td>
                                    </tr>
                                  </tbody>
                                </table></td>
                            </tr>
                          </tbody>
                        </table></td>
                    </tr>
                    <tr>
                      <td height="35" valign="center"><img src="images/pingpai_new.gif" usemap="#FPMap_brand" border="0" height="31" width="780"></td>
                    </tr>
                  </tbody>
                </table></td>
            </tr>
          </tbody>
        </table></td>
    </tr>
    <tr>
      <td width="100%"><!--�ײ�INC-->
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tbody>
            <tr>
              <td class="white" target="_blank" align="center" bgcolor="#383eb6" height="25"><a href="http://www.thinkshop.cn/extend.php#%C9%CC%B3%C7%B6%A8%CE%BB" class="white" target="_blank">�̳Ƕ�λ</a> | <a href="http://www.thinkshop.cn/extend.php#%BB%E1%D4%B1%D3%C5%BB%DD" class="white" target="_blank"><font color="#ffffff">��Ա�Ż�</font></a> | <a href="http://www.thinkshop.cn/extend.php#%B6%A9%B5%A5%B4%A6%C0%ED%B9%FD%B3%CC" class="white" target="_blank">��������</a> | <a href="http://www.thinkshop.cn/extend.php#%D3%D0%B9%D8%BB%FD%B7%D6%B6%D2%BB%BB" class="white" target="_blank">���ֶһ�</a> | <a href="http://www.thinkshop.cn/extend.php#%B3%A3%BC%FB%CE%CA%CC%E2" class="white" target="_blank">��������</a> | <a href="http://www.thinkshop.cn/extend.php#%D0%D1%BF%CD%C0%F1%C8%AF" class="white" target="_blank">��ȯ</a> | <a href="http://www.thinkshop.cn/extend.php#%B9%BA%C2%F2%B7%BD%CA%BD" class="white" target="_blank">����ʽ</a> | <a href="http://www.thinkshop.cn/extend.php#%BC%DB%B8%F1%CB%B5%C3%F7" class="white" target="_blank">�۸�˵��</a> | <a href="http://www.thinkshop.cn/extend.php#%B8%B6%BF%EE%B7%BD%CA%BD" class="white" target="_blank">���ʽ</a> | <a href="http://www.thinkshop.cn/extend.php#%C5%E4%CB%CD%B7%BD%CA%BD%D3%EB%B7%D1%D3%C3" class="white" target="_blank">���ͷ���</a> | <a href="http://www.thinkshop.cn/extend.php#%B1%A3%D0%DE%D3%EB%CD%CB%BB%BB" class="white" target="_blank">�����˻�</a> | <a href="http://www.allsmart.com.cn/ThinkPad/bbs/upload/forumdisplay.php?fid=40" class="white" target="_blank">�ͷ�ר��</a> | <a href="http://www.thinkshop.cn/searchlist.htm" class="white" target="_blank">�������</a> | <a href="http://www.allsmart.com/about_me/about_9.htm" class="white" target="_blank">������ʿ</a> <span class="white">| <a href="http://www.allsmart.com/about_me/about_10.htm" class="white" target="_blank">��������</a></span></td>
            </tr>
            <tr>
              <td align="center" height="5"><img src="images/line.gif" height="1" width="1"></td>
            </tr>
            <tr>
              <td align="center"><table style="border-collapse: collapse; font-size: 9pt;" border="0" bordercolor="#111111" cellpadding="0" cellspacing="0">
                  <tbody>
                    <tr>
                      <td width="50" height="38" align="left"><a href="http://www.ebworld.com.cn/html/2006-12-11/2006121183850.asp" target="_blank"><img src="images/2006-TOP100.gif" alt="�ڶ�����������ϲ������վTOP100" border="0" height="43" width="44"></a></td>
                      <td><p align="left"><a href="http://www.hd315.gov.cn/beian/view.asp?bianhao=010202006053100022" target="_blank"><img src="images/biaoshi.gif" border="0" height="35" width="28"></a></p></td>
                    </tr>
                  </tbody>
                </table></td>
            </tr>
          </tbody>
        </table></td>
    </tr>
  </tbody>
</table>
<script src="images/addnum.htm"></script>
</body>
</html>
