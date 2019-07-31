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
        function create() {
            $.ajax({
                type: "POST",
                //dataType: "json",//预期服务器返回的数据类型
                url: "${pageContext.request.contextPath}/createExam.do",//url
                data: $('#form1').serialize(),
                success: function (result) {
                    if (result == 'success') {
                    	alert("创建成功！");
                    };
                },
                error : function() {
                    alert("创建失败");
                }
            });
        }
    </script>
</head>
<body>
  <div class="form">
      
      <!-- 添加考试信息 -->
      <div class="tab-content">
        <div id="signup">   
          <h1>Create An Examination</h1>
          
          <form id="form1" action="##" method="post" onsubmit="return false">
          
            <div class="field-wrap">
              <label>
                Name<span class="req">*</span>
              </label>
              <input type="text" name="name" required autocomplete="off" />
            </div>

          <div class="field-wrap">
            <label>
               Start Time<span class="req">*</span>
            </label>
            <input type="text" name="start" required autocomplete="off"/>
          </div>
          <div class="field-wrap">
            <label>
              Duration<span class="req">*</span>
            </label>
            <input type="text" name="time" required autocomplete="off"/>
          </div>
          <div class="field-wrap">
            <label>
              Score<span class="req">*</span>
            </label>
            <input type="text" name="score" required autocomplete="off"/>
          </div>
          <div class="field-wrap">
            <label>
              Paper ID<span class="req">*</span>
            </label>
            <input type="text" name="paper" required autocomplete="off"/>
          </div>
          
          <button type="submit" class="button button-block"  onclick="create()">Create</button>
          
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
