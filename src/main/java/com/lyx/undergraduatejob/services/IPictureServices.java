package com.lyx.undergraduatejob.services;

import com.lyx.undergraduatejob.pojo.Picture;

import java.util.List;
import java.util.Map;

public interface IPictureServices {
    /**
     * 通过ownerid，owner_type，picture_type三个参数来查询图片
     * @param ownerid
     * @param owner_type
     * @param picture_type
     * @return
     */
    List<Picture> queryPicturebyOwnerid(Integer ownerid,Integer owner_type,Integer picture_type);

    /**
     * 添加图片
     * @param p
     * @return
     */
    Map<String,String> addPicture(Picture p);

    /**
     * 修改图片
     * @param p
     * @return
     */
    Map<String,String> updatePictuer(Picture p);


}
