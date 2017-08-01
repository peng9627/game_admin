[@override name="title"]封号管理[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a onclick="window.location.href='/logged'" href="javascript:void(0)">首页</a></li>
    <li>封号管理</li>
</ul>
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
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
        <form class="form-inline no-margin">
            <div class="form-group">
                <label for="sealNo" class="control-label">IP/设备</label>
                <input type="text" class="form-control" id="sealNo" name="sealNo" value="${command.sealNo!}"
                       placeholder="IP/设备"/>
            </div>
            <div class="form-group">
                <label for="type" class="control-label">封号方式</label>
                <select name="type" id class="form-control">
                    [#assign type = (command.type!)?default("") /]
                    <option value="">全部</option>
                    <option value="IP" [@mc.selected type "IP" /]>IP</option>
                    <option value="DEVICE" [@mc.selected type "DEVICE" /]>设备</option>
                </select>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-md btn-success">查询</button>
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
                            <th>IP/设备</th>
                            <th>封号创建时间</th>
                            <th>封号方式</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            [#if pagination.data??]
                                [#list pagination.data as seal ]
                                <tr>
                                    <td>${seal.sealNo!}</td>
                                    <td>[@mc.dateShow seal.createDate/]</td>
                                    <td>${(seal.type.getName())!}</td>
                                    <td>
                                        <a href="[@spring.url '/seal/delete/${seal.id!}'/]"
                                           data-toggle="tooltip" data-placement="top" title="点击取消封号">
                                            <span class="label label-info">取消</span>
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
            [@mc.showPagination '/seal/pagination?sealNo=${command.sealNo!}&type=${command.type!}' /]
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