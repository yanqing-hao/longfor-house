<html>
<head>
    <title></title>
    <script type="text/javascript">
        $(function(){
            //导航定位
            $(".nav li:eq(2)").addClass("navCur");
            $(".nav li:eq(0)").removeClass("navCur");
            $(".nav li:eq(1)").removeClass("navCur");
            $(".nav li:eq(3)").removeClass("navCur");
            $(".nav li:eq(4)").removeClass("navCur");
            $(".nav li:eq(5)").removeClass("navCur");
            $(".nav li:eq(6)").removeClass("navCur");
        })
    </script>
</head>
<body>
    <form id="seaForm" class="pro-search">
        <div id="div_main">
             <div class="width1190">
                <table>
                    <tr>
                        <th>价格：</th>
                        <td>
                            <a href="javascript:seaPriceFun(1);" class="pro-cur">不限</a>
                            <a href="javascript:seaPriceFun(2);">10-50万元</a>
                            <a href="javascript:seaPriceFun(3);">51-100万元</a>
                            <a href="javascript:seaPriceFun(4);">101-200万元</a>
                            <a href="javascript:seaPriceFun(5);">200万元以上</a>
                            <input type="text" name="houseSellPriceB" id="sellPriB" size="2px"/> - <input type="text" id="sellPriE" name="houseSellPriceE"  size="2px"/> 元
                            <input type="button" id="priSub" class="proSub"  value="确定"/>
                        </td>
                    </tr>
                    <tr>
                        <th>面积：</th>
                        <td>

                            <a href="javascript:sesAreaFun(1)" class="pro-cur">不限</a>
                            <a href="javascript:sesAreaFun(2)">10-20平方</a>
                            <a href="javascript:sesAreaFun(3)">21-40平方</a>
                            <a href="javascript:sesAreaFun(4)">41-60平方</a>
                            <a href="javascript:sesAreaFun(5)">61-80平方</a>
                            <a href="javascript:sesAreaFun(6)">100平方以上</a>
                            <input type="hidden" name="staffAreaS" id="staffAreaS"/>
                            <input type="hidden" name="staffAreaB" id="staffAreaB"/>
                        </td>
                    </tr>
                    <tr>
                        <th>房型：</th>
                        <td>
                            <a href="javascript:typeFun(1)" class="pro-cur">不限</a>
                            <a href="javascript:typeFun(2)">一室一厅</a>
                            <a href="javascript:typeFun(3)">一室一厅一卫</a>
                            <a href="javascript:typeFun(4)">两室一厅一卫</a>
                            <a href="javascript:typeFun(5)">三室一厅一卫</a>
                            <a href="javascript:typeFun(6)">三室以上</a>
                            <input type="hidden" name="houseCount" id="houseCount"/>
                        </td>
                    </tr>
                </table>
                <div class="paixu">
                    <strong>排序：</strong>
                    <a href="javascript:sortFun(1)" class="pai-cur">默认</a>
                    <a href="javascript:sortFun(2)">价格 &or;</a>
                    <a href="javascript:sortFun(3)">最新 &or;</a>
                    <input type="hidden" name="sort" id="newSort">
                    <input type="hidden" name="order" id="newOrder">
                </div>
        </div><!--width1190/-->

        <div id="sea_div"></div>
    </div>
    </form><!--pro-search/-->


    <script>
        seaFun();
        function seaPriceFun(flag) {
            if(flag==1){
                $("#sellPriB").val(null);
                $("#sellPriE").val(null);
            }else if(flag==2){
                $("#sellPriB").val(10);
                $("#sellPriE").val(50);
            }else if(flag==3){
                $("#sellPriB").val(51);
                $("#sellPriE").val(100);
            }else if(flag==4){
                $("#sellPriB").val(101);
                $("#sellPriE").val(200);
            }else{
               $("#sellPriB").val(201);
                $("#sellPriE").val(null);
           }
            seaFun();
        }

        $("#priSub").click(function () {
            seaFun();
        })


        function seaFun() {
            $.ajax({
                type:'get',
                url:'/newHouse/queryNewLlist',
                data:$("#seaForm").serialize(),
                dataType:'html',
                success:function (data) {
                    $("#sea_div").html(data)
                },
                error:function () {
                    alert("条件查询异常")
                }
            })
        }

        function sesAreaFun(flag) {
            if(flag==1){
                $("#staffAreaS").val(null);
                $("#staffAreaB").val(null);
            }else if(flag==2){
                $("#staffAreaS").val(10);
                $("#staffAreaB").val(20);
            }else if(flag==3){
                $("#staffAreaS").val(21);
                $("#staffAreaB").val(40);
            }else if(flag==4){
                $("#staffAreaS").val(41);
                $("#staffAreaB").val(60);
            }else if(flag==5){
                $("#staffAreaS").val(61);
                $("#staffAreaB").val(100);
            }else if(flag==6){
                $("#staffAreaS").val(101);
                $("#staffAreaB").val(null);
            }
            seaFun();
        }
        
        function typeFun(msg) {
            if(msg==1){
                $("#houseCount").val(null);
            }else if(msg==2){
                $("#houseCount").val(1);
            }else if(msg==3){
                $("#houseCount").val(2);
            }else if(msg==4){
                $("#houseCount").val(3);
            }else if(msg==5){
                $("#houseCount").val(4);
            }else if(msg==6){
                $("#houseCount").val(5);
            }
            seaFun();
        }

        //排序方法
        var sum = 0;
        function sortFun(num) {
            if(num==1){
                $("#newSort").val(null);
            }else if(num==2){
                var number = sum++;
                console.log(number)
                if(number%2==0){
                    $("#newOrder").val("asc");
                }else {
                    $("#newOrder").val("desc");
                }
                $("#newSort").val("houseSellPrice");
            }else{
                $("#newSort").val(null);
            }
            seaFun();
        }

    </script>
    <script>
        //产品搜索
        $(".pro-search td a").click(function(){
            $(this).addClass("pro-cur").siblings("a").removeClass("pro-cur");
        })
        $(".paixu a").click(function(){
            $(this).addClass("pai-cur").siblings("a").removeClass("pai-cur");
        })
    </script>
</body>
</html>