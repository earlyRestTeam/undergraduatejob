package com.lyx.undergraduatejob.services.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.ResumeMapper;
import com.lyx.undergraduatejob.pojo.Resume;
import com.lyx.undergraduatejob.pojo.ResumeExample;
import com.lyx.undergraduatejob.search.entity.RentValueBlock;
import com.lyx.undergraduatejob.search.entity.ResumeSearchEntity;
import com.lyx.undergraduatejob.services.IResumeServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResumeServicesImp implements IResumeServices {

    Logger logger = LoggerFactory.getLogger(IResumeServices.class);

    @Autowired
    ResumeMapper resumeMapper;


    /**
     * 搜索栏搜索招聘信息
     * 按工作职位，结算方式，工作区域，工作时间，发布时间
     *
     * @param indexPage
     * @param resumeSearchEntity
     * @return
     */
    @Override
    public PageInfo<Resume> queryResumeByKey(Integer indexPage, int pageSize, ResumeSearchEntity resumeSearchEntity) {
        indexPage = (indexPage != null) ? indexPage : 1;
        PageHelper.startPage(indexPage,pageSize);

        ResumeExample example = new ResumeExample();
        ResumeExample.Criteria criteria = example.createCriteria();

        criteria.andStatusEqualTo(1);
        criteria.andPushStatusEqualTo(1);
        criteria.andAulStatusEqualTo(2);

        //职位
        String job = resumeSearchEntity.getJob();
        if(job != null)
            criteria.andJobEqualTo(job);
        //年龄
        RentValueBlock ageArea = RentValueBlock.getAgeArea(resumeSearchEntity.getAge());
        if(ageArea.getMin() > 0)
            criteria.andAgeGreaterThan(ageArea.getMin());
        if(ageArea.getMax() > 0)
            criteria.andAgeLessThan(ageArea.getMax());
        //性别
        Integer gender = resumeSearchEntity.getGender();
        if(gender != null)
            criteria.andGenderEqualTo(gender);
        //支付方式
        Integer closeAnAccount = resumeSearchEntity.getCloseAnAccount();
        if(closeAnAccount != null)
            criteria.andCloseTypeEqualTo(closeAnAccount);

        //兼职/全职
        Integer partFull = resumeSearchEntity.getPartFull();
        if(partFull != null)
            criteria.andPushStatusEqualTo(partFull);

        String jobAddress = resumeSearchEntity.getJobAddress();
        if(jobAddress != null)
            criteria.andJobAddressEqualTo(jobAddress);

        //薪水区间
        RentValueBlock rentValueBlock = RentValueBlock.getRentValueBlock(resumeSearchEntity.getSalaryArea());
        if(rentValueBlock.getMin() > 0)
            criteria.andSalaryGreaterThan(rentValueBlock.getMin());
        if(rentValueBlock.getMax() > 0)
            criteria.andSalaryLessThan(rentValueBlock.getMax());

        //排序的字段+降序/升序
        example.setOrderByClause(resumeSearchEntity.getOrderExample()+resumeSearchEntity.getOrder());

        List<Resume> resumeList = resumeMapper.selectByExample(example);
        PageInfo<Resume> pageInfo = new PageInfo<>(resumeList,5);
        return pageInfo;
    }

    /**
     * 管理员查询未审核、通过、未通过、冻结、启用
     *
     * @param indexPage
     * @param pageSize
     * @param status
     * @param autStatus
     * @return
     */
    @Override
    public PageInfo<Resume> queryResumeByAdminExample(Integer indexPage, int pageSize, Integer status, Integer autStatus) {
        indexPage = (indexPage != null) ? indexPage : 1;
        PageHelper.startPage(indexPage,pageSize);
        ResumeExample example = new ResumeExample();
        ResumeExample.Criteria criteria = example.createCriteria();

        //查询条件
        if(status != null)
            criteria.andStatusEqualTo(status);
        if(autStatus != null)
            criteria.andAulStatusEqualTo(autStatus);

        List<Resume> resumeList = resumeMapper.selectByExample(example);
        PageInfo<Resume> pageInfo = new PageInfo<>(resumeList);
        return pageInfo;
    }


    /**
     * 通过简历id查看发布的简历
     *
     * @param resumeId
     * @return
     */
    @Override
    public Resume queryResumeById(int resumeId) {
        return resumeMapper.selectByPrimaryKey(resumeId);
    }

    /**
     * 通过用户id查看个人简历(未失效的简历)
     * @param userId
     * @return
     */
    @Override
    public Resume queryResumeByUserId(int userId) {
        ResumeExample example = new ResumeExample();
        ResumeExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andStatusEqualTo(StaticPool.RESUME_OK);

        List<Resume> resumes = resumeMapper.selectByExample(example);

        if(resumes == null || resumes.size() != 1){
            logger.warn("服务器繁忙"+resumes);
            return null;
        }
        return resumes.get(0);
    }

    /**
     * 添加个人简历
     * @param r
     * @return
     */
    @Override
    public Map<String,String> addResume(Resume r,Integer userId) {
        Map<String,String> result = new HashMap<>();

        if(r == null){
            logger.error("非法访问！！！用户id："+userId);
            result.put(StaticPool.ERROR,"服务器繁忙，添加失败");
            return result;
        }

        //数据库已存在简历
        if(r.getUserId() != userId && queryResumeByUserId(userId) != null){
            logger.error("非法访问！！！用户id："+userId);
            result.put(StaticPool.ERROR,"简历修改失败");
            return result;
        }
        if(queryResumeByUserId(userId) != null){
            logger.error("非法访问！！！用户id："+userId);
            result.put(StaticPool.ERROR,"简历修改失败");
            return result;
        }
        //设置id我空，防止非法操作
        r.setId(null);
        int insert = resumeMapper.insert(r);
        if(insert > 0){
            result.put(StaticPool.SUCCESS,"简历添加成功");
        }else {
            logger.warn("服务器繁忙，简历添加失败！"+r);
            result.put(StaticPool.ERROR,"简历添加失败");
        }

        return result;
    }


    /**
     * 用户改变个人简历
     * 需要安全验证
     * @param r
     * @return
     */
    public Map<String,String> updateResumeByUser(Resume r,Integer userId) {
        Map<String,String> result = new HashMap<>();
        Resume resume = resumeMapper.selectByPrimaryKey(r.getId());
        if(resume == null){
            logger.error("简历不存在。非法访问！！！用户id："+userId);
            result.put(StaticPool.ERROR,"服务器繁忙，简历修改失败");
            return result;
        }

        if(resume.getUserId() == userId){
            System.out.println(r.getPushStatus());
            int i = resumeMapper.updateByPrimaryKeySelective(r);
            if(i > 0){
                result.put(StaticPool.SUCCESS,"简历修改成功");
            }else {
                logger.warn("服务器繁忙，简历修改失败！"+r);
                result.put(StaticPool.ERROR,"简历修改失败");
            }
        }else {
            logger.error("简历和访问用户不匹配非法访问！！！用户id："+userId);
            result.put(StaticPool.ERROR,"服务器繁忙，简历修改失败");
        }


        return result;
    }

    /**
     * 用户删除个人简历
     *
     * @param resumeId
     * @param userId
     * @return
     */
    @Override
    public Map<String, String> updateDelResume(Integer resumeId, Integer userId) {
        Resume r = new Resume();
        r.setId(resumeId);
        r.setUserId(userId);
        r.setStatus(0);
        return updateResumeByUser(r,userId);
    }


    /**
     * 发布个人简历
     * @param resumeId
     * @param userId
     * @return
     */
    @Override
    public Map<String,String> updatePushResume(Integer resumeId, Integer userId){
        Resume r = new Resume();
        r.setId(resumeId);
        r.setUserId(userId);
        r.setPushStatus(1);
        return updateResumeByUser(r,userId);
    }

    /**
     * 取消发布个人简历
     * @param resumeId
     * @param userId
     * @return
     */
    @Override
    public Map<String, String> updateDisPushResume(Integer resumeId, Integer userId) {
        Resume r = new Resume();
        r.setId(resumeId);
        r.setUserId(resumeId);
        r.setPushStatus(0);

        return updateResumeByUser(r, userId);
    }



    /**
     * 管理员改变个人简历
     * @param r
     * @return
     */
    @Override
    public Map<String,String> updateResumeByAdmin(Resume r) {
        Map<String,String> result = new HashMap<>();

        int i = resumeMapper.updateByPrimaryKeySelective(r);
        if(i > 0){
            result.put(StaticPool.SUCCESS,"简历修改成功");
        }else {
            logger.warn("服务器繁忙，简历修改失败！"+r);
            result.put(StaticPool.ERROR,"简历修改失败");
        }


        return result;
    }





    /**
     * 管理员永久删除个人简历
     * @param
     * @return
     */
    @Override
    public Map<String,String> deleteResumeByAdmin(Integer resumeId) {
        Map<String,String> result = new HashMap<>();
        int i = resumeMapper.deleteByPrimaryKey(resumeId);

        if(i > 0){
            result.put(StaticPool.SUCCESS,"简历删除成功");
        }else {
            logger.warn("服务器繁忙，简历删除失败！");
            result.put(StaticPool.ERROR,"简历删除失败");
        }
        return result;
    }

//    /**
//     * 发布/取消发布个人简历
//     * @param
//     * @return
//     */
//    @Override
//    public Map<String,String> updateResumeStatus(Integer resumeId, Integer pushStatus) {
//        Map<String,String> result = new HashMap<>();
//
//        ResumeExample example = new ResumeExample();
//        ResumeExample.Criteria criteria = example.createCriteria();
//        criteria.andUserIdEqualTo(userId);
//        criteria.andStatusEqualTo(StaticPool.RESUME_OK);
//
//        List<Resume> resumes = resumeMapper.selectByExample(example);
//
//        if(resumes == null || resumes.size() != 1){
//            logger.warn("服务器繁忙"+resumes);
//            result.put(StaticPool.ERROR,"操作失败");
//        }
//
//
//        resume.setStatus(pushStatus);
//
////        return resumeMapper.updateByPrimaryKey(resume) > 0;
//        return result;
//    }

//    @Override
//    public boolean updateDisResume(Integer userId) {
//        return false;
//    }


}
