package com.lyx.undergraduatejob.controlles;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.Message;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.search.entity.CompanySerchEntity;
import com.lyx.undergraduatejob.search.entity.UsersSearchEntity;
import com.lyx.undergraduatejob.services.impl.ICompanyInfoServicesImpl;
import com.lyx.undergraduatejob.services.impl.ManagerUserServices;
import com.lyx.undergraduatejob.services.impl.MessageServicesImpl;
import com.lyx.undergraduatejob.services.impl.UserServicesImpl;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Date 2019/12/24 14:56
 * @Version V1.0
 */
@Controller
@RequestMapping("/managerUser")
public class ManagerUserController {
    @Autowired
    ICompanyInfoServicesImpl companyInfoServices;

    @Autowired
    ManagerUserServices userServices;

    @Autowired
    MessageServicesImpl messageServices;

    @RequestMapping("query_company")
    public String queryCompany(HttpServletRequest request,Integer indexpage, String companyName,String companyType,Integer companyVip,Integer status){

        CompanySerchEntity entity = new CompanySerchEntity();
        entity.setCompanyName(companyName);
        entity.setCompanyType(companyType);
        entity.setCompanyVip(companyVip);
        entity.setStatus(status);
        PageInfo info = companyInfoServices.queryCompanyListByAdmin(indexpage, entity);
        request.setAttribute("pages",info);

        return "/admin/manager-company::table";
    }

    @RequestMapping("update_company_status")
    public String updateCompanyStatus(HttpServletRequest request,Integer id,Integer status,Integer userId){
        status = (status+1)%2;
        Company company = new Company();
        company.setId(id);
        company.setStatus(status);
        Map<String, String> map = companyInfoServices.updateCompanyInfobyAdmin(company);

        if (map.get(StaticPool.SUCCESS) != null){
            Message message = new Message();
            message.setReceiverId(userId);
            message.setReceiverType(1);
            if (status == 0){
                message.setMessageTitle("公司账号冻结");
                message.setMessageContent("您的公司账号已冻结，要解冻请联系客服。");
            } else {
                message.setMessageTitle("公司账号启用");
                message.setMessageContent("您的公司账号已启用，完成认证后就可以发布招聘信息了！");
            }
            Map<String, Object> addMessage = messageServices.addMessage(message);
            request.setAttribute("result",addMessage);
        } else {
            request.setAttribute("result",map);
        }

        return "redirect:/admin/manager-company";///managerUser/query_company
    }

    @RequestMapping("query_student")
    public String queryStudent(HttpServletRequest request,Integer indexpage, String username,String nickName,Integer userVip,Integer status){

        UsersSearchEntity entity = new UsersSearchEntity();
        entity.setUsername(username);
        entity.setNickName(nickName);
        entity.setUserVip(userVip);
        entity.setStatus(status);
        PageInfo<Users> info = userServices.queryUsers(indexpage, entity);
        request.setAttribute("pages",info);

        return "/admin/manager-student::table";
    }

    @RequestMapping("update_student_status")
    public String updateUserStatus(HttpServletRequest request,Integer id,Integer status){
        status = (status+1)%2;
        Users student = new Users();
        student.setId(id);
        student.setStatus(status);
        Map<String, String> map = userServices.updateStatus(student);
        request.setAttribute("result",map);

        return "redirect:/admin/manager-student";
    }
}
