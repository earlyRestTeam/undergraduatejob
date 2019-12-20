package com.lyx.undergraduatejob.controlles.api;

import com.lyx.undergraduatejob.pojo.JobList;
import com.lyx.undergraduatejob.services.IJobListServices;
import com.lyx.undergraduatejob.utils.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 职业表 控制器
 *
 * @createTime 2019.12.20.11:59
 */
@RestController
public class JobListController {
    @Autowired
    IJobListServices jobListServices;

    @PostMapping("allJobList")
    public APIResult getAllJobList(@RequestParam(value="industriesId",defaultValue = "0") Integer id){
        List<JobList> jobLists;
        if( id == 0)
            jobLists = jobListServices.queryALL();
        else
            jobLists = jobListServices.queryALLByIndustriesId(id);
        return APIResult.genSuccessApiResponse(jobLists);
    }
}
