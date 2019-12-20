package com.lyx.undergraduatejob.controlles.api;

import com.lyx.undergraduatejob.pojo.IndustriesList;
import com.lyx.undergraduatejob.services.Industries_listServices;
import com.lyx.undergraduatejob.utils.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 行业 控制器
 *
 * @createTime 2019.12.20.12:00
 */
@RestController
public class IndustriesController {
    @Autowired
    Industries_listServices industriesListServices;

    @PostMapping("/allIndustriesWithOutJobList")
    public APIResult getAllIndustries(){
        List<IndustriesList> industriesLists = industriesListServices.queryALLWithOutJobList();
        return APIResult.genSuccessApiResponse(industriesLists);
    }
    @PostMapping("/allIndustriesWithJobList")
    public APIResult getAllIndustriesWithJobList(){
        List<Map<String, Object>> list = industriesListServices.queryALLWithJobList();
        return APIResult.genSuccessApiResponse(list);
    }
    
}
