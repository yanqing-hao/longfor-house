package com.lf.background.picture.mapper;

import com.lf.background.picture.domain.Picture;

public interface PictureMapper {
    int deleteByPrimaryKey(Integer picId);

    Picture selectByPrimaryKey(Integer picId);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);

    int insertSelective(Picture record);

    int insert(Picture record);
}