<html>
    <head>
        <title>龙湖后宫</title>
        <link rel="shortcut icon" href="${request.contextPath}/foreground/images/favicon.ico" />
        <link type="text/css" href="${request.contextPath}/foreground/css/css.css" rel="stylesheet" />
        <script type="text/javascript" src="${request.contextPath}/foreground/js/jquery.js"></script>
        <script type="text/javascript" src="${request.contextPath}/foreground/js/js.js"></script>
        <!--dialog-->
        <script type="text/javascript" src="${request.contextPath}/commons/dialog/dialog-plus.js"></script>
        <link rel="stylesheet" href="${request.contextPath}/commons/dialog/ui-dialog.css" />
        <script type="text/javascript">
            $(function(){
                //导航定位
                $(".nav li:eq(0)").addClass("navCur");
            })
        </script>
    </head>
    <body>
        <div><#include "fore-top.ftl"></div> <#--top-->
        <div class="list-nav">
            <div class="width1190">
                <div class="list">
                    <h3>房源分类</h3>
                    <div class="list-list">
                        <dl>
                            <dt><a href="javascript:;">房源区域</a></dt>
                            <dd>
                                <a href="javascript:;">智慧园</a>
                                <a href="javascript:;">立民村</a>
                                <a href="javascript:;">塘口村</a>
                                <a href="javascript:;">勤劳村</a>

                                <a href="javascript:;">芦胜村</a>
                                <a href="javascript:;">知新村</a>
                            </dd>
                        </dl>
                        <dl>
                            <dt><a href="#">租房</a></dt>
                            <dd>
                                <a href="#">租金</a>
                                <a href="#">出租方式</a>
                                <a href="#">面积</a>
                                <a href="#">房型</a>
                            </dd>
                        </dl>
                        <dl>
                            <dt><a href="#">新房</a></dt>
                            <dd>
                                <a href="#">价格</a>
                                <a href="#">面积</a>
                                <a href="#">房型</a>
                            </dd>
                        </dl>
                        <dl>
                            <dt><a href="#">二手房</a></dt>
                            <dd>
                                <a href="#">价格</a>
                                <a href="#">面积</a>
                                <a href="#">房型</a>
                            </dd>
                        </dl>
                    </div>
                </div><!--list/-->
                <ul class="nav">
                    <li><a href="/skip/toForeHome">首页</a></li>
                    <li><a href="javascript:openFtl('toForeLeaseHouse')">租房</a></li>
                    <li><a href="javascript:openFtl('toForeNewSellHouse')">新房</a></li>
                    <li><a href="javascript:openFtl('toForeOldSellHouse')">二手房</a></li>
                    <li class="zhiding"><a href="javascript:;">指定购房</a></li>
                    <li><a href="#">申请自由经纪人</a></li>
                    <li><a href="#">关于我们</a></li>
                    <div class="clears"></div>
                </ul><!--nav/-->
                <div class="clears"></div>
            </div><!--width1190/-->
        </div><!--list-nav/-->
        <div class="banner" style="background:url(${request.contextPath}/foreground/images/ban1.jpg) center center no-repeat;"></div>

        <div id="joinContent">
            <div class="content">
                <div class="width1190">
                <#--租房-->
                    <h2 class="title">租房 <a href="javascript:openFtl('toForeLeaseHouse')">更多&gt;&gt;</a></h2>
                    <div id="leaseHouseId" class="index-fang-list"></div><!--index-fang-list/-->
                <#--新房-->
                    <h2 class="title">新房 <a href="javascript:openFtl('toForeNewSellHouse')">更多&gt;&gt;</a></h2>
                    <div id="newHouseId" class="index-fang-list"></div><!--index-fang-list/-->
                <#--二手房-->
                    <h2 class="title">二手房 <a href="javascript:openFtl('toForeOldSellHouse')">更多&gt;&gt;</a></h2>
                    <div class="index-ershou">
                        <div class="in-er-left">
                            <a href="proinfo.html"><img src="${request.contextPath}/foreground/images/fangt1.jpg" width="380" height="285" /></a>
                            <div class="in-er-left-text"><strong class="fl">闵行南方发的撒的发的司法</strong><strong class="fr alignRight">¥2841</strong></div>
                        </div><!--in-er-left/-->
                        <div id="oldHouseRight" class="in-er-right"></div><!--in-er-right/-->
                        <div class="clears"></div>
                    </div><!--index-ershou/-->
                </div><!--width1190/-->
            </div>
        </div>  <#--引入其他页面（租赁、新房、二手... ...）-->

        <div><#include "fore-bottom.ftl"></div> <#--bottom-->

    <script>

        //注销
        function logout(){
            //清除redis缓存
            $.get("/redis/clearUser",function(){
                location.href = location;
            });
        }

        $(function(){
            //获取redis中的数据
            $.get("/redis/getUser",function(data){
                if(data.userPhone!=null){
                    $("#welcome").html("欢迎<font color='red'>"+data.userPhone+"</font>登录");
                }else{

                }
            });
        })
        //页面卸载函数
        window.onunload = function(){
            //清除redis缓存
            $.get("/redis/clearUser",function(){});
        }
    </script>
    <script>
        /*租房*/
        leaseHouse();
        function leaseHouse(){
            $.ajax({
                url:"/foreHouse/queryLeaseHouseList",
                type:'get',
                dataType:'html',
                success:function(data){
                    $("#leaseHouseId").html(data);
                },
                error:function(){
                    console.info("引入页面失败")
                }
            });
        }
        /*新房*/
        newHouse();
        function newHouse(){
            $.ajax({
                url:"/foreHouse/queryNewHouseList",
                type:'get',
                dataType:'html',
                success:function(data){
                    $("#newHouseId").html(data);
                },
                error:function(){
                    console.info("引入页面失败")
                }
            });
        }
        /*二手房*/
        oldHouse();
        function oldHouse(){
            $.ajax({
                url:"/foreHouse/queryErHouseList",
                type:'get',
                dataType:'html',
                success:function(data){
                    $("#oldHouseRight").html(data);
                },
                error:function(){
                    console.info("引入页面失败")
                }
            });
        }


        /*映入相应的页面*/
        function openFtl(url){
            $.ajax({
                url:url,
                type:'get',
                dataType:'html',
                success:function(data){
                    $("#joinContent").html(data);
                },
                error:function(){
                    console.info("引入页面失败")
                }
            });
        }
    </script>
    </body>
</html>