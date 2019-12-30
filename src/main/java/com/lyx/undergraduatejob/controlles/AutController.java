package com.lyx.undergraduatejob.controlles;


import com.github.pagehelper.PageInfo;
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
    public String query(HttpServletRequest request, Integer indexpage, String proposerName, Integer companyId, Integer status){
        request.setAttribute("proposerName",proposerName);
        request.setAttribute("companyId",companyId);
        request.setAttribute("status",status);

        AutCompany autCompany = new AutCompany();
        autCompany.setCompanyId(companyId);
        autCompany.setProposerName(proposerName);
        autCompany.setStatus(status);
        PageInfo<AutCompany> info = autCompanyService.queryAutCompanyBack(indexpage,autCompany);
        request.setAttribute("pages",info);

        return "/admin/authentication-company::table";
    }

    @RequestMapping("update_aut_company_status")
    @ResponseBody
    public APIResult updateStatus(@RequestParam Integer id,@RequestParam Integer status,@RequestParam Integer companyId){
        AutCompany autCompany = new AutCompany();
        autCompany.setId(id);
        autCompany.setStatus(status);
        Map<String, String> map = autCompanyService.updateAutCompany(autCompany);
        if (map.get(StaticPool.ERROR) != null){
            return APIResult.genSuccessApiResponse(map.get(StaticPool.ERROR));
        }

        Company company = new Company();
        company.setId(companyId);
        if (status == 0) {
            company.setAulStatus(2);
        } else {
            company.setAulStatus(1);
        }
        Map<String, String> map1 = companyInfoServices.updateCompanyInfobyAdmin(company);
        if (map1.get(StaticPool.ERROR) != null){
            return APIResult.genSuccessApiResponse(map1.get(StaticPool.ERROR));
        }

        Message message = new Message();
        message.setReceiverId(companyId);
        message.setReceiverType(2);
        //message.setSenderId();
        message.setSenderType(1);
        message.setMessageTitle("认证消息");
        if (status == 0) {
            message.setMessageContent("您的营业执照照片不清楚，请重新上传营业执照照片！");
        } else {
            message.setMessageContent("您的认证已通过，您的公司可以发布职位与招聘信息了");
        }
        Map<String, Object> map2 = messageServices.addMessage(message);
        if (map2.get(StaticPool.ERROR) != null){
            return APIResult.genSuccessApiResponse(map2.get(StaticPool.ERROR));
        }

        return APIResult.genSuccessApiResponse("认证审核完成！");
    }

    @PostMapping("aut_company_pic_load")
    @ResponseBody
    public APIResult picLoad(@RequestParam Integer id){
        List<Picture> pictureList = pictuerService.queryPicturebyOwnerid(id, 4, 2);
        return APIResult.genSuccessApiResponse(pictureList);
    }

    @RequestMapping("query_aut_student")
    public String query_aut_student(HttpServletRequest request, Integer indexpage, String proposerName, Integer userId, Integer status){
        request.setAttribute("proposerName",proposerName);
        request.setAttribute("userId",userId);
        request.setAttribute("status",status);

        AutStudent autStudnet = new AutStudent();
        autStudnet.setUserId(userId);
        autStudnet.setProposerName(proposerName);
        autStudnet.setStatus(status);
        PageInfo<AutStudent> info = autStudentService.queryAutStudentBack(indexpage,autStudnet);
        request.setAttribute("pages",info);

        return "/admin/authentication-student::table";
    }

    @RequestMapping("update_aut_student_status")
    @ResponseBody
    public APIResult updateAutStudentStatus(@RequestParam Integer id,@RequestParam Integer status,@RequestParam Integer userId){
        AutStudent autStudent = new AutStudent();
        autStudent.setId(id);
        autStudent.setStatus(status);
        Map<String, String> map = autStudentService.updateAutStudentBack(autStudent);
        if (map.get(StaticPool.ERROR) != null){
            return APIResult.genSuccessApiResponse(map.get(StaticPool.ERROR));
        }

        if (status == 1){
            Users student = new Users();
            student.setId(userId);
            student.setAutStatus(1);
            Map<String, String> map1 = userServices.updateInfo(student);
            if (map1.get(StaticPool.ERROR) != null){
                return APIResult.genSuccessApiResponse(map1.get(StaticPool.ERROR));
            }
        }

        Message message = new Message();
        message.setReceiverId(userId);
        message.setReceiverType(1);
        //message.setSenderId();
        message.setSenderType(1);
        message.setMessageTitle("认证消息");
        if (status == 0) {
            message.setMessageContent("您的身份证或学生证照片不清楚，请重新上传照片！");
        } else {
            message.setMessageContent("您的认证已通过，您可以发布与投递简历了");
        }
        Map<String, Object> map2 = messageServices.addMessage(message);
        if (map2.get(StaticPool.ERROR) != null){
            return APIResult.genSuccessApiResponse(map2.get(StaticPool.ERROR));
        }

        return APIResult.genSuccessApiResponse("认证审核完成！");
    }

    @PostMapping("aut_student_pic_load")
    @ResponseBody
    public APIResult picAutStudenstLoad(@RequestParam Integer id){
        List<Picture> pictureList = pictuerService.queryPicturebyOwnerid(id, 3, 1);
        return APIResult.genSuccessApiResponse(pictureList);
    }
}
