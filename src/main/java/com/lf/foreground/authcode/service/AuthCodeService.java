package com.lf.foreground.authcode.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by monst on 2019/6/20.
 */
public interface AuthCodeService {
    void randomNum(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
