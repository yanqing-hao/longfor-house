<div style="clear: both;font-size: 20px;padding-left:600px ;">
   <#-- //这里的PageInfo 对应的是controller 层中的    mav.addObject("PageInfo",pageInfo);-->
    共有${PageInfo.total }条
    有${PageInfo.pages }页
    当前是${PageInfo.pageNum }页
    <a href="javascript:pages(1)">首页</a>
    <a href="javascript:pages(2)">上一页</a>
    <a href="javascript:pages(3)">下一页</a>
    <a href="javascript:pages(4)">尾页</a>
    <script type="text/javascript">
        var pageNums = null;
        function pages(str){
            if (str == 1) {
                pageNums = 1;
            }
            if(str == 2){
                if (${PageInfo.pageNum }>1){
                    pageNums=${PageInfo.pageNum };
                    --pageNums;
                    console.log(pageNums)
                }

            }
            if(str == 3){
                if (${PageInfo.prePage }<${PageInfo.pages }){
                    pageNums = ${PageInfo.pageNum };
                    ++pageNums;

                }
            }
            if(str == 4){
                pageNums	= ${PageInfo.pages };
            }
            if (pageNums !=null){
                getDate(pageNums);
            }

            function getDate(pageNums) {
                $.ajax({
                    type:"GET",
                    url:"/agoLeaseController/agoLeaseList",
                    data:{"pageNum":pageNums},
                    dataType:"html",
                    success:function (data) {
                        //这里是跟的展示页面的div 的id
                        $("#lis").html(data);
                    },
                    error:function () {
                        alert("有问题")
                    }

                });
            }

        }
    </script>
</div>