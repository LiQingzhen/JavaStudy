/**
 * register.jsp 注册表单的信息验证
 * 
 */

function checkForm() {
	var nametip = checkUserName();
	var passtip = checkPassword();
	var conpasstip = confirmPassword();
	var phonetip = checkPhone();
	// select标签下拉条未被选中的话 selectedIndex 属性为0
	var addresstip = checkAddress()
			&& document.getElementById("prov").selectedIndex != 0
			&& document.getElementById("city").selectedIndex != 0
			&& document.getElementById("country").selectedIndex != 0;
	if (nametip && passtip && conpasstip && phonetip && addresstip)
		return true;
	alert("请完善您的注册信息！");
	return false;
}
// 验证用户名
function checkUserName() {
	var username = document.getElementById("username");
	var pattern = /^[a-zA-Z0-9_-]{4,16}$/; // 4到16位（字母，数字，下划线，减号）
	if (username.value == "") {
		document.getElementById("nameerr").innerHTML = "用户名不能为空";
		return false;
	} else if (!pattern.test(username.value)) {
		document.getElementById("nameerr").innerHTML = "用户名不合规范";
		return false;
	} else {
		document.getElementById("nameerr").innerHTML = "OK";
		return true;
	}
}

// 验证密码
function checkPassword() {
	var userpasswd = document.getElementById("password");
	var pattern = /^\w{4,8}$/; // 密码要在4-8位
	if (!pattern.test(userpasswd.value)) {
		document.getElementById('passworderr').innerHTML = "密码不合规范";
		return false;
	} else {
		document.getElementById('passworderr').innerHTML = "OK";
		return true;
	}
}

// 确认密码
function confirmPassword() {
	var userpasswd = document.getElementById('password');
	var userConPassword = document.getElementById('affirm');
	if ((userpasswd.value) != (userConPassword.value)
			|| userConPassword.value.length == 0) {
		document.getElementById('affirmerr').innerHTML = "密码不一致";
		return false;
	} else {
		document.getElementById('affirmerr').innerHTML = "OK";
		return true;
	}
}
// 验证手机号
function checkPhone() {
	var userphone = document.getElementById('phone');
	var pattern = /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8}$/;
	if (!pattern.test(userphone.value)) {
		document.getElementById('phoneerr').innerHTML = "手机号码不合规范";
		return false;
	} else {
		document.getElementById('phoneerr').innerHTML = "OK";
		return true;
	}
}

// 验证地址2
function checkAddress() {
	var address = document.getElementById("address2");
	if (address.value == "" || address.value.trim() == "") {
		document.getElementById('addresserr').innerHTML = "地址不能为空";
		return false;
	} else {
		document.getElementById('addresserr').innerHTML = "OK";
		return true;
	}
}
