
<#--  类适于循环   as 前面是 返回的集合   后面是 别名  -->
<#list PageInfo.list as ago>
    <div class="width1190">
        <div class="pro-left">
            <dl>
                <dt><a href="proinfo.html"><img src="/background/images/1.jpg" width="286" height="188" /></a></dt>
                <dd>
                    <h3><a href="javascript:seaAllFun(${ago.houseId?c});">${ago.houseTitle!}</a></h3>
                    <div class="pro-wei">
                        <img src="/background/images/weizhi.png" width="12" height="16" />${ago.houseName!} <strong class="red">

                    </strong>
                    </div>
                    <div class="pro-fang">
                                            <#if ago.houseCount==1>
                                                单间
                                            <#elseif ago.houseCount==2>
                                                一室一厅
                                            <#elseif ago.houseCount==3>
                                                一室一厅一卫
                                            <#elseif ago.houseCount==4>
                                                两室一厅一卫
                                            <#elseif ago.houseCount==5>
                                                三室一厅一卫
                                            <#else>
                                                三室以上
                                            </#if>

                        所在${ago.houseFloor!}楼层    ${ago.staffArea!}平   </div>
                    <div>地址:${ago.houseAddr!}</div>
                    <div class="pra-fa">发布人：${ago.staffName!} 发布时间： ${ago.houseCreateTime!?string('yyyy-MM-dd hh:mm:ss')}  </div>
                </dd>
                <div class="price" style="width: 500px">¥<strong>${ago.houseLeasePrice!}</strong><span class="font12">元/月</span></div>
                <div class="clears"></div>
            </dl>
        </div>
    </div>
</#list>
<#--引入的是分页的页面-->
         <#include "page.ftl"/>
<script>
    /*lease_particulars_ago.ftl 这个页面放入到mian页面中
        *
        * */

    function seaAllFun(hid) {
        console.log(hid);
        $.ajax({
            type:'get',
            url:'/agoLeaseController/agoParticulars',
            data:{"houseId":hid},
            dataType:'text',
            success:function (data) {
                $("#big_div").html(data)
            },
            error:function () {
                alert("查询详情失败")
            }
        })
    }


</script>


