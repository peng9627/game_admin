[@override name="title"]充值满送管理 - 充值满送创建[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a onclick="window.location.href='/logged'" href="javascript:void(0)">首页</a></li>
    <li><a href="/recharge_give/pagination">充值满送管理</a></li>
    <li>充值满送创建</li>
</ul>
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/recharge_give/create" method="post" data-parsley-validate>

            [@spring.bind "command.money"/]
            <div class="form-group">
                <label for="money" class="col-md-3 control-label">倍数*</label>
                <div class="col-md-9">
                    <input class="form-control" id="money" name="money"
                           value="${command.money!}" placeholder="输入充值满送金额"
                           data-parsley-required="true" data-parsley-required-messages="充值满送金额不能为空"
                           data-parsley-trigger="change" type="number"/>
                    [@spring.showErrors "money" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.giveMoney"/]
            <div class="form-group">
                <label for="giveMoney" class="col-md-3 control-label">赠送金额*</label>
                <div class="col-md-9">
                    <input class="form-control" id="giveMoney" name="giveMoney"
                           value="${command.giveMoney!}" placeholder="赠送金额"
                           data-parsley-required="true" data-parsley-required-messages="赠送金额不能为空"
                           data-parsley-trigger="change" type="number"/>
                    [@spring.showErrors "giveMoney" "parsley-required"/]
                </div>
            </div>


            <div class="text-center m-top-md">
                <button type="reset" class="btn btn-default">重置</button>
                <button type="submit" class="btn btn-success">创建</button>
            </div>
        </form>
    </div>
    <div class="col-lg-3">
        <ul class="blog-sidebar-list font-18">创建注意事项
            <li>*位必填项</li>
        </ul>
    </div>
</div>

[/@override]
[@extends name="/decorator.ftl"/]