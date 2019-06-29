<html>
<head>
    <title>main</title>
<#include "../../commons/import.ftl">
    <style type="text/css">
        li a:link{font-size:20px;color:#0000FF}
        li a:visited{font-size:20px;color:#0000FF}
        li a:hover{font-size:20px;color:red}
        li a:active{font-size:20px;color:#0000FF}
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'north',split:false,border:false" style="height:150px;color:#bf10dd;font-size:20px;">
    <sapn id="welcomeStaff" style="float: left;"></sapn>
    <a href="/staffLogin/logout" style="float: right;">注销</a>
    <center><h1 style="clear: both;">欢迎来到员工管理平台</h1></center>
    <sapn id="timeStaff" style="float: right;"></sapn>
</div>
<div data-options="region:'west',title:'导航菜单栏',split:false,collapsible:false" style="width:200px;">
    <div id="song-accordion" class="easyui-accordion" style="width:300px;height:200px;" fit="true">
        <div title="员工管理" data-options="iconCls:'icon-large-clipart'" style="overflow:auto;padding:10px;">
            <ul style="list-style:none;padding-left:10px;padding-top:-20px">
                <li><a href="javascript:openTabs('添加员工信息','/skip/toAddStaff');">添加员工信息</a></li>
                <li><a href="javascript:openTabs('员工信息','/skip/staffList');">员工信息</a></li>
                <li><a href="javascript:openTabs('员工销售业绩信息','/skip/toStaffLists');">员工销售业绩查看</a></li>
                <li><a href="javascript:openTabs('员工租赁业绩信息','/skip/toStaffLists2');">员工租赁业绩查看</a></li>
            </ul>
        </div>
        <div title="用户管理" data-options="iconCls:'icon-large-smartart'" style="padding:10px;">
            <ul style="list-style:none;padding-left:10px;padding-top:-20px">
                <li><a href="javascript:openTabs('添加用户信息','/skip/toUserInsertTow');">添加用户信息</a></li>
                <li><a href="javascript:openTabs('查看用户信息','/skip/toUserList');">查看用户信息</a></li>
            </ul>
        </div>
    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" fit=true>
        <div title="首页" style="background-color: black;background: url('/background/images/3.jpg')">
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
        $("#timeStaff").html(year+"-"+month+"-"+day+" "+" "+hour+":"+minute+":"+second);
    }

    //页面加载函数
    $(function(){
        //注销

        //时间更新
        setInterval("timeUpdate()",1000); //设置过1000毫秒就是1秒，调用show方法

        //获取redis中的数据
        $.get("/redis/getStaff",function(data){
            if(data!=null&&data!=undefined&&data!=""){
                $("#welcomeStaff").html("欢迎<font color='red'>"+data.staffName+"</font>登录龙湖管理系统");
            }
        });
    })

    //页面卸载函数
    window.onunload = function(){
        //清除redis缓存
        $.get("/redis/clearStaff",function(data){
            $("#welcomeStaff").html("清除");
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