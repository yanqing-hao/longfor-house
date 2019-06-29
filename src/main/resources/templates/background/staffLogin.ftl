<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>登录</title>
    <style type="text/css">
        #login {
            height:200px;
            width: 400px;
            background-color:#B3E4F2;
            margin-top: 100px;
            margin-right: auto;
            margin-bottom: auto;
            margin-left: auto;
            background-image:url(/background/images/login.gif);
            padding-top:100px;
            padding-left:50px;
        }
        td{
            height:40px;
            width:100px;
            text-align: center;
        }
        &ndash;&gt;
    </style>

</head>
<script src="../../commons/js/jquery-2.1.1.js"></script>
<body>
<form>
    <div id="login">
        <table>
            <tr><td>用户名：</td>
                <td>
                    <input type="text" name="staffLoginName"/>
                </td>
            </tr>
            <tr><td>密码：</td>
                <td>
                <input type="text" name="staffLoginPass"/>
                </td>
            </tr>
            <tr><td style="text-align:right"><input type="button" value="登录" onclick="loginAbc()"/></td>
                <td><input type="button" value="重置"/></td>
            </tr>
            <tr>
                <td colspan="2"><span id="span"></span></td>
            </tr>
        </table>
    </div>
</form>
<script>
    function loginAbc() {
        var name = $("input[name='staffLoginName']").val();
        var pass = $("input[name='staffLoginPass']").val();
        if (name!=null){
            $.ajax({
                type:"GET",
                async:false,
                url:"/staffLogin/login",
                data:{loginName:name,loginPass:pass},
                dataType:"json",
                success:function(data) {
                    if (data.code == 200) {
                        //跳转到管理员页面
                        location.href = "/skip/toStaffMain";
                    } else if(data.code == 201){
                        //跳转后台页面
                        location.href = "/skip/toMain";
                    }else{
                        $("#span").html("<font color='red'>"+data.info+"</font>");
                    }
                }

            })
        }else {
            alert("用户不能为空")
        }
    }

</script>
</body>
</html>
