[@override name="title"]充值满送管理[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a onclick="window.location.href='/logged'" href="javascript:void(0)">首页</a></li>
    <li>充值满送管理</li>
</ul>
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row margin-md">
    <a href="/recharge_give/create" class="btn btn-md btn-success">新增充值满送</a>
</div>
<div class="smart-widget widget-green">
    <div class="smart-widget-header">
        <span class="smart-widget-option">
            [#--<span class="refresh-icon-animated" style="display: none;"><i--]
            [#--class="fa fa-circle-o-notch fa-spin"></i></span>--]
                <a href="#" class="widget-toggle-hidden-option"><i class="fa fa-cog"></i></a>
            <a href="#" class="widget-collapse-option" data-toggle="collapse"><i class="fa fa-chevron-up"></i></a>
            [#--<a href="#" class="widget-refresh-option"><i class="fa fa-refresh"></i></a>--]
            <a href="#" class="widget-remove-option"><i class="fa fa-times"></i></a>
        </span>
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
                        <th>满送金额</th>
                        <th>赠送金额</th>
                        <th>创建时间</th>
                        <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            [#if pagination.data??]
                                [#list pagination.data as give ]
                                <tr>
                                    <td>${give.money!}</td>
                                    <td>${give.giveMoney!}</td>
                                    <td>${give.createDate!}</td>
                                    <td>
                                        <a href="[@spring.url '/recharge_give/delete/${give.id!}'/]"
                                           data-toggle="tooltip" data-placement="top" title="点击删除信息">
                                            <span class="label label-primary">删除</span>
                                        </a>
                                    </td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>
                </section>
            </div>
            <div class="bg-grey">
                [#if pagination!]
            [@mc.showPagination '/recharge_give/pagination' /]
        [/#if]
            </div>
        </div>

    </div>
</div>
    [#include 'shared/confirmation.ftl'/]
[/@override]

[@override name="bottomResources"]
    [@super /]
[/@override]
[@extends name="/decorator.ftl"/]