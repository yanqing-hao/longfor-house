<html>
<head>
    <title>销售信息添加页面</title>
    <#--<script type="text/javascript" src="/commons/js/jquery-2.1.1.js"></script>-->
</head>
<body>

    <form id="add_sell">
        <table border="1px" bgcolor="#a9a9a9" width="100%">
            <tr>
                <td colspan="4" height="60px" align="center"><font size="5">添加销售信息</font></td>
            </tr>
            <tr>
                <td>房源编号</td>
                <td>
                    <input id="hoId" name="houseId" class="easyui-combobox" name="dept" required
                           data-options="valueField:'id',textField:'text',url:'/sell/selectHouseId',prompt: '请选择',editable:false" />
                </td>
                <td>员工编号</td>
                <td><input class="easyui-validatebox" type="text" name="staffId" readonly/></td>
            </tr>
            <tr>
                <td>用户姓名</td>
                <td><input class="easyui-validatebox" type="text" name="userName" data-options="required:true"/></td>
                <td>用户电话</td>
                <td><input class="easyui-validatebox" type="text" name="userPhone" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>成交价</td>
                <td><input class="easyui-validatebox" type="text" name="sellPrice" id="pri" data-options="required:true"/>元</td>
                <td>税费</td>
                <td><input class="easyui-validatebox" type="text" name="sellTaxation"  data-options="required:true"/>元</td>
            </tr>
            <tr>
                <td>中介费</td>
                <td><input class="easyui-validatebox" type="text" name="sellAgency" data-options="required:true"/>元</td>
                <td>定金</td>
                <td><input class="easyui-validatebox" type="text" name="sellHandsel" id="han" data-options="required:true"/>元</td>
            </tr>
            <tr>
                <td>首付款</td>
                <td><input class="easyui-validatebox" type="text" name="sellDown" id="dow" data-options="required:true"/>元</td>
                <td>尾款</td>
                <td><input class="easyui-validatebox" type="text" name="sellFinal" id="fin" data-options="required:true,editable:false"/>元</td>
            </tr>
            <tr>
                <td>按揭方式</td>
                <td>
                    <select id="cc" class="easyui-combobox" name="sellMortgage" style="width:200px" panelHeight="auto">
                        <option value="0">请选择</option>
                        <option value="1">公积金贷款</option>
                        <option value="2">商业贷款</option>
                        <option value="3">组合贷款</option>
                    </select>
                </td>
                <td>销售日期</td>
                <td><input class="easyui-validatebox" type="date" name="sellDate" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>备注</td>
                <td colspan="3">
                            <textarea name="sellRemark" cols="70px" rows="15px">
                            </textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="button" value="保存" id="sub_sell"/></td>
                <td colspan="2" align="center"><input type="reset" value="重置"/></td>
            </tr>
        </table>
    </form>
    <script>
        $("#sub_sell").click(function () {
            $.messager.progress();	// 显示进度条
            $('#add_sell').form('submit', {
                onSubmit: function(){
                    var isValid = $(this).form('validate');
                    if (!isValid){
                        $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                    }
                    return isValid;	// 返回false终止表单提交
                },
                url: "/sell/addSell",
                success: function(data){
                   // data = eval("("+data+")");
                    $.messager.progress('close');	// 如果提交成功则隐藏进度条
                    $.messager.show({
                        title:'我的消息',
                        msg:data,
                        timeout:3000,
                        showType:'slide'
                    });
                    $.messager.confirm('确认','去销售列表页面？',function(r){
                        if (r){
                            openTabs('销售记录','/skip/toSellList');
                        }
                    });
                    $('#dg').datagrid('reload');//刷新DataGrid
                    $("#add_sell").form("reset")//重置表单
                }
            });
        })
        $("#fin").click(function () {
            var  fina = $("#pri").val()-$("#han").val()-$("#dow").val();
            $("#fin").val(fina);
        })
    </script>
</body>
</html>