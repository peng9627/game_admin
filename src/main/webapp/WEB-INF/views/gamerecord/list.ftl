[@override name="title"]游戏记录[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a onclick="window.location.href='/logged'" href="javascript:void(0)">首页</a></li>
    <li>游戏记录</li>
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
                <label for="userName" class="control-label">用户名</label>
                <input type="text" class="form-control" id="user" name="user" value="${command.user!}"
                       placeholder="用户名"/>
            </div>
            <div class="form-group">
                <label for="gameType" class="control-label">游戏类型</label>
                <select name="gameType" id="gameType" class="form-control">
                    [#assign gameType = (command.gameType!)?default("") /]
                    <option value="">所有类型</option>
                    <option value="NIUNIU_GET_BANKER" [@mc.selected gameType "NIUNIU_GET_BANKER" /]>牛牛上庄</option>
                    <option value="FIXED_BANKER" [@mc.selected gameType "FIXED_BANKER" /]>固定庄家</option>
                    <option value="FREE_GET_BANKER" [@mc.selected gameType "FREE_GET_BANKER" /]>自由抢庄</option>
                    <option value="OPEN_GET_BANKER" [@mc.selected gameType "OPEN_GET_BANKER" /]>明牌抢庄</option>
                    <option value="COMPARE" [@mc.selected gameType "COMPARE" /]>通比</option>
                </select>
            </div>
            <div class="form-group">
                <label for="roomOwner" class="control-label">房主</label>
                <input type="text" class="form-control" id="roomOwner" name="roomOwner" value="${command.roomOwner!}"
                       placeholder="房主"/>
            </div>
            <div class="form-group">
                <label for="roomNo" class="control-label">桌号</label>
                <input type="text" class="form-control" id="roomNo" name="roomNo" value="${command.roomNo!}"
                       placeholder="桌号"/>
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
                            <th>用户名</th>
                            <th>桌号</th>
                            <th>房主</th>
                            <th>得分</th>
                            <th>游戏开始后加入</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            [#if pagination.data??]
                                [#list pagination.data as command ]
                                <tr>
                                    <td>${command.userNames!}</td>
                                    <td>${command.roomNo!}</td>
                                    <td>${command.roomOwner!}</td>
                                    <td>${command.score!}</td>
                                    [#if command.startedInto==true]
                                        <td class="green">是</td>
                                    [#else]
                                        <td class="red">否</td>
                                    [/#if]
                                    <td>
                                        <a data-xqurl="[@spring.url '/gamerecord/info/${command.id}'/]"
                                           data-toggle="tooltip" data-placement="top" title="点击查看详情"
                                           onclick="chakanxiangqing(this)">
                                            <span class="label label-info">查看</span>
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
            [@mc.showPagination '/gamerecord/pagination?user=${command.user!}&gameType=${command.gameType!}&roomOwner=${command.roomOwner!}' /]
        [/#if]
            </div>
        </div>

    </div>

</div>
    [#include 'shared/confirmation.ftl'/]
[/@override]
[@override name="bottomResources"]
    [@super /]
<script type="text/javascript">
    function chakanxiangqing(e) {
        var url = $(e).attr("data-xqurl");
        layer.open({
            offset: '200px',
            type: 2,
            area: ['75%', '250px'],
            content: url
        });
    }
</script>
[/@override]
[@extends name="/decorator.ftl"/]