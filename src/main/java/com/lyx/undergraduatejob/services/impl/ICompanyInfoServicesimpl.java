package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.CommentMapper;
import com.lyx.undergraduatejob.mapper.CompanyMapper;
import com.lyx.undergraduatejob.pojo.Comment;
import com.lyx.undergraduatejob.pojo.CommentExample;
import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.CompanyExample;
import com.lyx.undergraduatejob.services.ICompanyInfoServices;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ICompanyInfoServicesimpl implements ICompanyInfoServices {

    Logger logger = LoggerFactory.getLogger(IJobServices.class);

    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    CommentMapper commentMapper;

    /**
     * 浏览企业信息(详情)
     * @param companyId
     * @return
     */
    @Override
    public Company queryCompanyById(Integer companyId) {
        Company company = companyId == null ? null : companyMapper.selectByPrimaryKey(companyId);
        return company;
    }

    /**
     * 查看企业评论
     * @param indexpage
     * @param companyId
     * @return
     */
    @Override
    public PageInfo<Comment> queryComment(Integer indexpage,Integer companyId) {
        indexpage = indexpage == null? 1 :indexpage ;
        if (companyId == null){
            logger.warn("查询公司评论失败，公司id不能为空");
            return null;
        }
        CommentExample example = new CommentExample();
        example.or().andCompanyIdEqualTo(companyId);
        PageHelper.startPage(indexpage,10);
        List<Comment> comments = commentMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(comments,5);
        return  pageInfo;
    }

    /**
     * 新增企业资料
     * @param c
     * @return
     */
    @Override
    public Map<String,String> addCompanyInfo(Company c) {
        Map<String,String> result = new HashMap<>();
        CompanyExample example = new CompanyExample();
        example.or().andCompanyNameEqualTo(c.getCompanyName());
        example.or().andUserIdEqualTo(c.getUserId());
        List<Company> companies = companyMapper.selectByExample(example);
        if (companies!=null&&companies.size()>0){
            result.put(StaticPool.ERROR,"公司名称已存在或用户已创建过公司");
            logger.error("插入公司数据失败，公司名称已存在或用户已创建过公司");
            return result;
        }
        int res = companyMapper.insert(c);
        if (res>0){
            logger.info("创建公司成功");
            result.put(StaticPool.SUCCESS,"创建公司成功");
        }else {
            logger.warn("系统繁忙,插入公司失败");
            result.put(StaticPool.ERROR,"系统繁忙,插入公司失败");

        }
        return result;
    }

    /**
     * 查看我的企业资料
     * @param userId
     * @return
     */
    @Override
    public Company queryCompanyByUserId(Integer userId) {
        CompanyExample example = new CompanyExample();
        example.or().andUserIdEqualTo(userId);
        Company company = userId == null ? null : companyMapper.selectByExample(example).get(0);
        return company;
    }

    /**
     * 修改我的企业资料
     * @param c
     * @return
     */
    @Override
    public Map<String, String> updateCompanyInfo(Company c) {
        Map<String,String> result = new HashMap<>();
       if (c.getId()==null){
           logger.error("修改失败，公司不存在");
           result.put(StaticPool.ERROR,"公司不存在");
           return result;
       }
        int res = companyMapper.updateByPrimaryKeySelective(c);
       if (res>0){
           logger.info("修改公司资料成功");
           result.put(StaticPool.SUCCESS,"修改公司资料成功！");
       }else {
           logger.warn("修改公司数据失败！系统繁忙");
           result.put(StaticPool.ERROR,"修改公司数据失败！系统繁忙");
       }
        return result;
    }

    /**
     * 按公司名称，公司类型，公司状态，公司认证状态查看公司列表（传入什么参数就AND加入条件）
     * @param indexpage
     * @param company
     * @return
     */
    @Override
    public PageInfo queryCompanyList(Integer indexpage,Company company) {
        Map<String,String> result = new HashMap<>();
        indexpage = indexpage == null? 1 :indexpage ;
        CompanyExample example = new CompanyExample();
        String companyName = company.getCompanyName();
        String companyType = company.getCompanyType();
        Integer status = company.getStatus();
        Integer aulStatus = company.getAulStatus();
        CompanyExample.Criteria criteria = example.createCriteria();
        if (companyName != null &&!"null".equals(companyName) &&!"".equals(companyName)){
            criteria.andCompanyNameLike(companyName+"%");
        }
        if (companyType != null &&!"null".equals(companyType) &&!"".equals(companyType)){
            criteria.andCompanyTypeLike(companyType+"%");
        }
        if (status!=null){
            criteria.andStatusEqualTo(status);
        }
        if (aulStatus!=null){
            criteria.andAulStatusEqualTo(aulStatus);
        }
        PageHelper.startPage(indexpage,10);
        List<Company> companies = companyMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(companies,5);
        return pageInfo;
    }
}
