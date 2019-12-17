package com.lyx.undergraduatejob.controlles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
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


    @RequestMapping("auth-login")
    public String login(){
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


    @RequestMapping("index")
    public String index(){
        return "/admin/index";
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
