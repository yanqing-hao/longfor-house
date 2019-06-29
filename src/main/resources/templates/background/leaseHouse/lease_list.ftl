<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>展示页面</title>
</head>
<body>
<table id="dg-lease" class="easyui-datagrid"
       style="width: 400px; height: 250px"
       data-options="toolbar:'#tb',
	fit:true,fitColumns:true,singleSelect:false,pagination:true,pageSize
	:3,pageList:[3,6,9],url:'/leasehouser/leaseList'">
    <thead>
    <tr>
    <#--    <th data-options="width:'15%'"><input type="checkbox" name="checkbox"/></th>-->
        <th data-options="field:'leaseId',width:'100'">编号</th>
       <th data-options="field:'houseId',width:'100'">房源编号</th>
        <th data-options="field:'staffId',width:'100'">员工编号</th>
        <th data-options="field:'userPhone',width:'100'">用户电话</th>
        <th data-options="field:'userName',width:'100'">用户姓名</th>
        <th data-options="field:'leaseAgency',width:'100'">中介费</th>
        <th data-options="field:'leaseRent',width:'100'">租金</th>
        <th data-options="field:'startTime',width:'100',formatter:dateFmt">开始日期</th>
        <th data-options="field:'endTime',width:'100',formatter:dateFmt">结束日期</th>
    <#--  <th data-options="field:'pm_date',width:'100',formatter:dateFmt">操作</th>-->
    </tr>
    </thead>
</table>
<div id="tb">

    <div>
        房源编号: <input id="houseId" name="houseId"
                   style="width: 80px" />
        开始时间:<input id="startTime" name="startTime" type="date"
                     style="width: 150px" />
        结束时间: <input id="endTime" name="endTime" type="date"
                     style="width: 150px" />
        <a href="javascript:searchCondition();"
           class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </div>

        <a
            href="javascript:toUpdate();" class="easyui-linkbutton"
            data-options="iconCls:'icon-edit',plain:true">修改</a>
        <a
            href="javascript:deleteBy();" class="easyui-linkbutton"
            data-options="iconCls:'icon-no',plain:true">删除</a>
        <a
            href="javascript:deriveCondition();" class="easyui-linkbutton"
            data-options="iconCls:'icon-save',plain:true">导出</a>
        <a
            href="javascript:deleteBy1();" class="easyui-linkbutton"
            data-options="iconCls:'icon-edit',plain:true">全选</a>
        <a
            href="javascript:deleteBy1();" class="easyui-linkbutton"
            data-options="iconCls:'icon-edit',plain:true">反选</a>
        <a
            href="javascript:particulars();" class="easyui-linkbutton"
            data-options="iconCls:'icon-edit',plain:true">详情</a>
</div>

<#--dialog 弹窗-->
<div id="dd"></div>

<script type="text/javascript">
    //逻辑删除
    function deleteBy(){
        //id 传到后台  修改状态
        var tempData = $("#dg-lease").datagrid('getSelected');
        $.ajax({
            url: '/leasehouser/leaseDelete',
            data:{leaseId:tempData.leaseId},
            type:"get",
            success:function(){
                alert("删除成功");
                //刷新表格
                $("#dg-lease").datagrid("reload");
            },
            error:function(){
                alert("删除错误");

            }
        })

    }

    //修改方法
    function updateInfo(){
        //通过ajax  吧修改的数据传到后台
        $.ajax({
            type:'get',
            url:'/leasehouser/leaseUpdate',
            data:$("#lease-form-update").serialize(),
            dataType:'json',
            success:function (data) {
                //成功后进行提示
                alert("修改成功");
                //关闭dialog 窗口
                closeDd();
                //刷新当前表单
                $("#dg-lease").datagrid("reload");
            },error:function () {
                alert("修改失败");
            }
        });
    }
    //回显方法
    function toUpdate(){
        //获取相中一行的id
        var tempData = $("#dg-lease").datagrid('getSelected');

        //通过ajax  获取回显页面
        $.ajax({
            type:'get',
            url:"/leasehouser/leaaseEcho?leaseId="+tempData.leaseId,
            dataType:'html',
            success:function (data) {
                //将回显页面放入dialog中
                $("#dd").dialog({
                    title: '修改',
                    width: "400px",
                    height: '300px',
                    content: data,
                })
            }
        });

    }

    //关闭dialog 窗口
    function closeDd(){
        $("#dd").dialog('close');

    }
    function toInsert(){
        $("#ddd").dialog('open');
    }
    function insertId(){
        $("#addForm").form('submit',{
            url:'/leasehouser/leaaseEcho',
            onSubmit: function(){
                var isValid = $(this).form('validate');
            },
            success: function(data){
                $("#ddd").dialog("close");
                $("#dg-lease").datagrid("reload");
            }
        })
    }
    /*条件查询*/
    /*var tName = $("#houseId").val();   houseId这个是对应的是上面的id */

    function searchCondition() {
        var houseId = $("#houseId").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();

        // load 重新加载表格    相当于
        $('#dg-lease').datagrid('load',{
            houseId: houseId,
         // 后台属性  ： 值
            startTime: startTime,
            endTime:endTime
        });

    }
/*导出表*/
    function deriveCondition() {
        var houseId = $("#houseId").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();

        location.href="/leasehouser/exportExcel?"
								+"houseId="+houseId+"&startTime="+startTime+"&endTime="+endTime;
        }




    //详情方法
    function particulars(){
        //获取相中一行的id
        var tempData = $("#dg-lease").datagrid('getSelected').leaseId;
        location.href="/leasehouser/leaaseParticulars?leaseId="+tempData;

    }



    
    function typeFmt(value,rows,index){
        if(value==0){
            return "隐藏";
        }
        if(value==1){
            return "展示";
        }

    }
    /*导出表格*/

    function derive(){






    }
    /* 时间 */
    function dateFmt(value,rows,index){
        return showDate(value,1);
    }
    function showDate(dateValue,status){

        var date = new Date(dateValue);
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var day = date.getDate();
        var newDate="";
        if(status==1){
            newDate = year+"-"+month+"-"+day;
        }
        if(status==2){
            newDate = year+"年"+month+"月"+day+"日";
        }
        return newDate;
    }

    function searchCondition1(){

        $('#dg-lease').datagrid('load',{
            startTime:$("#startTime").val(),
            endTime:$("#endTime").val(),
        })
    }
</script>
</body>
</html>