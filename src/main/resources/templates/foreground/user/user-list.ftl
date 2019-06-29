<html>
<head>
    <title>龙湖地产用户信息展示页面</title>
</head>
<body>
<table id="dg" class="easyui-datagrid" title="用户信息列表" style="width:700px;height:250px"
       data-options="singleSelect:true,collapsible:true,
                        url:'/user/queryList',method:'get',fit:true,
                        pagination:true,fitColumns:true,pageList:[5,10,20,30,50],pageSize:5">
    <thead>
    <tr>
        <th data-options="field:'userId',width:80,align:'center',sortable:true,order:'desc'">用户编号</th>
        <th data-options="field:'houseId',width:100,align:'center',formatter:function(value,rows){
            if(rows.houseId==null){
                return rows.houseId2;
            }else{
                return rows.houseId;
            }
        }">房源编号</th>
        <th data-options="field:'userName',width:100,align:'center'">用户姓名</th>
        <th data-options="field:'userSex',width:100,align:'center',formatter:function(value){
            if(value==1){
                return '男';
            }else{
                return '女';
            }
        }">用户性别</th>
        <th data-options="field:'userPhone',width:100,align:'center'">联系方式</th>
        <th data-options="field:'userBirthday',width:100,align:'center'">出生日期</th>
    </tr>
    </thead>
</table>

<div id="dd"></div>
<script>

    $(function () {
        initToolbar();  //就绪函数调用initToolbar()方法
    })
    //给数据表格添加工具栏
    function initToolbar(){
        $('#dg').datagrid({
            toolbar: [
                {
                    iconCls: 'icon-add',
                    handler: function(){
                        openAdd();   //打开添加页面
                    }
                },'-',{
                    iconCls: 'icon-edit',
                    handler: function(){

                        //调用打开修改页面
                        openUpdate();

                    }
                },'-',{
                    iconCls: 'icon-no',
                    handler: function(){

                        //删除所选行
                        deleteByUserId();
                    }
                },'-',{
                    text: '用户编号:<input type="text" name="userUserId" id="userUserId"/>',
                    handler: function(){
                    }
                },'-',{
                    text:'房源编号:<input type="text" name="userHouseId" id="userHouseId"/>',
                    handler: function(){
                    }
                },'-',{
                    text:'用户姓名:<input type="text" name="userUserName" id="userUserName"/>',
                    handler: function(){
                    }
                },'-',{
                    iconCls: 'icon-search',
                    text:'搜索',
                    handler: function(){
                        var houseId = $("#userHouseId").val();
                        var userId = $("#userUserId").val();
                        var userUserName = $("#userUserName").val();
                        $('#dg').datagrid('load',{
                            userHouseId:houseId,    //获取输入的条件传给后台
                            userUserId:userId,
                            userUserName:userUserName,
                        });
                    }
                },'-',{
                    iconCls: 'icon-redo',
                    text:'导出',
                    handler: function(){

                        //导出回调函数
                        var houseId = $("#userHouseId").val();
                        var userId = $("#userUserId").val();
                        var userUserName = $("#userUserName").val();

                        location.href="/user/exportExcel?"
                                +"userHouseId="+houseId+"&userUserId="+userId+"&userUserName="+userUserName;
                    }
                }]
        });
    }


    //打开添加页面
    function openAdd(){
        $('#dd').dialog({
            title: '添加数据',
            width: 600,
            height:500,
            closed: false,
            cache: false,
            href: '/skip/toUserAdd',
            modal: true,
            buttons:[{
                text:'保存',
                handler:function(){
                    //点击保存按钮触发的函数
                    addUser();
                }
            },{
                text:'关闭',
                handler:function(){
                    //点击关闭按钮触发的函数 (关闭弹框)
                    $('#dd').dialog("close");
                }
            }]
        });
    }

    function addUser() {  //保存方法
        $.messager.progress();	// 显示进度条
        $('#ff_user').form('submit', {
            url: '/user/addUser',
            onSubmit: function(){
                var isValid = $(this).form('validate');
                if (!isValid){
                    $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                }
                return isValid;	                 // 返回false终止表单提交
            },
            success: function(msg){
                $.messager.progress('close');	// 如果提交成功则隐藏进度条
                $.messager.show({
                    title:'消息',
                    msg:msg,
                    timeout:2000,
                    showType:'slide'
                });
                $('#dd').dialog("close");      //关闭dialog弹框
                $("#dg").datagrid("reload");   //添加成功  刷新页面
            }
        });
    }

    function openUpdate() {  //打开修改页面 并回显
        //获取 选中的行
        var row = $('#dg').datagrid('getSelected');
        console.log(row);
        if(row == null){
            $.messager.alert('警告','请选择一行进行修改');
            return;
        }

        rowFirst = row;

        $('#dd').dialog({
            title: '修改销售记录',
            width: 600,
            height: 'auto',
            closed: false,
            cache: false,
            href: '/skip/toUserUpdate',
            modal: true,
            buttons:[{
                text:'修改',
                handler:function(){
                    //触发修改方法
                    updateByid();
                }
            },{
                text:'关闭',
                handler:function(){
                    console.log('点击关闭');
                    $('#dd').dialog("close");
                }
            }]
        });
    }

    function updateByid(){  //修改方法
        $.messager.progress();   //显示进度条
        //获取表单提交信息
        $('#up_user').form('submit', {
            url: '/user/updateUser',
            onSubmit: function(){
                var isValid = $(this).form('validate');
                if (!isValid){
                    $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                }
                return isValid;	// 返回false终止表单提交
            },
            success: function(msg){
                $.messager.progress('close');	// 如果提交成功则隐藏进度条

                $.messager.show({
                    title:'消息',
                    msg:msg,
                    timeout:2000,
                    showType:'slide'
                });

                //关闭dialog
                $('#dd').dialog('close');
                //刷新列表数据
                $("#dg").datagrid("reload");

            }
        });
    }

    function deleteByUserId() {  //逻辑删除方法
        //获取选中的行
        var row = $("#dg").datagrid("getSelected");
        if(row==null){
            $.messager.alert('警告','请选择一行!!!');
            return ;
        }
        $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
            if (r){
                $.ajax({
                    type:'get',
                    url:'/user/deleteUser',
                    data:{'userId':row.userId},
                    dataType:'text',
                    success:function(msg){
                        $.messager.show({
                            title:'我的消息',
                            msg:msg,
                            timeout:2000,
                            showType:'slide'
                        });
                        $("#dg").datagrid("reload")
                    },
                    error:function(){
                        alert("删除方法异常")
                    }
                })
            }
        });
    }

</script>
</body>
</html>