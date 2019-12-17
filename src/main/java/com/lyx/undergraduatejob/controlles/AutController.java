package com.lyx.undergraduatejob.controlles;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aut")
public class AutController {
    @RequestMapping("aut_company")
    public String aut_company(){
        return "/aut/aut_company";
    }

    @RequestMapping("aut_student")
    public String aut_student(){
        return "/aut/aut_student";
    }
}
