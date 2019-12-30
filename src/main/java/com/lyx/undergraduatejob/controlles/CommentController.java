package com.lyx.undergraduatejob.controlles;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Comment;
import com.lyx.undergraduatejob.services.impl.ICommentServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CommentController
 * @Date 2019/12/23
 * @Version V1.0
 **/
@Controller
@RequestMapping("/admin")
public class CommentController {

    @Autowired
    ICommentServicesImpl services;

//    @RequestMapping("/comment")
//    public String commentList(HttpServletRequest request, Comment comment){
//        List<Comment> comments = services.queryComment(comment);
//        request.setAttribute("userId",comment.getUserId());
//        request.setAttribute("list",comments);
//        return "/admin/manager-comment";
//    }

    @RequestMapping("/comment")
    public String commentList(HttpServletRequest request,Integer indexpage,Integer userId){
        PageInfo<Comment> info = services.queryComment(indexpage, userId);
        request.setAttribute("id",userId);
        request.setAttribute("pages",info);
        return "/admin/manager-comment";
    }

    @RequestMapping("/del_comment")
    public String deleteComment(Comment comment){
        Map<String, Object> stringObjectMap = services.deleteComment(comment);
        return "/admin/manager-comment";
    }

    @RequestMapping("/batch")
    public String batchDel(Integer[] ids){
        boolean b = services.deleteComments(ids);
        return "redirect:/admin/manager-comment";
    }
}
