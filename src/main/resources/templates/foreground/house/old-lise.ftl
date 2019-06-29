<#list oldHouseList.list as list >
    <dl>
        <dt><a href="javascript:oldHouse(${list.houseId?c});"><img src="${request.contextPath}${list.picPath!}" width="150" height="115" /></a></dt>
        <dd>
            <h3><a href="javascript:oldHouse(${list.houseId?c});">${list.houseTitle!}</a></h3>
            <div class="in-er-right-text">
                ${list.houseAbout!}
            </div>
            <div class="price">¥<strong>${list.houseSellPrice!}</strong></div>
        </dd>
        <div class="clears"></div>
    </dl>
</#list>
<div class="clears"></div>
<script>
    //详情页
    function oldHouse(hid) {
        console.log(hid);
        $.ajax({
            type:'get',
            url:'/pro/queryProId',
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