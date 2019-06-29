<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>Sales Detail</title>
    <style type="text/css">
        tr{
            height:40px;
        }
        th{
            background-color:#E0EBF1;
        }
    </style>

</head>

<body>
<div>
    <table  border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
        <tr >
            <td colspan="2" align="center" >详细信息</td>
        </tr>
        <tr>
            <th width="100px" >编号</th><td>${vo.leaseId!}</td>
        </tr>
        <tr>
            <th width="100px" >房源编号</th><td>${vo.houseId!}</td>
        </tr>
        <tr>
            <th>员工编号</th><td>${vo.staffId!}</td>
        </tr>
        <tr>
            <th>用户姓名</th><td>${vo.userName!}</td>
        </tr>
        <tr>
            <th>用户电话</th><td>${vo.userPhone!}</td>
        </tr>
        <tr>
            <th>租金</th><td>${vo.leaseRent!}</td>
        </tr>
        <tr>
            <th>中介费</th><td>${vo.leaseAgency!}</td>
        </tr>
        <tr>
            <th>押金</th><td>${vo.leaseCashPledge!}</td>
        </tr>
      <tr>
            <th>开始时间</th><td>${vo.startTimes!}</td>
        </tr>
        <tr>
            <th>租赁合同扫描件</th><td>D:/user/....</td>
        </tr>
        <tr>
            <th>备注</th><td>${vo.leaseRemark!}</td>
        </tr>
        <tr>
            <th>操作日期</th><td>${vo.leaseUpdateTimes!}</td>
        </tr>
    </table>
</div>
</body>
</html>
