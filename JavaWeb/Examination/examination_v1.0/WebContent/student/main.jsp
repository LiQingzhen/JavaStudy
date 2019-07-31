<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>在线考试系统</title>
	<meta charset="utf-8" />
    <link href="${pageContext.request.contextPath}/js/MyLayout/frame/layui/css/layui.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/js/MyLayout/frame/static/css/style.css" rel="stylesheet" />
    <link rel="icon" href="${pageContext.request.contextPath}/js/MyLayout/frame/static/image/code.png">
    <script src="${pageContext.request.contextPath}/js/jquery-1.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/cookie.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
    <script src="${pageContext.request.contextPath}/js/getsession.js"></script>
    <script src="${pageContext.request.contextPath}/js/MyLayout/frame/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/MyLayout/frame/static/js/vip_comm.js"></script>
</head>
<body>
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header my-header">
            <a href="index.html">
                <div class="my-header-logo">线上考试</div>
            </a>
            <div class="my-header-btn">
                <button class="layui-btn layui-btn-small btn-nav"><i class="layui-icon">&#xe65f;</i></button>
            </div>
            <!-- 顶部左侧添加选项卡监听 -->
            <ul class="layui-nav" lay-filter="side-top-left"></ul>
            <!-- 顶部右侧添加选项卡监听 -->
            <ul class="layui-nav my-header-user-nav" lay-filter="side-top-right">
                <li class="layui-nav-item"></li>
                <li class="layui-nav-item">
                    <a class="name" href="javascript:;"><i class="layui-icon">&#xe629;</i>主题</a>
                    <dl class="layui-nav-child">
                        <dd data-skin="0"><a href="javascript:;">默认</a></dd>
                        <dd data-skin="1"><a href="javascript:;">纯白</a></dd>
                        <dd data-skin="2"><a href="javascript:;">蓝白</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">


                    <a class="name" href="javascript:;"><i class="layui-icon">&#xe629;</i>${ sessionScope.user}</a>

                    <dl class="layui-nav-child">
                        <dd><i class="layui-icon" style="color:black"><a href="student/changePassword.jsp" id="Pwd">修改密码</a></i></dd>
                        <dd><i class="layui-icon" style="color:black"><a href="login.jsp" id="tui">退出</a></i></dd>
                    </dl>
                </li>
            </ul>

        </div>
        <!-- side -->
        <div class="layui-side my-side">
            <div class="layui-side-scroll">
                <!-- 左侧主菜单添加选项卡监听 -->
                <ul class="layui-nav layui-nav-tree" lay-filter="side-main">
                    <li class="layui-nav-item  layui-nav-itemed" id="custom">
                        <a href="javascript:;"><i class="layui-icon">&#xe620;</i>考试管理</a>
                        <dl class="layui-nav-child">
                            <dd id="chancesmanager"><a href="javascript:;" href-url="student/joinExam.jsp"><i class="layui-icon">&#xe621;</i>模拟考试</a></dd>
                            <dd id="dischances"><a href="javascript:;" href-url="student/examInfo.jsp"><i class="layui-icon">&#xe621;</i>正式考试</a></dd>          
                        </dl>
                    </li>
                    <li class="layui-nav-item" id="orders">
                        <a href="javascript:;"><i class="layui-icon">&#xe620;</i>成绩管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:;" href-url="student/report.jsp"><i class="layui-icon">&#xe621;</i>成绩详情</a></dd>
                            <dd><a href="javascript:;" href-url="student/print.jsp"><i class="layui-icon">&#xe621;</i>成绩单打印</a></dd>
                        </dl>
                    </li>
                    
                </ul>

            </div>
        </div>
        <!-- body -->
        <div class="layui-body my-body" style="background-image:url(images/8.png);background-size: 100% 110%;">
            <div class="layui-tab layui-tab-card my-tab" lay-filter="card" lay-allowClose="true">
                <ul class="layui-tab-title">
                    <li class="layui-this" lay-id="1"><span><i class="layui-icon">&#xe638;</i>欢迎页</span></li>
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">
                        <iframe id="iframe" frameborder="0"></iframe>
                    </div>
                </div>
            </div>
        </div>
        <!-- footer -->
        <div class="layui-footer my-footer">

        </div>
    </div>
    <!-- 右键菜单 -->
    <div class="my-dblclick-box none">
        <table class="layui-tab dblclick-tab">
            <tr class="card-refresh">
                <td><i class="layui-icon">&#x1002;</i>刷新当前标签</td>
            </tr>
            <tr class="card-close">
                <td><i class="layui-icon">&#x1006;</i>关闭当前标签</td>
            </tr>
            <tr class="card-close-all">
                <td><i class="layui-icon">&#x1006;</i>关闭所有标签</td>
            </tr>
        </table>
    </div>
    <script src="${pageContext.request.contextPath}/js/MyLayout/frame/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/MyLayout/frame/static/js/vip_comm.js"></script>
   <!--   <script type="text/javascript">

layui.use(['layer','vip_nav'], function () {

    // 操作对象
    var layer = layui.layer
        ,vipNav = layui.vip_nav
        ,$ = layui.jquery;

    // 顶部左侧菜单生成 [请求地址,过滤ID,是否展开,携带参数]

    //vipNav.top_left('./json/nav_top_left.json','side-top-left',false);
    //// 主体菜单生成 [请求地址,过滤ID,是否展开,携带参数]
    //vipNav.main('./json/nav_main.json','side-main',true);
    // you code ...
});

    var arr = GetMsg();
    var rid=arr[1];
    var uname=arr[2];
    var uid = arr[0];
    if (uid == 'null' || uid == undefined) {
        window.location.href = "../View/System/Login.html";
    }
    $(function () {

    $("#username").text(uname);
        if (rid == 1) {
            $("#custom").hide();
            $("#orders").hide();
            $("#service").hide();
        }
        if (rid == 2) {
            $("#createservice").hide();
            $("#dealservice").hide();
            $("#backservice").hide();
            $("#system").hide();
            $("#plans").hide();
            $("#orders").hide();
        }
        if (rid == 3) {
            $("#disservice").hide();
            $("#system").hide();
            $("#dischances").hide();
        }
})
    $("#tui").live("click", function () {
        $.ajax({
            url: '../../../asmx/Login.asmx/DelSession',
            type: 'post',
            contentType: 'application/json',
            data: null,
            async: false,
            success: function (data) {
                if (data.d > 0) {
                    window.location.href = "../View/System/Login.html";
                }
            }
        })
    })

    $("#Pwd").click(function () {
        window.addTab('nav(side-main)', '修改密码', 'ChangePassword.html?id=' + uid);
    })
    $("#Information").click(function () {
        window.addTab('nav(side-main)', '个人信息', 'Information.html?id=' + uid);
    })
    </script>
    -->
</body>

</html>
