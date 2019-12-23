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
import org.springframework.web.bind.annotation.ResponseBody;

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
}
