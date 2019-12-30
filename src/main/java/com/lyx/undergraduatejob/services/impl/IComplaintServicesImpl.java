package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.CompanyMapper;
import com.lyx.undergraduatejob.mapper.ComplaintMapper;
import com.lyx.undergraduatejob.mapper.JobMapper;
import com.lyx.undergraduatejob.pojo.*;
import com.lyx.undergraduatejob.services.ICommentServices;
import com.lyx.undergraduatejob.services.IComplaintServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :Yang Jiahong
 * @date :2019/12/18 15:38
 */
@Service
public class IComplaintServicesImpl implements IComplaintServices {

    Logger logger = LoggerFactory.getLogger(IComplaintServices.class);

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    JobMapper jobMapper;

    @Autowired
    ComplaintMapper complaintMapper;


    /**
     * 投诉此职位
     * @param complaint
     * @return
     */
    @Override
    public Map<String, String> ComplaintJob(Complaint complaint) {

        Integer type = complaint.getComplaintType();

        int loginuserid = 1; //测试id

        Map<String,String> res = new HashMap<>();

        Job job = jobMapper.selectByPrimaryKey(complaint.getComplaintId());

        if(job.getStatus() == 1 && job.getAulStatus() == 2 && loginuserid == complaint.getUserId()){

            int result = complaintMapper.insert(complaint);
            if(result > 0){
                res.put(StaticPool.SUCCESS,"投诉成功");
            }else{
                logger.warn("insert erro"  + complaint);
                res.put(StaticPool.ERROR,"投诉失败");
            }
            return res;
        }else{
            logger.error("登录异常 + login erro");
            res.put(StaticPool.ERROR,"投诉失败");
            return res;
        }
    }


    /**
     * 投诉此企业
     * @param complaint
     * @return
     */
    @Override
    public Map<String, String> ComplaintCompany(Complaint complaint) {
        Map<String,String> res = new HashMap<>();
        int loginuserid = 1; //测试id

        Company company = companyMapper.selectByPrimaryKey(complaint.getComplaintId());

        if(company.getStatus() == 1 && company.getAulStatus() == 1 && loginuserid == complaint.getUserId()){
            int result = complaintMapper.insert(complaint);
            if(result > 0){
                res.put(StaticPool.SUCCESS,"投诉成功");
            }else{
                logger.warn("insert erro"  + complaint);
                res.put(StaticPool.ERROR,"投诉失败");
            }
            return res;
        }else{
            logger.error("登录异常 + login erro");
            res.put(StaticPool.ERROR,"投诉失败");
            return res;
        }
    }


    /**
     * 查看历史投诉
     * @param indexpage
     * @param userId
     * @return
     */
    @Override
    public PageInfo<Complaint> queryComplaint(Integer indexpage, Integer userId) {
        ComplaintExample complaintExample = new ComplaintExample();
        ComplaintExample.Criteria criteria = complaintExample.createCriteria();

        if(userId != null){
            criteria.andUserIdEqualTo(userId);
        }

        indexpage = indexpage == null ? 1 : indexpage;
        PageHelper.startPage(indexpage,5);

        List<Complaint> complaintList = complaintMapper.selectByExample(complaintExample);

        PageInfo<Complaint> info = new PageInfo<>(complaintList,3);

        return info;
    }

    /**
     * 前台查询
     * @param complaint
     * @return
     */
    @Override
    public List<Complaint> queryCommplaint(Complaint complaint) {
        ComplaintExample example = new ComplaintExample();
        ComplaintExample.Criteria criteria = example.createCriteria();
        if (complaint.getUserId() != null){
            criteria.andUserIdEqualTo(complaint.getUserId());
        }
        if (complaint.getComplaintId() != null){
            criteria.andComplaintIdEqualTo(complaint.getComplaintId());
        }
        List<Complaint> complaints = complaintMapper.selectByExample(example);
        return complaints;
    }

    /**
     * 后台
     * @param complaintId
     * @return
     */
    public Complaint queryCommplaint(Integer complaintId) {
        ComplaintExample example = new ComplaintExample();
        ComplaintExample.Criteria criteria = example.createCriteria();
        if (complaintId != null){
            criteria.andComplaintIdGreaterThanOrEqualTo(complaintId);
        }
        Complaint complaint = complaintMapper.selectByPrimaryKey(complaintId);
        return complaint;
    }

    /**
     * 分页
     * @param indexpage
     * @param complaint
     * @return
     */
    @Override
    public PageInfo queryCommplaint(Integer indexpage,Complaint complaint) {
        ComplaintExample example = new ComplaintExample();
        ComplaintExample.Criteria criteria = example.createCriteria();
        if (indexpage == null){
            indexpage = 1;
        }
        if (complaint.getUserId() != null){
            criteria.andUserIdEqualTo(complaint.getUserId());
        }
        if (complaint.getDealStatus() != null){
            criteria.andDealStatusEqualTo(complaint.getDealStatus());
        }
        if (complaint.getComplaintId() != null){
            criteria.andComplaintIdEqualTo(complaint.getComplaintId());
        }
        PageHelper.startPage(indexpage,10);
        List<Complaint> complaints = complaintMapper.selectByExample(example);
        PageInfo info = new PageInfo(complaints,5);
        return info;
    }

    @Override
    public Map<String, Object> updateCommplaint(Complaint complaint) {
        Map<String,Object> res = new HashMap<>();
        complaint.setDealStatus(1);
        int i = complaintMapper.updateByPrimaryKeySelective(complaint);
        if (i > 0){
            res.put(StaticPool.SUCCESS,"成功");
        }else{
            logger.warn("update error"+complaint);
            res.put(StaticPool.ERROR,"失败");
        }
        return res;
    }

}
