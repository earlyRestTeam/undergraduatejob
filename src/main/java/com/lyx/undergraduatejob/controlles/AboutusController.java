package com.lyx.undergraduatejob.controlles;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.search.entity.CompanySerchEntity;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;
import com.lyx.undergraduatejob.search.entity.UsersSearchEntity;
import com.lyx.undergraduatejob.services.ICollectServices;
import com.lyx.undergraduatejob.services.ICompanyInfoServices;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.services.IUserServices;
import com.lyx.undergraduatejob.services.security.LoginEntityHelper;
import com.lyx.undergraduatejob.services.security.OnlineEntity;
import com.lyx.undergraduatejob.utils.Jobs;
import com.lyx.undergraduatejob.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class AboutusController {
    @Autowired
    IJobServices iJobService;

    @Autowired
    ICompanyInfoServices iCompanyInfoServices;

    @Autowired
    IUserServices userServices;

    @Autowired
    ICollectServices iCollectServices;

    @Autowired
    LoginEntityHelper loginEntityHelper;


    //关于平台
    @RequestMapping("toaboutus")
    public String toaboutus( HttpServletRequest request){
        JobSearchEntity jobSearchEntity = new JobSearchEntity();
        PageInfo<Job> jobPageInfo = iJobService.selectJobByJobSearchEntityWithOutCompany(1, 10, jobSearchEntity);
        Long jobnNums =  jobPageInfo.getTotal();

        UsersSearchEntity usersSearchEntity = new UsersSearchEntity();
        usersSearchEntity.setStatus(0);
        PageInfo<Users> usersPageInfo = userServices.queryUsers(1, 10, usersSearchEntity);
        long userNums = usersPageInfo.getTotal();

        CompanySerchEntity companySerchEntity = new CompanySerchEntity();
        companySerchEntity.setAulStatus(1);
        companySerchEntity.setStatus(1);
        PageInfo info = iCompanyInfoServices.queryCompanyListByAdmin(1, companySerchEntity);
        long companyNums = info.getTotal();

        Map<String, Long> map = new HashMap<>();
        map.put("jobnNums",jobnNums);
        map.put("userNums",userNums);
        map.put("companyNums",companyNums);

        request.setAttribute("result",map);
        return "/about_us";

    }

    //按条件查询公司
    @RequestMapping("allCompany")
    public String allCompany(Integer indexpage, String companyName,Integer id,String companyType, HttpServletRequest request){
        CompanySerchEntity companySerchEntity = new CompanySerchEntity();
        companySerchEntity.setAulStatus(1);
        companySerchEntity.setStatus(1);
        System.out.println("indexpage"  + indexpage);
        System.out.println("Typessss"  + companyType);
        System.out.println("idssss"  + id);
        System.out.println("CompanyNamesssss" + companyName);

        if(id != null){
            companySerchEntity.setId(id);
        }
        if(companyName!= null){
            companySerchEntity.setCompanyName(companyName);
        }
        if(companyType != null){
            companySerchEntity.setCompanyType(companyType);
        }

        PageInfo pageInfo = iCompanyInfoServices.queryCompanyList(indexpage,companySerchEntity);
        request.setAttribute("pages",pageInfo);

        if(indexpage == null && companyType == null &&
                companyName == null && id == null && indexpage == null){
            return"/companies";
        }else{
            return "/companies::company_type";
        }

    }


    //显示公司详情
    @RequestMapping("tocompanysingle")
    public String toCompanySingle(Integer companyId,Integer indexpage,HttpServletRequest request){
        OnlineEntity entity = loginEntityHelper.getOnlineEntity();
        MyPage page = iCompanyInfoServices.queryDetailbyCompanyId(indexpage,companyId);
        List<Integer> status = new ArrayList<>();
        List<Integer> companystatus = new ArrayList<>();
        List<Job> list = (List<Job>) page.getMap().get("jobs");
        if(entity != null){
            List<Integer> jobids = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                jobids.add(list.get(i).getId());
            }
            List<Integer> companyIdlist = new ArrayList<>();
            companyIdlist.add(companyId);
            status = iCollectServices.queryCollectStatus(jobids, 2, entity.getId(), 1);
            companystatus = iCollectServices.queryCollectStatus(companyIdlist,1,entity.getId(),1);
        }else{
            companystatus.add(0);
            for (int i = 0; i < list.size(); i++) {
                status.add(0);
            }
        }
        page.getMap().put("status",status);
        Integer flag = companystatus.get(0);
        page.getMap().put("flag",flag);
        System.out.println(page.getMap().get("jobs").toString());
        request.setAttribute("pages",page);
        return "/company_single";

    }
}
