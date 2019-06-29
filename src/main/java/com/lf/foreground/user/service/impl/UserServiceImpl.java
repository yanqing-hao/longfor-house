package com.lf.foreground.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lf.commons.DataGrid;
import com.lf.commons.MD5;
import com.lf.commons.PageUtil;
import com.lf.commons.RedisUtil;
import com.lf.commons.ResultVo;
import com.lf.commons.SmsUtil;
import com.lf.commons.StrTool;
import com.lf.foreground.user.domain.SeachUser;
import com.lf.foreground.user.domain.User;
import com.lf.foreground.user.domain.UserTemp;
import com.lf.foreground.user.mapper.UserMapper;
import com.lf.foreground.user.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by 代光磊 on 2019/6/13.
 */
@Service
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    //注入redisUtil
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页和条件查询
     * @param sea
     * @param page
     * @return
     */
    @Override
    public DataGrid<UserTemp> queryList(SeachUser sea, PageUtil page) {

        //分页工具类
        PageHelper.startPage(page.getPage(),page.getRows());
        //排序字段  驼峰转_
        String sort = StrTool.humpToLine2(page.getSort());
        //给前台传来的名称去除两端空格
        if (null!=sea.getUserUserName()){
            sea.setUserUserName( sea.getUserUserName().trim());
        }
        List<UserTemp> list = userMapper.queryList(sea,sort,page.getOrder());
        //分页对象
        PageInfo<UserTemp> pageInfo = new PageInfo<>(list);
        DataGrid<UserTemp> dg = new DataGrid<>();
        dg.setTotal(pageInfo.getTotal());   //返回给easyUI的数据集合
        dg.setRows(list);    //返回给easyUI的数据总条数
        return dg;
    }

    /**
     * 添加方法
     * @param user
     * @return
     */
    @Override
    public String addUser(User user) {
        if(user.getUserPass()==null){
            user.setUserPass(MD5.md5("999"));
        }
        userMapper.insert(user);
        return "添加用户信息成功!!!";
    }

    /**
     * 根据id查询
     * @param usId
     * @return
     */
    @Override
    public User selectUser(Integer usId) {
        return userMapper.selectByPrimaryKey(usId);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public String updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
        return "信息修改成功!!!";
    }

    @Override
    public String deleteUser(Integer userId) {
        userMapper.deleteByPrimaryKey(userId);
        return "删除信息成功!!!";
    }

    /**
     * 手机唯一校验;额
     * @param userPhone
     * @return
     */
    @Override
    public ResultVo checkUserPhone(String userPhone) {
        ResultVo resultVo = new ResultVo();
        //根据手机号查询user
        User user = userMapper.checkUserPhone(userPhone);
        //如果user存在   手机号已注册
        if(user!=null){
            resultVo.setCode(601);
            resultVo.setInfo("<font color='red'>手机号已注册</font>");
            return resultVo;
        }
        //user为空   手机号可以注册
        resultVo.setCode(200);
        resultVo.setInfo("<font color='green'>可以注册</font>");
        return resultVo;
    }

    /**
     * 发送电子邮件
     * @return
     */
    @Override
    public ResultVo sendMs(HttpServletRequest request, String userPhone) {
        ResultVo resultVo = new ResultVo(501,"<font color='red'>短信发送失败</font>");
        String mobile = userPhone;
        Integer tplId = 160257;
        String  tplValue = "#code#=";
        String num = "";
        Random random = new Random();
        for (int i=0;i<6;i++){
            num += random.nextInt(10);
        }
        tplValue += num;
        JSONObject sendMsg = SmsUtil.sendMsg(mobile, tplId, tplValue);
        //{"reason":"操作成功","result":{"sid":"6583181567092575777","fee":1,"count":1},"error_code":0}
        boolean bool = sendMsg.containsValue("操作成功");
        if(bool){
            //将手机验证码放在session中
            request.getSession().setAttribute("phoneCode",num);
            resultVo.setCode(200);
            resultVo.setInfo("<font color='green'>发送成功</font>");
            return resultVo;
        }
        return resultVo;
    }

    /**
     * 校验手机验证码
     * @param request
     * @param authCode
     * @return
     */
    @Override
    public ResultVo checkPhoneCode(HttpServletRequest request, String authCode) {
        ResultVo resultVo = new ResultVo(501,"<font color='red'>验证码错误</font>");
        // 获取session中的验证码
        Object phoneCode = request.getSession().getAttribute("phoneCode");
        // 如果验证码一致
        if(authCode.equals(phoneCode)){
            resultVo.setCode(200);
            resultVo.setInfo("<font color='green'>验证码正确</font>");
        }
        return resultVo;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public ResultVo userRegist(User user) {
        ResultVo resultVo = new ResultVo(500,"<font color='red'>注册失败</font>");
        //手机唯一验证
        User user1 = userMapper.checkUserPhone(user.getUserPhone());
        //如果user存在   手机号已注册
        if(user==null){
            resultVo.setCode(601);
            resultVo.setInfo("<font color='red'>手机号已注册</font>");
            return resultVo;
        }
        //进行注册
        user.setUserCreateTime(new Date());
        user.setUserStatus(1);
        user.setUserPass(MD5.md5(user.getUserPass()));
        userMapper.insertSelective(user);
        resultVo.setCode(200);
        resultVo.setInfo("<font color='green'>注册成功</font>");
        return resultVo;
    }

    //校验图片验证码
    @Override
    public ResultVo checkAuthCode(HttpServletRequest request,String picAuthCode) {
        ResultVo resultVo = new ResultVo();
        //session中大的验证码
        Object code = request.getSession().getAttribute("code");
        //转为小写
        String s = code.toString().toLowerCase();
        //转为小写
        String s1 = picAuthCode.toString().toLowerCase();
        //不一致
        if(!s.equals(s1)){
            resultVo.setCode(500);
            resultVo.setInfo("<font color='red'>验证码不正确</font>");
            return resultVo;
        }
        //一致
        resultVo.setCode(200);
        resultVo.setInfo("<font color='green'>√</font>");
        return resultVo;
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public ResultVo userLogin(User user) {
        ResultVo resultVo = new ResultVo(500,"<font color='red'>登录失败</font>");
        //根据手机号查询
        User user1 = userMapper.checkUserPhone(user.getUserPhone());
        //如果user1不存在   手机号未注册
        if(user1==null){
            resultVo.setCode(500);
            resultVo.setInfo("<font color='red'>手机号或账号不正确</font>");
            return resultVo;
        }
        //判断密码
        if(!MD5.md5(user.getUserPass()).equals(user1.getUserPass())){
            //密码不正确
            resultVo.setCode(501);
            resultVo.setInfo("<font color='red'>手机号或账号不正确</font>");
            return resultVo;
        }
        //密码正确  登录成功
        resultVo.setCode(200);
        resultVo.setInfo("<font color='green'>登陆成功</font>");
        redisUtil.set("user",user);
        return resultVo;
    }
}
