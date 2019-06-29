<html>
    <head>
        <title>详细信息</title>
    </head>
    <body>
        <table border="1px">
            <tr>
            <th colspan="2" bgcolor="#a9a9a9"><font size="5">销售详细信息</font></th>
        </tr>
            <tr>
                <th bgcolor="#a9a9a9">房源编号</th>
                <td>${sell.houseId!""}</td>
            </tr>
            <tr>
                <th bgcolor="#a9a9a9">员工编号</th>
                <td>${sell.staffId!""}</td>
            </tr>
            <tr>
                <th bgcolor="#a9a9a9">用户名</th>
                <td>${sell.userName!""}</td>
            </tr>
            <tr>
                <th bgcolor="#a9a9a9">用户手机号</th>
                <td>${sell.userPhone!""}</td>
            </tr>
            <tr>
                <th bgcolor="#a9a9a9">成交价</th>
                <td>${sell.sellPrice!""}</td>
            </tr>
            <tr>
                <th bgcolor="#a9a9a9">首付款</th>
                <td>${sell.sellDown!""}</td>
            </tr>
            <tr>
                <th bgcolor="#a9a9a9">尾款</th>
                <td>${sell.sellFianl!""}</td>
            </tr>
            <tr>
                <th bgcolor="#a9a9a9">销售时间</th>
                <td>${(sell.sellDate?string("yyyy-MM-dd"))!}</td>
            </tr>
            <tr>
                <th bgcolor="#a9a9a9">备注</th>
                <td>${sell.sellRemark!"这个家伙很懒,什么也没有留下!!!"}</td>
            </tr>
        </table>
    </body>
</html>