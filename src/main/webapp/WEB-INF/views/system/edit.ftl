[@override name="title"]系统配置修改[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a onclick="window.location.href='/logged'" href="javascript:void(0)">首页</a></li>
    <li>系统配置修改</li>
</ul>
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/system/edit" method="post" data-parsley-validate>

            [@spring.bind "command.userAgreement"/]
            <div class="form-group">
                <label for="userAgreement" class="col-md-3 control-label">用户协议*</label>
                <div class="col-md-9">
                    <textarea class="form-control" id="userAgreement" name="userAgreement" placeholder="输入用户协议"
                              data-parsley-required="true" data-parsley-required-messages="用户协议不能为空"
                              data-parsley-trigger="change">${system.userAgreement!command.userAgreement}</textarea>
                    [@spring.showErrors "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.ratio"/]
            <div class="form-group">
                <label for="ratio" class="col-md-3 control-label">兑换比例*</label>
                <div class="col-md-9">
                    <input class="form-control" id="ratio" name="ratio" placeholder="输入兑换比例"
                           data-parsley-required="true" value="${system.ratio!command.ratio}"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.countMultiple"/]
            <div class="form-group">
                <label for="countMultiple" class="col-md-3 control-label">在线人数倍数*</label>
                <div class="col-md-9">
                    <input class="form-control" id="countMultiple" name="countMultiple"
                           placeholder="在线人数=实际人数×在线人数倍数+0或1"
                           data-parsley-required="true" value="${system.countMultiple!command.countMultiple}"
                           data-parsley-trigger="change" type="number"/>
                    [@spring.showErrors "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.registerGive"/]
            <div class="form-group">
                <label for="registerGive" class="col-md-3 control-label">注册送*</label>
                <div class="col-md-9">
                    <input class="form-control" id="registerGive" name="registerGive" placeholder="请输入注册需要送的钱"
                           data-parsley-required="true" value="${system.registerGive!command.registerGive}"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.spreadGive"/]
            <div class="form-group">
                <label for="spreadGive" class="col-md-3 control-label">推荐送*</label>
                <div class="col-md-9">
                    <input class="form-control" id="spreadGive" name="spreadGive" placeholder="请输入推荐需要送的钱"
                           data-parsley-required="true" value="${system.spreadGive!command.spreadGive}"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.extensionDomain"/]
            <div class="form-group">
                <label for="extensionDomain" class="col-md-3 control-label">推广地址</label>
                <div class="col-md-9">
                    <input class="form-control" id="extensionDomain" name="extensionDomain"
                           data-parsley-required="true" value="${system.extensionDomain!command.extensionDomain}"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "extensionDomain" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.payurl"/]
            <div class="form-group">
                <label for="payurl" class="col-md-3 control-label">支付地址</label>
                <div class="col-md-9">
                    <input class="form-control" id="payurl" name="payurl"
                           data-parsley-required="true" value="${system.payurl!command.payurl}"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "payurl" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.agentGroup"/]
            <div class="form-group">
                <label for="agentGroup" class="col-md-3 control-label">代理QQ群</label>
                <div class="col-md-9">
                    <input class="form-control" id="agentGroup" name="agentGroup"
                           data-parsley-required="true" value="${system.agentGroup!command.agentGroup}"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "agentGroup" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.weChatNumber"/]
            <div class="form-group">
                <label for="weChatNumber" class="col-md-3 control-label">微信公众号</label>
                <div class="col-md-9">
                    <input class="form-control" id="weChatNumber" name="weChatNumber"
                           data-parsley-required="true" value="${system.weChatNumber!command.weChatNumber}"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "weChatNumber" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.customerService"/]
            <div class="form-group">
                <label for="customerService" class="col-md-3 control-label">客服QQ</label>
                <div class="col-md-9">
                    <input class="form-control" id="customerService" name="customerService"
                           data-parsley-required="true" value="${system.customerService!command.customerService}"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "customerService" "parsley-required"/]
                </div>
            </div>

            <div class="text-center m-top-md">
                <button type="reset" class="btn btn-default">重置</button>
                <button type="submit" class="btn btn-success">保存</button>
            </div>
        </form>
    </div>
    <div class="col-lg-3">
        <ul class="blog-sidebar-list font-18">注意事项
            <li>*位必填项</li>
            <li>百人牛牛系统日输赢金币:当系统日输（赢）金币超过设置的值，系统赢（输）概率自动提升50%</li>
        </ul>
    </div>
</div>

[/@override]

[@override name="bottomResources"]
    [@super /]
[/@override]
[@extends name="/decorator.ftl"/]