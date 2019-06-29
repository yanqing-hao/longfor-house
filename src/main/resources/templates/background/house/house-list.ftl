
<table id="dgHouse"></table>
<div id="ddHouse"></div>

<script>
    var rows = null;
    listShow();
    //房源列表展示
   function listShow(){
       $('#dgHouse').datagrid({
           url:'/house/houseList',
           fit:true,
           pagination:true,
           fitColumns:true,
           singleSelect:true,
           columns:[[
               {field:'houseId',title:'房屋编号',width:100},
               {field:'houseTitle',title:'标题',width:100},
               {field:'houseMethod',title:'业务方式',width:100,
                   formatter: function(value){
                       if(value==1){
                           return "租赁";
                       }else{
                           return "销售";
                       }
                   }
               },
               {field:'houseLeasePrice',title:'租赁价格',width:100,
                   formatter: function(value){
                       if(value){
                           return value+"元/月";
                       }else{
                           return value;
                       }
                   }
               },
               {field:'houseSellPrice',title:'销售价格',width:100,
                   formatter: function(value){
                       if(value){
                           return value+"万元";
                       }else{
                           return value;
                       }
                   }
               },
               {field:'houseCreateTime',title:'发布时间',width:100,sortable:true},
               {field:'houseBuild',title:'建筑类型',width:100,
                   formatter: function(value){
                       if(value==1){
                           return "普通住宅";
                       }else if(value==2){
                           return "宿舍";
                       }else if(value==3){
                           return "公寓";
                       }else if(value==4){
                           return "别墅";
                       }else{
                           return "";
                       }
                   }
               },
               {field:'houseStatus',title:'房屋状态',width:100,
                   formatter: function(value,row,index){
                       if(row.houseMethod==1){
                           if(value==0) return "未出租"
                           else if(value==1) return "已出租"

                       }else if(row.houseMethod==2){
                           if(value==0) return "未销售"
                           else if(value==1) return "已销售"

                       }
                   }
               }
           ]],
           toolbar: [{
               iconCls: 'icon-edit',
               text:'修改',
               handler: function(){
                   rows = $('#dgHouse').datagrid("getSelected");
                   if(rows==null){
                       $.messager.alert('警告','没有选中要修改的数据');
                       return;
                   }
                   if(rows.houseMethod==1){  //判断房源业务类型  1.租赁 2.销售
                       openSave("修改租赁房源信息","/skip/getLeaseHouseForm");  //跳转修改页面
                   }else{
                       openSave("修改销售房源信息","/skip/getSellHouseForm");
                   }

               }
           },'-',{
               iconCls: 'icon-cancel',
               text:'删除',
               handler: function(){
                   rows = $('#dgHouse').datagrid("getSelected");
                   if(rows==null){
                       $.messager.alert('警告','没有选中要删除的数据');
                       return;
                   }
                   deleteInfo(rows.houseId);  //调用删除
               }
           },'-',{
               iconCls: 'icon-blank',
               text:'查看',
               handler: function(){
                   rows = $('#dgHouse').datagrid("getSelected");
                   if(rows==null){
                       $.messager.alert('警告','没有选中要查看的数据');
                       return;
                   }
                   getInfo(rows.houseTitle);  //打开查看详情弹框
               }
           },'-',{
               text:'发布时间:<input type="date" size="5" id="timeB"/>-<input type="date" size="5" id="timeE"/>',
               handler: function(){
               }
           },'-',{
               text:'状态:<label><input type="radio" name="status" value="101"/>未出租</label>' +
               '<label><input type="radio" name="status" value="102"/>已出租</label>'+
               '<label><input type="radio" name="status" value="103"/>未销售</label>'+
               '<label><input type="radio" name="status" value="104"/>已销售</label>',
               handler: function(){

               }
           },'-',{
               iconCls: 'icon-search',
               text:'搜索',
               handler: function(){
                   var sta = $("input[name='status']:checked").val();
                   var status = null;
                   var houseMethod = null;
                   if(sta==101){
                       status = 0;
                       houseMethod = 1;
                   }else if(sta==102){
                       status = 1;
                       houseMethod = 1;
                   }else if(sta==103){
                       status = 0;
                       houseMethod = 2;
                   }else if(sta==104){
                       status = 1;
                       houseMethod = 2;
                   }
                   $('#dgHouse').datagrid('load',{
                       createTimeB:$("#timeB").val(),
                       createTimeE:$("#timeE").val(),
                       houseStatus:status,
                       houseMethod:houseMethod
                   })
               }
           }]
       });
   }

   //根据ID删除信息
   function deleteInfo(houseId){
       $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
           if (r){
               $.ajax({
                   url:'/house/delete/'+houseId,
                   type:'get',
                   dataType:'json',
                   success:function(data){
                       $.messager.show({
                           title:'我的消息',
                           msg:data.info,
                           timeout:3000,
                           showType:'slide'
                       });
                       $('#dgHouse').datagrid('reload');//刷新列表
                   },
                   error:function(){

                   }
               });
           }
       });
   }

   //回显信息
    function getHouseById(){
       $.ajax({
           url:"/house/reShow/"+rows.houseId,
           type:"get",
           dataType:"json",
           success:function(houseInfo){
               rows = houseInfo;
               console.log(rows);
           },
           error:function(){

           }
       });
    }

   //跳转修改页面
    function openSave(title,url){
        getHouseById(); //调用回显
        $('#ddHouse').dialog({
            title: title,
            height: 600,
            width: 900,
            closed: false,
            cache: false,
            href: url,
            modal: true,
            buttons:[{
                text:'保存',
                handler:function(){
                    var houseMethod = $("input[name='houseMethod']").val();
                    //updateInfo(); //修改方法
                    if(houseMethod==1){
                        updateLeaseInfo();//租赁
                    }else{
                        updateSellInfo();//销售
                    }
                }
            },{
                text:'关闭',
                handler:function(){
                    $('#ddHouse').dialog("close") //关闭弹框
                    $('#dgHouse').datagrid("reload") //刷新DataGrid
                }
            }]
        });
    }

    //修改租赁房源方法
    function updateLeaseInfo(){
        $.messager.progress();	// 显示进度条
        $("#ffLeaseHouseForm").form('submit', {
            onSubmit: function(){
                var isValid = $(this).form('validate');
                if (!isValid){
                    $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                }
                return isValid;	// 返回false终止表单提交
            },
            url: "/hlease/update",
            success: function(data){
                data = eval("("+data+")");
                $.messager.progress('close');	// 如果提交成功则隐藏进度条
                $.messager.show({
                    title:'我的消息',
                    msg:data.info,
                    timeout:3000,
                    showType:'slide'
                });
                $('#dgHouse').datagrid('reload');//刷新DataGrid
                $('#ddHouse').dialog('close')//关闭dialog
            }
        });
    }

    //修改销售房源方法
    function updateSellInfo(){
        $.messager.progress();	// 显示进度条
        $("#ffSellHouseForm").form('submit', {
            onSubmit: function(){
                var isValid = $(this).form('validate');
                if (!isValid){
                    $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                }
                return isValid;	// 返回false终止表单提交
            },
            url: "/hsell/update",
            success: function(data){
                data = eval("("+data+")");
                $.messager.progress('close');	// 如果提交成功则隐藏进度条
                $.messager.show({
                    title:'我的消息',
                    msg:data.info,
                    timeout:3000,
                    showType:'slide'
                });
                $('#dgHouse').datagrid('reload');//刷新DataGrid
                $('#ddHouse').dialog('close')//关闭dialog
            }
        });
    }


    //打开详情弹框
    function getInfo(title){
        getHouseById(); //调用回显
        $('#ddHouse').dialog({
            title: title+"—房屋详情",
            height: 650,
            width: 800,
            closed: false,
            cache: false,
            href: '/skip/toGetHouse',
            modal: true,
        });
    }
</script>