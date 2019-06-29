<html>
<head>
    <title></title>
    <script type="text/javascript">
        $(function(){
            //导航定位
            $(".nav li:eq(3)").addClass("navCur");
            $(".nav li:eq(0)").removeClass("navCur");
            $(".nav li:eq(1)").removeClass("navCur");
            $(".nav li:eq(2)").removeClass("navCur");
            $(".nav li:eq(4)").removeClass("navCur");
            $(".nav li:eq(5)").removeClass("navCur");
            $(".nav li:eq(6)").removeClass("navCur");
        })
    </script>
</head>
<body>
    <div class="width1190">
        <form id="proFrom" class="pro-search">
            <div id="proOldId">
                <div class="width1190">
                    <table>
                        <tr>
                            <th>价格：</th>
                            <td>
                                <a href="javascript:oldTiaoFun(0);" class="pro-cur">不限</a>
                                <a href="javascript:oldTiaoFun(1);">500-1000</a>
                                <a href="javascript:oldTiaoFun(2);">1001-2000</a>
                                <a href="javascript:oldTiaoFun(3);">2001-3000</a>
                                <a href="javascript:oldTiaoFun(4);">3001-4000</a>
                                <input type="text" name="priceD" id="priceDi"  size="2px"/> - <input type="text" name="priceG" id="priceGao"  size="2px"/> 元
                                <input type="button" id="priSub" class="proSub" value="确定" />
                            </td>
                        </tr>
                        <tr>
                            <th>面积：</th>
                            <td>
                                <a href="javascript:oldTiaoFun(5);" class="pro-cur">不限</a>
                                <a href="javascript:oldTiaoFun(6);">10平方</a>
                                <a href="javascript:oldTiaoFun(7);">20-40平方</a>
                                <a href="javascript:oldTiaoFun(8);">41-60平方</a>
                                <a href="javascript:oldTiaoFun(9);">61-80平方</a>
                                <a href="javascript:oldTiaoFun(10);">100平方以上</a>
                            </td>
                        </tr>
                        <tr>
                            <th>房型：</th>
                            <td>
                                <a href="javascript:oldTiaoFun(11);" class="pro-cur">不限</a>
                                <a href="javascript:oldTiaoFun(12);">一室一厅</a>
                                <a href="javascript:oldTiaoFun(13);">两室一厅</a>
                                <a href="javascript:oldTiaoFun(14);">3室一厅</a>
                                <a href="javascript:oldTiaoFun(15);">一室一厅一卫</a>
                            </td>
                        </tr>
                    </table>
                </div>
            <div class="paixu">
                <strong>排序：</strong>
                <a href="javascript:;" class="pai-cur">默认</a>
                <a href="javascript:;">价格 &or;</a>
                <a href="javascript:;">最新 &or;</a>
            </div>




            <div id="oldHouseMain"></div>

            </div>
        </form><!--pro-search/-->
    </div><!--width1190/-->

</body>
    <script>
        /*就绪函数*/
        $(function () {
            proQuery();
        })
        function proQuery() {
            $.ajax({
                type:"get",//get请求
                //后台方法路径
                url: "/pro/queryProList",
                dataType:"html",//返回值类型
                data:$("#proFrom").serialize(),//条件
                success:function (data) {
                    $("#oldHouseMain").html(data)
                }
            })
        }

        //查询页
        function oldTiaoFun(proSerVo) {
            $.ajax({
                type:'get',
                url:'/pro/queryProList?proSearch='+proSerVo,
                dataType:'html',
                success:function (data) {
                    $("#oldHouseMain").html(data)
                },
            })
        }


    </script>
</html>