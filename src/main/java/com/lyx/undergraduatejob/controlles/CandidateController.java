package com.lyx.undergraduatejob.controlles;

import com.alibaba.fastjson.JSONObject;
import com.lyx.undergraduatejob.pojo.Resume;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.pojo.WorkExperience;
import com.lyx.undergraduatejob.services.impl.ResumeServicesImp;
import com.lyx.undergraduatejob.services.impl.UserServicesImpl;
import com.lyx.undergraduatejob.services.impl.WorkExperienceServicesImpl;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class CandidateController {
    Logger logger = LoggerFactory.getLogger(CandidateController.class);

    @Autowired
    UserServicesImpl userServices;
    @Autowired
    ResumeServicesImp resumeServicesImp;
    @Autowired
    WorkExperienceServicesImpl workExperienceServices;

    @RequestMapping("/user/candidate_applied_job")
    public String candidate_applied_job(){

        return "dashboard/candidate_applied_job";
    }


    @RequestMapping("/user/candidate_dashboard")
    public String candidate_dashboard(HttpServletRequest request){
        Integer userId = 1;
        Users users = userServices.queryUserById(userId);
        System.out.println(users);
        request.setAttribute("users",users);
        return "/dashboard/candidate_dashboard";
    }



//    @RequestMapping("/user/candidate_index")
//    public String candidate_index(HttpServletRequest request){
//        Integer userId = 1;
//
//        Users users = userServices.queryUserById(1);
//        System.out.println(users);
//        request.setAttribute("users",users);
//
//        return "dashboard/candidate_index";
//    }

    @RequestMapping("/user/delMyInfo")
    @ResponseBody
    public APIResult delMyInfo(@RequestBody Users users, HttpServletRequest request){
        Integer userId = 1;
        System.out.println("传回来的JSON："+users);
        users.setId(userId);
        Map<String, String> result = userServices.updateInfo(users);
        if(result.get(StaticPool.SUCCESS) != null){
            return APIResult.genSuccessApiResponse("修改成功");
        }else {
            return APIResult.genSuccessApiResponse("修改失败");
        }

    }

    @RequestMapping("/user/changeUserPwd")
    @ResponseBody
    public APIResult changeUserPwd(@RequestBody JSONObject jsonObject){

        Integer userId = 1;

        APIResult result = null;

        String oldPassword = (String) jsonObject.get("oldPassword");

        String newPassword = (String) jsonObject.get("newPassword");

        if(StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)){
            result =  APIResult.genFailApiResponse500("PARAMS ERROR!");
            return result;
        }

        Map<String, String> res = userServices.updateUserPassword(1, oldPassword, newPassword);

        if( res.get(StaticPool.ERROR) != null ){
            result = APIResult.genFailApiResponse500(res.get(StaticPool.ERROR));
        }else {
            result = APIResult.genSuccessApiResponse(res.get(StaticPool.SUCCESS));
        }
        return result;
    }




    @RequestMapping("/user/candidate_edit_profile")
    public String candidate_edit_profile(){

        return "dashboard/candidate_edit_profile";
    }

    @RequestMapping("/user/candidate_favourite_job")
    public String candidate_favourite_job(){

        return "dashboard/candidate_favourite_job";
    }

    @RequestMapping("/user/candidate_resume")
    public String candidate_resume(HttpServletRequest request){
        Integer userId = 1;
        Users users = userServices.queryUserById(1);
        System.out.println(users);
        request.setAttribute("users",users);

        Resume resume = resumeServicesImp.queryResumeByUserId(userId);
        System.out.println("我的简历：" + resume);
        if(resume == null){
            logger.warn("服务器繁忙，未找到数据");
            throw new RuntimeException("error");
        }
        request.setAttribute("resume",resume);


        List<WorkExperience> workExperienceList = workExperienceServices.selectWorkExByUserId(userId);
        if(workExperienceList != null && workExperienceList.size() > 0){
            request.setAttribute("workExperienceList",workExperienceList);
        }


        return "/dashboard/candidate_resume";
    }
}
