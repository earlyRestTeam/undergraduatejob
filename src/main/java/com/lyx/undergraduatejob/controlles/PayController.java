package com.lyx.undergraduatejob.controlles;

import cn.hutool.http.HttpResponse;
import com.alipay.api.AlipayApiException;
import com.lyx.undergraduatejob.mapper.UsersMapper;
import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.pojo.VipOrder;
import com.lyx.undergraduatejob.services.IAliPayService;
import com.lyx.undergraduatejob.services.ICompanyInfoServices;
import com.lyx.undergraduatejob.services.OrderServices;
import com.lyx.undergraduatejob.services.security.LoginEntityHelper;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.PayException;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.attribute.standard.NumberUp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author :Yang Jiahong
 * @date :2019/12/23 19:07
 */
@Controller
public class PayController {

    @Autowired
    IAliPayService iAliPayService;

    @Autowired
    ICompanyInfoServices iCompanyInfoServices;

    @Autowired
    OrderServices orderServices;

    @Autowired
    ICompanyInfoServices companyInfoServices;

    @Autowired
    UsersMapper usersMapper;

    @RequestMapping("topay")
    public void sumbitOrder(Integer flag, HttpServletResponse httpResponse) throws IOException {
        String amount = null;
        String orderno = null;
        switch (flag){
            case 1:
                amount = "30";
                break;
            case 2:
                amount = "159";
                break;
            case 3:
                amount = "288";
                break;
            case 4:
                amount = "20";
                break;
            case 5:
                amount = "100";
                break;
            case 6:
                amount = "180";
                break;
        }
        if(flag == 1 || flag == 2 || flag == 3){
             orderno = "c" + UUID.randomUUID().toString().replaceAll("-", "");
        }else{
             orderno = "u" + UUID.randomUUID().toString().replaceAll("-", "");
        }

        String form = iAliPayService.genPage(amount,orderno);
        httpResponse.setContentType("text/html;charset=" + "utf8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    /*
    LoginEntityHelper loginEntityHelper = new LoginEntityHelper();
    Users user = (Users) loginEntityHelper.getEntityByClass(User.class);
    if(user != null){
    */
    @RequestMapping("/user/returnUrl")
    public String paySuccess(HttpServletRequest request){
            String orderno = request.getParameter("out_trade_no");
            String amount = request.getParameter("total_amount");
            System.out.println("ssssssssss" + orderno);
            int time = 0;
            if(amount.equals("30.00") || amount.equals("20.00")){
                time = 30;
            }
            else if(amount.equals("159.00") || amount.equals("100.00")){
                time = 180;
            }else if(amount.equals("288.00") || amount.equals("180.00")){
                time = 365;
            }

            if(orderno != null && orderno.indexOf("c") == 0){
                Company company = iCompanyInfoServices.queryCompanyByUserId(1);
                int companyID = company.getId();
                VipOrder vipOrder = new VipOrder();
                vipOrder.setCreateTime(new Date());
                vipOrder.setStatus(1);
                vipOrder.setOrderTitle("会员充值");
                vipOrder.setUserId(companyID);
                vipOrder.setVipType(2);
                vipOrder.setTotalAmount(Float.parseFloat(amount));
                vipOrder.setOrderno(orderno);

                if(iAliPayService.checkAlipay(orderno)) {
                    Map<String,String> res = new HashMap<>();
                    int count = 3;
                    company.setCompanyVip(1);
                    Date endTime1 = company.getVipEndTime();
                    LocalDateTime endTime = null;
                    LocalDateTime time1 = LocalDateTime.now();
                    if(endTime1 == null){
                        endTime = time1.plusDays(time);
                    }else{
                        LocalDateTime locaendTime1 =  LocalDateTime.ofInstant(endTime1.toInstant(), ZoneId.systemDefault());
                        if(locaendTime1.isAfter(time1)){
                            endTime = locaendTime1.plusDays(time);
                        }else{
                            endTime = time1.plusDays(time);
                        }
                    }
                    company.setVipStartTime(new Date());
                    company.setVipEndTime(Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant()));
                    int result = orderServices.insertOrder(vipOrder);
                    res = companyInfoServices.updateCompanyInfo(company);
                    if(result > 0  && res.get(StaticPool.SUCCESS) != null){
                        return "redirect:/allCompany";
                    }else{
                        try {
                            iAliPayService.refund(orderno,amount);
                        } catch (AlipayApiException e) {
                            e.printStackTrace();
                        }
                        throw new PayException("订单提交失败");
                    }
                }
                return "redirect:/pricing_table";
            }

            else if(orderno != null && orderno.indexOf("u") == 0){


            /*    LoginEntityHelper loginEntityHelper = new LoginEntityHelper();
                Users user = (Users) loginEntityHelper.getEntityByClass(User.class);
                if(user != null){*/
                    VipOrder vipOrder = new VipOrder();
                    vipOrder.setCreateTime(new Date());
                    vipOrder.setStatus(1);
                    vipOrder.setOrderTitle("会员充值");
                    vipOrder.setUserId(1);
                    vipOrder.setVipType(1);
                    vipOrder.setTotalAmount(Float.parseFloat(amount));
                    vipOrder.setOrderno(orderno);
                    if(iAliPayService.checkAlipay(orderno)) {
                        int count = 3;
                        Users users = usersMapper.selectByPrimaryKey(1);
                        users.setUserVip(1);
                        Date endTime1 = users.getVipEndTime();
                        LocalDateTime endTime = null;
                        LocalDateTime time1 = LocalDateTime.now();
                        if(endTime1 == null){
                            endTime = time1.plusDays(time);
                        }else{
                            LocalDateTime locaendTime1 =  LocalDateTime.ofInstant(endTime1.toInstant(), ZoneId.systemDefault());
                            if(locaendTime1.isAfter(time1)){
                                endTime = locaendTime1.plusDays(time);
                            }else{
                                endTime = time1.plusDays(time);
                            }
                        }
                        users.setVipStartTime(new Date());
                        users.setVipEndTime(Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant()));
                        int result = orderServices.insertOrder(vipOrder);
                        int  a = usersMapper.updateByPrimaryKey(users);
                        if(result > 0  && a > 0){
                            return "redirect:/allCompany";
                        }else{
                            try {
                                iAliPayService.refund(orderno,amount);
                            } catch (AlipayApiException e) {
                                e.printStackTrace();
                            }
                            throw new PayException("订单提交失败");
                        }
                    }
                    return "redirect:/pricing_table?message=订单支付未成功";
               /* }
                else{
                    return "redirect:/pricing_table?message=登陆异常";
                }*/
            }
            else{
                return "redirect:/pricing_table?message=订单异常";
            }
    }

    @RequestMapping("iscompany")
    @ResponseBody
    public APIResult iscompany(){
        APIResult apiResult = new APIResult();
      /*  LoginEntityHelper loginEntityHelper = new LoginEntityHelper();
        Users user = (Users) loginEntityHelper.getEntityByClass(User.class);
        if(user != null){*/
            Company company = iCompanyInfoServices.queryCompanyByUserId(1);
            if(company.getId() == null){
                apiResult.setResult(false);
                apiResult.setMessage("穷逼还想开企业会员，先去注册公司再说");
            }else{
                apiResult.setResult(true);
            }
       /* }else{
            apiResult.setResult(false);
            apiResult.setMessage("登录异常");
        }*/
        return apiResult;
    }


}
