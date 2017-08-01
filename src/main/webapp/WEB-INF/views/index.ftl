[@override name="title"]首页控制台[/@override]

[@override name="breadcrumb"]
<div class="row">
    <div class="col-sm-12">
        <div class="page-sub-header">
            <h4>欢迎回来, ${user.userName!}</h4>
        </div>
    </div>
</div>
<div class="custom-popup delete-widget-popup delete-confirmation-popup" id="deleteWidgetConfirm">
    <div class="popup-header text-center">
				<span class="fa-stack fa-4x">
				  <i class="fa fa-circle fa-stack-2x text-danger"></i>
				  <i class="fa fa-exclamation-circle fa-stack-1x fa-inverse"></i>
				</span>
    </div>
    <div class="popup-body text-center">
        <h5>[#if maintenance]你确定维护完成了吗?[#else]你确定要进行维护吗?[/#if]</h5>
        <strong class="block m-top-xs"><i class="fa fa-exclamation-circle m-right-xs text-danger"></i>
            [#if maintenance]维护完成玩家将可以进入开始游戏![#else]维护过程中玩家将不能进入游戏,已经在游戏中的玩家可以玩完当前这把游戏[/#if]
        </strong>

        <div class="text-center m-top-lg">
            <a class="btn btn-success m-right-sm remove-widget-btn">确定</a>
            <a class="btn btn-default deleteWidgetConfirm_close">取消</a>
        </div>
    </div>
</div>
[/@override]

<a class="widget-remove-option" href="javascript:void(0)"><h4>[#if maintenance]
    系统正在维护中,如已完成维护,请点击此处[#else]
    系统正常运行中,需要维护请先点击此处[/#if]</h4></a>

[@override name="subContent"]
<div class="smart-widget widget-green">
    <div class="smart-widget-header">
        <span class="smart-widget-option">
                <a href="#" class="widget-toggle-hidden-option"><i class="fa fa-cog"></i></a>
            <a href="#" class="widget-collapse-option" data-toggle="collapse"><i class="fa fa-chevron-up"></i></a>
            <a href="#" class="widget-remove-option"><i class="fa fa-times"></i></a>
        </span>
        <form class="form-inline no-margin" role="form">
            <div class="form-group">
                <label for="startTime" class="control-label">时间</label>
                <input type="text" class="form-control" value="${command.startTime!}" id="startDate"
                       name="startTime"/>
                到
                <input type="text" class="form-control" value="${command.endTime!}" id="endDate"
                       name="endTime"/>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-md btn-success">查询</button>
            </div>
            <div class="form-group">
                [#list landlordsCount as landlords]
                    <h4>${landlords}</h4>
                [/#list]
            </div>
            <div class="form-group">&nbsp;&nbsp;&nbsp;&nbsp;</div>
            <div class="form-group">
                [#list threecardCount as threecard]
                    <h4>${threecard}</h4>
                [/#list]
            </div>
            <div class="form-group">&nbsp;&nbsp;&nbsp;&nbsp;</div>
            <div class="form-group">
                <h4>百人牛牛在线人数：${hundredCount}</h4>
            </div>
        </form>
    </div>
    <div class="smart-widget-inner">
        <div class="smart-widget-hidden-section" style="display: none;">
            <ul class="widget-color-list clearfix">
                <li style="background-color:#20232b;" data-color="widget-dark"></li>
                <li style="background-color:#4c5f70;" data-color="widget-dark-blue"></li>
                <li style="background-color:#23b7e5;" data-color="widget-blue"></li>
                <li style="background-color:#2baab1;" data-color="widget-green"></li>
                <li style="background-color:#edbc6c;" data-color="widget-yellow"></li>
                <li style="background-color:#fbc852;" data-color="widget-orange"></li>
                <li style="background-color:#e36159;" data-color="widget-red"></li>
                <li style="background-color:#7266ba;" data-color="widget-purple"></li>
                <li style="background-color:#f5f5f5;" data-color="widget-light-grey"></li>
                <li style="background-color:#fff;" data-color="reset"></li>
            </ul>
        </div>
        <div class="smart-widget-body no-padding">
            <div class="padding-md">
                <section class="overflow-auto nice-scrollbar">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>时间</th>
                            <th>斗地主抽成</th>
                            <th>扎金花抽成</th>
                            <th>斗牛抽成</th>
                            <th>百人牛牛抽成</th>
                            <th>充值成功总金额</th>
                            <th>提现成功总金额</th>
                            <th>提现成功总手续费</th>
                            <th>注册人数</th>
                        </tr>
                        </thead>
                        <tbody>
                            [#if countList??]
                                [#list countList as count ]
                                <tr>
                                    <td>${count.time!}</td>
                                    <td>${count.landlordsTotal!}</td>
                                    <td>${count.threecardTotal!}</td>
                                    <td>${count.bullfightTotal!}</td>
                                    <td>${count.hundredTotal!}</td>
                                    <td>${count.rechargeTotal!}</td>
                                    <td>${count.withdrawTotal!}</td>
                                    <td>${count.withdrawPoundageTotal!}</td>
                                    <td>${count.userCount!}</td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>
                </section>
            </div>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]
<script type="text/javascript">
    $(".remove-widget-btn").click(function () {
        window.location.href = '/maintenance';
    });
</script>
[/@override]
[@extends name="/decorator.ftl"/]
