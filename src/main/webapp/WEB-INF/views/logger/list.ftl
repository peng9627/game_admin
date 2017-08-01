[@override name="title"]系统日志[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a onclick="window.location.href='/logged'" href="javascript:void(0)">首页</a></li>
    <li>系统日志</li>
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
            <div class="form-group">
                <label for="operationUser" class="control-label">操作用户</label>
                <input type="text" class="form-control" id="operationUser" name="operationUser"
                       value="${command.operationUser!}"
                       placeholder="用户名"/>
            </div>
            <div class="form-group">
                <label for="loggerType" class="control-label">日志类型</label>
                <select name="loggerType" id="loggerType" class="form-control">
                    [#assign loggerType = (command.loggerType!)?default("") /]
                    <option value="">所有类型</option>
                    <option value="APP_LOGGER" [@mc.selected loggerType "APP_LOGGER" /]>APP日志</option>
                    <option value="OPERATION" [@mc.selected loggerType "OPERATION" /]>操作日志</option>
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
                            <th>操作用户</th>
                            <th>时间</th>
                            <th>日志类型</th>
                            <th>日志内容</th>
                            <th>ip</th>
                        </tr>
                        </thead>
                        <tbody>
                            [#if pagination.data??]
                                [#list pagination.data as command ]
                                <tr>
                                    <td>${command.userName!}</td>
                                    <td>${command.createDate!}</td>
                                    <td>${command.loggerType.getName()!}</td>
                                    <td>${command.loggerContent!}</td>
                                    <td>${command.ip!}</td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>
                </section>
            </div>
            <div class="bg-grey">
                [#if pagination!]
            [@mc.showPagination '/logger/pagination?user=${command.userName!}&gameType=${command.loggerType!}startDate=${command.startDate}&endDate=${command.endDate}' /]
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