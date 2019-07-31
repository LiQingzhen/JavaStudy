<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>在线考试平台</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/test.css" rel="stylesheet" type="text/css" />
<style>
.hasBeenAnswer {
	background: #5d9cec;
	color:#fff;
}
</style>
<script>
     <!-- 解决页面嵌套在iframe中问题 -->
     if (window != top){
        top.location.href = location.href;
     }
</script>
<script>
<!-- 防刷新，关闭 -->
window.onbeforeunload=function(){
	event.returnValue="确定放弃当前考试吗？";
}
</script>
<script type="text/javascript">
/* Ajax提交form表单 */

function commit() {
	//alert($('#form1').serialize());
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/markPaper.do",
        data: $('#form1').serialize(),
        success: function (result) {
        	alert("你的成绩："+result);
        },
        error : function() {
            alert("提交失败，请不要重复提交！");
        }
    });
}
</script>
</head>

<body>
	<div class="main">
		<div class="test_main">
			<div class="nr_left">
				<div class="test">
					<form id="form1" action="##" method="post" onsubmit="return false">
						<input type="hidden" name="stuId" value="${sessionScope.id }"/>
						<input type="hidden" name="examId" value="${examId }"/>
						<div class="test_title">
							<p class="test_time">
								<i class="icon iconfont">&#xe6fb;</i><b class="alt-1">${time }</b>
							</p>
							<font><input type="button" name="test_jiaojuan" value="交卷" onclick="commit()"></font>
						</div>		
							<div class="test_content">
								<div class="test_content_title">
									<h2>单选题</h2>
									<p>
										<span>共</span><i class="content_lit">20</i><span>题，</span><span>合计</span><i class="content_fs">40</i><span>分</span>
									</p>
								</div>
							</div>
							<div class="test_content_nr">
								<ul>	
								<%int i = 0; %>
								<c:forEach var="qu" items="${choices}">
										
										<li id="<%="qu_0_"+i %>">
											<div class="test_content_nr_tt">
												<i><%=++i %></i><span>(2分)</span><font>${qu.details }</font><b class="icon iconfont">&#xe881;</b>
											</div>
											<div class="test_content_nr_main">
												<ul>
													<input type="hidden" name="<%="choice_answer_"+i %>" value="${qu.answer }"/>
														<li class="option">
																<input type="radio" class="radioOrCheck" name="<%="choice_"+i %>" id="<%="0_answer_"+i+"_option_1" %>" value="A"/>
															<label for="<%="0_answer_"+i+"_option_1" %>">
																A.
																<p class="ue" style="display: inline;">${qu.optionA }</p>
															</label>
														</li>
														<li class="option">
																<input type="radio" class="radioOrCheck" name="<%="choice_"+i %>" id="<%="0_answer_"+i+"_option_2" %>" value="B"/>
															<label for="<%="0_answer_"+i+"_option_2" %>">
																B.
																<p class="ue" style="display: inline;">${qu.optionB }</p>
															</label>
														</li>
														<li class="option">
																<input type="radio" class="radioOrCheck" name="<%="choice_"+i %>" id="<%="0_answer_"+i+"_option_3" %>" value="C"/>
															<label for="<%="0_answer_"+i+"_option_3" %>">
																C.
																<p class="ue" style="display: inline;">${qu.optionC }</p>
															</label>
														</li>
														<li class="option">
																<input type="radio" class="radioOrCheck" name="<%="choice_"+i %>" id="<%="0_answer_"+i+"_option_4" %>" value="D"/>
															<label for=<%="0_answer_"+i+"_option_4" %>>
																D.
																<p class="ue" style="display: inline;">${qu.optionD }</p>
															</label>
														</li>
												</ul>
											</div>
										</li>								
									</c:forEach>
								</ul>
							</div>
						
							<div class="test_content">
								<div class="test_content_title">
									<h2>判断题</h2>
									<p>
										<span>共</span><i class="content_lit">10</i><span>题，</span><span>合计</span><i class="content_fs">10</i><span>分</span>
									</p>
								</div>
							</div>
							<div class="test_content_nr">
								<ul>
								<%i = 0; %>
									<c:forEach var="qu" items="${judges}">
										
										<li id="<%="qu_1_"+i %>">
											<div class="test_content_nr_tt">
												<i><%=++i %></i><span>(1分)</span><font>${qu.details }</font><b class="icon iconfont">&#xe881;</b>
											</div>
											<div class="test_content_nr_main">
												<ul>
													<input type="hidden" name="<%="judge_answer_"+i %>" value="${qu.answer }"/>
														<li class="option">
																<input type="radio" class="radioOrCheck" name="<%="judge_"+i %>" id="<%="1_answer_"+i+"_option_1" %>" value="T"/>
															<label for="<%="1_answer_"+i+"_option_1" %>">
																<p class="ue" style="display: inline;">正确</p>
															</label>
														</li>
														<li class="option">
																<input type="radio" class="radioOrCheck" name="<%="judge_"+i %>" id="<%="1_answer_"+i+"_option_2" %>" value="F"/>
															<label for="<%="1_answer_"+i+"_option_2" %>">
																<p class="ue" style="display: inline;">错误</p>
															</label>
														</li>
												</ul>
											</div>
										</li>								
									</c:forEach>
									
								</ul>
							</div>
							
							<div class="test_content">
								<div class="test_content_title">
									<h2>填空题</h2>
									<p>
										<span>共</span><i class="content_lit">10</i><span>题，</span><span>合计</span><i class="content_fs">20</i><span>分</span>
									</p>
								</div>
							</div>
							<div class="test_content_nr">
								<ul>
								<%i = 0; %>
									<c:forEach var="qu" items="${blanks}">
										
										<li id="<%="qu_2_"+i %>">
											<div class="test_content_nr_tt">
												<i><%=++i %></i><span>(2分)</span><font>${qu.details }</font><b class="icon iconfont">&#xe881;</b>
											</div>
											<div class="test_content_nr_main">
											<input type="hidden" name="<%="blank_answer_"+i %>" value="${qu.answer }"/>
												<input type="text" style="width: 200px; height: 25px" name="<%="blank_"+i %>" id="<%="2_answer_"+i+"_option_1" %>" value=""/>	
											</div>
										</li>								
									</c:forEach>
									
								</ul>
							</div>
						
					</form>
				</div>

			</div>
			<div class="nr_right">
				<div class="nr_rt_main">
					<div class="rt_nr1">
						<div class="rt_nr1_title">
							<h1>
								<i class="icon iconfont">&#xe692;</i>答题卡
							</h1>
							<p class="test_time">
								<i class="icon iconfont">&#xe6fb;</i><b class="alt-1">${time }</b>
							</p>
						</div>
						
							<div class="rt_content">
								<div class="rt_content_tt">
									<h2>单选题</h2>
									<p>
										<span>共</span><i class="content_lit">20</i><span>题</span>
									</p>
								</div>
								<div class="rt_content_nr answerSheet">
									<ul>
										<%
										for(i = 0; i < 20; i++){
										%>
											<li><a href=<%="#qu_0_"+i %>><%=i+1 %></a></li>
										<%
										}
										%>	
									</ul>
								</div>
							</div>
						
							<div class="rt_content">
								<div class="rt_content_tt">
									<h2>判断题</h2>
									<p>
										<span>共</span><i class="content_lit">10</i><span>题</span>
									</p>
								</div>
								<div class="rt_content_nr answerSheet">
									<ul>
										<%
										for(i = 0; i < 10; i++){
										%>
											<li><a href=<%="#qu_1_"+i %>><%=i+1 %></a></li>
										<%
										}
										%>	
									</ul>
								</div>
							</div>
							
							<div class="rt_content">
								<div class="rt_content_tt">
									<h2>填空题</h2>
									<p>
										<span>共</span><i class="content_lit">10</i><span>题</span>
									</p>
								</div>
								<div class="rt_content_nr answerSheet">
									<ul>
										<%
										for(i = 0; i < 10; i++){
										%>
											<li><a href=<%="#qu_2_"+i %>><%=i+1 %></a></li>
										<%
										}
										%>	
									</ul>
								</div>
							</div>
						
					</div>

				</div>
			</div>
		</div>
		<!--nr end-->
		<div class="foot"></div>
	</div>

	<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.easy-pie-chart.js"></script>
	<!--时间js-->
	<script src="${pageContext.request.contextPath}/js/jquery.countdown.js"></script>
	<script>
		window.jQuery(function($) {
			"use strict";
			
			$('time').countDown({
				with_separators : false
			});
			$('.alt-1').countDown({
				css_class : 'countdown-alt-1'
			});
			$('.alt-2').countDown({
				css_class : 'countdown-alt-2'
			});
			$('.alt-1').on('time.elapsed', function () {
				// 倒计时结束，自动交卷
			    commit();
			} ) ;
			
		});
		
		
		$(function() {
			$('li.option label').click(function() {
			debugger;
				var examId = $(this).closest('.test_content_nr_main').closest('li').attr('id'); // 得到题目ID
				var cardLi = $('a[href=#' + examId + ']'); // 根据题目ID找到对应答题卡
				// 设置已答题
				if(!cardLi.hasClass('hasBeenAnswer')){
					cardLi.addClass('hasBeenAnswer');
				}
				
			});
		});
	</script>

</body>
</html>