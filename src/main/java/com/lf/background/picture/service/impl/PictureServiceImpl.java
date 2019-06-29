package com.lf.background.picture.service.impl;

import com.lf.background.picture.domain.Picture;
import com.lf.background.picture.mapper.PictureMapper;
import com.lf.background.picture.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by monst on 2019/6/12.
 */
@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    /**
     * 添加图片
     * @param picture
     */
    @Override
    public void pictureService(Picture picture) {
        pictureMapper.insertSelective(picture);
    }
}
