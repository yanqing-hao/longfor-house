package com.lf.foreground.user.service;

import com.lf.commons.DataGrid;
import com.lf.commons.PageUtil;
import com.lf.commons.ResultVo;
import com.lf.foreground.user.domain.SeachUser;
import com.lf.foreground.user.domain.User;
import com.lf.foreground.user.domain.UserTemp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 代光磊 on 2019/6/13.
 */
public interface UserService {

    DataGrid<UserTemp> queryList(SeachUser sea, PageUtil page);

    String addUser(User user);

    User selectUser(Integer usId);

    String updateUser(User user);

    String deleteUser(Integer userId);

    ResultVo checkUserPhone(String userPhone);

    ResultVo sendMs(HttpServletRequest request, String userPhone);

    ResultVo checkPhoneCode(HttpServletRequest request, String authCode);

    ResultVo userRegist(User user);

    ResultVo checkAuthCode(HttpServletRequest request,String picAuthCode);

    ResultVo userLogin(User user);

}
