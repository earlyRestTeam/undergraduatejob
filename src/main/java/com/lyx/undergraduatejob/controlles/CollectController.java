package com.lyx.undergraduatejob.controlles;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Collect;
import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.services.ICollectServices;
import com.lyx.undergraduatejob.services.ICompanyInfoServices;
import com.lyx.undergraduatejob.services.security.LoginEntityHelper;
import com.lyx.undergraduatejob.services.security.OnlineEntity;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Autowired
    LoginEntityHelper loginEntityHelper;


    //用户收藏公司操作
    @RequestMapping("collectCompany")
    @ResponseBody
    public APIResult collectCompany(Integer companyId){
        APIResult apiResult = new APIResult();
        OnlineEntity entity = loginEntityHelper.getOnlineEntity();
        if(entity == null)
            return APIResult.genFailApiResponse500("必须要登录才能收藏");

        Integer userid = null;
        if(entity != null){
            userid = entity.getId();
        }
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

    //用户收藏职位操作
    @RequestMapping("collectJob")
    @ResponseBody
    public APIResult collectJob(Integer jobId) {
        APIResult apiResult = new APIResult();
        OnlineEntity entity = loginEntityHelper.getOnlineEntity();
        if(entity == null)
            return APIResult.genFailApiResponse500("必须要登录才能收藏");


        Integer userid = null;
        if(entity != null){
            userid = entity.getId();
        }

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


    //公司收藏简历操作
    @RequestMapping("collectResume")
    @ResponseBody
    public APIResult collectResume(Integer resumeId) {
        APIResult apiResult = new APIResult();
        OnlineEntity entity = loginEntityHelper.getOnlineEntity();
        if (entity == null)
            return APIResult.genFailApiResponse500("必须要登录才能收藏");

        Integer companyId = null;
        if (entity.getCompanyId() == null)
            return APIResult.genFailApiResponse500("穷逼没公司还想收藏简历");

        companyId = entity.getCompanyId();
        List<Integer> list = new ArrayList<>();
        list.add(resumeId);

        List<Integer> list1 = iCollectServices.queryCollectStatus(list, 3, companyId, 2);
        if (list1.size() > 0 && list1 != null) {
            int flag = 2;
            apiResult.setData(flag);
            Map<String, String> map = iCollectServices.deleteCollectResume(resumeId, companyId);
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
            if (resumeId != null && companyId != null) {
                Collect collect = new Collect();
                collect.setCollectorId(companyId);
                collect.setCollectionId(resumeId);
                collect.setCollectorType(2);
                collect.setCollectionType(3);
                collect.setCreateTime(new Date());
                Map<String, String> map = iCollectServices.addCollectResume(collect);
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

    //用户查看收藏的职位
    @RequestMapping("/visit_job")
    @ResponseBody
    public APIResult add_resume(Integer indexpage){
        System.out.println("访问到了");
        Integer userId = 1;

        PageInfo<Job> jobPageInfo = iCollectServices.queryUserCollectJob(indexpage, userId);
        System.out.println("jobPageInfo:" + jobPageInfo);
        APIResult result = APIResult.genSuccessApiResponse(jobPageInfo);

        if(jobPageInfo.getList().size() == 0)
            result = APIResult.genSuccessApiResponse("无收藏");
        System.out.println("result=====:" + result.getData());

        return result;
    }


}
