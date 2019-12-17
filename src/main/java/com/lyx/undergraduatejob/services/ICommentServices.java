package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Comment;
import com.lyx.undergraduatejob.pojo.Company;

/**
 * 评论
 */
public interface ICommentServices {
    /**
     * 发表评论
     * @param c
     * @return
     */
    boolean addComment(Comment c);

    /**
     * 查看我可已评价的企业
     * @param indexpage
     * @param o
     * @return
     */
    PageInfo<Company> queryCompany(Integer indexpage, Object o);

    /**
     * 查看我的历史评价
     * @param indexpage
     * @param userId
     * @return
     */
    PageInfo<Comment> queryComment(Integer indexpage, int userId);

    /**
     * 删除我的历史评价
     * @param commentId
     * @return
     */
    boolean deleteComment(int commentId);
}
