<script>
    $(function(){
        $("#houseDetails").form('load',{
            houseId:rows.houseId,
            houseMethod:rows.houseMethod==1?"租赁":"销售",
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
            houseEquity:rows.houseEquity,    //产权
            houseLeaseType:rows.houseLeaseType,   //出租方式
            houseLeasePrice:rows.houseLeasePrice,  //出租价格
            houseStatus:rows.houseStatus,  //房屋状态
            houseRemark:rows.houseRemark,  //备注
            houseAbout:rows.houseAbout,  //简介
            staffId:rows.staffId //发布者
        })
        var list = rows.pictureList;
        console.info(list);
        if(list!=null){
            $.each(list,function(n,value) {
                $("#img"+n).attr({
                    src:"${request.contextPath}"+value.picPath,
                    width:"50px",
                    height:"50px"
                })//图片回显
            });
        }
    })
</script>

<form id="houseDetails" method="post">
    <table cellpadding="3px" border="3" cellspacing="0" width="900px">
        <tr>
            <td align="right">房源id:</td>
            <td><input disabled type="text" name="houseId"/></td>
            <td align="right">业务方法:</td>
            <td><input disabled type="text" name="houseMethod"/></td>
        </tr>
        <tr>
            <td align="right">标题:</td>
            <td><input disabled type="text" name="houseTitle"/></td>
            <td align="right">房屋面积:</td>
            <td><input disabled name="staffArea"/></td>
        </tr>
        <tr>
            <td align="right">小区名称:</td>
            <td><input disabled name="houseName"/></td>
            <td align="right">小区地址:</td>
            <td><input disabled name="houseAddr"/></td>
        </tr>
        <tr>
            <td align="right">室厅卫数:</td>
            <td>
                <select disabled name="houseCount">
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
            <td><input disabled name="houseFloor"/></td>
        </tr>
        <tr>
            <td align="right">总楼层:</td>
            <td><input disabled name="houseSumFloor"/></td>
            <td align="right">装修情况:</td>
            <td>
                <label><input disabled type="radio" name="houseCase" value="1"/>清水</label>
                <label><input disabled type="radio" name="houseCase" value="2"/>精装</label>
                <label><input disabled type="radio" name="houseCase" value="3"/>简装</label>
            </td>
        </tr>
        <tr>
            <td align="right">建筑类型:</td>
            <td>
                <label><input disabled type="radio" name="houseBuild" value="1"/>普通住宅</label>
                <label><input disabled type="radio" name="houseBuild" value="2"/>宿舍</label></br>
                <label><input disabled type="radio" name="houseBuild" value="3"/>公寓</label>
                <label><input disabled type="radio" name="houseBuild" value="4"/>别墅</label>
            </td>
            <td align="right">房屋朝向:</td>
            <td><input disabled type="text" name="houseDirection"/></td>
        </tr>
        <tr>
            <td align="right">行政区市:</td>
            <td><input disabled type="text" name="houseRegion"/></td>
            <td align="right">备注:</td>
            <td><input disabled type="text" name="houseRemark"/></td>
        </tr>
        <tr>
            <td align="right">出租方式:</td>
            <td>
                <label><input disabled type="radio" name="houseLeaseType" value="1"/>押一付三</label>
                <label><input disabled type="radio" name="houseLeaseType" value="2"/>押一付一</label></br>
                <label><input disabled type="radio" name="houseLeaseType" value="3"/>押二付三</label>
            </td>
            <td align="right">出租价格:</td>
            <td><input disabled type="text" name="houseLeasePrice"/></td>
        </tr>
        <tr>
            <td align="right">房屋状态:</td>
            <td>
                <label><input disabled type="radio" name="houseStatus" value="0"/>未出租</label>
                <label><input disabled type="radio" name="houseStatus" value="1"/>已出租</label>
            </td>
            <td align="right">产权:</td>
            <td><input disabled type="text" name="houseEquity"/></td>
        </tr>
        <tr>
            <td align="right">房屋类型:</td>
            <td>
                <label><input disabled type="radio" name="houseRtype" value="1"/>新房</label>
                <label><input disabled type="radio" name="houseRtype" value="2"/>二手房</label>
            </td>
            <td align="right">销售价格:</td>
            <td>
                <input disabled type="text" name="houseSellPrice"/>
            </td>
        </tr>
        <tr>
            <td align="right">开发商:</td>
            <td><input disabled type="text" name="houseDevelopers"/></td>
            <td align="right">房屋状态:</td>
            <td>
                <label><input disabled type="radio" name="houseStatus" value="0"/>未销售</label>
                <label><input disabled type="radio" name="houseStatus" value="1"/>已销售</label>
            </td>
        </tr>
        <tr>
            <td align="right">简介:</td>
            <td colspan="3">
                <textarea name="houseAbout" disabled rows="5" cols="90"></textarea>
            </td>
        </tr>
        <tr>
            <td align="right">图片展示：</td>
            <td colspan="3">
                <img id="img0"><img id="img1"><img id="img2"><img id="img3"><img id="img4">
                <img id="img5"><img id="img6"><img id="img7"><img id="img8"><img id="img9">
            </td>
        </tr>
    </table>
</form>

