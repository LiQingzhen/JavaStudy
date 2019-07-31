<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
  <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
  <title>密码修改</title>
  <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.min.css">  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">  
</head>
<% session.invalidate(); %>
<body>
  <div class="form">
      
      
      
      <div class="tab-content">
        <div id="signup">   
          <h1>Examination for JAVA</h1>
          
          <form action="${pageContext.request.contextPath}/changePwd2.do" method="post">
          
            <div class="field-wrap">
              <label>
                Student ID<span class="req">*</span>
              </label>
              <input type="text" name="id" required autocomplete="off" />
            </div>

          <div class="field-wrap">
            <label>
               Old Password<span class="req">*</span>
            </label>
            <input type="password" name="opwd" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Set New Password<span class="req">*</span>
            </label>
            <input type="password" name="pwd" required autocomplete="off"/>
          </div>
          
          <button type="submit" class="button button-block">Get Chaged</button>
          
          </form>

        </div>
        
        <div id="login">   
          <h1>Welcome Back!</h1>
          
          

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
  <script src='${pageContext.request.contextPath}/js/jquery.min.js'></script>

    <script  src="${pageContext.request.contextPath}/js/index.js"></script>

</body>
</html>
