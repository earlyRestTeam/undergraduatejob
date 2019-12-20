package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.AutCompanyMapper;
import com.lyx.undergraduatejob.mapper.AutStudentMapper;
import com.lyx.undergraduatejob.mapper.CompanyMapper;
import com.lyx.undergraduatejob.mapper.PictureMapper;
import com.lyx.undergraduatejob.pojo.*;
import com.lyx.undergraduatejob.search.entity.PictureSerchEntity;
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

    Logger logger = LoggerFactory.getLogger(IPictureServices.class);
    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    AutStudentMapper autStudentMapper;

    @Autowired
    AutCompanyMapper autCompanyMapper;

    @Autowired
    CompanyMapper companyMapper;


    /**
     * 前台通过ownerid，owner_type，picture_type三个参数来查询状态为1图片
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
        example.or().andOwnerIdEqualTo(ownerid)
                .andOwnerTypeEqualTo(owner_type)
                .andPictureTypeEqualTo(picture_type).andStatusEqualTo(1);
        return pictureMapper.selectByExample(example);
    }

    /**
     * 后台通过ownerid，owner_type，picture_type,status三个参数来查询图片
     * @param pictureSerchEntity
     * @return
     */
    @Override
    public PageInfo queryPicturebyOwneridbyAdmin(Integer indexpage,PictureSerchEntity pictureSerchEntity) {
        if (pictureSerchEntity.getOwnerId()==null
                ||pictureSerchEntity.getOwnerType()==null
                ||pictureSerchEntity.getPictureType()==null
                ||pictureSerchEntity.getStatus()==null){
            logger.warn("查询图片失败，ownerid，owner_type，picture_type,status都不能为空");
            return null;
        }
        PictureExample example = new PictureExample();
        example.or().andOwnerIdEqualTo(pictureSerchEntity.getOwnerId())
                .andOwnerTypeEqualTo(pictureSerchEntity.getOwnerType())
                .andPictureTypeEqualTo(pictureSerchEntity.getPictureType())
                .andStatusEqualTo(pictureSerchEntity.getStatus());
        example.setOrderByClause(pictureSerchEntity.getOrderExample()+pictureSerchEntity.getOrder());
        PageHelper.startPage(indexpage,10);
        List<Picture> pictures = pictureMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(pictures,5);
        return pageInfo;
    }

    /**
     * 添加图片
     * @param p
     * @return
     */
    @Override
    public Map<String, String> addPicture(Picture p,Integer userid,Integer comanyid) {
        Map<String,String> result = new HashMap<>();
        p.setId(null);
        switch (p.getPictureType()){
            case 1:
                //判断是不是用户插入他的学生认证图片
                if (!userid.equals(autStudentMapper.selectByPrimaryKey(p.getOwnerId()).getUserId())){
                    logger.error("添加失败，用户"+userid+"非法访问");
                    result.put(StaticPool.ERROR,"添加失败，用户"+userid+"非法访问");
                    return result;
                }
            case 2:

                if (!comanyid.equals(autCompanyMapper.selectByPrimaryKey(p.getOwnerId()).getCompanyId())){
                    logger.error("添加失败，用户"+userid+"非法访问");
                    result.put(StaticPool.ERROR,"添加失败，用户"+userid+"非法访问");
                    return result;
                }
            case 3:
                if (!comanyid.equals(p.getOwnerId())){
                    logger.error("添加失败，用户"+userid+"非法访问");
                    result.put(StaticPool.ERROR,"添加失败，用户"+userid+"非法访问");
                    return result;
                }
        }
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
     * 后台修改图片
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

    /**
     * 用户修改自己的申请认证图片
     * @param p
     * @return
     */
    @Override
    public Map<String, String> updatePictuerbyUser(Picture p,Integer userid) {
        Map<String,String> result = new HashMap<>();
        //找到用户要修改的图片
        Picture pictures = pictureMapper.selectByPrimaryKey(p.getId());
        //再找到图片所属的申请表以及查看申请表是不是属于当前用户
        AutStudent autStudent = autStudentMapper.selectByPrimaryKey(pictures.getOwnerId());
            if (!userid.equals(autStudent.getUserId())){
                logger.error("修改失败，用户"+userid+"非法访问");
                result.put(StaticPool.ERROR,"修改失败，用户"+userid+"非法访问");
                return result;
        }
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

    /**
     * 企业修改自己的图片
     * @param p
     * @return
     */
    @Override
    public Map<String, String> updatePictuerbyCompany(Picture p,Integer companyid) {
        Map<String,String> result = new HashMap<>();
        //找到用户要修改的图片
        Picture pictures = pictureMapper.selectByPrimaryKey(p.getId());
        //再找到图片所属的公司
        Company company = companyMapper.selectByPrimaryKey(pictures.getOwnerId());
        AutCompany autCompany = autCompanyMapper.selectByPrimaryKey(pictures.getOwnerId());
        if (!companyid.equals(company.getId())){
            logger.error("修改失败，用户"+companyid+"非法访问");
            result.put(StaticPool.ERROR,"修改失败，用户"+companyid+"非法访问");
            return result;
            //或再找到图片所属的营业认证表，判断当前营业认证表是不是属于当前公司
        }else if (!company.equals(autCompany.getCompanyId())){
            logger.error("修改失败，用户"+companyid+"非法访问");
            result.put(StaticPool.ERROR,"修改失败，用户"+companyid+"非法访问");
            return result;
        }
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
