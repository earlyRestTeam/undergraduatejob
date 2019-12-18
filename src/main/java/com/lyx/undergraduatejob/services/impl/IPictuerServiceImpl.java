package com.lyx.undergraduatejob.services.impl;

import com.lyx.undergraduatejob.mapper.PictureMapper;
import com.lyx.undergraduatejob.pojo.Picture;
import com.lyx.undergraduatejob.pojo.PictureExample;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.services.IPictureServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IPictuerServiceImpl implements IPictureServices {

    Logger logger = LoggerFactory.getLogger(IJobServices.class);
    @Autowired
    PictureMapper pictureMapper;


    /**
     * 通过ownerid，owner_type，picture_type三个参数来查询图片
     * @param ownerid
     * @param owner_type
     * @param picture_type
     * @return
     */
    @Override
    public List<Picture> queryPicturebyOwnerid(Integer ownerid, Integer owner_type, Integer picture_type) {
        if (ownerid==null||owner_type==null||picture_type==null){
            logger.warn("查询图片失败，ownerid，owner_type，picture_type都不能为空");
            return null;
        }
        PictureExample example = new PictureExample();
        example.or().andOwnerIdEqualTo(ownerid).andOwnerTypeEqualTo(owner_type).andPictureTypeEqualTo(picture_type);
        return pictureMapper.selectByExample(example);
    }

    /**
     * 添加图片
     * @param p
     * @return
     */
    @Override
    public Map<String, String> addPicture(Picture p) {
        Map<String,String> result = new HashMap<>();
        int res = pictureMapper.insert(p);
        if (res>0){
            logger.info("添加图片成功");
            result.put(StaticPool.SUCCESS,"添加图片成功");
        }else {
            logger.warn("系统繁忙,添加图片失败");
            result.put(StaticPool.ERROR,"系统繁忙，添加图片失败");

        }
        return result;
    }

    /**
     * 修改图片
     * @param p
     * @return
     */
    @Override
    public Map<String, String> updatePictuer(Picture p) {
        Map<String,String> result = new HashMap<>();
        int res = pictureMapper.updateByPrimaryKeySelective(p);
        if (res>0){
            logger.info("修改图片资料成功");
            result.put(StaticPool.SUCCESS,"修改图片资料成功！");
        }else {
            logger.warn("修改图片数据失败！系统繁忙");
            result.put(StaticPool.ERROR,"修改图片数据失败！系统繁忙");
        }
        return result;
    }
}
