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
        function autoPaper() {
            $.ajax({
                type: "POST",
                //dataType: "json",//预期服务器返回的数据类型
                url: "${pageContext.request.contextPath}/createPaper.do",//url
                data: $('#form1').serialize(),
                success: function (result) {
                    alert("试卷编号："+result)
                },
                error : function() {
                    alert("组卷失败");
                }
            });
        }
    </script> 
</head>
<body>
  <div class="form">
      <div class="tab-content">
        <div id="signup">   
          <h1>Create a Paper</h1>
          
          <form id="form1" action="##" method="post" onsubmit="return false"> 
          	
          	<div class="top-row">
            <div class="field-wrap">
              <label>
                	考点<span class="req">*</span>
              </label>
              <input type="text" name="epoint" required autocomplete="off" />
            </div>
        
            <div class="field-wrap">
              <label>
                	难度系数<span class="req">*</span>
              </label>
              <input type="text" name="ep" required autocomplete="off"/>
            </div>
          </div>
          <div class="top-row">
            <div class="field-wrap">
              <label>
                	选择题数量<span class="req">*</span>
              </label>
              <input type="text" name="ca" required autocomplete="off" />
            </div>
        
            <div class="field-wrap">
              <label>
                	单题分数<span class="req">*</span>
              </label>
              <input type="text" name="cs" required autocomplete="off"/>
            </div>
          </div>
          <div class="top-row">
            <div class="field-wrap">
              <label>
                	判断题数量<span class="req">*</span>
              </label>
              <input type="text" name="ja" required autocomplete="off" />
            </div>
        
            <div class="field-wrap">
              <label>
                	单题分数<span class="req">*</span>
              </label>
              <input type="text" name="js" required autocomplete="off"/>
            </div>
          </div>
          <div class="top-row">
            <div class="field-wrap">
              <label>
                	填空题数量<span class="req">*</span>
              </label>
              <input type="text" name="ba" required autocomplete="off" />
            </div>
        
            <div class="field-wrap">
              <label>
                	单题分数<span class="req">*</span>
              </label>
              <input type="text" name="bs" required autocomplete="off"/>
            </div>
          </div>
          <div class="top-row">
            <div class="field-wrap">
              <label>
                	编程题数量<span class="req">*</span>
              </label>
              <input type="text" name="pa" required autocomplete="off" />
            </div>
        
            <div class="field-wrap">
              <label>
                	单题分数<span class="req">*</span>
              </label>
              <input type="text" name="ps" required autocomplete="off"/>
            </div>
          </div>
        	  <button type="submit" class="button button-block" onclick="autoPaper()">Create</button>    
          </form>

        </div>
        
        <div id="login">   
        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
  <script src='${pageContext.request.contextPath}/js/jquery.min.js'></script>

    <script  src="${pageContext.request.contextPath}/js/index.js"></script>

</body>
</html>
