<!DOCTYPE>
<html lang="zh_cn">
<head>
    <link href="[@spring.url '/resources/bootstrap/css/bootstrap.min.css'/]" rel="stylesheet">
    <link href="[@spring.url '/resources/css/simplify.min.css'/]" rel="stylesheet">
</head>

<body class="overflow-hidden">
<div>
    <div class="padding-md">
        <div class="row" style="line-height: 25px">
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">账号名：</div>
                    <div style="float: left;color: #666;">${user.userName!}</div>
                </div>
            </div>
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">创建时间：</div>
                    <div style="float: left;color: #666;">[@mc.dateShow user.createDate/]</div>
                </div>
            </div>
        </div>
        <div class="row" style="line-height: 25px">
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">角色：</div>
                    <div style="float: left;color: #666;">${user.roles[0]!}</div>
                </div>
            </div>
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">姓名：</div>
                    <div style="float: left;color: #666;">${user.name!}</div>
                </div>
            </div>
        </div>
        <div class="row" style="line-height: 25px">
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">手机号：</div>
                    <div style="float: left;color: #666;">${user.phoneNo!}</div>
                </div>
            </div>

            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">金币：</div>
                    <div style="float: left;color: #666;">${user.gold!}</div>
                </div>
            </div>
        </div>
        <div class="row" style="line-height: 25px">
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">上级：</div>
                    <div style="float: left;color: #666;">${user.parent!}</div>
                </div>
            </div>
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">最后登录IP：</div>
                    <div style="float: left;color: #666;">${user.lastLoginIP!}</div>
                </div>
            </div>
        </div>
        <div class="row" style="line-height: 25px">
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">最后登录时间：</div>
                    <div style="float: left;color: #666;">[@mc.dateShow user.lastLoginDate/]</div>
                </div>
            </div>
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">最后登录平台：</div>
                    <div style="float: left;color: #666;">${user.lastLoginPlatform!}</div>
                </div>
            </div>
        </div>
        <div class="row" style="line-height: 25px">
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">用户状态：</div>
                    <div style="float: left;color: #666;">${(user.status.getName())!}</div>
                </div>
            </div>
            <div class="col-xs-6">

            </div>
        </div>
    </div>
</div>
<script src="[@spring.url '/resources/js/jquery.min.js'/]"></script>
<script src="[@spring.url '/resources/js/layer/layer.js'/]"></script>
</body>
</html>