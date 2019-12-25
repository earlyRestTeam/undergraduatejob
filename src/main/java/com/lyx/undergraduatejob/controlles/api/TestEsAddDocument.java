package com.lyx.undergraduatejob.controlles.api;


import cn.hutool.json.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.utils.APIResult;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @createTime 2019.12.24.1:15
 */
@RestController
public class TestEsAddDocument {
    @Autowired
    IJobServices jobServices;
    @Qualifier("restClient")
    @Autowired
    private RestClient client;
    
    @RequestMapping("add")
    public APIResult add() throws IOException {
        StringBuilder sb = new StringBuilder(10240);
        PageInfo<Job> jobPageInfo = jobServices.selectJobByJobSearchEntityWithOutCompany(1, 100, new JobSearchEntity());
        for (Job j : jobPageInfo.getList()){
            // 构造HTTP请求，第一个参数是请求方法，第二个参数是服务器的端点，host默认是http://localhost:9200，
            // endpoint直接指定为index/type的形式
            Request request = new Request("PUT", new StringBuilder("/fe_city/job/").
                    append(j.getId()).toString());
            // 设置其他一些参数比如美化json
            request.addParameter("pretty", "true");


            JSONObject jsonObject = new JSONObject(j);
            System.out.println(jsonObject.toString());
            // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
            request.setEntity(new NStringEntity(jsonObject.toString()));

            // 发送HTTP请求
            Response response = client.performRequest(request);

            // 获取响应体, id: AWXvzZYWXWr3RnGSLyhH
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("responseBody = " + responseBody);
            sb.append(responseBody);
            //return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }
        return APIResult.genSuccessApiResponse(sb.toString());
    }
}
