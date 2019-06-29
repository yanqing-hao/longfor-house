
<script>
    $(function(){
        $("#ffLeaseHouseForm").form('load',{
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
            houseConfigure:rows.houseConfigure.split(','),   //房屋配置
            houseLeaseType:rows.houseLeaseType,   //出租方式
            houseLeasePrice:rows.houseLeasePrice,  //出租价格
            houseStatus:rows.houseStatus,  //房屋状态
            houseRemark:rows.houseRemark,  //备注
            houseAbout:rows.houseAbout,  //简介
            staffId:rows.staffId //发布者
        });
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

<form id="ffLeaseHouseForm" method="post">
    <input type="hidden" name="picId"/>
    <input type="hidden" name="houseId"/>
    <input type="hidden" name="houseMethod"/>
    <table cellpadding="3px" border="3" cellspacing="0" width="900px">
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
                <label><input type="radio" name="houseBuild" value="2"/>宿舍</label></br>
                <label><input type="radio" name="houseBuild" value="3"/>公寓</label>
                <label><input type="radio" name="houseBuild" value="4"/>别墅</label>
            </td>
        </tr>
        <tr>
            <td align="right">房屋朝向:</td>
            <td><input class="easyui-validatebox" type="text" name="houseDirection"/></td>
            <td align="right">行政区市:</td>
            <td><input class="easyui-validatebox" type="text" name="houseRegion" data-options="required:true" /></td>
            <td align="right">房屋配置:</td>
            <td>
                <label><input type="checkbox" name="houseConfigure" value="0" checked hidden/></label>
                <label><input type="checkbox" name="houseConfigure" value="1"/>电视</label>
                <label><input type="checkbox" name="houseConfigure" value="2"/>洗衣机</label>
                <label><input type="checkbox" name="houseConfigure" value="3"/>空调</label>
                <label><input type="checkbox" name="houseConfigure" value="4"/>沙发</label></br>
                <label><input type="checkbox" name="houseConfigure" value="5"/>衣柜</label>
                <label><input type="checkbox" name="houseConfigure" value="6"/>天然气</label>
                <label><input type="checkbox" name="houseConfigure" value="7"/>宽带</label>
                <label><input type="checkbox" name="houseConfigure" value="8"/>热水器</label></br>
                <label><input type="checkbox" name="houseConfigure" value="9"/>消毒柜</label>
                <label><input type="checkbox" name="houseConfigure" value="10"/>冰箱</label>
                <label><input type="checkbox" name="houseConfigure" value="11"/>油烟机</label>
            </td>
        </tr>
        <tr>
            <td align="right">出租方式:</td>
            <td>
                <label><input type="radio" name="houseLeaseType" value="1"/>押一付三</label>
                <label><input type="radio" name="houseLeaseType" value="2"/>押一付一</label></br>
                <label><input type="radio" name="houseLeaseType" value="3"/>押二付三</label>
            </td>
            <td align="right">出租价格:</td>
            <td><input class="easyui-validatebox" type="text" name="houseLeasePrice" data-options="required:true" /></td>
            <td align="right">房屋状态:</td>
            <td>
                <label><input type="radio" name="houseStatus" value="0"/>未出租</label>
                <label><input type="radio" name="houseStatus" value="1"/>已出租</label>
            </td>
        </tr>
        <tr>
            <td align="right">备注:</td>
            <td><input class="easyui-validatebox" type="text" name="houseRemark"/></td>
            <td align="right">简介:</td>
            <td colspan="3"><input class="easyui-validatebox" type="text" name="houseAbout"/></td>
        </tr>
        <tr>
            <td align="right">图片：</td>
            <td colspan="5">
                <img id="img0"><img id="img1"><img id="img2"><img id="img3"><img id="img4">
                <img id="img5"><img id="img6"><img id="img7"><img id="img8"><img id="img9">
            </td>
        </tr>
    </table>
</form>
