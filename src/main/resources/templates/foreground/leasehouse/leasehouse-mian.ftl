<html>
<head>
    <title></title>
    <script type="text/javascript">
        $(function(){
            //导航定位
            $(".nav li:eq(1)").addClass("navCur");
            $(".nav li:eq(0)").removeClass("navCur");
            $(".nav li:eq(2)").removeClass("navCur");
            $(".nav li:eq(3)").removeClass("navCur");
            $(".nav li:eq(4)").removeClass("navCur");
            $(".nav li:eq(5)").removeClass("navCur");
            $(".nav li:eq(6)").removeClass("navCur");
        })
    </script>
</head>
<body>
<div class="width1190">
   <#-- /*lease_particulars_ago.ftl 这个页面放入到mian页面中
    *<div id="big_div">
    * */-->

    <div id="big_div">
        <form id="seaForm" class="pro-search">
            <table>
                <tr>
                    <th>价格：</th>
                    <td>
                        <a href="javascript:seaPriceFun(0);" class="pro-cur">不限</a>
                        <a href="javascript:seaPriceFun(1);">500-1500元</a>
                        <a href="javascript:seaPriceFun(2);">1600-2500元</a>
                        <a href="javascript:seaPriceFun(3);">2600-3900元</a>
                        <a href="javascript:seaPriceFun(4);">4000元以上</a>
                        <input type="text" name="houseSellPriceB" id="sellPriB" size="2px"/> - <input type="text" id="sellPriE" name="houseSellPriceE"  size="2px"/> 元
                        <input type="button" id="priSub" class="proSub"  value="确定"/>
                    </td>
                </tr>
                <tr>
                    <th>面积：</th>
                    <td>
                        <input type="hidden" name="staffAreaS" id="seaAreaS"/>
                        <a href="javascript:seaPriceFun(5)" class="pro-cur">不限</a>
                        <a href="javascript:seaPriceFun(6)">10-20平方</a>
                        <a href="javascript:seaPriceFun(7)">21-40平方</a>
                        <a href="javascript:seaPriceFun(8)">41-60平方</a>
                        <a href="javascript:seaPriceFun(9)">61-80平方</a>
                        <a href="javascript:seaPriceFun(10)">100平方以上</a>


                    </td>
                </tr>
                <tr>
                    <th>房型：</th>
                    <td>
                        <a href="javascript:seaPriceFun(11)" class="pro-cur">不限</a>
                        <a href="javascript:seaPriceFun(12)">单间</a>
                        <a href="javascript:seaPriceFun(13)">一室一厅</a>
                        <a href="javascript:seaPriceFun(14)">一室一厅一卫</a>
                        <a href="javascript:seaPriceFun(15)">两室一厅一卫</a>
                        <a href="javascript:seaPriceFun(16)">三室一厅一卫</a>
                        <a href="javascript:seaPriceFun(17)">三室以上</a>
                        <input type="hidden" name="houseCount" id="houseCount" size="2px"/>
    <#--这个作用-->

                    </td>
                </tr>
            </table>


            <div class="paixu">
                <strong>排序：</strong>
                <a href="javascript:paiXu1(1);" class="pai-cur">默认</a>
                <a href="javascript:paiXu1(2);" id="priceId">价格∨</a>
                <a href="javascript:paiXu1(3);" id="timeId">最新∨</a>
            </div>
        </form><!--pro-search/-->
      <#--  /*把leseList_zu 这个页面放入到mian页面中
        *
        * */-->
        <div id="lis"></div>
    </div>
</div><!--width1190/-->





<script>
    /*就绪函数*/
    $(function () {
        getData();
    })




/*把leseList_zu 这个页面放入到mian页面中
*
* */
    function getData() {
       $.ajax({
           type:"get",//get请求
           url: "/agoLeaseController/agoLeaseList", //后台方法路径
           dataType:"html",//返回值类型
           //data:$("#seaForm").serialize(),//条件
           success:function (data) {
               $("#lis").html(data)
           }
       })
    }

/*条件查询使用
这里的参数是传入的下标   fals*/
    function seaPriceFun(fals){
        $.ajax({
            type:"get",//get请求
            url: "/agoLeaseController/agoLeaseList?state="+fals, //后台方法路径
            dataType:"html",//返回值类型
            //data:$("#seaForm").serialize(),//条件
            success:function (data) {
                $("#lis").html(data)
            }
        })
    }


    //排序
   function paiXu1(msg) {
        if(msg==1){
            paixuUtil("asc","house_id");
        }
        if(msg==2){
            //获取a标签的元素
            var price = document.getElementById("priceId").firstChild;
            if(price.data=="价格v"){
                paixuUtil("desc","house_lease_price");
                price.data="价格^";
            }else{
                paixuUtil("asc","house_lease_price");
                price.data="价格v";
            }
        }
        if(msg==3){
            var price = document.getElementById("timeId").firstChild;
            if(price.data=="最新v"){
                paixuUtil("desc","house_create_time");
                price.data="最新^";
            }else{
                paixuUtil("asc","house_create_time");
                price.data="最新v";
            }
        }
    }
    function paixuUtil(orderby,orderbyDate){
        $.ajax({
            type:'get',
            url:'/agoLeaseController/agoLeaseList',
            data:{orderby:orderby,orderbyDate:orderbyDate},
            success:function(data){
                // 后台获取到的数据放到div
                $("#lis").html(data)
            }
        })
    }



</script>
</body>
</html>
