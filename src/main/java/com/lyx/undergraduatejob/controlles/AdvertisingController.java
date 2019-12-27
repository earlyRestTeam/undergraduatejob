package com.lyx.undergraduatejob.controlles;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Advertising;
import com.lyx.undergraduatejob.services.impl.AdvertisingServicesImpl;
import com.lyx.undergraduatejob.utils.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;


/**
 * @ClassName AdvertisingController
 * @Date 2019/12/20
 * @Version V1.0
 **/
@Controller
@RequestMapping("/admin")
public class AdvertisingController {

    @Autowired
    AdvertisingServicesImpl services;

    @RequestMapping("/advertising")
    public String index(HttpServletRequest request, Integer indexpage, Advertising advertising,String keyword){
        PageInfo info = services.queryAdvertiser(indexpage, advertising, keyword);
        request.setAttribute("pages",info);
        request.setAttribute("keyword",keyword == null ? "" : keyword);
        request.setAttribute("id",advertising.getId());
        request.setAttribute("status",advertising.getStatus());
        return "/admin/manager-advertising";
    }

    @RequestMapping("/batch_delete")
    public String batchDel(Integer[] ids){
        boolean b = services.deleteAdvertising(ids);
        return "redirect:/admin/manager-advertising";
    }

//    @RequestMapping("/advertising")
//    public String index(HttpServletRequest request, Advertising advertising,String keyword){
//        List<Advertising> advertisings = services.queryAdvertising(advertising, keyword);
//        request.setAttribute("list",advertisings);
//        request.setAttribute("keyword",keyword == null ? "" : keyword);
//        request.setAttribute("id",advertising.getId());
//        request.setAttribute("status",advertising.getStatus());
//        return "/admin/manager-advertising";
//    }

    @RequestMapping("/adv_delete")
    public String deleteAdvertising(Advertising advertising){
        Map<String, Object> stringObjectMap = services.deleteAdvertising(advertising);
        return "/admin/manager-advertising";
    }

    @RequestMapping("/adv_add")
    public String addAdvertising(Model model, Advertising advertising){
        Map<String, Object> stringObjectMap = services.addAdvertising(advertising);
        model.addAttribute("adv",stringObjectMap);
        return "redirect:/admin/manager-advertising";
    }

    @RequestMapping("/adv_update")
    @ResponseBody
    public APIResult updateAdvertising( Advertising advertising){
        Advertising advertising1 = services.updateAdvertising(advertising);
        return APIResult.genSuccessApiResponse(advertising1);
    }

    @RequestMapping("/adver_update")
    public String update(Advertising advertising){
        Map<String, Object> update = services.update(advertising);
        return "/admin/manager-advertising";
    }
}
