<html>
<head>
    <title>租赁记录</title>
</head>

<body>




<form id="commons-form">
    <table border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc" bgcolor="#E0EBF1">
        <tr>
            <td colspan="4" style="font-size:24px; text-align:center">填写信息进行购买</td>
        </tr>
        <tr><#--onblur="letFun()"-->
            <th>房源编号</th>
           <td><input type="text" id="houseId"name="houseId"  value="${(hsp.houseId)!''}" readonly/></td>
            <th>租金</th>
            <td><input type="text"  name="houseLeasePrice" value="${(hsp.houseLeasePrice)!''}" disabled/>元/月</td>
        </tr>
        <tr>
            <th>户型</th><#--<#if hsp.houseCount ==1>一厅一室一卫 <#elseif hsp.houseCount ==2>一厅二室 <#else>一厅三室</#if>-->
           <td><input type="text" value="${(hsp.houseCount)!''}" disabled/></td>
            <th>面积</th>
            <td><input type="text" value="${(hsp.staffArea)!''}"  disabled/>㎡</td>
        </tr>
        <tr>
            <th>用户姓名</th>
            <td><input  type="text" name="userName"/></td>
            <th>用户手机号</th>
            <td><input  type="text" name="userPhone"/></td>
        </tr>
        <tr>
            <th>用户银行卡号</th>
            <td><input  type="text" name="userBank"/></td>
        </tr>
    </table>
</form>
</body>
</html>