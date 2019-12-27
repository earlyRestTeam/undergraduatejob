package com.lyx.undergraduatejob.controlles.api;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;
import com.lyx.undergraduatejob.search.entity.RentValueBlock;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.services.security.LoginEntityHelper;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public APIResult selectJob(@RequestParam(value="start",defaultValue = "1") Integer start,
                               @RequestParam(value="pageSize",defaultValue = "12") Integer pageSize,
                               @RequestBody JobSearchEntity entity){
        if(start <= 0)
            start = 1;
        MyPage myPage = jobServices.selectJobByJobSearchEntityWithCompany(start, pageSize, entity);

        return APIResult.genSuccessApiResponse(myPage);
    }
    @GetMapping("getSalaryArea")
    public APIResult getSalaryArea(){
        Map<String, RentValueBlock> salaryArea = RentValueBlock.salaryArea;
        return APIResult.genSuccessApiResponse(salaryArea);
    }
}
