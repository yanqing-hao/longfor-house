<html>
<head>
    <title>main</title>
    <#include "${request.contextPath}/commons/import.ftl">
    <style type="text/css">
        li a:link{font-size:20px;color:#0000FF}
        li a:visited{font-size:20px;color:#0000FF}
        li a:hover{font-size:20px;color:red}
        li a:active{font-size:20px;color:#0000FF}
    </style>
</head>
<body class="easyui-layout">
    <div data-options="region:'north',split:false,border:false" style="height:150px;color:#000000;font-size:20px;background: url('')">
        <sapn id="welcome" style="float: left;"></sapn>
        <a href="/staffLogin/logout" style="float: right;">注销</a>
        <center><h1 style="clear: both;">龙湖房地产管理系统</h1></center>
        <sapn id="time" style="float: right;"></sapn>
    </div>
    <div data-options="region:'west',title:'导航菜单栏',split:false,collapsible:false" style="clear: both;width:200px;">
        <div id="song-accordion" class="easyui-accordion" style="width:300px;height:200px;" fit="true">
            <div title="房源管理模块" data-options="iconCls:'icon-large-clipart'" style="overflow:auto;padding:10px;background: url('')">
                <ul style="list-style:none;padding-left:10px;padding-top:-20px">
                    <li><a href="javascript:openTabs('发布租赁房源','/skip/toInsertLeaseHouse');">发布租赁房源</a></li>
                    <li><a href="javascript:openTabs('发布销售房源','/skip/toInsertSellHouse');">发布销售房源</a></li>
                    <li><a href="javascript:openTabs('房源列表','/skip/toHouseList');">查看房源列表</a></li>
                </ul>
            </div>
            <div title="销售管理模块" data-options="iconCls:'icon-large-smartart'" style="padding:10px;background: url('')">
                <ul style="list-style:none;padding-left:10px;padding-top:-20px">
                    <li><a href="javascript:openTabs('添加销售信息','/skip/toSellInsertTow');">添加销售信息</a></li>
                    <li><a href="javascript:openTabs('销售记录','/skip/toSellList');">销售记录</a></li>
                </ul>
            </div>
            <div title="租赁管理模块" data-options="iconCls:'icon-large-shapes'" style="overflow:auto;background: url('')">
                <ul style="list-style:none;padding-left:10px;padding-top:-20px">
                    <li><a href="javascript:openTabs('添加租赁信息','/skip/toLeaseInsert');">添加租赁信息</a></li>
                    <li><a href="javascript:openTabs('租赁记录','/skip/toLeaseList');">租赁记录</a></li>
                </ul>
            </div>
            <div title="用户管理模块" data-options="iconCls:'icon-large-shapes'" style="overflow:auto;background: url('')">
                <ul style="list-style:none;padding-left:10px;padding-top:-20px">
                    <li><a href="javascript:openSave('添加用户信息','/skip/toUserInsert');">添加用户信息</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div data-options="region:'center'">
        <div id="tt" class="easyui-tabs" fit=true>
            <div title="首页" style="background-color: black;background: url('/background/images/6.jpg')">
            </div>
        </div>
    </div>
</body>
<script>
    //当前时间
    function timeUpdate() {
        var date=new Date();
        var year=date.getFullYear();
        var month=date.getMonth()+1;
        var day=date.getDate();
        var hour="00"+date.getHours();
        hour=hour.substr(hour.length-2);
        var minute="00"+date.getMinutes();
        minute=minute.substr(minute.length-2);
        var second="00"+date.getSeconds();
        second=second.substr(second.length-2);
        $("#time").html(year+"-"+month+"-"+day+" "+" "+hour+":"+minute+":"+second);
    }

    //页面加载函数
    $(function(){
        //注销

        //时间更新
        setInterval("timeUpdate()",1000); //设置过1000毫秒就是1秒，调用show方法

        //获取redis中的数据
        $.get("/redis/getStaff",function(data){
            if(data!=null&&data!=undefined&&data!=""){
                $("#welcome").html("欢迎<font color='red'>"+data.staffName+"</font>登录龙湖管理系统");
            }
        });
    })

    //页面卸载函数
    window.onunload = function(){
        //清除redis缓存
        $.get("/redis/clearStaff",function(data){
            $("#welcome").html("清除");
        });
    }
</script>
<script>
    //创建选项卡
    function openTabs(title,url){
        //判断是否存在
        var tab = $('#tt').tabs('exists',title);
        if(tab){
            //选项卡存在则选择该选项卡
            $('#tt').tabs('select',title);
            return;
        }
        //新增选项卡
        $('#tt').tabs('add',{
            title:title,
            href:url,
            closable:true,
            tools:[{
                iconCls:'icon-reload',
                handler:function(){
                    var tab = $('#tt').tabs('getSelected',title);
                    tab.panel('refresh',url);
                }
            }]
        });
    }
</script>
</html>