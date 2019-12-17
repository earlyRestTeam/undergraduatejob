package com.lyx.undergraduatejob.controlles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("")
public class UsersController {

    @RequestMapping("about_us")
    public String about_us(){

        return "about_us";
    }
    
    @RequestMapping("blog_category_right_sidebar")
    public String blog_category_right_sidebar(){

        return "blog_category_right_sidebar";
    }

    @RequestMapping("blog_single")
    public String blog_single(){

        return "blog_single";
    }

    @RequestMapping("companies")
    public String companies(){

        return "companies";
    }
    
    @RequestMapping("company_single")
    public String company_single(){

        return "company_single";
    }
    
    @RequestMapping("contact_us")
    public String contact_us(){

        return "contact_us";
    }

    @RequestMapping("error_page")
    public String error_page(){

        return "error_page";
    }

    @RequestMapping("index")
    public String index(){

        return "index";
    }


    @RequestMapping("index_III")
    public String index_III(){

        return "index_III";
    }
    
    @RequestMapping("job_listing_grid_left_filter")
    public String job_listing_grid_left_filter(){

        return "job_listing_grid_left_filter";
    }
    
    @RequestMapping("job_listing_list_left_filter")
    public String job_listing_list_left_filter(){

        return "job_listing_list_left_filter";
    }
    
    @RequestMapping("job_single")
    public String job_single(){

        return "job_single";
    }

    @RequestMapping("login")
    public String login(){

        return "login";
    }
    
    @RequestMapping("pricing_table")
    public String pricing_table(){

        return "pricing_table";
    }
    
    @RequestMapping("sign_up")
    public String sign_up(){

        return "sign_up";
    }
    
}
