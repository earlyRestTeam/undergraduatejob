package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Comment;
import com.lyx.undergraduatejob.pojo.Company;

import java.util.List;
import java.util.Map;

/**
 * 评论
 */
public interface ICommentServices {
    /**
     * 用户发表评论
     * @param c
     * @return
     */
    public Map<String,String> addComment(Comment c);

    /**
     * 查看用户可以评价的企业
     * @param indexpage
     * @param userid
     * @return
     */
    public PageInfo<Company> queryCompany(Integer indexpage, Integer userid);

    /**
     * 查看用户的历史评价
     * @param indexpage
     * @param userId
     * @return
     */
    public PageInfo<Comment> queryComment(Integer indexpage, Integer userId);

    public List<Comment> queryComment(Comment comment);

    /**
     * 删除用户的历史评价
     * @param commentId
     * @return
     */
    public Map<String,String> deleteComment(Integer commentId, Integer userid);

    public Map<String,Object> deleteComment(Comment comment);

    /**
     *批量删除
     * @param ids
     * @return
     */
    boolean deleteComments(Integer[] ids);
}
