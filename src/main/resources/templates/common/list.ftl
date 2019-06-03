<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../order/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <div class="container-fluid" style="padding-top:5px;margin-bottom:15px;margin-left:20px;font-weight:bold;letter-spacing:2px;">
                            <form action="/api/log/detail" method="get">
                                访问时间：<input type="date" name="date" value="${.now?date}"/>
                                <input type="submit" value="查询" class="test" style="border-radius: 6px;padding-left: 10px;padding-right: 10px;margin-left: 40px;">
                            </form>

                        </div>
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>类名</th>
                            <th>方法名</th>
                            <th>ip</th>
                            <th>请求方法</th>
                            <th>所用时间</th>
                            <th>访问时间</th>
                            <th>异常</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list orderDTOPage.content as LogInfo>
                        <tr>
                            <td>${LogInfo.id}</td>
                            <td>${LogInfo.classname}</td>
                            <td>${LogInfo.methodname}</td>
                            <td>${LogInfo.ip}</td>
                            <td>${LogInfo.requestname}</td>
                            <td>${LogInfo.alltime}</td>
                            <td>${LogInfo.date}</td>
                            <td>
                            <#if LogInfo.exceptions??>
                                ${LogInfo.exceptions}
                            <#else>无
                            </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/api/log/detail?date=${date}&page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..orderDTOPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/api/log/detail?<#if RequestParameters["date"]??>date=${RequestParameters["date"]}&</#if>page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte orderDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/api/log/detail?<#if RequestParameters["date"]??>date=${RequestParameters["date"]}&</#if>page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>

<#--弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>
    </div>
</div>

<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/static/mp3/song.mp3" type="audio/mpeg" />
</audio>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


</body>
</html>