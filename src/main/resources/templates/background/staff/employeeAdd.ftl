<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
    <form id = "staffAddId">
        <table border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc" bgcolor="#E0EBF1">
            <tr><td colspan="4" style="font-size:24px; text-align:center">添加员工信息</td></tr>
            <input type="hidden" name="staffId"/>
            <tr>
                <th>姓名</th><td><input type="text" name="staffName" required/><span class="star">*</span></td>
            </tr
            ><tr>
            <th >性别</th>
            <td><input type="radio" name="staffSex"value="1"/>男
                <input type="radio" name="staffSex" value="2"/>女
            </td>
            <th>联系方式</th><td><input type="text" name="staffPhone" onkeypress="IsNum(event)"/></td>
        </tr
        ><tr>
            <th>身份证号</th><td><input type="text" name="staffCard" onkeypress="IsNum(event)"/></td>
            <th>出生日期</th>
            <td><input class="easyui-datebox" name="staffBirthday" id="demo1" maxlength="25" size="25"/>
                <img  onclick="javascript:NewCssCal('demo1','yyyyMMdd')" style="cursor:pointer"/></td>
        </tr>
            <tr>
                <th>家庭住址</th><td colspan="3"><input class="input1" type="text" name="staffAddress"/></td>
               </tr>
            <tr>
                <th>所属分店</th><td>
                <select name="staffSubbranch">
                    <option value="0">请选择</option>
                    <option value="1">分店1</option>
                    <option value="2">分店2</option>
                    <option value="3">分店3</option>
                </select><span class="star">*</span></td>
                <th>加入时间</th><td><input class="easyui-datebox" name="staffDate" id="demo2" maxlength="25" size="25"/>
                <img  onclick="javascript:NewCssCal('demo2','yyyyMMdd')" style="cursor:pointer"/></td>
            </tr>
            <tr>
                <th>职位</th><td><select name="staffPost">
                <option value="0">请选择</option>
                <option value="1">总经理</option>
                <option value="2">一般职员</option>
                <option value="3">管理员</option>
            </select></td>
                <th>权限</th><td><select name="staffShiro">
                <option value="1">一般员工</option>
                <option value="2">管理员</option>
            </select></td>
            </tr>
            <tr align="center">
                <td colspan="2"><input type="button" value="提交" onclick="staffAdds()"/></td>
                <td colspan="2"><input type="reset" value="重置"/></td>
            </tr>
        </table>
    </form>
</div>
<script>

    function staffAdds() {
        $.ajax({
            type:'get',
            url:'/staff/addStaff',
            data:$("#staffAddId").serialize(),
            dataType:'json',
            success:function (data) {
                if(data == 1){
                    alert("保存成功");
                    location.href="/skip/toStaffMain";
                }else{
                    alert("保存失败");
                }
            }
        });
    }

</script>
</body>
</html>
