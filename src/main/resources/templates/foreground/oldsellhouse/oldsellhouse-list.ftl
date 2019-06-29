<#list list.list as item>
<div class="width1190">
    <div class="pro-left">
        <dl>
            <dt>
                <h3>
                    <a href="javascript:proIdFun(${item.houseId?c});">
                        <img src="${item.picPath!"图片走丢啦!!!"}" width="228px" height="128px">
                    </a>
                </h3>
            </dt>
            <dd>
                <h3><a href="javascript:proIdFun(${item.houseId?c});">${item.houseTitle!"龙湖地产"}</a></h3>
                <div class="pro-wei">
                    <img src="/foreground/images/weizhi.png" width="12" height="16" /> <strong class="red">智慧园</strong>
                </div>
                <div class="pro-fang">
                    <#if item.houseCount==1>
                        一室一厅
                    <#elseif item.houseCount==2>
                        两室一厅
                    <#elseif item.houseCount==3>
                        三室一厅
                    <#elseif item.houseCount==4>
                        两室一厅一卫
                    <#elseif item.houseCount==5>
                        两室一厅一卫一厨
                    </#if>

                    所在${item.houseFloor!}楼层    ${item.staffArea}平   </div>
                <div class="pra-fa">
                    发布人:${item.staffName!}&nbsp;&nbsp;&nbsp;&nbsp;发布时间:${(item.houseCreateTime?string("yyyy-MM-dd HH:mm:ss"))!}
                </div>
            </dd>
            <div class="price">¥<strong>${item.houseSellPrice!}</strong><span class="font12">元/</span></div>
            <div class="clears"></div>


        </dl>
    </div>
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
            $("#pageNum").val(${list.prePage});

        }else if(num==3){
            $("#pageNum").val(${list.nextPage});
        }else{
            $("#pageNum").val(${list.pages});
        }
        proQuery();
    }

    //详情页
    function proIdFun(hid) {
        console.log(hid);
        $.ajax({
            type:'get',
            url:'/pro/queryProId',
            data:{"houseId":hid},
            dataType:'text',
            success:function (data) {
                $("#proOldId").html(data)
            },
            error:function () {
                alert("查询详情失败")
            }
        })
    }
</script>