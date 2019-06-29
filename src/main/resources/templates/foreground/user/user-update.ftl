<html>
<head>
    <title>用户信息修改页面</title>
</head>
<body>

<form id="up_user">
    <table border="1px" bgcolor="#a9a9a9" width="100%">
        <input type="hidden" name="userId"/>
        <tr>
            <td colspan="4" height="60px" align="center"><font size="5">用户信息修改页面</font></td>
        </tr>
        <tr>
            <td>用户名</td>
            <td><input class="easyui-validatebox" type="text" name="userName" data-options="required:true" readonly="true"/></td>
            <td>性别</td>
            <td>
                <input class="easyui-validatebox" type="radio" name="userSex" value="1"/>男
                <input class="easyui-validatebox" type="radio" name="userSex" value="2"/>女
            </td>
        </tr>
        <tr>
            <td>出身日期</td>
            <td><input class="easyui-validatebox" type="date" name="userBirthday"/></td>
            <td>身份证号</td>
            <td><input class="easyui-validatebox" type="text" name="userCard" data-options="required:true"/></td>
        </tr>
        <tr>
            <td>银行卡号</td>
            <td><input class="easyui-validatebox" type="text" name="userBank" data-options="required:true"/></td>
            <td>联系电话</td>
            <td><input class="easyui-validatebox" type="text" name="userPhone" data-options="required:true"/></td>
        </tr>
        <tr>
            <td>备注</td>
            <td colspan="3">
                        <textarea name="userRemark" cols="70px" rows="15px">

                        </textarea>
            </td>
        </tr>
        tr>
        <td>用户头像</td>
        <td colspan="3">

        </td>
        </tr>
    </table>
</form>
    <script>
        $(function(){
            if(rowFirst != null && rowFirst != undefined){
                $("#up_user").form('load','/user/selUserByid?usId='+rowFirst.userId);
            }
        })
    </script>
</body>
</html>