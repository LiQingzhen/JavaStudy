<%@page import="java.sql.Timestamp"%>
<%@page import="personal.shopping.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");	// 统一编码
String action = request.getParameter("action");
if(action != null && action.equalsIgnoreCase("post")){
	// 用户数据提交到数据库
	String username = request.getParameter("user");
	String password = request.getParameter("password");
	String phone = request.getParameter("phone");
	String address = request.getParameter("address") + request.getParameter("address2");
	/* 	
	User user = new User();
	user.setUsername(username);
	user.setPassword(password);
	user.setPhone(phone);
	user.setAddress(address);
	user.setRegisterDate(new Timestamp(System.currentTimeMillis()));	
	 */
	User user = new User(username, password, phone, address);
	user.save();
%>
注册成功
<%} else {%>
<form action="register.jsp" method="post" onsubmit="return checkForm()">
	<input type="hidden" name="action" value="post"/>
	<table align="center" border=1>
		<tr align="center"><td colspan=2>用户注册</td></tr>
		<tr>
			<td>用户名：</td>
			<td>
				<input type="text" id="username" name="user" value="" onBlur="checkUserName()" />
				<span class="default" id="nameerr">4到16位（字母，数字，下划线，减号）</span> 
			</td>
		</tr>
		<tr>
			<td>密码：</td>
			<td>
				<input type="password" id="password" name="password" value="" onBlur="checkPassword()"/>
				<span class="default" id="passworderr">4到8位</span>
			</td>
		</tr>
		<tr>
			<td>确认密码：</td>
			<td>
				<input type="password" name="affirm" id="affirm" value="" onBlur="confirmPassword()"/>
				<span class="default" id="affirmerr">再次输入密码</span>
			</td>
		</tr>
		<tr>
			<td>电话：</td>
			<td>
				<input type="text" name="phone" id="phone" value="" onBlur="checkPhone()"/>
				<span class="default" id="phoneerr">11位手机号码</span>
			</td>
		</tr>
	<!--  
		<tr>
			<td>性别：</td>
			<td>
				<input type="radio" name="sex" id="male" value="1"/>
				<label for="male">男</label>
				<input type="radio" name="sex" id="female" value="0"/>
				<label for="female">女</label>
			</td>
		</tr>
	-->
		<tr>
			<td>地址：</td>
			<td>
				<input type="hidden" class="met1" name="address" value="" id="addr-show">
				<!--省份选择-->
            	<select id="prov" onchange="showCity(this)">
                	<option>=请选择省份=</option>
 				</select>
 
            	<!--城市选择-->
            	<select id="city" onchange="showCountry(this)">
                	<option>=请选择城市=</option>
            	</select>
 
            	<!--县区选择-->
            	<select id="country" onchange="selectCountry(this)">
                	<option>=请选择县区=</option>
            	</select>
            	<br>
            	<span class="default" id="addresserr">请将地址精确到门牌号</span><br>
            	<textarea rows="2" cols="35" id="address2" name="address2" onBlur="checkAddress()"></textarea>		
			</td>
		</tr>
		<tr align="center">
			<!-- 横向合并单元格 -->
            <td colspan="2">
            	<input type="submit" value="注册"/>
            	<input type="reset" value="重置"/>
            </td>
        </tr>
	</table>
</form>
<script src="javascript/address.js"></script>
<script src="javascript/method.js"></script> 
<script src="javascript/check_form.js"></script>
<%}%>
</body>
</html>