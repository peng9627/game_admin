<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <title>个人信息</title>
    <!-- Bootstrap core CSS -->
    <link href="[@spring.url '/resources/bootstrap/css/bootstrap.min.css'/]" rel="stylesheet">
    <!-- Simplify -->
    <link href="[@spring.url '/resources/css/simplify.min.css'/]" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="[@spring.url '/resources/css/font-awesome.min.css'/]" rel="stylesheet">
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        .my {
            padding: 20px;
        }

        .tishi {
            background-color: #f2dedd;
            border: 1px solid #e6bbb8;
            padding: 10px 15px;
            color: #984451;
            font-size: 14px;
            border-radius: 5px;
            margin-top: 10px;
        }

        .tit {
            background-color: #f5f5f5;
            line-height: 40px;
            font-size: 16px;
            text-align: center;
            border: 1px solid #ddd;
            border-radius: 5px 5px 0 0;
            margin-top: 20px;
            border-bottom: none;
        }

        .con {
            border: 1px solid #ddd;
            border-radius: 0 0 5px 5px;
            padding: 15px 15px 20px;
        }

        .con .info {
            width: 100%;
            overflow-x: auto;
            overflow-y: hidden;
            -webkit-overflow-scrolling: touch;
            border: 1px solid #ddd;
            border-width: 0 1px;
        }

        .infotable {
            table-layout: fixed;
            width: 500px;
            border-top: 1px solid #ddd;
            font-size: 12px;
            color: #333;
            min-width: 100%;
        }

        .infotable td {
            border: 1px solid #ddd;
            border-width: 0 1px 1px 0;
            padding: 10px 5px;
        }

        .infotable tr td:nth-of-type(2n+1) {
            background-color: #F5F5F5;
            width: 100px;
            text-align: right;
        }

        .infotable tr td:last-of-type {
            border-right: none;
        }

        .infotable.moneytable {
            width: 340px;
        }

        .infotable.moneytable tr td:nth-of-type(2n+1) {
            width: 65px;
        }

        .money {
            display: flex;
            width: 200px;
        }

        .money input {
            width: 90px;
            padding: 10px;
            line-height: 15px;
            border: 1px solid #ddd;
            border-right: none;
            -webkit-appearance: none;
            outline: none;
            border-radius: 4px 0 0 4px;
        }

        .money span {
            width: 90px;
            background-color: #428ccb;
            border-radius: 0 4px 4px 0;
            line-height: 37px;
            text-align: center;
            color: #fff;
            font-size: 14px;
        }

        .foot {
            font-size: 12px;
            color: #666;
            text-align: center;
            line-height: 40px;
            background-color: #F5F5F5;
            border-top: 1px solid #ddd;
        }
    </style>

    <!-- Jquery -->
    <script src="[@spring.url '/resources/js/jquery.min.js'/]"></script>
    <!-- Bootstrap -->
    <script src="[@spring.url '/resources/bootstrap/js/bootstrap.min.js'/]"></script>
    <!-- Slimscroll -->
    <script src="[@spring.url '/resources/js/jquery.slimscroll.min.js'/]"></script>
    <!-- Simplify -->
    <script src="[@spring.url '/resources/js/simplify/simplify.js'/]"></script>

    <script src="[@spring.url '/resources/js/common.js'/]"></script>

</head>
<body>
<div class="my">
    <h3>代理信息</h3>
    <p class="tishi">代理须知:提示信息阿达阿萨德大，阿萨德ADS阿萨德ad.asd阿达阿达ADS。阿萨德按时大多数阿达啊。阿达阿达阿达的啊是的的阿萨德。</p>

    <p class="tit">我的基本信息</p>
    <div class="con">
        <div class="info">
            <table class="infotable" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>游戏账号ID：</td>
                    <td>${agent.userName}</td>
                    <td>游戏账号昵称：</td>
                    <td>${agent.name}</td>
                </tr>
                <tr>
                    <td>手机号：</td>
                    <td>${agent.phoneNo}</td>
                    <td>微信：</td>
                    <td>${agent.weChatNo}</td>
                </tr>
                <tr>
                    <td>邀请码：</td>
                    <td>${agent.inviteCode}</td>
                    <td>代理级别：</td>
                    <td>${agent.agentLevel}</td>
                </tr>
                <tr>
                    <td>注册代理时间：</td>
                    <td>${agent.createDate}</td>
                    <td>上级代理邀请码：</td>
                    <td>${agent.higherInviteCode}</td>
                </tr>
                <tr>
                    <td>最近登录时间：</td>
                    <td>${agent.lastLoginDate}</td>
                    <td>最近登录IP：</td>
                    <td>${agent.lastLoginIP}</td>
                </tr>
                <tr>
                    <td>地址：</td>
                    <td colspan="3">${agent.lastLoginPlatform}</td>
                </tr>
            </table>
        </div>
    </div>

[@mc.showAlert /]
    <p class="tit">金额信息</p>
    <div class="con">
        <div class="info">
            <table class="infotable moneytable" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>可用金额：</td>
                    <td>${agent.money}</td>
                    <td>累计收益：</td>
                    <td>${agent.income}</td>
                </tr>
                <tr>
                [#if agent.weChatNo ==""]
                    <td>绑定微信：</td>
                    <td colspan="3">
                        <div class="money">
                            <input type="text" name="weChatNo" id="weChatNo" value=""/>
                            <span data-toggle="modal" data-target="#myModal">绑定</span>
                        </div>
                    </td>
                [#else ]
                    <td>转账金额：</td>
                    <td colspan="3">
                        <div class="money">
                            <input type="number" name="" id="" value=""/>
                            <span onclick="alert('功能开发中')">微信取款</span>
                        </div>
                    </td>
                [/#if]
                </tr>
            </table>
        </div>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    <i class="fa fa-exclamation-circle" aria-hidden="true"></i>绑定提示
                </h4>
            </div>
            <div class="modal-body">
                微信绑定后不能更改，请确认微信号是否正确
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">返回
                </button>
                <button type="button" onclick="weChatBind()" class="btn btn-primary">
                    提交
                </button>
            </div>
        </div>
    </div>
</div>
<p class="foot">xxxxx科技有限公司</p>

<script type="text/javascript">
    function weChatBind() {
        var weChatNo = document.getElementById("weChatNo").value;
        if (weChatNo.replace(/(^\s*)|(\s*$)/g, "") == "") {
            alert("微信号不能为空");
            return;
        }
        window.location.href = "/agent/weChat_bind?userName=${agent.userName}&weChatNo=" + weChatNo;
    }
</script>
</body>
</html>
