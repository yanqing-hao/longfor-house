<#list leaseHouseList.list as list>
    <dl>
        <dt><a href="javascript:leaseHouse(${list.houseId?c});"><img src="${request.contextPath}${list.picPath!}" width="150" height="115" /></a></dt>
        <dd>
            <h3><a href="javascript:leaseHouse(${list.houseId?c});"></a>${list.houseName!""}</h3>
            <div class="hui">
                <#if list.houseCount==1>
                    一室一厅
                <#elseif list.houseCount==2>
                    一室一厅一卫
                <#elseif list.houseCount==3>
                    两室一厅一卫
                <#elseif list.houseCount==4>
                    三室一厅一卫
                <#else>
                    三室以上
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
    function leaseHouse(hid) {
        console.log(hid);
        $.ajax({
            type:'get',
            url:'/agoLeaseController/agoParticulars',
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
