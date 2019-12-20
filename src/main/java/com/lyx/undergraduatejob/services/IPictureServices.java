package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.Picture;
import com.lyx.undergraduatejob.search.entity.PictureSerchEntity;

import java.util.List;
import java.util.Map;

public interface IPictureServices {
    /**
     * 前台通过ownerid，owner_type，picture_type三个参数来查询状态为1图片
     * @param ownerid
     * @param owner_type
     * @param picture_type
     * @return
     */
    List<Picture> queryPicturebyOwnerid(Integer ownerid,Integer owner_type,Integer picture_type);


    /**
     * 前台通过ownerid，owner_type，picture_type三个参数来查询状态为1图片
     * @param pictureSerchEntity
     * @return
     */
    PageInfo queryPicturebyOwneridbyAdmin(Integer indexpage,PictureSerchEntity pictureSerchEntity);
    /**
     * 添加图片
     * @param p
     * @return
     */
    Map<String,String> addPicture(Picture p,Integer userid,Integer companyid);

    /**
     * 后台修改图片
     * @param p
     * @return
     */
    Map<String,String> updatePictuer(Picture p);

    /**
     * 用户修改自己的学生认证图片
     * @param p
     * @param userid
     * @return
     */
    Map<String,String> updatePictuerbyUser(Picture p,Integer userid);

    /**
     * 企业修改自己的营业认证图片和企业相册
     * @param p
     * @param Companyid
     * @return
     */
    Map<String,String> updatePictuerbyCompany(Picture p,Integer Companyid);
}
