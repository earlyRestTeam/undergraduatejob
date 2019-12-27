package com.lyx.undergraduatejob.controlles;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.JobMapper;
import com.lyx.undergraduatejob.pojo.*;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;
import com.lyx.undergraduatejob.search.entity.LoginEntity;
import com.lyx.undergraduatejob.services.impl.*;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
//    @Autowired
//    LoginEntity loginEntity;
    @Autowired
    ICompanyInfoServicesImpl companyInfoServices;
    @Autowired
    IPictuerServiceImpl pictuerService;
    @Autowired
    ReceiveResumeServicesImpl receiveResumeServices;
    @Autowired
    RelationWelafareServiceImpl relationWelafareService;
    @Autowired
    JobServicesImpl jobServices;
    @Autowired
    IndustriesListServices industriesListServices;
    @Autowired
    TbAreaServiceImpl tbAreaService;
    @RequestMapping("candidate_applied_job")
    public String candidate_applied_job(){

        return "dashboard/candidate_applied_job";
    }


    @RequestMapping("candidate_dashboard")
    public String candidate_dashboard(){

        return "dashboard/candidate_dashboard";
    }


    @RequestMapping("candidate_edit_profile")
    public String candidate_edit_profile(){

        return "dashboard/candidate_edit_profile";
    }

    @RequestMapping("candidate_favourite_job")
    public String candidate_favourite_job(){

        return "dashboard/candidate_favourite_job";
    }

    @RequestMapping("candidate_resume")
    public String candidate_resume(){

        return "dashboard/candidate_resume";
    }

    /**
     * 首次进入公司的收到简历页面加载数据
     * @param request
     * @param jobid
     * @return
     */
    @RequestMapping("comp_applications")
    public String comp_applications(HttpServletRequest request,Integer jobid){
        Integer companyid = 1;
        PageInfo<Resume> resumePageInfo = receiveResumeServices.queryReceiveResume(1, 5, jobid, companyid, 0);
        if (resumePageInfo == null){
            resumePageInfo = new PageInfo<>();
        }
        request.setAttribute("pages",resumePageInfo);
        List<Map<String, Object>> list = industriesListServices.queryALLWithJobList();
        request.setAttribute("list",list);
        return "dashboard/comp_applications";
    }

    /**
     * 删除或者已读公司收到的简历
     * @param request
     * @param jobid
     * @return
     */
    @RequestMapping("updateResumebyId")
    public String updateResumebyId(HttpServletRequest request,Integer jobid){
        Integer companyid = 1;
        return "dashboard/comp_applications";
    }

    /**
     * 收到简历的搜索加分页
     * @param model
     * @param jobid
     * @return
     */
    @RequestMapping("comp_applications_serch")
    public String comp_applications_serch(Model model, Integer indexpage, Integer jobid,Integer status){
        Integer companyid = 1;
        if (jobid == 0){
            jobid =null;
        }
        PageInfo<Resume> resumePageInfo = receiveResumeServices.queryReceiveResume(indexpage, 5, jobid, companyid, status);
        if (resumePageInfo == null){
            resumePageInfo = new PageInfo<>();
        }
        model.addAttribute("pages",resumePageInfo);
        List<Map<String, Object>> list = industriesListServices.queryALLWithJobList();
        model.addAttribute("list",list);
        return "dashboard/comp_applications::joblistdiv";
    }

    /**
     * 级联行业和职业的下拉框
     * @param index
     * @return
     */
    @PostMapping("comp_applicationsAPIResult")
    @ResponseBody
    public APIResult comp_applicationsAPIResult(Integer index){
        List<Map<String, Object>> list = industriesListServices.queryALLWithJobList();
        if (index > list.size()){
            return APIResult.genFailApiResponse400("参数不正常！");
        }
        List<JobList> jobList = (List<JobList>) list.get(index).get("jobList");
        return APIResult.genSuccessApiResponse(jobList);
    }


    @RequestMapping("comp_company_page")
    public String comp_company_page(HttpServletRequest request){

        return "dashboard/comp_company_page";
    }



    /**
     * 公司资料页面加载信息
     * @param request
     * @return
     */
    @RequestMapping("comp_employer_dashboard")
    public String comp_employer_dashboard(HttpServletRequest request){
        Integer userid=1;
        Company company = companyInfoServices.queryCompanyByUserId(userid);
        request.setAttribute("company",company);
        List<Resume> resumes = null;
        List<Welfare> welfares = null;
        PageInfo<Resume> resumePageInfo;
        if (company.getId()!=null){
            resumePageInfo = receiveResumeServices.queryReceiveResume(1, 6,null, company.getId(), null);
            welfares = relationWelafareService.queryWelfarebyOwnerIdAndOwnerType(company.getId(), 2);
            if (resumePageInfo!=null){
                resumes = resumePageInfo.getList().subList(0,6);
            }
        }
        request.setAttribute("resumes",resumes);
        request.setAttribute("welfares",welfares);
        return "/dashboard/comp_employer_dashboard";
    }

    /**
     * 删除职位
     * @param jobid
     * @return
     */
    @PostMapping("deleteJobbyId")
    @ResponseBody
    public APIResult deleteJobbyId(Integer jobid){
        Map<String, String> result = null;
        if (jobid== null){
            return APIResult.genFailApiResponse401("非法访问！");
        }else {
          result = jobServices.deleteJob(jobid);
        }
        if (result.get(StaticPool.ERROR)!=null){
            return APIResult.genFailApiResponse500("服务器繁忙，请稍后再试");
        }
        return APIResult.genSuccessApiResponse(result.get(StaticPool.SUCCESS));
    }

    @RequestMapping("comp_employer_edit_profile")
    public String comp_employer_edit_profile(){

        return "dashboard/comp_employer_edit_profile";
    }

    /**
     * 加载公司的职位信息
     * @param request
     * @return
     */
    @RequestMapping("comp_employer_manage_jobs")
    public String comp_employer_manage_jobs(HttpServletRequest request){
        Integer indexpage = 1;
        Integer companyId = 1 ;
        Integer pagesize = 10;
        JobSearchEntity jobSearchEntity = new JobSearchEntity();
        jobSearchEntity.setCompanyId(companyId);
        jobSearchEntity.setStatus(1);
        jobSearchEntity.setAulStatus(null);
        PageInfo<Job> jobPageInfo = jobServices.selectJobByJobSearchEntityWithOutCompany(indexpage, pagesize, jobSearchEntity);
        request.setAttribute("pages",jobPageInfo);
        return "dashboard/comp_employer_manage_jobs";
    }

    /**
     * 进入发布职位页面
     * @param request
     * @return
     */
    @RequestMapping("comp_post_new_job")
    public String comp_post_new_job(HttpServletRequest request){
        List<Map<String, Object>> list = industriesListServices.queryALLWithJobList();
        request.setAttribute("list",list);
        List<TbArea> tbAreas = tbAreaService.queryTbAreabyParentId(100000);
        request.setAttribute("province",tbAreas);
        return "dashboard/comp_post_new_job";
    }
    @RequestMapping("addNewJob")
    @ResponseBody
    public APIResult addNewJob(Job job){
        Map<String, String> stringStringMap = jobServices.addJob(job);
        if (stringStringMap.get(StaticPool.SUCCESS)!=null){
            return APIResult.genSuccessApiResponse(stringStringMap.get(StaticPool.SUCCESS));
        }
        return APIResult.genSuccessApiResponse(stringStringMap.get(StaticPool.ERROR));
    }
    /**
     * 地址下拉框级联
     * @param parentId
     * @return
     */
    @RequestMapping("Area_list")
    @ResponseBody
    public APIResult Area_list(Integer parentId){
        List<TbArea> tbAreas = tbAreaService.queryTbAreabyParentId(parentId);
        return APIResult.genSuccessApiResponse(tbAreas);
    }

    @RequestMapping("message")
    public String message(){

        return "dashboard/message";
    }

    @RequestMapping("pricing_plans")
    public String pricing_plans(){

        return "dashboard/pricing_plans";
    }

}
