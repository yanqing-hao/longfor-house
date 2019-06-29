<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>回显页面</title>
</head>
<body>
<form id = "lease-form-update">
    <center>
        <input type="hidden" name="leaseId" value="${vo.leaseId!}">
        <div>
            <label for="leaseRent">租金:</label> <input class="easyui-validatebox"
                                                         type="text" name="leaseRent" value="${vo.leaseRent!}" data-options="required:true" />
        </div>
        <div>
            <label for="startTime">开始时间:</label> <input class="easyui-validatebox"
                                                         type="text" name="startTime" value="${vo.startTimes!}" data-options="required:true" />
        </div>
        <div>
            <label for="leaseRent">结束时间:</label> <input class="easyui-validatebox"
                                                         type="text" name="endTime" value="${vo.endTimes!}" data-options="required:true" />
        </div>
        <div>
            <label for="userName">用户姓名:</label> <input class="easyui-validatebox"
                                                       type="text" name="userName" value="${vo.userName!}" data-options="required:true" />
        </div>
        <div>
            <label for="userPhone">用户电话:</label> <input class="easyui-validatebox"
                                                       type="text" name="userPhone" value="${vo.userPhone!}" data-options="required:true" />
        </div>
        </div>
        <div>
            <label for="leaseAgency">中介费:</label> <input class="easyui-validatebox"
                                                         type="text" name="leaseAgency" value="${vo.leaseAgency!}"  data-options="required:true" />
        </div>

        <input type="button" onclick="updateInfo()" value="修改">
        <input type="button" onclick="closeDd()" value="取消">
    </center>
</form>
</body>
</html>