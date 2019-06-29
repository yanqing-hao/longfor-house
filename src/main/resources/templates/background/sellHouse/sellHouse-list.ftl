<html>
    <head>
        <title>龙湖地产销售展示页面</title>
        <#--<script type="text/javascript" src="/commons/js/jquery-2.1.1.js"></script>-->
    </head>
    <body>
        <table id="dg" class="easyui-datagrid" title="销售列表" style="width:700px;height:250px"
               data-options="singleSelect:true,collapsible:true,
                        url:'/sell/queryList',method:'get',fit:true,
                        pagination:true,fitColumns:true,pageList:[5,10,20,30,50],pageSize:5">
            <thead>
            <tr>
                <th data-options="field:'ck',width:80,align:'center',checkbox:true" >操作</th>
                <th data-options="field:'sellId',width:80,align:'center',sortable:true,order:'desc'">编号</th>
                <th data-options="field:'houseId',width:100,align:'center'">房源编号</th>
                <th data-options="field:'staffId',width:100,align:'center'">员工编号</th>
                <th data-options="field:'userName',width:100,align:'center'">用户姓名</th>
                <th data-options="field:'userPhone',width:100,align:'center'">用户电话</th>
                <th data-options="field:'sellDate',width:100,align:'center'">销售时间</th>
                <th data-options="field:'sellPrice',width:100,align:'center'">成交价</th>
                <th data-options="field:'sellDown',width:100,align:'center'">首付款</th>
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
                                deleteByComId();
                            }
                        },'-',{
                            text:'房源编号:<input type="text" name="seaHouseId" id="houseId"/>',
                            handler: function(){
                            }
                        },'-',{
                            text:'员工编号:<input type="text" name="seaStaffId" id="staffId"/>',
                            handler: function(){
                            }
                        },'-',{
                            text:'销售时间:<input type="date" name="sellTimeB" id="sellTimeB"/>--<input type="date" name="sellTimeE" id="sellTimeE"/>',
                            handler: function(){
                            }
                        },'-',{
                            iconCls: 'icon-search',
                            text:'搜索',
                            handler: function(){
                                var houseId = $("#houseId").val();
                                var staffId = $("#staffId").val();
                                var sellTimeB = $("#sellTimeB").val();
                                var sellTimeE = $("#sellTimeE").val();

                                $('#dg').datagrid('load',{
                                    seaHouseId:houseId,    //获取输入的条件传给后台
                                    seaStaffId:staffId,
                                    sellTimeB:sellTimeB,
                                    sellTimeE:sellTimeE
                                });
                            }
                        },'-',{
                            iconCls: 'icon-redo',
                            text:'导出',
                            handler: function(){
                                //导出回调函数
                                var houseId = $("#houseId").val();
                                var staffId = $("#staffId").val();
                                var sellTimeB = $("#sellTimeB").val();
                                var sellTimeE = $("#sellTimeE").val();

                                location.href="/sell/exportExcel?"
                                        +"seaHouseId="+houseId+"&seaStaffId="+staffId+"&sellTimeB="+sellTimeB+"&sellTimeE="+sellTimeE;
                            }
                        },'-',{
                            iconCls: 'icon-large-smartart',
                            text:'详细信息',
                            handler: function(){
                                //查看详细信息
                                selectAllSell();
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
                    href: '/skip/toSellInsert',
                    modal: true,
                    buttons:[{
                        text:'保存',
                        handler:function(){
                            //点击保存按钮触发的函数
                            addSell();
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

            function addSell() {  //保存方法
                $.messager.progress();	// 显示进度条
                $('#ff_sell').form('submit', {
                    url: '/sell/addSell',
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
                    href: '/skip/toSellUPdate',
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
                $('#up_sell').form('submit', {
                    url: '/sell/updateByid',
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

            function deleteByComId() {  //逻辑删除方法
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
                            url:'/sell/deleteSell',
                            data:{'sellId':row.sellId},
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
            
            function selectAllSell() {
                //获取 选中的行
                var rowName = $('#dg').datagrid('getSelected');
                if(rowName == null) {
                    $.messager.alert('警告', '请选择一行进行查看');
                    return;
                }
                location.href="/sell/selectAll?sellId="+rowName.sellId;
            }

        </script>
    </body>
</html>