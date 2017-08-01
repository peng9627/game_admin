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
                    <div style="width: 100px;float: left;text-align: right;">用户名：</div>
                    <div style="float: left;color: #666;">${gameRecord.userNames!}</div>
                </div>
            </div>
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">创建时间：</div>
                    <div style="float: left;color: #666;">[@mc.dateShow gameRecord.createDate/]</div>
                </div>
            </div>
        </div>
        <div class="row" style="line-height: 25px">
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">基础分数：</div>
                    <div style="float: left;color: #666;">${gameRecord.baseScore}</div>
                </div>
            </div>
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">分数：</div>
                    <div style="float: left;color: #666;">${gameRecord.score!}</div>
                </div>
            </div>
        </div>
        <div class="row" style="line-height: 25px">
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">游戏类型：</div>
                    <div style="float: left;color: #666;">
                    [#if gameRecord.gameType=="NIUNIU_GET_BANKER"]牛牛上庄
                    [#elseif gameRecord.gameType=="FIXED_BANKER"]固定庄家
                    [#elseif gameRecord.gameType=="FREE_GET_BANKER"]自由抢庄
                    [#elseif gameRecord.gameType=="OPEN_GET_BANKER"]明牌抢庄
                    [#elseif gameRecord.gameType=="COMPARE"]通比
                    [/#if]
                    </div>
                </div>
            </div>
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">房主：</div>
                    <div style="float: left;color: #666;">${gameRecord.roomOwner!}</div>
                </div>
            </div>
        </div>
        <div class="row" style="line-height: 25px">
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">总局数：</div>
                    <div style="float: left;color: #666;">${gameRecord.totalRound!}</div>
                </div>
            </div>
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">规则：</div>
                    <div style="float: left;color: #666;">${gameRecord.rule!}</div>
                </div>
            </div>
        </div>
        <div class="row" style="line-height: 25px">
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">对子牛：</div>

                    <div style="float: left;color: #666;">
                    [#if gameRecord.doubleBull==true]是[#else]否[/#if]</div>
                </div>
            </div>
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">五花牛：</div>
                    <div style="float: left;color: #666;">[#if gameRecord.spottedBull==true]是[#else]否[/#if]</div>
                </div>
            </div>
        </div>
        <div class="row" style="line-height: 25px">
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">炸弹牛：</div>
                    <div style="float: left;color: #666;">[#if gameRecord.bombBull==true]是[#else]否[/#if]</div>
                </div>
            </div>
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">五小牛：</div>
                    <div style="float: left;color: #666;">[#if gameRecord.smallBull==true]是[#else]否[/#if]</div>
                </div>
            </div>
        </div>
        <div class="row" style="line-height: 25px">
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">闲家推注：</div>
                    <div style="float: left;color: #666;">[#if gameRecord.playerPush==true]是[#else]否[/#if]</div>
                </div>
            </div>
            <div class="col-xs-6">
                <div style="overflow: hidden;width: 100%;">
                    <div style="width: 100px;float: left;text-align: right;">游戏开始后加入：</div>
                    <div style="float: left;color: #666;">[#if gameRecord.startedInto==true]是[#else]否[/#if]</div>
                </div>
            </div>
        </div>

    </div>
</div>
<script src="[@spring.url '/resources/js/jquery.min.js'/]"></script>
<script src="[@spring.url '/resources/js/layer/layer.js'/]"></script>
</body>
</html>