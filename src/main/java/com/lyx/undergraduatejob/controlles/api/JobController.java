package com.lyx.undergraduatejob.controlles.api;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发布的 工作 控制器
 *
 * @createTime 2019.12.20.12:01
 */
@RestController
public class JobController {
    @Autowired
    IJobServices jobServices;

    @PostMapping("/getJobs")
    public APIResult selectJob(@RequestParam(value="start",defaultValue = "0") Integer start,
                               @RequestParam(value="pageSize",defaultValue = "12") Integer pageSize,
                               JobSearchEntity entity){

        MyPage myPage = jobServices.selectJobByJobSearchEntityWithCompany(start, pageSize, entity);

        return APIResult.genSuccessApiResponse(myPage);
    }
}
