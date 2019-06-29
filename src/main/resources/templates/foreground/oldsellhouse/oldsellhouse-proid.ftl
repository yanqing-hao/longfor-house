
<script>
    $(".proEq li").click(function(){
        $(this).addClass("proEqCur").siblings("li").removeClass("proEqCur");
        var proeq=$(this).index();
        $(".proList").eq(proeq).fadeIn().siblings(".proList").hide();
    })
</script>


<div class="content">
    <div class="width1190" style="width:1000px; id="platform">
    <div class="proImg fl">
        <img src="/foreground/images/fangt1.jpg" />
    </div><!--proImg/-->
    <div class="proText fr">
        <h3 class="proTitle">${list.houseTitle!"龙湖地产"}</h3>
        <div class="proText1">
            编号：<input type="hidden" id="addUpdateById"value="${list.houseId}"> ${list.houseId}<br />
            售价：${list.houseSellPrice!""}<br />
            户型：<#if list.houseCount==1>
                    一室一厅
                <#elseif list.houseCount==2>
                    一室一厅一卫
                <#elseif list.houseCount==3>
                    两室一厅一卫
                <#elseif list.houseCount==4>
                    三室一厅一卫
                <#else>
                    三室以上
                </#if><br />
            面积：${list.staffArea!""}<br />
            朝向：${list.houseDirection!""}<br />
            楼层：${list.houseFloor!"1"}/${list.houseSumFloor!"1"}层<br />
            装修：<#if list.houseCase==1>
                        清水
                  <#elseif list.houseCase==2>
                        精装
                  <#else>
                        简装
                  </#if><br />
            小区：${list.houseName!"龙湖地产"}
        </div>
        <div class="xun-car">
            <a href="javascript:priceFun()" class="xwjg"><strong>${list.houseSellPrice!""}$$$</strong></a>
            <a href="javascript:;" class="projrgwc">关注房源</a>
        </div><!--xun-car/-->
    </div><!--proText/-->
    <div class="clears"></div>
    </div><!--width1190/-->
        <div class="proBox" style="width:1000px;margin:10px auto;">
            <div class="proEq">
                <ul class="fl">
                    <li class="proEqCur">房源详情</li>
                    <li>房源图片</li>
                    <li>小区介绍</li>
                </ul>
                <div class="lxkf fr"><a href="http://wpa.qq.com/msgrd?v=3&uin=1072631488&site=qq&menu=yes" target="_blank"></a></div>
                <div class="clears"></div>
            </div><!--proEq/-->
            <div class="proList">
                &nbsp;&nbsp;你的出现直至现在都是我命里的一抹灿烂。然而日子越来越久以后，这种曾经我们以为的爱情，
                渐渐的变成我生命里一份值得我永久记得的情愫，不再关乎爱情，不再关乎男女，真的就那么
                慢慢凝结成一份来世都想记得的感动。我想理清楚我们的关系，不想再不知道该如何称呼你。
                R.Y,最近有强烈的冲动想要告诉你，无论以后怎么样，不见面不要紧，不相爱不要紧，不联系
                也不要紧，只要不从我视线里消失，无论你在哪，都不要让我找不到你……
            </div><!--proList/-->
            <div class="proList">
                <img src="/foreground/images/fang1.jpg" /><img src="/foreground/images/fang2.jpg" />
                <img src="/foreground/images/fang3.jpg" /><img src="/foreground/images/fang4.jpg" />
                <img src="/foreground/images/fang5.jpg" /><img src="/foreground/images/fang6.jpg" />
                <img src="/foreground/images/fang7.jpg" />
            </div><!--proList/-->
            <div class="proList">
            ${list.houseName!""}是由${list.houseDevelopers!"龙湖地产"}精心打造的高档住宅!<br>
                &nbsp;&nbsp; 中建一局集团第三建筑有限公司，简称中建一局三公司，成立于1952年，其前身为中国建筑第一
                工程局第三建筑公司，隶属于中国建筑工程总公司旗下的中建一局集团。现具有房屋建筑工程施
                工总承包特级资质、机电设备安装专业承包、起重设备安装工程专业承包、建筑装修装饰专业承
                包一级资质，涉足房屋建筑、市政基础设施、房地产三个业务领域。<br>
                &nbsp;&nbsp;60多年来，三公司脚踏实地，勇于创新，追求卓越，以高品质的管理树立企业品牌。20世纪50
                到70年代，三公司先后为天津、内蒙古包头和卓资山、宁夏石嘴山、河北石家庄、四川攀枝花以及
                贵州、湖南等地的重要国防建设、城市建设做出突出贡献，被称为“南征北战的建筑野战军”。
            </div><!--proList/-->
        </div><!--proBox/-->
    </div><!--content/-->


<script>

    function priceFun() {
        $.ajax({
            type:'get',
            url:'/pro/addUpdate',
            data:{"houseId":$("#addUpdateById").val()},
            success:function (data) {
                var d = dialog({
                    title: '龙湖地产请您确认购房信息',
                    content: data,
                    okValue: '提交',
                    ok: function (data) {
                        //提交数据
                        addUpdateQuen();
                    },
                    cancelValue: '取消',
                    cancel: function () {
                        console.log("cancel  function");
                    }
                });
                d.showModal();
            },
             error:function (){
                alert("购买查询异常11")
             }
        })
    }
    function addUpdateQuen() {
        $.ajax({
            type:'get',
            url:'/pro/addPro',
            data:$("#fromAddPro").serialize(),
            success:function () {
        alert("您已购房成功!!!")
    },
        error:function (){
            alert("购买查询异常22")
        }
        })
    }
</script>