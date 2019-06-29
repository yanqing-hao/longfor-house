package com.lf.foreground.user.mapper;

import com.lf.foreground.user.domain.SeachUser;
import com.lf.foreground.user.domain.User;
import com.lf.foreground.user.domain.UserTemp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<UserTemp> queryList(@Param("sea") SeachUser sea, @Param("sort") String sort, @Param("order") String order);

    User checkUserPhone(@Param("userPhone") String userPhone);
}