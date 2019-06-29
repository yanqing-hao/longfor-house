<#list newHouseList.list as list >
    <dl>
        <dt><a href="javascript:sellFun(${list.houseId?c});"><img src="${request.contextPath}${list.picPath!'五'}" width="150" height="115" /></a></dt>
        <dd>
            <h3><a href="javascript:sellFun(${list.houseId?c});"></a>${list.houseName!}</h3>
            <div class="hui">
                <#if list.houseCount==1>
                    一室一厅
                <#elseif list.houseCount==2>
                    一室一厅一卫
                <#elseif list.houseCount==3>
                    两室一厅一卫
                <#elseif list.houseCount==4>
                    三室一厅一卫
                <#elseif list.houseCount==5>
                    三室以上
                <#else>
                    室厅卫数
                </#if> |
                ${list.staffArea!}m² |
                <#if list.houseCase==0>
                    装修类型
                <#elseif list.houseCase==1>
                    清水
                <#elseif list.houseCase==2>
                    精装
                <#else>
                    简装
                </#if> |
            </div>
        </dd>
    </dl>
</#list>
<div class="clears"></div>
<script>
    //查询详情
    function sellFun(hid) {
        console.log(hid);
        $.ajax({
            type:'get',
            url:'/newHouse/selectAll',
            data:{"houseId":hid},
            dataType:'text',
            success:function (data) {
                $("#joinContent").html(data)
            },
            error:function () {
                alert("查询详情失败")
            }
        })
    }
</script>
