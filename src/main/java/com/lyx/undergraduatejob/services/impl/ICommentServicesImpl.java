package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.CommentMapper;
import com.lyx.undergraduatejob.mapper.CompanyMapper;
import com.lyx.undergraduatejob.mapper.ReceiveResumeMapper;
import com.lyx.undergraduatejob.pojo.*;
import com.lyx.undergraduatejob.services.ICommentServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ICommentServicesImpl implements ICommentServices {

    Logger logger = LoggerFactory.getLogger(ICommentServices.class);

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    ReceiveResumeMapper resumeMapper;

    /**
     * 用户发表评论
     * @param c
     * @return
     */
    @Override
    public Map<String,String> addComment(Comment c) {
        Map<String,String> result = new HashMap<>();
        int res = commentMapper.insert(c);

        if(res > 0){
            result.put(StaticPool.SUCCESS,"评论成功");
        }else{
            result.put(StaticPool.ERROR,"评论失败");
            logger.error("insert erro" + c);
        }
        return result;
    }


    /**
     * 查看用户可已评价的企业
     * @param indexpage
     * @return
     */
    @Override
    public PageInfo<Company> queryCompany(Integer indexpage,Integer userid) {
        ReceiveResumeExample receiveResumeExample = new ReceiveResumeExample();
        ReceiveResumeExample.Criteria criteria = receiveResumeExample.createCriteria();
        criteria.andStatusEqualTo(2);
        criteria.andUserIdEqualTo(userid);
        List<ReceiveResume> resumeList= resumeMapper.selectByExample(receiveResumeExample);
        List<Integer> idlist = new ArrayList<>();
        if(resumeList == null)
            return null;

        for (int i = 0; i < resumeList.size(); i++) {
            System.out.println(resumeList.get(i).getCompanyId());
            idlist.add(resumeList.get(i).getCompanyId());
        }
        CompanyExample companyExample = new CompanyExample();
        CompanyExample.Criteria criteria1 = companyExample.createCriteria();
        criteria1.andIdIn(idlist);

        indexpage = indexpage == null ? 1 : indexpage;

        PageHelper.startPage(indexpage, 5);

        List<Company> companyList =  companyMapper.selectByExample(companyExample);

        if(companyList == null)
            return null;

        PageInfo<Company> info = new PageInfo<>(companyList,3);

        return info;
    }


    /**
     * 查看用户的历史评价
     * @param indexpage
     * @param userId
     * @return
     */
    @Override
    public PageInfo<Comment> queryComment(Integer indexpage, Integer userId) {
        CommentExample commentExample  = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();

        if(userId != null){
            criteria.andUserIdEqualTo(userId);
        }

        indexpage = indexpage == null ? 1 : indexpage;

        PageHelper.startPage(indexpage,5);

        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        if(commentList == null)
            return null;

        PageInfo<Comment> info = new PageInfo(commentList,3);

        return info;
    }

    /**
     * 删除用户的历史评价
     * @param commentId
     * @return
     */
    @Override
    public Map<String,String> deleteComment(Integer commentId, Integer userid) {
        Map<String,String> res = new HashMap<>();

        CommentExample commentExample  = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();

        criteria.andUserIdEqualTo(userid);
        criteria.andIdEqualTo(commentId);

        int result = commentMapper.deleteByExample(commentExample);
        if(result > 0){
            res.put(StaticPool.SUCCESS,"删除成功");
        }else{
            logger.error("delete erro" +commentId);
            res.put(StaticPool.ERROR,"删除成功");
        }
        return res;
    }
}
