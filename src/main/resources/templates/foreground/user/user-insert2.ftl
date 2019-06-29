<html>
<head>
    <title>用户信息添加页面</title>
    <script src="/commons/js/jquery-2.1.1.js"></script>
</head>
<body>

    <form id="add_user">
        <table border="1px" bgcolor="#a9a9a9" width="100%">
            <tr>
                <td colspan="4" height="60px" align="center"><font size="5">用户信息添加页面2</font></td>
            </tr>
            <tr>
                <td>用户名</td>
                <td><input class="easyui-validatebox" type="text" name="userName" data-options="required:true" placeholder="此字段必填"/></td>
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
            <tr>
                <td>用户头像</td>
                <td colspan="3">
                    <div id="uploader-demo">
                        <div id="fileList" class="uploader-list"></div><!--用来回显图片的div-->
                        <div id="filePicker">选择图片</div>
                        <input type="text" id="imgUrl" name="userUrl"/><!-- name改成实体对象的url  -->
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="button" value="保存" id="sub"/>
                </td>
                <td colspan="2" align="center">
                    <input type="reset" value="重置"/>
                </td>
            </tr>
        </table>
    </form>
        <#include "userUpload.ftl">
    <script>
        $("#sub").click(function () {
            $.ajax({
                type:'post',
                url:'/user/addUser',
                data:$("#add_user").serialize(),
                success:function () {
                    var boo = confirm("是否跳转到展示页面?");
                    if(boo){
                        openTabs('查看用户信息','/skip/toUserList');
                    }
                },
                error:function () {
                    alert("添加异常!!!")
                }
            })
        })

    </script>
</body>
</html>