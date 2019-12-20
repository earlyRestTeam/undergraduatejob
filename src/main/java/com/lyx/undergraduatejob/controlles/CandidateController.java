package com.lyx.undergraduatejob.controlles;

import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.services.impl.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CandidateController {

    @Autowired
    UserServicesImpl userServices;

    @RequestMapping("/user/candidate_applied_job")
    public String candidate_applied_job(){

        return "dashboard/candidate_applied_job";
    }


    @RequestMapping("/user/candidate_dashboard")
    public String candidate_dashboard(HttpServletRequest request){
        Integer userId = 1;
        Users users = userServices.queryUserById(1);
        System.out.println(users);
        request.setAttribute("user",users);
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
}
