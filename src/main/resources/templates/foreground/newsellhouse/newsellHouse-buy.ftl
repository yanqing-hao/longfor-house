 <form id="sell-down">
    <center>
        <table border="2px">
            <tr>
                <td colspan="4" bgcolor="#faebd7" align="center"><h3>龙湖地产请您确认购房信息</h3></td>
            </tr>
            <tr>
                <th align="right">姓名:</th>
                <td><input type="text" name="userName" placeholder="请填写真实姓名"/></td>
                <th align="right">性别:</th>
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
                <td>
                    <input type="text" name="userEmail" size="9px">
                    <select name="userEmail">
                        <option value="1">@163.com</option>
                        <option value="2">@qq.com</option>
                        <option value="3">@sina.com</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="4" bgcolor="aqua" align="center"><h3>房屋信息</h3></td>
            </tr>
            <tr>
                <th>房源编号:</th>
                <td>
                    ${house.houseId}
                    <input type="hidden" name="houseId" id="buyId" value="${house.houseId}">
                </td>
                <th>小区名称:</th>
                <td>${house.houseTitle!"龙湖旗下产业"}</td>
            </tr>
            <tr>
                <th>销售价格:</th>
                <td>${house.houseSellPrice!}万元</td>
                <th>装修情况:</th>
                <td>
                    <#if house.houseCase==1>
                        清水
                        <#elseif house.houseCase==2>
                        精装
                        <#else>
                        简装
                    </#if>
                </td>
            </tr>
            <tr>
                <th align="right">税费:</th>
                <td>${house.houseSellPrice*0.02!}万元</td>
                <th align="right">中介费:</th>
                <td>${house.houseSellPrice*0.01!}万元</td>
            </tr>
            <tr>
                <th align="right">定金:</th>
                <td>${house.houseSellPrice*0.02!}万元</td>
                <th align="right">首付款:</th>
                <td>${house.houseSellPrice*0.1!}万元</td>
            </tr>
            <tr>
                <th align="right">房屋朝向:</th>
                <td>${house.houseDirection!"南"}</td>
                <th>房屋面积:</th>
                <td>${house.staffArea!}</td>
            </tr>
            <tr>
                <th>厅室个数:</th>
                <td>
                    <#if house.houseCount==1>
                        一室一厅
                    <#elseif house.houseCount==2>
                        一室一厅一卫
                    <#elseif house.houseCount==3>
                        两室一厅一卫
                    <#elseif house.houseCount==4>
                        三室一厅一卫
                    <#else>
                        三室以上
                    </#if>
                </td>
                <th>所在楼层</th>
                <td>${house.houseFloor!"1"}/${house.houseSumFloor!""}层</td>
            </tr>
            <tr>
                <th>建筑类型:</th>
                <td>
                    <#if house.houseBuild==1>
                        普通
                    <#elseif house.houseBuild==2>
                        宿舍
                    <#elseif house.houseBuild==3>
                        公寓
                    <#else>
                        别墅
                    </#if>
                </td>
                <th>建筑时间:</th>
                <td>${(house.houseCreateTime?string("yyyy-MM-dd"))!}</td>
            </tr>
            <tr>
                <th align="right">产权:</th>
                <td>${house.houseEquity!}年</td>
                <th align="right">开发商:</th>
                <td>${house.houseDevelopers!}</td>
            </tr>
            <tr>
                <th>小区地址:</th>
                <td>${house.houseAddr!}</td>
                <th align="right">简介:</th>
                <td>${house.houseAbout!"暂无信息,详情见龙湖地产官网"}</td>
            </tr>
        </table>
    </center>

 </form>
