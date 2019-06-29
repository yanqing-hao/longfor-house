package com.lf.background.login.service;

import com.lf.background.staff.domain.Staff;
import com.lf.background.staff.mapper.StaffMapper;
import com.lf.commons.RedisUtil;
import com.lf.commons.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Zy on 2019/6/13.
 */
@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private StaffMapper staffMapper;
    //注入redisUtil工具类
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResultVo getLoing(String name, String pass) {
        ResultVo resultVo = new ResultVo();
        //1.根据name去数据库查询    员工对象
        Staff staff = staffMapper.getStaffByName(name);
        //判断staff对象是否存在
        if(staff==null){  //如果不存在
            resultVo.setCode(501);
            resultVo.setInfo("用户名不存在");
            return resultVo;
        }else{
            //判断密码是否一致
            if(!pass.equals(staff.getStaffPass())){
                resultVo.setCode(502);
                resultVo.setInfo("密码错误");
                return resultVo;
            }else if(staff.getStaffShiro()==1) {
                resultVo.setCode(201);
                resultVo.setInfo("成功");
                //存在redis缓存中
                redisUtil.set("staff",staff);

                return resultVo;
            }else if(staff.getStaffShiro()==2) {
                resultVo.setCode(200);
                resultVo.setInfo("管理员成功");
                //存在redis缓存中
                redisUtil.set("staff",staff);

                return resultVo;
            }
        }
            return null;
    }

    /**
     * 注销员工登录
     */
    @Override
    public void logout() {
        redisUtil.remove("staff");
    }
}
