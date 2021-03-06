package com.lyx.undergraduatejob.controlles;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.*;
import com.lyx.undergraduatejob.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ComplaintController
 * @Date 2019/12/23
 * @Version V1.0
 **/
@Controller
@RequestMapping("/admin")
public class ComplaintController {

    @Autowired
    IComplaintServicesImpl services;

    @Autowired
    ICompanyInfoServicesImpl service;

    @Autowired
    JobServicesImpl jobServices;

    @Autowired
    MessageServicesImpl messageServices;

    @RequestMapping("/complaint")
    public String complaint(HttpServletRequest request, Integer indexpage, Complaint complaint) {
        PageInfo info = services.queryCommplaint(indexpage, complaint);
        request.setAttribute("userId", complaint.getUserId());
        request.setAttribute("dealStatus",complaint.getDealStatus());
        request.setAttribute("complaintId", complaint.getComplaintId());
        request.setAttribute("pages", info);
        return "/admin/manager-complaint";
    }

//    @RequestMapping("/complaint")
//    public String complaintList(HttpServletRequest request,Complaint complaint){
//        List<Complaint> complaints = services.queryCommplaint(complaint);
//        request.setAttribute("userId",complaint.getUserId());
//        request.setAttribute("complaintId",complaint.getComplaintId());
//        request.setAttribute("list",complaints);
//        return "/admin/manager-complaint";
//    }

    @RequestMapping("/update_complaints")
    public String updateComplaints(Complaint complaint) {
        Message message = new Message();
        Admin admin = new Admin();
        admin.setId(1);
        Company company = new Company();
        Job job = new Job();

        Complaint complaint1 = services.queryCommplaint(complaint.getId());
        message.setReceiverId(complaint1.getUserId());
        message.setReceiverType(1);
        message.setSenderId(admin.getId());
        message.setSenderType(1);
        message.setSenderStatus(0);
        message.setMessageTitle("您的投诉信息已受理！");
        message.setMessageContent("您的投诉已被处理，受您举报的用户"+complaint1.getComplaintId()+"已被冻结，感谢您对本平台做出的贡献！");

        if (complaint1.getComplaintType() == 1){
            company.setId(complaint1.getComplaintId());
            company.setStatus(0);
            Map<String, String> stringStringMap = service.updateCompanyInfobyAdmin(company);
        }
        if (complaint1.getComplaintType() == 2){
            job.setId(complaint1.getComplaintId());
            job.setStatus(0);
            Map<String, String> stringStringMap1 = jobServices.updateJob(job);
        }

        Map<String, Object> stringObjectMap1 = messageServices.addMessage(message);
        Map<String, Object> stringObjectMap = services.updateCommplaint(complaint);

        return "redirect:/admin/complaint";
    }

    @RequestMapping("/update_complaint")
    public String updateComplaint(Complaint complaint) {
        Message message = new Message();
        Admin admin = new Admin();
        admin.setId(1);
        List<Complaint> complaints = services.queryCommplaint(complaint);
        message.setReceiverId(complaints.get(0).getUserId());
        message.setSenderId(admin.getId());
        message.setReceiverType(1);
        message.setSenderType(1);
        message.setSenderStatus(0);
        message.setMessageTitle("您的投诉信息已受理！");
        message.setMessageContent("您的投诉经过审核并未通过，感谢您对本平台的贡献！");
        Map<String, Object> stringObjectMap1 = messageServices.addMessage(message);
        Map<String, Object> stringObjectMap = services.updateCommplaint(complaint);
        return "redirect:/admin/complaint";
    }
}

