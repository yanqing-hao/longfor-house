<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>展示页面</title>
</head>
<body>
<table id="dg-dg-sell" class="easyui-datagrid"
       style="width: 400px; height: 250px"
       data-options="toolbar:'#tb2',
	fit:true,fitColumns:true,singleSelect:false,pagination:true,pageSize
	:3,pageList:[3,6,9],url:'/performanceController/performanceList2'">
    <thead>
    <tr>
        <th data-options="field:'staffId',width:'100'">员工编号</th>
        <th data-options="field:'staffName',width:'100'">员工姓名</th>
        <th data-options="field:'perLeaseCount',width:'100'">租赁单数</th>
        <th data-options="field:'perLeasePrice',width:'100'">租赁中介费总额</th>
    </tr>
    </thead>
</table>
<div id="tb2">
    <a href="javascript:toInsert();" class="easyui-linkbutton"
       data-options="iconCls:'icon-edit',plain:true">导出</a>

    <div>
        员工编号: <input id="staffId2" name="staffId"
                    />
        员工姓名: <input id="staffName2" name="staffName"
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
        var staffId = $("#staffId2").val();
        var staffName = $("#staffName2").val();

        // load 重新加载表格    相当于 重新请求到controller
        $('#dg-dg-sell').datagrid('load',{
            staffId: staffId,
            // 后台属性  ： 值
            staffName: staffName

        });

    }


</script>
</body>
</html>