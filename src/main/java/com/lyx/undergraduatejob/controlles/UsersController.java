package com.lyx.undergraduatejob.controlles;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.search.entity.LoginEntity;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.services.IUserServices;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UsersController {
    @Autowired
    IUserServices userServices;
    @Value("${jwt.tokenHead}")
    String tokenHead;
    @Autowired
    IJobServices jobServices;
    @PostMapping("/login")
    @ResponseBody
    public APIResult result(@RequestBody LoginEntity entity, HttpServletResponse response){
        String username = entity.getUsername();
        String password = entity.getPassword();
        String token = null;
        if(!StringUtils.isEmpty(username)
                && !StringUtils.isEmpty(password) ) {
            token = userServices.login(username, password);
        }
        Map<String,String> map = new HashMap<>();
        map.put("header",tokenHead);
        map.put("token",token);
//        response.setHeader(tokenHead,token);
//        response.A
        return APIResult.genSuccessApiResponse(map);
    }
    @PostMapping("/hi")
    @ResponseBody
    public APIResult sayHi(){
        return APIResult.genSuccessApiResponse("hi");
    }
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
    public String job_single(@RequestParam("jobid") Integer id,Model model){
        Map<String, Object> map = jobServices.selectJobById(id);
        model.addAttribute("res",map);
        return "job_single";
    }

    @RequestMapping("/user/login")
    public String login(Model model){
        model.addAttribute("type", StaticPool.USER);
        return "/login";
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
