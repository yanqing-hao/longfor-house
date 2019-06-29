<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<table id="dog" class="easyui-datagrid" title="球巴子挂镰刀" style="width:700px;height:250px"
       data-options="singleSelect:true,collapsible:true,
					url:'/staff/queryStaffList',method:'get',fit:true,
					pagination:true,pageSize:5,fitColumns:true,pageList:[5,10,15,20]">
    <thead>
    <tr>
        <th data-options="field:'staffId',width:180">编号</th>
        <th data-options="field:'staffName',width:60,align:'right'">姓名</th>
        <th data-options="field:'staffSex',width:80,formatter:function(value){
            if(value==1){
                return '男';
            }else{
                return '女';
            }
        }">性别</th>
        <th data-options="field:'staffPhone',width:80">联系方式</th>
        <th data-options="field:'staffSubbranch',width:100,formatter:typeFmt">所属分店</th>
        <th data-options="field:'staffDate',width:80">加入时间</th>
    </tr>
    </thead>


</table>
<div id="dd"></div>

<script type="text/javascript">
    //给数据表格添加工具栏
    var rowFirst;
    initToolbar();
    function initToolbar(){
        $('#dog').datagrid({
            toolbar: [{
                iconCls: 'icon-no',
                handler: function(){
                    //删除所选行
                    deleteStaffById();
                }
            },'-',{
                iconCls: 'icon-edit',
                handler: function(){
                    //打开修改页面
                    updateStaffIdOpen();
                }
            },'-',{
                text:'员工姓名：<input type="text" id="staName">',
                handler: function(){

                }
            },'-',{
                iconCls: 'icon-search',
                text:'搜索',
                handler: function(){

                    var staName = $("#staName").val();

                    $('#dog').datagrid('load',{
                        staName: staName,
                    });
                }
            },'-',{
                iconCls: 'icon-save',
                text:'导出',
                handler: function(){

                    var tName = $("#tName").val();
                    var birB = $("#birB").val();
                    var birE = $("#birE").val();

                    location.href="<%=request.getContextPath()%>/travel/exportQueryListExcel.do?"
                            +"tName="+tName+"&birB="+birB+"&birE="+birE;
                }
            }]

        });

    }

    //根据选择的行 进行删除信息
    function deleteStaffById(){
        //获取 选中的行
        var row = $('#dog').datagrid('getSelected');

        console.info(row);
        if(row == null){
            $.messager.alert('警告','请选择一行进行删除');
            return;
        }

        $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
            if (r){

                $.ajax({
                    url:'/staff/deleteUpdate',
                    data:{"staffId":row.staffId},
                    type:'get',
                    dataType:'text',
                    success:function(msg){
                        console.info(msg);
                        $.messager.show({
                            title:'消息',
                            msg:msg,
                            timeout:200,
                            showType:'slide'
                        });
                        //刷新列表数据
                        $("#dog").datagrid("reload");
                    },
                    error:function(){
                        $("#dog").datagrid("reload");
                    }

                });


            }
        });

    }

    //打开修改窗口并且 异部查询
    function updateStaffIdOpen(){
        //获取 选中的行
        var row = $('#dog').datagrid('getSelected');
        console.log(row);
        if(row == null){
            $.messager.alert('警告','请选择一行进行修改');
            return;
        }

        rowFirst = row;
        //打开修改页面
        $('#dd').dialog({
            title: '修改信息',
            width: 400,
            height: 300,
            closed: false,
            cache: false,
            href: '/staff/updateStaff',
            modal: true,
            buttons:[{
                text:'修改',
                handler:function(){
                    //保存学生信息到数据库
                    updateStaff1();
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

    //修改
    function updateStaff1(){
        $.messager.progress();
        //获取表单提交信息
        $('#ffu').form('submit', {
            url: '/staff/updateStaffId',
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
                    timeout:200,
                    showType:'slide'
                });

                //关闭dialog
                $('#dd').dialog('close');
                //刷新列表数据
                $("#dog").datagrid("reload");


            }
        });
    }

    /*所属分店*/
    function typeFmt(value,rows,index){
        if(value==1){
            return "fen店1";
        }
        if(value==2){
            return "fen店2";
        }
        if(value==3){
            return "fen店3";

        }
        if(value==4){
            return "fen店4";

        }
        if(value==5){
            return "fen店5";

        }

    }
</script>
</body>
</html>
