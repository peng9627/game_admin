[@override name="title"]充值记录[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">

    <li>充值记录</li>
</ul>
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="smart-widget widget-green">
    <div class="smart-widget-header">
        <span class="smart-widget-option">
                <a href="#" class="widget-toggle-hidden-option"><i class="fa fa-cog"></i></a>
            <a href="#" class="widget-collapse-option" data-toggle="collapse"><i class="fa fa-chevron-up"></i></a>
            <a href="#" class="widget-remove-option"><i class="fa fa-times"></i></a>
        </span>
        <form class="form-inline no-margin" role="form">
            <div class="form-group hidden">
                <label for="userName" class="control-label">用户名</label>
                <input type="text" class="form-control" id="userName" name="userName" value="${command.userName!}"
                       placeholder="用户名"/>
            </div>
            <div class="form-group">
                <label for="isSuccess" class="control-label">是否成功</label>
                <select name="isSuccess" id="isSuccess" class="form-control">
                    [#assign flowType = (command.isSuccess!)?default("") /]
                    <option value="ALL" [@mc.selected flowType "ALL" /]>全部</option>
                    <option value="YES" [@mc.selected flowType "YES" /]>成功</option>
                    <option value="NO" [@mc.selected flowType "NO" /]>失败</option>
                </select>
            </div>
            <div class="form-group">
                <label for="startDate" class="control-label">时间</label>
                <input type="text" class="form-control" value="${command.startDate!}" id="startDate"
                       name="startDate"/>
                到
                <input type="text" class="form-control" value="${command.endDate!}" id="endDate"
                       name="endDate"/>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-md btn-success">查询</button>
            </div>
            <div class="form-group">
                <h4>当前条件总合计:${totalMoney}</h4>
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
                            <th>用户名</th>
                            <th>时间</th>
                            <th>金额</th>
                            <th>支付状态</th>
                            <th>支付方式</th>
                        </tr>
                        </thead>
                        <tbody>
                            [#if pagination.data??]
                                [#list pagination.data as moneyDetailed ]
                                <tr>
                                    <td>${moneyDetailed.user!}</td>
                                    <td>[@mc.dateShow moneyDetailed.createDate/]</td>
                                    <td>${moneyDetailed.money!}</td>
                                    [#if moneyDetailed.isSuccess=='YES']
                                        <td class="green">已支付</td>
                                    [#else]
                                        <td class="red">未支付</td>
                                    [/#if]
                                    <td>${(moneyDetailed.payType.getName())!}</td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>
                </section>
            </div>
            <div class="bg-grey">
                [#if pagination!]
            [@mc.showPagination '/user/agent_recharge?userName=${command.userName!}&isSuccess=${command.isSuccess!}&startDate=${command.startDate}&endDate=${command.endDate}' /]
        [/#if]
            </div>
        </div>

    </div>
</div>
    [#include 'shared/confirmation.ftl'/]
[/@override]
[@extends name="/decorator.ftl"/]