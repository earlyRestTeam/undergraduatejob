package com.lyx.undergraduatejob.controlles;

import com.lyx.undergraduatejob.pojo.Collect;
import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.services.ICollectServices;
import com.lyx.undergraduatejob.services.ICompanyInfoServices;
import com.lyx.undergraduatejob.services.security.LoginEntityHelper;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.StaticPool;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * @author :Yang Jiahong
 * @date :2019/12/27 10:05
 */

@Controller
@RequestMapping("/user")
public class CollectController {

    @Autowired
    ICompanyInfoServices iCompanyInfoServices;

    @Autowired
    ICollectServices iCollectServices;


    //用户收藏公司
    @RequestMapping("collectCompany")
    @ResponseBody
    public APIResult collectCompany(Integer companyId){
        APIResult apiResult = new APIResult();
        LoginEntityHelper loginEntityHelper = new LoginEntityHelper();
        Users user = (Users) loginEntityHelper.getEntityByClass(Users.class);
        Integer userid = null;
        if(user != null){
            userid = user.getId();
        }
        userid = 1;
        if(iCollectServices.queryBycompIdAndUserId(companyId,userid)){
            int flag = 2;
            apiResult.setData(flag);
            Map<String, String> map = iCollectServices.deleteUserCollectConpany(companyId, userid);
            if(map.get(StaticPool.SUCCESS) != null){
                apiResult.setResult(true);
                apiResult.setMessage("取消成功");
            }else{
                apiResult.setResult(false);
                apiResult.setMessage("取消失败");
            }
        }else{
            int flag = 1;
            apiResult.setData(flag);
            if(companyId != null && userid != null){
                Collect collect = new Collect();
                collect.setCollectorId(userid);
                collect.setCollectionId(companyId);
                collect.setCollectorType(1);
                collect.setCollectionType(1);
                collect.setCreateTime(new Date());
                Map<String, String> map = iCollectServices.addcollectCompany(collect);
                if(map.get(StaticPool.SUCCESS) != null){
                    apiResult.setResult(true);
                    apiResult.setMessage("收藏成功");
                }else{
                    apiResult.setResult(false);
                    apiResult.setMessage("收藏失败");
                }
            }else{
                apiResult.setResult(false);
                apiResult.setMessage("收藏失败");
            }
        }
        return apiResult;
    }


    //用户收藏职位
    @RequestMapping("collectJob")
    @ResponseBody
    public APIResult collectJob(Integer jobId) {
        APIResult apiResult = new APIResult();
        LoginEntityHelper loginEntityHelper = new LoginEntityHelper();
        Users user = (Users) loginEntityHelper.getEntityByClass(Users.class);
        Integer userid = null;
        if (user != null) {
            userid = user.getId();
        }
        userid = 1;

        if (iCollectServices.queryjobIdByjobIdAndUserId(jobId, userid)) {
            int flag = 2;
            apiResult.setData(flag);
            Map<String, String> map = iCollectServices.deleteUserCollectJob(jobId, userid);
            if (map.get(StaticPool.SUCCESS) != null) {
                apiResult.setResult(true);
                apiResult.setMessage("取消成功");
            } else {
                apiResult.setResult(false);
                apiResult.setMessage("取消失败");
            }
        } else {
            int flag = 1;
            apiResult.setData(flag);
            if (jobId != null && userid != null) {
                Collect collect = new Collect();
                collect.setCollectorId(userid);
                collect.setCollectionId(jobId);
                collect.setCollectorType(1);
                collect.setCollectionType(2);
                collect.setCreateTime(new Date());
                Map<String, String> map = iCollectServices.addcollectJob(collect);
                if (map.get(StaticPool.SUCCESS) != null) {
                    apiResult.setResult(true);
                    apiResult.setMessage("收藏成功");
                } else {
                    apiResult.setResult(false);
                    apiResult.setMessage("收藏失败");
                }
            } else {
                apiResult.setResult(false);
                apiResult.setMessage("收藏失败");
            }
        }
        return apiResult;
    }

}
