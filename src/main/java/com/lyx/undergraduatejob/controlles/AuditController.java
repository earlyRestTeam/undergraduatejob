package com.lyx.undergraduatejob.controlles;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.JobMapper;
import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.pojo.Message;
import com.lyx.undergraduatejob.pojo.Resume;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;
import com.lyx.undergraduatejob.services.impl.JobServicesImpl;
import com.lyx.undergraduatejob.services.impl.MessageServicesImpl;
import com.lyx.undergraduatejob.services.impl.ResumeServicesImp;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2019/12/26 10:42
 * @Version V1.0
 */
@Controller
@RequestMapping("/audit")
public class AuditController {
    @Autowired
    ResumeServicesImp resumeServices;
    @Autowired
    JobServicesImpl jobServices;
    @Autowired
    MessageServicesImpl messageServices;
    @Autowired
    JobMapper jobMapper;

    @RequestMapping("query_resume")
    public String queryResume(HttpServletRequest request,Integer indexpage,Integer aulStatus,Integer status){
        PageInfo<Resume> info = resumeServices.queryResumeByAdminExample(indexpage, 10, status, aulStatus);
        request.setAttribute("pages",info);
        return "/admin/audit-resume::resumetab";
    }

    @RequestMapping("aud_resume")
    public String audResume(HttpServletRequest request,Integer id){
        Resume resume = resumeServices.queryResumeById(id);
        request.setAttribute("resume",resume);

        return "/admin/audit-resume::modal";
    }

    @RequestMapping("update_resume_status")
    @ResponseBody
    public APIResult updateResumeStatus(@RequestParam Integer id,@RequestParam Integer status,@RequestParam Integer userId){
        status = (status+1)%2;
        Resume resume = new Resume();
        resume.setId(id);
        resume.setStatus(status);
        Map<String, String> map = resumeServices.updateResumeByAdmin(resume);
        if (map.get(StaticPool.ERROR) != null){
            return APIResult.genSuccessApiResponse(map.get(StaticPool.ERROR));
        }

        Message message = new Message();
        message.setReceiverId(userId);
        message.setReceiverType(1);
        //message.setSenderId();
        message.setSenderType(1);
        message.setMessageTitle("简历状态改变");
        if (status == 1){
            message.setMessageContent("您的简历已变为有效状态！");
        } else {
            message.setMessageContent("您的简历已变为无效状态！");
        }
        Map<String, Object> map1 = messageServices.addMessage(message);
        if (map1.get(StaticPool.ERROR) != null){
            return APIResult.genSuccessApiResponse(map1.get(StaticPool.ERROR));
        }

        return APIResult.genSuccessApiResponse("状态更改成功！");
    }

    @RequestMapping("update_resume_aul_status")
    @ResponseBody
    public APIResult updateResumeAulStatus(@RequestParam Integer id,@RequestParam Integer status, @RequestParam Integer userId){
        Resume resume = new Resume();
        resume.setId(id);
        resume.setAulStatus(status);
        if (status == 2){
            resume.setPushStatus(1);
        }
        if (status == 3){
            resume.setPushStatus(0);
        }
        Map<String, String> map = resumeServices.updateResumeByAdmin(resume);
        if (map.get(StaticPool.ERROR) != null){
            return APIResult.genSuccessApiResponse(map.get(StaticPool.ERROR));
        }

        Message message = new Message();
        message.setReceiverId(userId);
        message.setReceiverType(1);
        //message.setSenderId();
        message.setSenderType(1);
        message.setMessageTitle("职位状态改变");
        if (status == 2){
            message.setMessageContent("您的简历的审核已通过！状态已置为发布状态。");
        } else {
            message.setMessageContent("您的简历的审核不通过！");
        }
        Map<String, Object> map1 = messageServices.addMessage(message);
        if (map1.get(StaticPool.ERROR) != null){
            return APIResult.genSuccessApiResponse(map1.get(StaticPool.ERROR));
        }

        return APIResult.genSuccessApiResponse("审核成功！");

    }

    @RequestMapping("query_job")
    public String queryJob(HttpServletRequest request,Integer indexpage,Integer companyId,Integer aulStatus,Integer status){
        JobSearchEntity entity = new JobSearchEntity();
        entity.setCompanyId(companyId);
        entity.setAulStatus(aulStatus);
        entity.setStatus(status);
        PageInfo<Job> info = jobServices.selectJobByJobSearchEntityWithOutCompany(indexpage, 10, entity);
        //request.setAttribute("job",new Job());
        request.setAttribute("pages",info);

        return "/admin/audit-job::jobtab";
    }


    @RequestMapping("update_job_status")
    @ResponseBody
    public APIResult updateCompanyStatus(@RequestParam Integer id,@RequestParam Integer status,
                                         @RequestParam Integer companyId,@RequestParam String jobName){


        status = (status+1)%2;
        Job job = new Job();
        job.setId(id);
        job.setStatus(status);
        int i = jobMapper.updateByPrimaryKeySelective(job);
        if (i > 0){
            Message message = new Message();
            message.setReceiverId(companyId);
            message.setReceiverType(2);
            //message.setSenderId();
            message.setSenderType(1);
            message.setMessageTitle("职位状态改变");
            if (status == 1){
                message.setMessageContent("您公司的"+jobName+"职位已变为有效状态！");
            } else {
                message.setMessageContent("您公司的"+jobName+"职位已变为无效状态！");
            }
            messageServices.addMessage(message);

            return APIResult.genSuccessApiResponse("状态更改成功！");
        }
        return APIResult.genSuccessApiResponse("服务器繁忙。");
    }

    @RequestMapping("aud_job")
    public String audJob(HttpServletRequest request,Integer id){
        Map<String, Object> map = jobServices.selectJobById(id);
        Job job = (Job) map.get("job");
        request.setAttribute("job", job);
        return "/admin/audit-job::modal";
    }

    @RequestMapping("update_job_aul_status")
    @ResponseBody
    public APIResult updateJobAulStatus(@RequestParam Integer id,@RequestParam Integer status,
                                        @RequestParam Integer companyId,@RequestParam String jobName){
        Job job = new Job();
        job.setId(id);
        job.setAulStatus(status);
        if (status == 2){
            job.setPushStatus(1);
        }
        if (status == 3){
            job.setPushStatus(0);
        }
        int i = jobMapper.updateByPrimaryKeySelective(job);

        if (i > 0){
            Message message = new Message();
            message.setReceiverId(companyId);
            message.setReceiverType(2);
            //message.setSenderId();
            message.setSenderType(1);
            message.setMessageTitle("职位状态改变");
            if (status == 2){
                message.setMessageContent("您公司的"+jobName+"职位的审核已通过！状态已置为发布状态。");
            } else {
                message.setMessageContent("您公司的"+jobName+"职位的审核不通过！");
            }
            messageServices.addMessage(message);

            return APIResult.genSuccessApiResponse("审核成功！");
        }
        return APIResult.genSuccessApiResponse("服务器繁忙。");
    }
}
