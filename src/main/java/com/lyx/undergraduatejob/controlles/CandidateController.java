package com.lyx.undergraduatejob.controlles;

import com.alibaba.fastjson.JSONObject;
import com.lyx.undergraduatejob.common.JwtTokenUtil;
import com.lyx.undergraduatejob.pojo.ReceiveResume;
import com.lyx.undergraduatejob.pojo.Resume;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.pojo.WorkExperience;
import com.lyx.undergraduatejob.services.impl.*;
import com.lyx.undergraduatejob.services.security.LoginEntityHelper;
import com.lyx.undergraduatejob.services.security.OnlineEntity;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CandidateController {
    Logger logger = LoggerFactory.getLogger(CandidateController.class);


    @Autowired
    JobServicesImpl jobServices;
    @Autowired
    UserServicesImpl userServices;
    @Autowired
    ResumeServicesImp resumeServicesImp;
    @Autowired
    WorkExperienceServicesImpl workExperienceServices;
    @Autowired
    ReceiveResumeServicesImpl receiveResumeServices;
    @Autowired
    LoginEntityHelper loginEntityHelper;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    String tokenHead;

    @RequestMapping("/dashboard/candidate_applied_job")
    public String candidate_applied_job(){

        return "dashboard/candidate_applied_job";
    }

    //显示个人信息
    @RequestMapping("/dashboard/candidate_dashboard")
    public String candidate_dashboard(HttpServletRequest request){
        OnlineEntity user = loginEntityHelper.getOnlineEntity();
        if(user == null){
            throw new RuntimeException("error");
        }
        Integer userId = user.getId();
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

    //修改账号信息
    @RequestMapping("/user/updateMyInfo")
    @ResponseBody
    public APIResult updateMyInfo(@RequestBody Users users, HttpServletRequest request){
        OnlineEntity user = loginEntityHelper.getOnlineEntity();
        if(user == null){
            throw new RuntimeException("error");
        }
        Integer userId = user.getId();
        System.out.println("传回来的JSON："+users);
        //Users users1 = userServices.loadUserByName(user.getUsername());
        String token = userServices.reFereshToken(user.getUsername());
//        res.put(StaticPool.SUCCESS,token);
        users.setId(userId);
        Map<String, String> result = userServices.updateInfo(users);
        if(result.get(StaticPool.SUCCESS) != null){
            APIResult res = APIResult.genSuccessApiResponse("修改成功");
            Map<String,String> map = new HashMap<>();
            map.put("header",tokenHead);
            map.put("token",token);
            res.setData(map);
            return res;
        }else {
            return APIResult.genSuccessApiResponse("修改失败");
        }

    }

    //修改密码
    @RequestMapping("/user/changeUserPwd")
    @ResponseBody
    public APIResult changeUserPwd(@RequestBody JSONObject jsonObject){

        OnlineEntity user = loginEntityHelper.getOnlineEntity();
        if(user == null){
            throw new RuntimeException("error");
        }
        Integer userId = user.getId();

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




//    @RequestMapping("/user/candidate_edit_profile")
//    public String candidate_edit_profile(){
//
//        return "dashboard/candidate_edit_profile";
//    }


    //我的收藏
    @RequestMapping("/dashboard/candidate_favourite_job")
    public String candidate_favourite_job(){

        return "dashboard/candidate_favourite_job";
    }

    //显示简历
    @RequestMapping("/dashboard/candidate_resume")
    public String candidate_resume(HttpServletRequest request){
        OnlineEntity user = loginEntityHelper.getOnlineEntity();
        if(user == null){
            throw new RuntimeException("error");
        }
        Integer userId = user.getId();
        Users users = userServices.queryUserById(1);
        System.out.println(users);
        request.setAttribute("users",users);

        List<Resume> resumeList = resumeServicesImp.queryResumeByUserId(userId);

        System.out.println("我的简历：" + resumeList);

        request.setAttribute("resumeList",resumeList);


        List<WorkExperience> workExperienceList = workExperienceServices.selectWorkExByUserId(userId);
        request.setAttribute("workExperienceList",workExperienceList);
//        if(workExperienceList != null && workExperienceList.size() > 0){
//            request.setAttribute("workExperienceList",workExperienceList);
//        }


        return "/dashboard/candidate_resume";
    }

    //申请职位时，获取简历信息
    @RequestMapping("/user/applyJob_resume")
    @ResponseBody
    public APIResult applyJob_resume(){
        APIResult result = null;
        OnlineEntity user = loginEntityHelper.getOnlineEntity();
        if(user == null){
            throw new RuntimeException("error");
        }
        Integer userId = user.getId();

        List<Resume> resumeList = resumeServicesImp.queryResumeByUserId(userId);
        System.out.println("我的简历：" + resumeList);
        if( resumeList == null || resumeList.size() == 0){
            return APIResult.genSuccessApiResponse("你没有简历");
        }
        return APIResult.genSuccessApiResponse(resumeList);

    }

    @RequestMapping("/user/addResume")
    public String addResume(HttpServletRequest request){

        Resume resume = new Resume();
        request.setAttribute("resume",resume);

        return "dashboard/add_resume";
    }

    //添加简历
    @RequestMapping("/user/add_resume")
    @ResponseBody
    public APIResult add_resume(@RequestBody Resume resume){
        System.out.println("添加简历:" + resume);
        Integer userId = 1;
        resume.setUserId(userId);
        APIResult result = null;
        Map<String, String> res = resumeServicesImp.addResume(resume,userId);
        if(res.get(StaticPool.SUCCESS) != null){
            result = APIResult.genSuccessApiResponse("简历添加成功");
        }else {
            result = APIResult.genFailApiResponse500(res.get(StaticPool.ERROR));
        }

        return result;
    }

    //跳转到预览简历的页面
    @RequestMapping("/user/visitResume")
    public String visitResume(Integer resumeId,HttpServletRequest request){
        Resume resume = resumeServicesImp.queryResumeById(resumeId);
        request.setAttribute("resume",resume);

        return "dashboard/visit_resume";
    }
    //跳转到修改简历的页面
    @RequestMapping("/user/updateResume")
    public String updateResume(Integer resumeId,HttpServletRequest request){

        Resume resume = resumeServicesImp.queryResumeById(resumeId);
        request.setAttribute("resume",resume);

        return "dashboard/update_resume";
    }
    //修改简历
    @RequestMapping("/user/update_resume")
    @ResponseBody
    public APIResult update_resume(@RequestBody Resume resume){
        APIResult result = null;
        System.out.println("修改简历:" + resume);
        OnlineEntity user = loginEntityHelper.getOnlineEntity();
        if(user == null){
            return APIResult.genFailApiResponse500("请先登录");
        }
        Integer userId = user.getId();
        Map<String, String> res = resumeServicesImp.updateResumeByUser(resume, userId);
        if(res.get(StaticPool.SUCCESS) != null){
            result = APIResult.genSuccessApiResponse("简历修改成功");
        }else {
            result = APIResult.genFailApiResponse500("服务器繁忙，修改失败");
        }

        return result;
    }

    //用户删除简历
    @RequestMapping("/user/delResume")
    @ResponseBody
    public APIResult delResume(Integer resumeId){
        APIResult result = null;

        OnlineEntity user = loginEntityHelper.getOnlineEntity();
        if(user == null){
            return APIResult.genFailApiResponse500("请先登录");
        }
        Integer userId = user.getId();
        Map<String, String> re = resumeServicesImp.updateDelResume(resumeId, userId);
        if(re.get(StaticPool.SUCCESS) != null){
            result = APIResult.genSuccessApiResponse("删除成功");
        }else {
            result = APIResult.genFailApiResponse500(re.get(StaticPool.ERROR));
        }

        return result;
    }
    //用户发布/取消发布简历
    @RequestMapping("/user/pushResume")
    @ResponseBody
    public APIResult pushResume(Integer resumeId, Integer pushStatus){
        APIResult result = null;

        OnlineEntity user = loginEntityHelper.getOnlineEntity();
        if(user == null){
            return APIResult.genFailApiResponse500("请先登录");
        }
        Integer userId = user.getId();
        System.out.println("pushStatus:"+pushStatus);
        Map<String, String> re = null;
        if(pushStatus == 1){
            re = resumeServicesImp.updatePushResume(resumeId,userId);
        }
        if(pushStatus == 0){
            re = resumeServicesImp.updateDisPushResume(resumeId,userId);
        }

        if(re.get(StaticPool.SUCCESS) != null){
            result = APIResult.genSuccessApiResponse("操作成功");
        }else {
            result = APIResult.genFailApiResponse500(re.get(StaticPool.ERROR));
        }

        return result;
    }

    //提交简历
    @RequestMapping("/user/submit_resume")
    @ResponseBody
    public APIResult submit_resume(@RequestBody ReceiveResume receiveResume){
        APIResult result = null;

        OnlineEntity user = loginEntityHelper.getOnlineEntity();
        if(user == null){
            return APIResult.genFailApiResponse500("请先登录");
        }
        Integer userId = user.getId();
        System.out.println("receiveResume:------------:" + receiveResume);
        Map<String, String> re = receiveResumeServices.addReceiveResume(receiveResume,userId);
        jobServices.updateIncrReceiveNum(receiveResume.getJobId(),1);
        if(re.get(StaticPool.SUCCESS) != null){
            result = APIResult.genSuccessApiResponse(re.get(StaticPool.SUCCESS));
        }else {
            result = APIResult.genFailApiResponse500(re.get(StaticPool.ERROR));
        }

        return result;
    }


}
