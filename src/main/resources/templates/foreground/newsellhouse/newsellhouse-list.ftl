<#list newList.list as hlist>
<div class="width1190">
    <div class="pro-left">
        <dl>
            <dt>
                <a href="javascript:seaAllFun(${hlist.houseId?c})">
                    <img src="${hlist.picPath!"图片走丢啦!!!"}" width="268px" height="168px">
                </a>
            </dt>
            <dd>
                <h3><a href="javascript:seaAllFun(${hlist.houseId?c});">${hlist.houseTitle!"龙湖地产"}</a></h3>
                <div class="pro-wei">
                    <img src="/foreground/images/weizhi.png" width="12" height="16" /> <strong class="red">龙湖地产</strong>
                </div>
                <div class="pro-fang">
                    <#if hlist.houseCount==1>
                        一室一厅
                    <#elseif hlist.houseCount==2>
                        一室一厅一卫
                    <#elseif hlist.houseCount==3>
                        两室一厅一卫
                    <#elseif hlist.houseCount==4>
                        三室一厅一卫
                    <#else>
                        三室以上
                    </#if> &nbsp; &nbsp; &nbsp;
                ${hlist.staffArea!}平方米&nbsp; &nbsp; &nbsp;
                ${hlist.houseAddr!""} &nbsp; &nbsp; &nbsp;
                ${hlist.houseFloor!"1"}/${hlist.houseSumFloor!"1"}层</div>
                <div class="pra-fa">
                    发布人：${hlist.staffName!"龙湖地产"} &nbsp; &nbsp; &nbsp; 发布时间：${(hlist.houseCreateTime?string("yyyy-MM-dd HH:mm:ss"))!}
                </div>
            </dd>
            <div class="price">¥ <strong>${hlist.houseSellPrice!""}</strong><span class="font12">万元</span></div>
            <div class="clears"></div>
        </dl>
    </div>
</#list>

    <div style="clear: both;text-align:center;">
        <a href="javascript:pageFun(1)">首页</a>
        <a href="javascript:pageFun(2)">上一页</a>
        <a href="javascript:pageFun(3)">下一页</a>
        <a href="javascript:pageFun(4)">尾页</a>
        <input type="hidden" name="pageNum" id="pageNum"/>
        <input type="hidden" name="pageSize" id="pageSize"/>
    </div>

    <script>
        function pageFun(num) {
            if(num==1){
                pageNum = 1;
            }else if(num==2){
                $("#pageNum").val(${newList.prePage});

            }else if(num==3){
                $("#pageNum").val(${newList.nextPage});
            }else{
                $("#pageNum").val(${newList.pages});
            }
                seaFun();
        }

        //查询详情

        function seaAllFun(hid) {
            $.ajax({
                type:'get',
                url:'/newHouse/selectAll',
                data:{"houseId":hid},
                dataType:'text',
                success:function (data) {
                    $("#div_main").html(data)
                },
                error:function () {
                    alert("查询详情失败")
                }
            })
        }
    </script>