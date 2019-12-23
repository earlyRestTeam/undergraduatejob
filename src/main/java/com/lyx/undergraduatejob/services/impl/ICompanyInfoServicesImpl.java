package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.AdminMapper;
import com.lyx.undergraduatejob.mapper.CommentMapper;
import com.lyx.undergraduatejob.mapper.CompanyMapper;
import com.lyx.undergraduatejob.pojo.*;
import com.lyx.undergraduatejob.search.entity.CompanySerchEntity;
import com.lyx.undergraduatejob.services.ICompanyInfoServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ICompanyInfoServicesImpl implements ICompanyInfoServices {

    Logger logger = LoggerFactory.getLogger(ICompanyInfoServices.class);

    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    AdminMapper adminMapper;

    /**
     * 浏览企业信息(详情)
     * @param companyId
     * @return
     */
    @Override
    public Company queryCompanyById(Integer companyId) {
         if (companyId==null){
             return new Company();
         }
        return companyMapper.selectByPrimaryKey(companyId);
    }

    /**
     * 前台查看企业评论
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
        example.or().andCompanyIdEqualTo(companyId).andStatusEqualTo(1);
        PageHelper.startPage(indexpage,10);
        List<Comment> comments = commentMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(comments,5);
        return  pageInfo;
    }

    /**
     * 后台查看企业评论
     * @param indexpage
     * @param companyId
     * @return
     */
    @Override
    public PageInfo<Comment> queryComment(Integer indexpage,Integer companyId,Integer status) {
        indexpage = indexpage == null? 1 :indexpage ;
        if (companyId == null||status ==null){
            logger.warn("查询公司评论失败，公司id不能为空,或评论状态不能为空");
            return null;
        }
        CommentExample example = new CommentExample();
        example.or().andCompanyIdEqualTo(companyId).andStatusEqualTo(status);
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
    public Map<String,String> addCompanyInfo(Company c,Integer userid) {
        Map<String,String> result = new HashMap<>();
        CompanyExample example = new CompanyExample();
        example.or().andUserIdEqualTo(userid);
        c.setId(null);
        List<Company> companies = companyMapper.selectByExample(example);
        if (!companies.isEmpty()&&companies.size()>0){
            result.put(StaticPool.ERROR,"非法访问！用户"+userid+"已经创建过公司");
            logger.error("非法访问！用户"+userid+"已经创建过公司");
            return result;
        }else if (!userid.equals(c.getUserId())){
            result.put(StaticPool.ERROR,"非法访问！用户"+userid+"不能给别人创建公司");
            logger.error("非法访问！用户"+userid+"不能给别人创建公司");
            return result;
        }
        int res = companyMapper.insert(c);
        if (res>0){
            logger.info("创建公司成功");
            result.put(StaticPool.SUCCESS,"创建公司成功");
        }else {
            logger.warn("系统繁忙,创建公司失败");
            result.put(StaticPool.ERROR,"系统繁忙,创建公司失败");

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
        List<Company> companies = companyMapper.selectByExample(example);
        if (companies.isEmpty()){
            return new Company();
        }
        return companies.get(0);
    }

    /**
     * 前台修改我的企业资料（Controller需要验证用户是不是当前企业所属的用户）
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
       //验证用户修改的公司信息是不是用户自己的，不是则是非法访问
       CompanyExample companyExample = new CompanyExample();
       companyExample.or().andUserIdEqualTo(c.getUserId()).andIdEqualTo(c.getId());
        List<Company> companies = companyMapper.selectByExample(companyExample);
        if (companies.isEmpty()){
            logger.error("修改失败，用户"+c.getUserId()+"非法访问!");
            result.put(StaticPool.ERROR,"修改失败，用户"+c.getUserId()+"非法访问!");
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
     * 后台修改企业资料
     * @param c
     * @return
     */
    @Override
    public Map<String, String> updateCompanyInfobyAdmin(Company c) {
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
     * 前台按公司名称，公司类型，公司状态为1，公司认证状态查看公司列表（传入什么参数就AND加入条件）
     * @param indexpage
     * @param companySerchEntity
     * @return
     */
    @Override
    public PageInfo queryCompanyList(Integer indexpage, CompanySerchEntity companySerchEntity) {
        Map<String,String> result = new HashMap<>();
        indexpage = indexpage == null? 1 :indexpage ;
        CompanyExample example = new CompanyExample();
        Integer companyId = companySerchEntity.getId();
        String companyName = companySerchEntity.getCompanyName();
        String companyType = companySerchEntity.getCompanyType();
        Integer aulStatus = companySerchEntity.getAulStatus();
        CompanyExample.Criteria criteria = example.createCriteria();
        if(companyId !=null){
            criteria.andIdEqualTo(companyId);
        }
        if (companyName != null &&!"null".equals(companyName) &&!"".equals(companyName)){
            criteria.andCompanyNameLike(companyName+"%");
        }
        if (companyType != null &&!"null".equals(companyType) &&!"".equals(companyType)){
            criteria.andCompanyTypeLike(companyType+"%");
        }
        criteria.andStatusEqualTo(1);
        if (aulStatus!=null){
            criteria.andAulStatusEqualTo(aulStatus);
        }
        example.setOrderByClause(companySerchEntity.getOrderExample()+companySerchEntity.getOrder());
        PageHelper.startPage(indexpage,2);
        List<Company> companies = companyMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(companies,5);
        return pageInfo;
    }

    /**
     * 前台按公司名称，公司类型，公司状态为1，公司认证状态查看公司列表（传入什么参数就AND加入条件）
     * @param indexpage
     * @param companySerchEntity
     * @return
     */
    @Override
    public PageInfo queryCompanyListByAdmin(Integer indexpage, CompanySerchEntity companySerchEntity) {
        Map<String,String> result = new HashMap<>();
        indexpage = indexpage == null? 1 :indexpage ;
        CompanyExample example = new CompanyExample();
        Integer companyId = companySerchEntity.getId();
        String companyName = companySerchEntity.getCompanyName();
        String companyType = companySerchEntity.getCompanyType();
        Integer status = companySerchEntity.getStatus();
        Integer aulStatus = companySerchEntity.getAulStatus();
        CompanyExample.Criteria criteria = example.createCriteria();
        if(companyId !=null){
            criteria.andIdEqualTo(companyId);
        }
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
        example.setOrderByClause(companySerchEntity.getOrderExample()+companySerchEntity.getOrder());
        PageHelper.startPage(indexpage,10);
        List<Company> companies = companyMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(companies,5);
        return pageInfo;
    }


}
