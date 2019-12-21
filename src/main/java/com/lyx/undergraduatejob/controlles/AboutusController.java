package com.lyx.undergraduatejob.controlles;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.search.entity.CompanySerchEntity;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;
import com.lyx.undergraduatejob.services.ICompanyInfoServices;
import com.lyx.undergraduatejob.services.IJobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;


@Controller
public class AboutusController {
    @Autowired
    IJobServices iJobService;

    @Autowired
    ICompanyInfoServices iCompanyInfoServices;

    @RequestMapping("toaboutus")
    public String toaboutus(){
        JobSearchEntity jobSearchEntity = new JobSearchEntity();
        jobSearchEntity.setKeyWord("status");
//        iJobService.selectJobByJobSearchEntity(1,1,jobSearchEntity);
        return "";

    }

    @RequestMapping("allCompany")
    public String allCompany(Integer indexpage, Company company, HttpServletRequest request){
        CompanySerchEntity companySerchEntity = new CompanySerchEntity();
        companySerchEntity.setAulStatus(1);
        companySerchEntity.setStatus(1);

        if(company.getId() != null){
            companySerchEntity.setId(company.getId());
        }
        if(company.getCompanyName() != null){
            companySerchEntity.setCompanyName(company.getCompanyName());
        }
        if(company.getCompanyType() != null){
            companySerchEntity.setCompanyType(company.getCompanyType());
        }

        System.out.println(company.toString());
        PageInfo pageInfo = iCompanyInfoServices.queryCompanyList(indexpage,companySerchEntity);
        request.setAttribute("pages",pageInfo);
        return"/companies";
    }
}
