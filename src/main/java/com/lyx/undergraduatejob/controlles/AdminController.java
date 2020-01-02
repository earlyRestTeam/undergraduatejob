package com.lyx.undergraduatejob.controlles;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.*;
import com.lyx.undergraduatejob.search.entity.CompanySerchEntity;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;
import com.lyx.undergraduatejob.search.entity.LoginEntity;
import com.lyx.undergraduatejob.search.entity.UsersSearchEntity;
import com.lyx.undergraduatejob.services.IAdminServices;
import com.lyx.undergraduatejob.services.impl.*;
import com.lyx.undergraduatejob.services.security.LoginEntityHelper;
import com.lyx.undergraduatejob.services.security.OnlineEntity;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AutCompanyServiceImpl autCompanyService;

    @Autowired
    AutStudentServiceImpl autStudnetService;

    @Autowired
    ICompanyInfoServicesImpl companyInfoServices;

    @Autowired
    UserServicesImpl userServices;

    @Autowired
    IAdminServices adminServices;

    @Value("${jwt.tokenHead}")
    String tokenHead;

    @Autowired
    ResumeServicesImp resumeServices;

    @Autowired
    JobServicesImpl jobServices;

    @RequestMapping("add")
    public String add(){
        return "/admin/add";
    }
    /**
     * 职位审核管理
     * @return
     */
    @RequestMapping("audit-job")
    public String audit_job(HttpServletRequest request) {
        JobSearchEntity entity = new JobSearchEntity();
        entity.setStatus(null);
        entity.setAulStatus(null);
        PageInfo<Job> info = jobServices.selectJobByJobSearchEntityWithOutCompany(1, 10, entity);
        request.setAttribute("pages",info);
        request.setAttribute("job",new Job());
        return "/admin/audit-job";
    }
    /**
     * 简历审核管理
     * @return
     */
    @RequestMapping("audit-resume")
    public String audit_resume(HttpServletRequest request) {
        PageInfo<Resume> info = resumeServices.queryResumeByAdminExample(1, 10, null, null);
        request.setAttribute("pages",info);
        request.setAttribute("resume",new Resume());
        return "/admin/audit-resume";
    }
    /**
     * 学生管理
     * @return
     */
    @RequestMapping("manager-student")
    public String manager_student(HttpServletRequest request) {
        UsersSearchEntity usersSearchEntity = new UsersSearchEntity();
        PageInfo<Users> info = userServices.queryUsers(1, 10, usersSearchEntity);
        request.setAttribute("pages",info);
        return "/admin/manager-student";
    }

    @PostMapping("login")
    @ResponseBody
    public APIResult login(@RequestBody LoginEntity loginEntity, HttpServletResponse response) {
        String username = loginEntity.getUsername();
        String password = loginEntity.getPassword();
        String token = null;
        Map<String,String> res;
        if(StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password) ) {
            //throw new RuntimeException("params error!");
            return APIResult.genFailApiResponse401("参数错误！");
        }
        res = adminServices.login(username, password);
        token = res.get(StaticPool.SUCCESS);
        if(token == null)
            return APIResult.genFailApiResponse401(res.get(StaticPool.ERROR) == null ? "服务繁忙" : res.get(StaticPool.ERROR));
        response.addHeader(tokenHead,token);
        response.addCookie(new Cookie(tokenHead,token));
        Map<String,String> map = new HashMap<>();
        map.put("header",tokenHead);
        map.put("token",token);

        return APIResult.genSuccessApiResponse(map);
    }
    /**
     * 公司管理
     * @return
     */
    @RequestMapping("manager-company")
    public String manager_company(HttpServletRequest request) {
        CompanySerchEntity companySerchEntity = new CompanySerchEntity();
        PageInfo info = companyInfoServices.queryCompanyListByAdmin(1, companySerchEntity);
        request.setAttribute("pages",info);
        return "/admin/manager-company";
    }
    /**
     * 学生认证管理
     * @return
     */
    @RequestMapping("authentication-student")
    public String authentication_student(HttpServletRequest request) {
        AutStudent autStudnet = new AutStudent();
        PageInfo<AutStudent> info = autStudnetService.queryAutStudentBack(1,autStudnet);
        request.setAttribute("pages",info);
        return "/admin/authentication-student";
    }
    /**
     * 公司认证管理
     * @return
     */
    @RequestMapping("authentication-company")
    public String authentication_company(HttpServletRequest request) {
        AutCompany autCompany = new AutCompany();
        PageInfo<AutCompany> info = autCompanyService.queryAutCompanyBack(1,autCompany);
        request.setAttribute("pages",info);
        return "/admin/authentication-company";
    }
    /**
     * 广告管理
     * @return
     */
    @RequestMapping("manager-advertising")
    public String manager_advertising() {
        return "forward:/admin/advertising";
    }
    /**
     * 评论管理
     * @return
     */
    @RequestMapping("manager-comment")
    public String manager_comment() {
        return "forward:/admin/comment";
    }
    /**
     * 投诉管理
     * @return
     */
    @RequestMapping("manager-complaint")
    public String manager_complaint() {
        return "forward:/admin/complaint";
    }

    @RequestMapping("auth-404")
    public String auth404(){
        return "/admin/auth-404";
    }

    @RequestMapping("auth-500")
    public String auth500(){
        return "/admin/auth-500";
    }

    @RequestMapping("auth-lock-screen")
    public String authLockScreen(){
        return "/admin/auth-lock-screen";
    }


    @RequestMapping("loginPage")
    public String login(Model model){
        model.addAttribute("type", StaticPool.ADMIN);
        return "/admin/auth-login";
    }


    @RequestMapping("auth-recoverpassword")
    public String auth_recoverpassword(){
        return "/admin/auth-recoverpassword";
    }


    @RequestMapping("auth-register")
    public String auth_register(){
        return "/admin/auth-register";
    }


    @RequestMapping("calendar")
    public String calendar(){
        return "/admin/calendar";
    }


    @RequestMapping("chart-chartist")
    public String chart_chartist(){
        return "/admin/chart-chartist";
    }


    @RequestMapping("chart-chartjs")
    public String chart_chartjs(){
        return "/admin/chart-chartjs";
    }


    @RequestMapping("chart-flot")
    public String chart_flot(){
        return "/admin/chart-flot";
    }


    @RequestMapping("chart-knob")
    public String chart_knob(){
        return "/admin/chart-knob";
    }


    @RequestMapping("chart-sparkline")
    public String chart_sparkline(){
        return "/admin/chart-sparkline";
    }


    @RequestMapping("email-compose")
    public String email_compose(){
        return "/admin/email-compose";
    }


    @RequestMapping("email-inbox")
    public String email_inbox(){
        return "/admin/email-inbox";
    }


    @RequestMapping("email-read")
    public String email_read(){
        return "/admin/email-read";
    }


    @RequestMapping("form-advanced")
    public String form_advanced(){
        return "/admin/form-advanced";
    }


    @RequestMapping("form-elements")
    public String form_elements(){
        return "/admin/form-elements";
    }


    @RequestMapping("form-summernote")
    public String form_summernote(){
        return "/admin/form-summernote";
    }


    @RequestMapping("form-upload")
    public String form_upload(){
        return "/admin/form-upload";
    }


    @RequestMapping("form-validation")
    public String form_validation(){
        return "/admin/form-validation";
    }


    @RequestMapping("form-wizard")
    public String form_wizard(){
        return "/admin/form-wizard";
    }


    @RequestMapping("icons-dripicons")
    public String icons_dripicons(){
        return "/admin/icons-dripicons";
    }


    @RequestMapping("icons-materialdesign")
    public String icons_materialdesign(){
        return "/admin/icons-materialdesign";
    }


    @RequestMapping("icons-simpleline")
    public String icons_simpleline(){
        return "/admin/icons-simpleline";
    }


    @RequestMapping({"/","index"})
    public String index(){
        return "/admin/index";
    }

    @RequestMapping("index-moban")
    public String index1(){
        return "/admin/index1";
    }

    @RequestMapping("layouts-boxed")
    public String layouts_boxed(){
        return "/admin/layouts-boxed";
    }


    @RequestMapping("layouts-dark-header")
    public String layouts_dark_header(){
        return "/admin/layouts-dark-header";
    }


    @RequestMapping("layouts-dark-sidebar")
    public String layouts_dark_sidebar(){
        return "/admin/layouts-dark-sidebar";
    }


    @RequestMapping("layouts-sidebar-collapsed")
    public String layouts_sidebar_collapsed(){
        return "/admin/layouts-sidebar-collapsed";
    }


    @RequestMapping("layouts-small-sidebar")
    public String layouts_small_sidebar(){
        return "/admin/layouts-small-sidebar";
    }


    @RequestMapping("maps-google")
    public String maps_google(){
        return "/admin/maps-google";
    }


    @RequestMapping("maps-vector")
    public String maps_vector(){
        return "/admin/maps-vector";
    }


    @RequestMapping("pages-faq")
    public String pages_faq(){
        return "/admin/pages-faq";
    }


    @RequestMapping("pages-invoice")
    public String pages_invoice(){
        return "/admin/pages-invoice";
    }


    @RequestMapping("pages-pricing")
    public String pages_pricing(){
        return "/admin/pages-pricing";
    }


    @RequestMapping("pages-starter")
    public String pages_starter(){
        return "/admin/pages-starter";
    }


    @RequestMapping("pages-timeline")
    public String pages_timeline(){
        return "/admin/pages-timeline";
    }


    @RequestMapping("tables-basic")
    public String tables_basic(){
        return "/admin/tables-basic";
    }


    @RequestMapping("tables-datatable")
    public String tables_datatable(){
        return "/admin/tables-datatable";
    }


    @RequestMapping("ui-buttons")
    public String ui_buttons(){
        return "/admin/ui-buttons";
    }


    @RequestMapping("ui-cards")
    public String ui_cards(){
        return "/admin/ui-cards";
    }


    @RequestMapping("ui-checkbox-radio")
    public String ui_checkbox_radio(){
        return "/admin/ui-checkbox-radio";
    }


    @RequestMapping("ui-general")
    public String ui_general(){
        return "/admin/ui-general";
    }


    @RequestMapping("ui-grid")
    public String ui_grid(){
        return "/admin/ui-grid";
    }


    @RequestMapping("ui-modals")
    public String ui_modals(){
        return "/admin/ui-modals";
    }


    @RequestMapping("ui-notifications")
    public String ui_notifications(){
        return "/admin/ui-notifications";
    }


    @RequestMapping("ui-progress")
    public String ui_progress(){
        return "/admin/ui-progress";
    }


    @RequestMapping("ui-range-slider")
    public String ui_range_slider(){
        return "/admin/ui-range-slider";
    }


    @RequestMapping("ui-ribbons")
    public String ui_ribbons(){
        return "/admin/ui-ribbons";
    }


    @RequestMapping("ui-sweetalerts")
    public String ui_sweetalerts(){
        return "/admin/ui-sweetalerts";
    }


    @RequestMapping("ui-tabs")
    public String ui_tabs(){
        return "/admin/ui-tabs";
    }


    @RequestMapping("ui-typography")
    public String ui_typography(){
        return "/admin/ui-typography";
    }



}
