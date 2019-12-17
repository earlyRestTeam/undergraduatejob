package com.lyx.undergraduatejob.controlles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

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

    @RequestMapping("comp_applications")
    public String comp_applications(){

        return "dashboard/comp_applications";
    }

    @RequestMapping("comp_company_page")
    public String comp_company_page(){

        return "dashboard/comp_company_page";
    }

    @RequestMapping("comp_employer_dashboard")
    public String comp_employer_dashboard(){

        return "dashboard/comp_employer_dashboard";
    }

    @RequestMapping("comp_employer_edit_profile")
    public String comp_employer_edit_profile(){

        return "dashboard/comp_employer_edit_profile";
    }

    @RequestMapping("comp_employer_manage_jobs")
    public String comp_employer_manage_jobs(){

        return "dashboard/comp_employer_manage_jobs";
    }

    @RequestMapping("comp_post_new_job")
    public String comp_post_new_job(){

        return "dashboard/comp_post_new_job";
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
