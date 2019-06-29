<form id="ffLeaseHouse" method="post">
    <input type="hidden" name="houseMethod"/>
    <table cellpadding="3px" border="3" cellspacing="0" width="900px">
        <tr>
            <td align="right">标题:</td>
            <td><input class="easyui-validatebox" type="text" name="houseTitle" data-options="required:true" /></td>
            <td align="right">房屋面积:</td>
            <td><input class="easyui-validatebox" type="text" name="staffArea" data-options="required:true" /></td>
            <td align="right">小区名称:</td>
            <td><input class="easyui-validatebox" type="text" name="houseName"/></td>
        </tr>
        <tr>
            <td align="right">小区地址:</td>
            <td><input class="easyui-validatebox" type="text" name="houseAddr" data-options="required:true" /></td>
            <td align="right">室厅卫数:</td>
            <td>
                <select name="houseCount" class="easyui-combobox">
                    <option value="0">-请选择-</option>
                    <option value="1">一室一厅</option>
                    <option value="2">一室一厅一卫</option>
                    <option value="3">两室一厅一卫</option>
                    <option value="4">三室一厅一卫</option>
                    <option value="5">三室以上</option>
                    <option value="6">单间</option>
                </select>
            </td>
            <td align="right">所在楼层:</td>
            <td><input class="easyui-validatebox" type="text" name="houseFloor" data-options="required:true" /></td>
        </tr>
        <tr>
            <td align="right">总楼层:</td>
            <td><input class="easyui-validatebox" type="text" name="houseSumFloor"/></td>
            <td align="right">装修情况:</td>
            <td>
                <label><input type="radio" name="houseCase" value="0" checked hidden/></label>
                <label><input type="radio" name="houseCase" value="1"/>清水</label>
                <label><input type="radio" name="houseCase" value="2"/>精装</label>
                <label><input type="radio" name="houseCase" value="3"/>简装</label>
            </td>
            <td align="right">建筑类型:</td>
            <td>
                <label><input type="radio" name="houseBuild" value="0" checked hidden/></label>
                <label><input type="radio" name="houseBuild" value="1"/>普通住宅</label>
                <label><input type="radio" name="houseBuild" value="2"/>宿舍</label></br>
                <label><input type="radio" name="houseBuild" value="3"/>公寓</label>
                <label><input type="radio" name="houseBuild" value="4"/>别墅</label>
            </td>
        </tr>
        <tr>
            <td align="right">房屋朝向:</td>
            <td><input class="easyui-validatebox" type="text" name="houseDirection" data-options="required:true" /></td>
            <td align="right">行政区市:</td>
            <td><input class="easyui-validatebox" type="text" name="houseRegion" data-options="required:true" /></td>
            <td align="right">房屋配置:</td>
            <td>
                <label><input type="checkbox" name="houseConfigure" value="0" checked hidden/></label>
                <label><input type="checkbox" name="houseConfigure" value="1"/>电视</label>
                <label><input type="checkbox" name="houseConfigure" value="2"/>洗衣机</label>
                <label><input type="checkbox" name="houseConfigure" value="3"/>空调</label>
                <label><input type="checkbox" name="houseConfigure" value="4"/>沙发</label></br>
                <label><input type="checkbox" name="houseConfigure" value="5"/>衣柜</label>
                <label><input type="checkbox" name="houseConfigure" value="6"/>天然气</label>
                <label><input type="checkbox" name="houseConfigure" value="7"/>宽带</label>
                <label><input type="checkbox" name="houseConfigure" value="8"/>热水器</label></br>
                <label><input type="checkbox" name="houseConfigure" value="9"/>消毒柜</label>
                <label><input type="checkbox" name="houseConfigure" value="10"/>冰箱</label>
                <label><input type="checkbox" name="houseConfigure" value="11"/>油烟机</label>
            </td>
        </tr>
        <tr>
            <td align="right">出租方式:</td>
            <td>
                <label><input type="radio" name="houseLeaseType" value="1"/>押一付三</label>
                <label><input type="radio" name="houseLeaseType" value="2"/>押一付一</label></br>
                <label><input type="radio" name="houseLeaseType" value="3"/>押二付三</label>
            </td>
            <td align="right">出租价格:</td>
            <td><input class="easyui-validatebox" type="text" name="houseLeasePrice" data-options="required:true" /></td>
            <td align="right">房屋状态:</td>
            <td>
                <label><input type="radio" name="houseStatus" value="0"/>未出租</label>
                <label><input type="radio" name="houseStatus" value="1"/>已出租</label>
            </td>
        </tr>
        <tr>
            <td align="right">备注:</td>
            <td><input class="easyui-validatebox" type="text" name="houseRemark"/></td>
            <td align="right">简介:</td>
            <td colspan="3"><input class="easyui-validatebox" type="text" name="houseAbout"/></td>
        </tr>
        <tr>
            <td align="right">上传图片：</td>
            <td colspan="5">
                <div id="uploader-demo">
                    <img id="img" name="imgs">
                    <div id="fileList" class="uploader-list"></div><!--用来回显图片的div-->
                    <div id="filePicker">选择图片</div>
                    <#--<input type="hidden" id="imgUrl" name="picPath"/><!-- name改成实体对象的url  &ndash;&gt;-->
                    <div id="imgUrls"></div>
                </div>
            </td>
        </tr>
        <tr>
            <th colspan="6">
                <input type="button" id="leaseHouseSubmit" value="提交">
                <input type="reset" value="重置">
            </th>
        </tr>
    </table>
</form>
<#include "upload.ftl">
<script>
    $("#leaseHouseSubmit").click(function(){ //绑定点击事件
        $.messager.progress();	// 显示进度条
        $('#ffLeaseHouse').form('submit', {
            onSubmit: function(){
                var isValid = $(this).form('validate');
                if (!isValid){
                    $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                }
                return isValid;	// 返回false终止表单提交
            },
            url: "/hlease/insert",
            success: function(data){
                data = eval("("+data+")");
                $.messager.progress('close');	// 如果提交成功则隐藏进度条
                $.messager.show({
                    title:'我的消息',
                    msg:data.info,
                    timeout:3000,
                    showType:'slide'
                });
                $.messager.confirm('确认','去房源列表页面？',function(r){
                    if (r){
                        openTabs('房源列表','/skip/toHouseList')
                        $('#tt').tabs('close','发布租赁房源'); //关闭当前页
                    }
                });
                $('#dgHouse').datagrid('reload');//刷新DataGrid
                $("#ffLeaseHouse").form("reset")//重置表单
            }
        });
    })
</script>