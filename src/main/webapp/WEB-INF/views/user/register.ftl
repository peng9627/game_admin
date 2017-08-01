<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="[@spring.url '/resources/css/bootstrap.min.css' /]" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="[@spring.url '/resources/css/zhuce.css' /]"/>
    <!-- 模板自定义css -->
    <link href="[@spring.url '/resources/css/simplify.min.css'/]" rel="stylesheet">
</head>
<body>
[#-- 显示提示消息 --]
[#macro showAlert]
    [#if alertMessage?? && (alertMessage.type)??]
        [#switch alertMessage.type.getValue()]
            [#case 0]
                [#local alertClass = "alert-success"]
                [#break]
            [#case 1]
                [#local alertClass = "alert-info"]
                [#break]
            [#case 2]
                [#local alertClass = "alert-warning"]
                [#break]
            [#case 3]
                [#local alertClass = "alert-danger"]
                [#break]
        [/#switch]
    <div class="alert ${alertClass} alert-custom alert-dismissible show-alert" role="alert">
        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">×</span><span class="sr-only">关闭</span>
        </button>
        <i class="fa fa-check-circle m-right-xs"></i><strong>${alertMessage.type.getName()}</strong> ${alertMessage.message}
        <i><span class="pull-right time-messages">5秒后自动关闭</span></i>
    </div>
    [/#if]
[/#macro]

<div class="zhuce">
    <form action="/user/register_web" method="post">
        <input name="parent" value="${user.id!command.parent}" hidden/>
        <div id="login">
            <div id="login_title">注册</div>
        [@mc.showAlert /]
            <div class="line"><span id="msg"></span></div>

            <div class="line">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账号:&nbsp;&nbsp;&nbsp;
            [#--[@spring.bind "command.userName"/]--]
                <input name="userName" type="text" placeholder="账号" value="${command.userName!}"/>
            [#--[@spring.showErrors "userName" "parsley-required"/]--]
            </div>


            <div class="line">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码:&nbsp;&nbsp;&nbsp;
            [#--[@spring.bind "command.password"/]--]
                <input name="password" type="password" placeholder="请输入密码" value="${command.password!}"/>
            [#--[@spring.showErrors "password" "parsley-required"/]--]
            </div>

            <div class="line">重复密码:&nbsp;&nbsp;&nbsp;
            [#--[@spring.bind "command.confirmPassword"/]--]
                <input name="confirmPassword" type="password" placeholder="请输入密码"/>
            [#--[@spring.showErrors "confirmPassword" "parsley-required"/]--]
            </div>

            <input id="log_submit" style="position: relative; left: 38px;" type="submit" value="注册">

            <h4>
                <a href="http://www.173600.com"> app下载地址：www.173600.com</a>
            </h4>
        [#--<h4>--]
        [#--扫码下载：--]
        [#--<img src="[@spring.url '/resources/images/www.png'/]"/>--]
        [#--</h4>--]
        </div>

    </form>
    <script src="[@spring.url '/resources/js/jquery.min.js'/]"></script>

    <script src="[@spring.url '/resources/js/common.js'/]"></script>
    <script src="[@spring.url '/resources/js/layer/layer.js'/]"></script>
</div>
</body>
</html>
