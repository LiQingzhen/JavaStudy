<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
  <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
  <title>登录/注册</title>
  <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.min.css">  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">  
  
  <script type="text/javascript">  
  		/* Ajax提交form表单 */
        function signup() {
            $.ajax({
                type: "POST",
                //dataType: "json",	//预期服务器返回的数据类型
                url: "${pageContext.request.contextPath}/signUp1.do",//url
                data: $('#form1').serialize(),
                success: function (result) {
                    if (result == 'success') {
                    	// 注册成功则进入用户界面
                    	window.location.href = "${pageContext.request.contextPath}/signUpSuccess.do";
                    };
                },
                error : function() {
                    alert("注册失败-学号已存在");
                }
            });
        }
  		
        function login() {
            $.ajax({
                type: "POST",
                //dataType: "json",//预期服务器返回的数据类型
                url: "${pageContext.request.contextPath}/login.do",
                data: $('#form2').serialize(),
                success: function (result) {
                    if (result == 'student') {
                    	// 考生成功则进入用户界面
                    	window.location.href = "${pageContext.request.contextPath}/signUpSuccess.do";
                    }else if(result == 'admin'){
                    	// 管理员登录成功
                    	window.location.href = "${pageContext.request.contextPath}/loginSuccess.do";
                    }else{
                    	alert("用户名或密码错误！");
                    };
                },
                error : function() {
                    alert("用户名或密码错误！");
                }
            });
        }
    </script>
</head>
<!-- 清除用户信息 -->
<% session.invalidate(); %>
<body>
  <div class="form">      
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
      </ul>
      
      <!-- 考生注册 -->
      <div class="tab-content">
        <div id="signup">   
          <h1>Examination for JAVA</h1>          
          <form id="form1" action="##" method="post" onsubmit="return false">       
            <div class="field-wrap">
              <label>
                Name<span class="req">*</span>
              </label>
              <input type="text" name="name" required autocomplete="off" />
            </div>

          <div class="field-wrap">
            <label>
              Student ID<span class="req">*</span>
            </label>
            <input type="text" name="id" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Set A Password<span class="req">*</span>
            </label>
            <input type="password" name="pwd" required autocomplete="off"/>
          </div>
          
          <button type="submit" class="button button-block"  onclick="signup()">Get Started</button>         
          </form>
        </div>
        
        <div id="login">   
          <h1>Welcome Back!</h1>         
          <form id="form2" action="##" method="post" onsubmit="return false">         
            <div class="field-wrap">
            <label>
              Student ID<span class="req">*</span>
            </label>
            <input type="text" name="id" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password" name="pwd" required autocomplete="off"/>
          </div>
          
          <p class="forgot"><a href="#">Forgot Password?</a></p>
          
          <button type="submit" class="button button-block" onclick="login()">Log In</button>        
          </form>
        </div>      
      </div>     
</div> 
 	<script src='${pageContext.request.contextPath}/js/jquery.min.js'></script>
    <script  src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>
