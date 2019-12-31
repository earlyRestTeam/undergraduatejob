package com.lyx.undergraduatejob.controlles;

import com.lyx.undergraduatejob.services.impl.MessageServicesImpl;
import com.lyx.undergraduatejob.services.security.LoginEntityHelper;
import com.lyx.undergraduatejob.services.security.OnlineEntity;
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
 * @ClassName MessageController
 * @Author czb
 * @Date 2019/12/30
 * @Version V1.0
 **/
@Controller
@RequestMapping("/admin")
public class MessageController {

    @Autowired
    MessageServicesImpl services;

    @RequestMapping("/message")
    @ResponseBody
    public APIResult showMessages(Model model){
        Map<String, Object> map = services.showMessage();
        List autCompanies = (List) map.get("autCompanies");
        model.addAttribute("autCompanies",autCompanies.size());
        List autStudents = (List) map.get("autStudents");
        model.addAttribute("autStudents",autStudents.size());
        List jobs = (List) map.get("jobs");
        model.addAttribute("jobs",jobs.size());
        List resumes = (List) map.get("resumes");
        model.addAttribute("resumes",resumes.size());
        List complaints = (List) map.get("complaints");
        model.addAttribute("complaints",complaints.size());
        int sum = autCompanies.size() + autStudents.size() + jobs.size() + resumes.size() + complaints.size();

        model.addAttribute("sum",sum);

        LoginEntityHelper loginEntityHelper = new LoginEntityHelper();
        OnlineEntity onlineEntity = loginEntityHelper.getOnlineEntity();
        model.addAttribute("admin",onlineEntity);

        return APIResult.genSuccessApiResponse(model);
    }
}
