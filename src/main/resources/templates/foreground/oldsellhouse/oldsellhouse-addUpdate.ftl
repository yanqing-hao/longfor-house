<form id="fromAddPro">
    <center>
        <table border="2px">
            <tr>
                <td colspan="2" bgcolor="#faebd7"><h3>龙湖地产请您确认购房信息</h3></td>
            </tr>
            <tr>
                <th>姓名:</th>
                <td><input type="text" name="userName" placeholder="请填写真实姓名"/></td>
                <th>性别:</th>
                <td>
                    <input type="radio" name="userSex" value="1"/>男
                    <input type="radio" name="userSex" value="2"/>女
                </td>
            </tr>
            <tr>
                <th>联系电话:</th>
                <td><input type="text" name="userPhone"></td>
                <th>身份证号:</th>
                <td><input type="text" name="userCard"></td>
            </tr>
            <tr>
                <th>银行卡号:</th>
                <td><input type="text" name="userBank"></td>
                <th>电子邮箱:</th>
                <td><input type="text" name="userEmail"></td>
            </tr>
            <tr>
                <td colspan="4" bgcolor="aqua"><h3>房屋信息</h3></td>
            </tr>
            <tr>
                <th>房源编号:</th>
                <td>
                ${houseStaffPicture.houseId}
                    <input type="hidden" name="houseId" id="buyId" value="${houseStaffPicture.houseId}">
                </td>
                <th>小区名称:</th>
                <td>${houseStaffPicture.houseName!"龙湖旗下产业"}</td>
            </tr>
            <tr>
                <th>销售价格:</th>
                <td>${houseStaffPicture.houseSellPrice!}</td>
                <th>装修情况:</th>
                <#--<td>-->
                <#--<#if houseStaffPicture.houseCase==1>-->
                    <#--清水-->
                <#--<#elseif house.houseCase==2>-->
                    <#--精装-->
                <#--<#else>-->
                    <#--简装-->
                <#--</#if>-->
                <#--</td>-->
            </tr>
            <tr>
                <th>房屋朝向:</th>
                <#--<td>${house.houseDirection!"南"}</td>-->
                <th>房屋面积:</th>
                <#--<td>${house.staffArea!}</td>-->
            </tr>
            <tr>
                <th>厅室个数:</th>
                <#--<td>-->
                <#--<#if house.houseCount==1>-->
                    <#--一室一厅-->
                <#--<#elseif house.houseCount==2>-->
                    <#--两室一厅-->
                <#--<#elseif house.houseCount==3>-->
                    <#--两室一厅一卫-->
                <#--<#elseif house.houseCount==4>-->
                    <#--三室一厅一卫-->
                <#--<#else>-->
                    <#--三室以上-->
                <#--</#if>-->
                <#--</td>-->
                <th>所在楼层</th>
                <#--<td>${house.houseFloor!"1"}/${house.houseSumFloor!"1"}>层</td>-->
                </tr>
                    <#--<tr>-->
                        <#--<th>建筑类型:</th>-->
                        <#--<td>-->
                            <#--<#if house.houseBuild==1>-->
                                <#--普通-->
                            <#--<#elseif house.houseBuild==2>-->
                                <#--宿舍-->
                            <#--<#elseif house.houseBuild==3>-->
                                <#--公寓-->
                            <#--<#else>-->
                    <#--别墅-->
                <#--</#if>-->
                <#--</td>-->
                <#--<th>建筑时间:</th>-->
                <#--<td>${(house.houseCreateTime?string("yyyy-MM-dd"))!}</td>-->
            <#--</tr>-->
            <tr>
                <th>产权:</th>
                <#--<td>${house.houseEquity!}</td>-->
                <th>开发商:</th>
                <#--<td>${house.houseDevelopers!}</td>-->
            </tr>
            <tr>
                <th>小区地址:</th>
                <#--<td>${house.houseAddr!}</td>-->
                <th>简介:</th>
                <#--<td>${house.houseAbout!"暂无信息,详情见龙湖地产官网"}</td>-->
            </tr>
        </table>
    </center>

</form>
