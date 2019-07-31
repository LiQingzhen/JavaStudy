<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
  <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
  <title>新增考生</title>
  <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.min.css">  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">  

  <script type="text/javascript">
  
  		/* Ajax提交form表单 */
        function signup() {
            $.ajax({
                type: "POST",
                //dataType: "json",//预期服务器返回的数据类型
                url: "${pageContext.request.contextPath}/signUp.do",//url
                data: $('#form1').serialize(),
                success: function (result) {
                    if (result == 'success') {
                        alert("注册成功");
                    };
                },
                error : function() {
                    alert("注册失败，学号已存在!");
                }
            });
        }
    </script>
</head>
<body>
  <div class="form">
  
      <div class="tab-content">
        <div id="signup">   
          <h1>Add a Student</h1>
          
          <form id="form1" action="##" method="post" onsubmit="return false">
          
            <div class="field-wrap">
              <label>
                Student ID<span class="req">*</span>
              </label>
              <input type="text" name="id" required autocomplete="off" />
            </div>
            
            <div class="field-wrap">
              <label>
                Name<span class="req">*</span>
              </label>
              <input type="text" name="name" required autocomplete="off" />
            </div>
          
          <div class="field-wrap">
            <label>
              Set Password<span class="req">*</span>
            </label>
            <input type="password" name="pwd" required autocomplete="off"/>
          </div>
          <input type="hidden" name="flag" value="admin"/>
          <button type="submit" class="button button-block" onclick="signup()">ADD</button>
          
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
