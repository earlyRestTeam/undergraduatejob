package com.lyx.undergraduatejob.controlles;


import com.lyx.undergraduatejob.pojo.*;
import com.lyx.undergraduatejob.services.impl.*;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/aut")
public class AutController {
    @Autowired
    AutCompanyServiceImpl autCompanyService;

    @Autowired
    AutStudentServiceImpl autStudentService;

    @Autowired
    IPictuerServiceImpl pictuerService;

    @Autowired
    MessageServicesImpl messageServices;

    @Autowired
    ICompanyInfoServicesImpl companyInfoServices;

    @Autowired
    UserServicesImpl userServices;

    @RequestMapping("aut_company")
    public String aut_company(){
        return "/aut/aut_company";
    }

    @RequestMapping("aut_student")
    public String aut_student(){
        return "/aut/aut_student";
    }
    //后台
    @RequestMapping("query_aut_company")
    public String query(HttpServletRequest request, String proposerName, Integer companyId, Integer status){
        request.setAttribute("proposerName",proposerName);
        request.setAttribute("companyId",companyId);
        request.setAttribute("status",status);

        AutCompany autCompany = new AutCompany();
        autCompany.setCompanyId(companyId);
        autCompany.setProposerName(proposerName);
        autCompany.setStatus(status);
        List<AutCompany> autCompanyList = autCompanyService.queryAutCompanyBack(autCompany);
        request.setAttribute("list",autCompanyList);

        return "/admin/authentication-company";
    }

    @RequestMapping("update_aut_company_status")
    public String updateStatus(HttpServletRequest request, Integer id, Integer status, Integer companyId){
        AutCompany autCompany = new AutCompany();
        autCompany.setId(id);
        autCompany.setStatus(status);

        Message message = new Message();
        message.setReceiverId(companyId);
        message.setReceiverType(2);

        if (status == 0){
            message.setMessageTitle("认证不通过");
            message.setMessageContent("您的营业执照照片不清楚，请重新上传营业执照照片！");
        } else if (status == 1){
            Company company = new Company();
            company.setId(companyId);
            company.setAulStatus(1);
            Map<String, String> map = companyInfoServices.updateCompanyInfobyAdmin(company);
            if (map.get(StaticPool.SUCCESS) != null){
                message.setMessageTitle("认证通过");
                message.setMessageContent("您的认证已通过，您的公司可以发布职位与招聘信息了");
            } else {
                request.setAttribute("result",map);
                return "redirect:/aut/query_aut_company";
            }
        }

        Map<String, String> stringStringMap = autCompanyService.updateAutCompany(autCompany);
        if (stringStringMap.get(StaticPool.SUCCESS) != null){
            Map<String, Object> map = messageServices.addMessage(message);
            request.setAttribute("result",map);
        } else {
            request.setAttribute("result",stringStringMap);
        }

        return "redirect:/aut/query_aut_company";
    }

    @PostMapping("aut_company_pic_load")
    @ResponseBody
    public APIResult picLoad(@RequestParam Integer id){
        List<Picture> pictureList = pictuerService.queryPicturebyOwnerid(id, 4, 2);
        return APIResult.genSuccessApiResponse(pictureList);
    }

    @RequestMapping("query_aut_student")
    public String query_aut_student(HttpServletRequest request, String proposerName, Integer userId, Integer status){
        request.setAttribute("proposerName",proposerName);
        request.setAttribute("userId",userId);
        request.setAttribute("status",status);

        AutStudent autStudnet = new AutStudent();
        autStudnet.setUserId(userId);
        autStudnet.setProposerName(proposerName);
        autStudnet.setStatus(status);
        List<AutStudent> autStudnetList = autStudentService.queryAutStudentBack(autStudnet);
        request.setAttribute("list",autStudnetList);

        return "/admin/authentication-student";
    }

    @RequestMapping("update_aut_student_status")
    public String updateAutStudentStatus(HttpServletRequest request, Integer id, Integer status, Integer userId){
        AutStudent autStudent = new AutStudent();
        autStudent.setId(id);
        autStudent.setStatus(status);

        Message message = new Message();
        message.setReceiverId(userId);
        message.setReceiverType(2);

        if (status == 0){
            message.setMessageTitle("认证不通过");
            message.setMessageContent("您的营业执照照片不清楚，请重新上传营业执照照片！");
        } else if (status == 1){
            Users student = new Users();
            student.setId(userId);
            student.setAutStatus(1);
            Map<String, String> map = userServices.updateInfo(student);
            if (map.get(StaticPool.SUCCESS) != null){
                message.setMessageTitle("认证通过");
                message.setMessageContent("您的认证已通过，您的公司可以发布职位与招聘信息了");
            } else {
                request.setAttribute("result",map);
                return "redirect:/aut/query_aut_Student";
            }
        }

        Map<String, String> stringStringMap = autStudentService.updateAutStudentBack(autStudent);
        if (stringStringMap.get(StaticPool.SUCCESS) != null){
            Map<String, Object> map = messageServices.addMessage(message);
            request.setAttribute("result",map);
        } else {
            request.setAttribute("result",stringStringMap);
        }

        return "redirect:/aut/query_aut_Student";
    }

    @PostMapping("aut_student_pic_load")
    @ResponseBody
    public APIResult picAutStudenstLoad(@RequestParam Integer id){
        List<Picture> pictureList = pictuerService.queryPicturebyOwnerid(id, 3, 1);
        return APIResult.genSuccessApiResponse(pictureList);
    }
}
