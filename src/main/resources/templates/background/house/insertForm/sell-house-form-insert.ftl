
<script>
    $(function(){
        $("#ffSellHouseForm").form('load',{
            picId:rows.picId,
            houseId:rows.houseId,
            houseMethod:rows.houseMethod,
            houseTitle:rows.houseTitle,
            staffArea:rows.staffArea,
            houseName:rows.houseName,
            houseAddr:rows.houseAddr,
            houseCount:rows.houseCount,
            houseFloor:rows.houseFloor,
            houseSumFloor:rows.houseSumFloor,
            houseCase:rows.houseCase,
            houseBuild:rows.houseBuild, //建筑类型
            houseDirection:rows.houseDirection,   //房屋朝向
            houseRegion:rows.houseRegion,
            houseRtype:rows.houseRtype,   //房屋类型
            houseSellPrice:rows.houseSellPrice,   //销售价格
            houseDevelopers:rows.houseDevelopers,  // 开发商
            houseStatus:rows.houseStatus,  //房屋状态
            houseEquity:rows.houseEquity,    //产权
            houseRemark:rows.houseRemark,  //备注
            houseAbout:rows.houseAbout,  //简介
            staffId:rows.staffId //发布者
        })
        var list = rows.pictureList;
        console.info(list);
        if(list!=null){
            $.each(list,function(n,value) {
               /* $("#ra"+n).attr("value",value.picId);*/
                $("#img"+n).attr({
                    src:"${request.contextPath}"+value.picPath,
                    width:"50px",
                    height:"50px"
                })//图片回显
            });
        }
    })
</script>

<form id="ffSellHouseForm" method="post">
    <input type="hidden" name="picId"/>
    <input type="hidden" name="houseId"/>
    <input type="hidden" name="houseMethod"/>
    <table cellpadding="5px" border="5px" cellspacing="0" width="900px">
        <tr>
            <td align="right">标题:</td>
            <td><input class="easyui-validatebox" type="text" name="houseTitle" data-options="required:true" /></td>
            <td align="right">房屋面积:</td>
            <td><input class="easyui-validatebox" type="text" name="staffArea" data-options="required:true" /></td>
            <td align="right">小区名称:</td>
            <td><input class="easyui-validatebox" type="text" name="houseName"/></td>
        </tr>
        <tr>
            <td align="right">小区地址:</td>
            <td><input class="easyui-validatebox" type="text" name="houseAddr" data-options="required:true" /></td>
            <td align="right">室厅卫数:</td>
            <td>
                <select name="houseCount" class="easyui-combobox">
                    <option value="0">-请选择-</option>
                    <option value="1">一室一厅</option>
                    <option value="2">一室一厅一卫</option>
                    <option value="3">两室一厅一卫</option>
                    <option value="4">三室一厅一卫</option>
                    <option value="5">三室以上</option>
                    <option value="6">单间</option>
                </select>
            </td>
            <td align="right">所在楼层:</td>
            <td><input class="easyui-validatebox" type="text" name="houseFloor" data-options="required:true" /></td>
        </tr>
        <tr>
            <td align="right">总楼层:</td>
            <td><input class="easyui-validatebox" type="text" name="houseSumFloor"/></td>
            <td align="right">装修情况:</td>
            <td>
                <label><input type="radio" name="houseCase" value="0" checked hidden/></label>
                <label><input type="radio" name="houseCase" value="1"/>清水</label>
                <label><input type="radio" name="houseCase" value="2"/>精装</label>
                <label><input type="radio" name="houseCase" value="3"/>简装</label>
            </td>
            <td align="right">建筑类型:</td>
            <td>
                <label><input type="radio" name="houseBuild" value="0" checked hidden/></label>
                <label><input type="radio" name="houseBuild" value="1"/>普通住宅</label>
                <label><input type="radio" name="houseBuild" value="2"/>宿舍</label>
                <label><input type="radio" name="houseBuild" value="3"/>公寓</label></br>
                <label><input type="radio" name="houseBuild" value="4"/>别墅</label>
            </td>
        </tr>
        <tr>
            <td align="right">房屋朝向:</td>
            <td><input class="easyui-validatebox" type="text" name="houseDirection"/></td>
            <td align="right">行政区市:</td>
            <td><input class="easyui-validatebox" type="text" name="houseRegion" data-options="required:true" /></td>
            <td align="right">房屋类型:</td>
            <td>
                <label><input type="radio" name="houseRtype" value="1"/>新房</label>
                <label><input type="radio" name="houseRtype" value="2"/>二手房</label>
            </td>
        </tr>
        <tr>
            <td align="right">销售价格:</td>
            <td>
                <input class="easyui-validatebox" type="text" name="houseSellPrice" data-options="required:true" />
            </td>
            <td align="right">开发商:</td>
            <td><input class="easyui-validatebox" type="text" name="houseDevelopers"/></td>
            <td align="right">房屋状态:</td>
            <td>
                <label><input type="radio" name="houseStatus" value="0"/>未销售</label>
                <label><input type="radio" name="houseStatus" value="1"/>已销售</label>
            </td>
        </tr>
        <tr>
            <td align="right">产权:</td>
            <td><input class="easyui-validatebox" type="text" name="houseEquity" /></td>
            <td align="right">备注:</td>
            <td><input class="easyui-validatebox" type="text" name="houseRemark" /></td>
            <td align="right">简介:</td>
            <td><input class="easyui-validatebox" type="text" name="houseAbout" /></td>
        </tr>
        <tr>
            <td align="right">图片：</td>
            <td colspan="5">
                <img id="img0">
                <img id="img1">
                <img id="img2">
                <img id="img3">
                <img id="img4">
                <img id="img5">
                <img id="img6">
                <img id="img7">
                <img id="img8">
                <img id="img9">
                <#--<label><input type="radio" id="ra0" name="picId"/></label>-->
                <#--<label><input type="radio" id="ra1" name="picId"/></label>-->
                <#--<label><input type="radio" id="ra2" name="picId"/></label>-->
                <#--<label><input type="radio" id="ra3" name="picId"/></label>-->
                <#--<label><input type="radio" id="ra4" name="picId"/></label>-->
                <#--<label><input type="radio" id="ra5" name="picId"/></label>-->
                <#--<label><input type="radio" id="ra6" name="picId"/></label>-->
                <#--<label><input type="radio" id="ra7" name="picId"/></label>-->
                <#--<label><input type="radio" id="ra8" name="picId"/></label>-->
                <#--<label><input type="radio" id="ra9" name="picId"/></label>-->
               <#-- <div id="uploader-demo">
                    <div id="fileListShow" class="uploader-list"></div><!--用来回显图片的div&ndash;&gt;
                    <div id="filePickerShow">选择图片</div>
                    <input type="hidden" id="imgUrlShow" name="picPath"/><!-- name改成实体对象的url  &ndash;&gt;
                </div>-->
            </td>
        </tr>
    </table>
</form>
<script>
    $list = $("#fileListShow");
    // 初始化Web Uploader
    var uploader = WebUploader.create({
        //上传文件的name
        fileVal:"img",//写改成后台接受文件属性驱动的File的名字
        // 选完文件后，是否自动上传。
        auto: true,
        // swf文件路径
        swf: '${request.contextPath}commons/webuploader-0.1.5/Uploader.swf',
        // 文件接收服务端,需要改成自己处理上传文件的Action中的方法
        server: '/picture/uploadFile',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filePickerShow',
        //只允许上传一张图片
        fileNumLimit:0,
        threads:1,
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });

    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        $list.empty();
        var $li = $(
                        '<div id="' + file.id + '" class="file-item thumbnail">' +
                        '<img>' +
                        '<div class="info">' + file.name + '</div>' +
                        '</div>'
                ),
                $img = $li.find('img');


        // $list为容器jQuery实例
        $list.append( $li );

        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 100 x 100
        var thumbnailWidth = 100;
        var thumbnailHeight = 100;
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr( 'src', src );
        }, thumbnailWidth, thumbnailHeight );//缩略图高度和宽度 （单位是像素），当宽高度是0~1的时候，是按照百分比计算，具体可以看api文档
    });

    //上传成功后要执行的回调函数
    uploader.on('uploadSuccess',function(file,data){
        $("#imgUrlShow").val(data.url);
    })

    // 文件上传失败，显示上传出错。
    uploader.on( 'uploadError', function( file ) {
        var $li = $( '#'+file.id ),
                $error = $li.find('div.error');

        // 避免重复创建
        if ( !$error.length ) {
            $error = $('<div class="error"></div>').appendTo( $li );
        }

        $error.text('上传失败');
    });
</script>