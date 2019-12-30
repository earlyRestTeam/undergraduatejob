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
import java.util.Date;
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
    @Autowired
    JobListServiceImpl jobListService;
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
                resumes = resumePageInfo.getList();
                if (resumes.size()>=6){
                    resumes =resumes.subList(0,6);
                }else {
                    resumes = resumes.subList(0,resumes.size());
                }
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

    /**
     * 进入修改公司信息界面加载原有信息
     * @param request
     * @return
     */
    @RequestMapping("comp_employer_edit_profile")
    public String comp_employer_edit_profile(HttpServletRequest request){
        Integer companyId = 1;
        Company company = companyInfoServices.queryCompanyByUserId(1);
        request.setAttribute("company",company);
        List<TbArea> tbAreas = tbAreaService.queryTbAreabyParentId(100000);
        request.setAttribute("province",tbAreas);
        List<Picture> pictures = pictuerService.queryPicturebyOwnerid(companyId, 2, 4);
        if (!pictures.isEmpty()){
            request.setAttribute("picture",pictures.get(0));
        }else {
            request.setAttribute("picture",new Picture());
        }
        List<Picture> pictures1 = pictuerService.queryPicturebyOwnerid(companyId, 2, 3);
        if (pictures1.isEmpty()){
            pictures1 = new ArrayList<>();
        }
        request.setAttribute("pictures",pictures1);

        return "/dashboard/comp_employer_edit_profile";
    }

    /**
     * 修改公司信息
     * @param company
     * @return
     */
    @PostMapping("updateCompanyinfo")
    @ResponseBody
    public APIResult updateCompanyinfo(Company company){
        company.setId(1);
        company.setUserId(1);
        Map<String, String> stringStringMap = companyInfoServices.updateCompanyInfo(company);
        if (stringStringMap.get(StaticPool.SUCCESS)!=null){
            return APIResult.genSuccessApiResponse(stringStringMap.get(StaticPool.SUCCESS));
        }
        return APIResult.genFailApiResponse500(stringStringMap.get(StaticPool.ERROR));
    }

    /**
     * 进入修改职位信息界面
     * @param model
     * @param jobid
     * @return
     */
    @RequestMapping("comp_edit_job")
    public String comp_edit_job(Model model,Integer jobid){
        Map<String, Object> stringObjectMap = jobServices.selectJobById(jobid);
        model.addAttribute("job",stringObjectMap.get("job"));
        List<Map<String, Object>> list = industriesListServices.queryALLWithJobList();
        model.addAttribute("list",list);
        List<TbArea> tbAreas = tbAreaService.queryTbAreabyParentId(100000);
        model.addAttribute("province",tbAreas);
        return "/dashboard/comp_edit_job";
    }

    /**
     * 发布/取消发布职业
     * @param job
     * @return
     */
    @PostMapping("updateJobPushStatus")
    @ResponseBody
    public APIResult updateJobPushStatus(Job job){
        Integer companyId = 1;

        Map<String, String> stringStringMap = jobServices.updateJob(job,companyId);
        if (stringStringMap.get(StaticPool.SUCCESS)!=null){
            return APIResult.genSuccessApiResponse(stringStringMap.get(StaticPool.SUCCESS));
        }
        return APIResult.genFailApiResponse500(stringStringMap.get(StaticPool.ERROR));
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
        Integer pagesize = 5;
        JobSearchEntity jobSearchEntity = new JobSearchEntity();
        jobSearchEntity.setCompanyId(companyId);
        jobSearchEntity.setStatus(1);
        jobSearchEntity.setAulStatus(null);
        PageInfo<Job> jobPageInfo = jobServices.selectJobByJobSearchEntityWithOutCompany(indexpage, pagesize, jobSearchEntity);
        request.setAttribute("pages",jobPageInfo);
        return "dashboard/comp_employer_manage_jobs";
    }

    /**
     * 分页公司的职位信息局部刷新
     * @param request
     * @return
     */
    @RequestMapping("comp_employer_manage_jobs_pages")
    public String comp_employer_manage_jobs_pages(HttpServletRequest request,Integer indexpage){
        Integer companyId = 1 ;
        Integer pagesize = 5;
        JobSearchEntity jobSearchEntity = new JobSearchEntity();
        jobSearchEntity.setCompanyId(companyId);
        jobSearchEntity.setStatus(1);
        jobSearchEntity.setAulStatus(null);
        PageInfo<Job> jobPageInfo = jobServices.selectJobByJobSearchEntityWithOutCompany(indexpage, pagesize, jobSearchEntity);
        request.setAttribute("pages",jobPageInfo);
        return "dashboard/comp_employer_manage_jobs::joblistdiv";
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

    /**
     * 公司发布新职位
     * @param job
     * @return
     */
    @RequestMapping("addNewJob")
    @ResponseBody
    public APIResult addNewJob(Job job){
        Integer companyId = 1;
        job.setCompanyId(companyId);
        Company company = companyInfoServices.queryCompanyById(companyId);
        job.setCompanyLogo(company.getLogo());
        job.setCompanyName(company.getCompanyName());
        job.setCreateTime(new Date());
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
