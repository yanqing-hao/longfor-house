<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>添加页面</title>
</head>
<script src="js/datetimepicker_css.js"></script>
<script src="js/validate.js"></script>
<script src="/static/commons/js/jquery-2.1.1.js"></script>
<style type="text/css">
    tr{
        height:30px;
    }

    .input1 {
        width: 300px;
    }
    th{
        width:100px;
        height:30px;
    }

</style>
<link href="css/star.css"  rel="stylesheet" type="text/css"/>
</head>

<body>
<div >
    <form id = "lease-form">
        <table border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc" bgcolor="#E0EBF1">
            <tr>
                <td colspan="4" style="font-size:24px; text-align:center">添加租赁信息</td>
            </tr>
            <tr>
                <th>房源编号</th>
                <td><input type="text" name="houseId" class="easyui-combobox" name="dept" required data-options="valueField:'id',textField:'text',url:'/leasehouser/selectLeaseHouseId',prompt: '请选择',editable:false" /><span class="star" >*</span></td>
                <th>员工编号</th>
                <td><input type="text" name="staffId" disabled/></td>
            </tr>
            <tr>
                <th>租金</th>
                <td><input type="text" name="leaseRent" />元/月</td>
                <th>中介费</th>
                <td><input  type="text" name="leaseAgency"/>元</td>

            </tr>
            <tr>
                <th>用户姓名</th>
                <td><input type="text" name="userName" /></td>
                <th>用户电话</th>
                <td><input  type="text" name="userPhone" id="userPhone" placeholder="请输入11位手机号"/></td>

            </tr>

            <tr>
                <th>开始时间</th>
                <td><input  class="easyui-datebox"  name="startTime" id="demo1"/>
                    <img  onclick="javascript:NewCssCal('demo1','yyyyMMdd')" style="cursor:pointer"/></td>
                <th>结束时间</th>
                <td><input class="easyui-datebox" name="endTime" id="demo1"/>
                    <img  onclick="javascript:NewCssCal('demo1','yyyyMMdd')" style="cursor:pointer"/>
                </td>
            </tr>
            <#--
            <tr>
                <th>开始时间</th>
                <td><input  type="text" name="startTime" id="demo1"/>
                    <img src="images/cal.gif" onclick="javascript:NewCssCal('demo1','yyyyMMdd')" style="cursor:pointer"/></td>
                <th>结束时间</th>
                <td><input type="text" name="endTime" id="demo1"/>
                    <img src="images/cal.gif" onclick="javascript:NewCssCal('demo1','yyyyMMdd')" style="cursor:pointer"/>
                </td>
            </tr-->
            <tr>
                <th>租赁合同扫描件</th>
                <td><input type="file" name="attachment"/>
                </td>
                <th>押金</th>
                <td><input type="text" name="leaseCashPledge" />元</td>
            </tr>
            <th >备注</th>
            <td colspan="3"><textarea name="leaseRemark"  cols="40" rows="4"></textarea></td>
            <tr align="center">
                <td colspan="2"><input type="button" value="提交" onclick="leaseAdd()" /></td><td colspan="2"><input type="reset" value="重置"/></td>  <#--onclick="validate_required(store);"-->
            </tr>
        </table>
    </form>
</div>
<script>
//把数据提交到后台进行新增
    function leaseAdd() {
       var userPhone =$("#userPhone").val();

        if (userPhone.length==11){
            $.ajax({
                type:'get',
                url:'/leasehouser/leaseAdd',
                data:$("#lease-form").serialize(),
                dataType:'json',
                success:function (data) {
                    if(data == 1){
                        alert("保存成功");
                    }else{
                        alert("保存失败");
                    }
                }
            });
        }else {
            alert("请输入11位手机号")
        }


    }


</script>
</body>
</html>