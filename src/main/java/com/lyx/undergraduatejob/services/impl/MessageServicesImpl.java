package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.*;
import com.lyx.undergraduatejob.pojo.*;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.services.IMessageServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MessageServicesImpl
 * @Date 2019/12/18
 * @Version V1.0
 **/
@Service
public class MessageServicesImpl implements IMessageServices {

    Logger logger = LoggerFactory.getLogger(IMessageServices.class);

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    AutCompanyMapper companyMapper;

    @Autowired
    AutStudentMapper studentMapper;

    @Autowired
    JobMapper jobMapper;

    @Autowired
    ResumeMapper resumeMapper;

    @Autowired
    ComplaintMapper complaintMapper;

    @Override
    public PageInfo queryMessage(Integer indexpage,Message message,@RequestParam(required = false)String keyword) {
        if (indexpage == null){
            indexpage = 1;
        }
        if (keyword == null){
            keyword = "";
        }
        MessageExample example = new MessageExample();
        MessageExample.Criteria criteria = example.createCriteria();
        if (message.getId() != null){
            criteria.andIdEqualTo(message.getId());
        }
        criteria.andMessageTitleLike(keyword+"%");
        if (message.getReceiverId() != null && message.getReceiverType() != null){
            criteria.andReceiverIdEqualTo(message.getReceiverId()).andReceiverTypeEqualTo(message.getReceiverType());
        }
        if (message.getStatus() != null){
            criteria.andStatusEqualTo(message.getStatus());
        }
        PageHelper.startPage(indexpage,10);
        List<Message> messages = messageMapper.selectByExample(example);
        PageInfo info = new PageInfo(messages,5);
        return info;
    }

    @Override
    public List<Message> queryMessage(Message message, String keyword) {
        return null;
    }

    @Override
    public Map<String,Object> addMessage(Message message) {
        Map<String,Object> res = new HashMap<>();
        message.setCreateTime(new Date());
        message.setStatus(1);
        message.setReceiverStatus(0);
        int insert = messageMapper.insert(message);
        if (insert > 0){
            res.put(StaticPool.SUCCESS,"成功");
        }else {
            logger.warn("insert error "+message);
            res.put(StaticPool.ERROR,"失败");
        }
        return res;
    }

    @Override
    public Map<String,Object> deleteInfo(Message message) {
        Map<String,Object> res = new HashMap<>();
        message.setStatus(0);
        int i = messageMapper.updateByPrimaryKeySelective(message);
        if (i > 0){
            res.put(StaticPool.SUCCESS,"成功");
        }else {
            logger.warn("服务繁忙 "+message);
            res.put(StaticPool.ERROR,"失败");
        }
        return res;
    }

    @Override
    public Map<String, Object> showMessage() {
        Map<String,Object> map = new HashMap<>();
        AutCompanyExample autCompanyExample = new AutCompanyExample();
        AutCompanyExample.Criteria criteria = autCompanyExample.createCriteria();
        criteria.andStatusEqualTo(2);
        List<AutCompany> autCompanies = companyMapper.selectByExample(autCompanyExample);

        AutStudentExample autStudentExample = new AutStudentExample();
        AutStudentExample.Criteria criteria1 = autStudentExample.createCriteria();
        criteria1.andStatusEqualTo(2);
        List<AutStudent> autStudents = studentMapper.selectByExample(autStudentExample);

        JobExample jobExample = new JobExample();
        JobExample.Criteria criteria2 = jobExample.createCriteria();
        criteria2.andAulStatusEqualTo(0);
        List<Job> jobs = jobMapper.selectByExample(jobExample);

        ResumeExample resumeExample = new ResumeExample();
        ResumeExample.Criteria criteria3 = resumeExample.createCriteria();
        criteria3.andAulStatusEqualTo(0);
        List<Resume> resumes = resumeMapper.selectByExample(resumeExample);

        ComplaintExample example = new ComplaintExample();
        ComplaintExample.Criteria criteria4 = example.createCriteria();
        criteria4.andDealStatusEqualTo(0);
        List<Complaint> complaints = complaintMapper.selectByExample(example);

        map.put("autCompanies",autCompanies);
        map.put("autStudents",autStudents);
        map.put("jobs",jobs);
        map.put("resumes",resumes);
        map.put("complaints",complaints);

        return map;
    }


}
