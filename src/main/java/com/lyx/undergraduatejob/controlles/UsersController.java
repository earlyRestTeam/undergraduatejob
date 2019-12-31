package com.lyx.undergraduatejob.controlles;


import com.alibaba.fastjson.JSONObject;
import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.IndustriesList;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.search.entity.LoginEntity;
import com.lyx.undergraduatejob.services.ICompanyInfoServices;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.services.IUserServices;
import com.lyx.undergraduatejob.services.Industries_listServices;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Controller
public class UsersController {
    @Autowired
    IUserServices userServices;
    @Value("${jwt.tokenHead}")
    String tokenHead;
    @Autowired
    IJobServices jobServices;
    @Autowired
    ICompanyInfoServices companyInfoServices;
    @Autowired
    Industries_listServices industries_listServices;
    @Autowired
    RedisTemplate redisTemplate;
    @RequestMapping("/logout")
    public String logout(){
        return "/";
    }
    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping({"/","index"})
    public String index(Model model){
//        companyInfoServices;
        // 明星 公司
        List<Company> companies = companyInfoServices.queryIndexCompany();
        // 明星 职业
        List<IndustriesList> industriesList = industries_listServices.queryIndexIndustries();
        // 明星 工作
        List<Job> jobs = jobServices.queryStarJob();
        // 获取 最新的 职业
        List<Job> recentJob = jobServices.queryRecentJob();
        // 获取最高薪的职业
        List<Job> bestJob = jobServices.queryBestJob();
        List<Job> bestFullJob = jobServices.queryBestFullJob();
        List<Job> bestPartJob = jobServices.queryBestPartJob();

        model.addAttribute("companies",companies);
        model.addAttribute("industriesList",industriesList);
        model.addAttribute("jobs",jobs);
        model.addAttribute("recentJob",recentJob);
        model.addAttribute("bestJob",bestJob);
        model.addAttribute("bestFullJob",bestFullJob);
        model.addAttribute("bestPartJob",bestPartJob);
        return "index";
    }
    @PostMapping("/user/login")
    @ResponseBody
    public APIResult result(@RequestBody LoginEntity entity){
        String username = entity.getUsername();
        String password = entity.getPassword();
        String token = null;
        Map<String,String> res;
        if(StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password) ) {
            throw new RuntimeException("params error!");
        }
        res = userServices.login(username, password);
        token = res.get(StaticPool.SUCCESS);
        if(token == null)
            return APIResult.genFailApiResponse401(res.get(StaticPool.ERROR) == null ? "服务繁忙" : res.get(StaticPool.ERROR));
//        response.addHeader(tokenHead,token);
//        response.addCookie(new Cookie(tokenHead,token));
        Map<String,String> map = new HashMap<>();
        map.put("header",tokenHead);
        map.put("token",token);

        return APIResult.genSuccessApiResponse(map);
    }
    /**
     * 账号找回
     * @param jsonObject
     * @return
     */
    @PostMapping("/user/findBack")
    @ResponseBody
    public APIResult findBack(@RequestBody JSONObject jsonObject){
        APIResult result = null;
        String email = (String) jsonObject.get("email");
        String password = (String) jsonObject.get("password");
        String code = (String) jsonObject.get("code");
        if(org.springframework.util.StringUtils.isEmpty(email) || org.springframework.util.StringUtils.isEmpty(password)
                || org.springframework.util.StringUtils.isEmpty(code)){
            result =  APIResult.genFailApiResponse500("PARAMS ERROR!");
            return result;
        }
        Map<String, String> res = userServices.forgetPassword(email, password, code);
        if( res.get(StaticPool.ERROR) != null ){
            result = APIResult.genFailApiResponse500(res.get(StaticPool.ERROR));
        }else {
            result = APIResult.genSuccessApiResponse(res.get(StaticPool.SUCCESS));
        }
        return result;
    }
    @PostMapping("/user/register")
    @ResponseBody
    public APIResult register(@RequestBody JSONObject jsonObject){
        APIResult result = null;

        String code = (String) jsonObject.get("code");
        String username = (String) jsonObject.get("username");
        String password = (String) jsonObject.get("password");
        String email = (String) jsonObject.get("email");
        if(org.springframework.util.StringUtils.isEmpty(code) ||
                org.springframework.util.StringUtils.isEmpty(username) ||
                org.springframework.util.StringUtils.isEmpty(password) ||
                org.springframework.util.StringUtils.isEmpty(email)){
            result =  APIResult.genFailApiResponse500("PARAMS ERROR!");
            return result;
        }
        Users user = new Users();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        String mail = user.getEmail();
        String code2 = (String) redisTemplate.opsForValue().get(mail);

        if(code.equalsIgnoreCase(code2)){
            redisTemplate.delete(mail);

            Map<String, String> res = userServices.addUser(user);
            if( res.get(StaticPool.ERROR) != null ){
                result = APIResult.genFailApiResponse500(res.get(StaticPool.ERROR));
            }else {
                result = APIResult.genSuccessApiResponse(res.get(StaticPool.SUCCESS));
            }
        }else {
            result = APIResult.genFailApiResponse500("验证码错误！请重新填写！");
        }
        return result;
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
    @GetMapping("resune_listing_grid_left_filter")
    public String resumes(){
        return "resune_listing_grid_left_filter";
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


    @RequestMapping("forgetPassword")
    public String findBack(){
        return "/findBack";
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
        List<Job> jobList = jobServices.queryNeerJob(1,((Job) map.get("job")).getJobType());
        List<Job> jobList1 = jobServices.queryNeerJob(2,((Job) map.get("job")).getJobType());
        model.addAttribute("res",map);
        model.addAttribute("jobList",jobList);
        model.addAttribute("jobList1",jobList1);
        return "job_single";
    }

    @RequestMapping("/user/loginPage")
    public String login(Model model){
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
