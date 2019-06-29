package com.lf.background.login.service;

import com.lf.commons.ResultVo;

/**
 * Created by Zy on 2019/6/13.
 */
public interface LoginService {

    ResultVo getLoing(String name, String pass);

    void logout();

}
