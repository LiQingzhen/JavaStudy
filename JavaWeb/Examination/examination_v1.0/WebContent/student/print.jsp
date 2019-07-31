<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
  <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
  <title>打印成绩单</title>
  <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.min.css">  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">  
</head>
<body>
  <div class="form">
      
      <div class="tab-content">
        <div id="signup">   
          <h1>Get My Report</h1>
          
          <form action="${pageContext.request.contextPath}/report.do" method="post">
          
            <div class="field-wrap">
              <label>
                Exam ID<span class="req">*</span>
              </label>
              <input type="text" name="eid" required autocomplete="off" />
            </div>
            <div class="field-wrap">
              <label>
                Save Path<span class="req">*</span>
              </label>
              <input type="text" name="path" required autocomplete="off" />
            </div>
           <input type="hidden" name="sid" value="${sessionScope.id }" />
       
          <button type="submit" class="button button-block">Print</button>
          
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
