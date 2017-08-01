<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <title>登录</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        .flex1 {
            flex: 1;
        }

        .login {
            width: 80%;
            margin: 50px auto;
            border-top: 4px solid #40c401;
            box-shadow: 0px 0px 13px 1px #666;
            padding: 20px;
        }

        .login h3 {
            text-align: center;
            margin-bottom: 15px;
            color: #666;
            font-weight: normal;
        }

        .login .item {
            display: flex;
            margin-bottom: 10px;
            border: 1px solid #eee;
            height: 42px;
            line-height: 40px;
        }

        .item i {
            width: 42px;
            opacity: .7;
        }

        .item .yonghu {
            background: url(/resources/img/yonghu.svg) no-repeat center/25px 25px;
        }

        .item .mima {
            background: url(/resources/img/mima.svg) no-repeat center/25px 25px;
        }

        .item .yzm {
            background: url(/resources/img/yzm.svg) no-repeat center/25px 25px;
        }

        .item input {
            width: 100%;
            border: none;
            -webkit-appearance: none;
            outline: none;
            padding: 10px 10px 10px 0;
            line-height: 20px;
            font-size: 14px;
            color: #333;
        }

        .item > div {
            width: 60px;
            text-align: center;
        }

        .item span {
            color: #13a9f3;
            font-size: 12px;
        }

        .img {
            width: 100%;
            height: 40px;
            text-align: center;
            margin-bottom: 20px;
        }

        .img img {
            height: 100%;
            max-width: 100%;
        }

        .loginbtn {
            line-height: 45px;
            text-align: center;
            font-size: 18px;
            color: #fff;
            background-color: #40C401;
        }
    </style>
    <!-- Bootstrap core CSS -->
    <link href="[@spring.url '/resources/bootstrap/css/bootstrap.min.css'/]" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="[@spring.url '/resources/css/font-awesome.min.css'/]" rel="stylesheet">

    <!-- ionicons -->
    <link href="[@spring.url '/resources/css/ionicons.min.css'/]" rel="stylesheet">

    <!-- Simplify -->
    <link href="[@spring.url '/resources/css/simplify.min.css'/]" rel="stylesheet">

    <link href="[@spring.url '/resources/app/login/login.css'/]" rel="stylesheet">

    <link href="[@spring.url '/resources//js/parsley/parsley.css'/]" rel="stylesheet">
    <!-- Jquery -->
    <script src="[@spring.url '/resources/js/jquery.min.js'/]"></script>
</head>
<body>
<div class="login">
    <h3>牛牛代理管理平台</h3>
    <form action="[@spring.url '/agent/main'/]" method="post" id="form" data-parsley-validate>
    [@mc.showAlert /]
        <div class="item">
            <i class="yonghu"></i>
            <input class="flex1" type="text" name="userName" id="userName" value="${command.userName!}"
                   required data-parsley-errors-container="#userNameError" placeholder="请填写用户名"/>
        </div>
        <span id="userNameError"></span>
        <div class="item">
            <i class="mima"></i>
            <input class="flex1" type="password" name="password" id="password"
                   required data-parsley-errors-container="#passwordError" placeholder="请填写密码"/>
        </div>
        <span id="passwordError"></span>
        <div class="item">
            <i class="yzm"></i>
            <input class="flex1" type="number" name="verificationCode"
                   required data-parsley-errors-container="#codeError" placeholder="请填写验证码"/>
            <div>
                <span onclick="changeImg()">换一张？</span>
            </div>
        </div>
        <span id="codeError"></span>
        <div class="img">
            <img id="codeImg" src="/verificationCode" title="点击切换验证码"/>
        </div>
        <div class="loginbtn" onclick="submit()">登&nbsp;&nbsp;录</div>
        <button type="submit" id="submit" class="hidden"></button>
    </form>
</div>

<!--验证表单-->
<script src="[@spring.url '/resources/js/parsley/parsley.js' /]" type="text/javascript"></script>
<!-- Bootstrap -->
<script src="[@spring.url '/resources/bootstrap/js/bootstrap.min.js'/]"></script>

<!-- Slimscroll -->
<script src="[@spring.url '/resources/js/jquery.slimscroll.min.js'/]"></script>

<!-- Popup Overlay -->
<script src="[@spring.url '/resources/js/jquery.popupoverlay.min.js'/]"></script>

<!-- Modernizr -->
<script src="[@spring.url '/resources/js/modernizr.min.js'/]"></script>

<!-- Simplify -->
<script src="[@spring.url '/resources/js/simplify/simplify.js'/]"></script>

<script src="[@spring.url '/resources/js/common.js'/]"></script>
<script type="text/javascript">
    function changeImg() {
        var img = document.getElementById("codeImg");
        img.setAttribute("src", "/verificationCode" + "?" + new Date().getTime());
    }

    function submit() {
        document.getElementById("submit").click();
    }
</script>
</body>
</html>
