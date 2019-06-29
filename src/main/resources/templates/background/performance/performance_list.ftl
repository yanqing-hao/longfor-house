<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>展示页面</title>
</head>
<body>
<table id="dg-dg-lease" class="easyui-datagrid"
       style="width: 400px; height: 250px"
       data-options="toolbar:'#tb',
	fit:true,fitColumns:true,singleSelect:false,pagination:true,pageSize
	:3,pageList:[3,6,9],url:'/performanceController/performanceList'">
    <thead>
    <tr>
        <th data-options="field:'staffId',width:'100'">员工编号</th>
        <th data-options="field:'staffName',width:'100'">员工姓名</th>
        <th data-options="field:'perSellCount',width:'100'">销售单数</th>
        <th data-options="field:'perSellPrice',width:'100'">总销售额</th>
        <th data-options="field:'perSellAgency',width:'100'">销售中介费总额</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <a href="javascript:toInsert();" class="easyui-linkbutton"
       data-options="iconCls:'icon-edit',plain:true">导出</a>

    <div>
        员工编号: <input id="staffId" name="staffId"
                    />
        员工姓名: <input id="staffName" name="staffName"
                     />
        <a href="javascript:searchCondition2();"
           class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a href="javascript:searchCondition();"
           class="easyui-linkbutton" iconCls="icon-search">查询销售业绩</a>
    </div>
</div>

<script type="text/javascript">




    /*条件查询*/
    /*var tName = $("#houseId").val();   houseId这个是对应的是上面的id */

    function searchCondition2() {
        var staffId = $("#staffId").val();
        var staffName = $("#staffName").val();

        // load 重新加载表格    相当于 重新请求到controller
        $('#dg-dg-lease').datagrid('load',{
            staffId: staffId,
            // 后台属性  ： 值
            staffName: staffName

        });

    }


</script>
</body>
</html>