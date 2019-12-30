package com.lyx.undergraduatejob.controlles.api;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Resume;
import com.lyx.undergraduatejob.search.entity.RentValueBlock;
import com.lyx.undergraduatejob.search.entity.ResumeSearchEntity;
import com.lyx.undergraduatejob.services.IResumeServices;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @createTime 2019.12.24.11:38
 */
@RestController
public class ResumeApiController {
    @Autowired
    IResumeServices resumeServices;

    @GetMapping("getAgeArea")
    public APIResult getAgeArea(){
        return APIResult.genSuccessApiResponse(RentValueBlock.ageArea);
    }
    @PostMapping("getResumes")
    public APIResult getResumes(@RequestParam(value="start",defaultValue = "1") Integer start,
                                @RequestParam(value="pageSize",defaultValue = "12") Integer pageSize,
                                @RequestBody ResumeSearchEntity entity){
        if(start <= 0)
            start = 1;
        MyPage resumePageInfo = resumeServices.queryResumeByKey(start, pageSize, entity);
        return APIResult.genSuccessApiResponse(resumePageInfo);
    }



}
