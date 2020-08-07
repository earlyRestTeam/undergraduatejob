/**
 * Copyright(c) Runsdata Technology Co.,Ltd.
 * All Rights Reserved.
 * <p>
 * This software is the confidential and proprietary information of Runsdata
 * Technology Co.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with Runsdata Technology Co.,Ltd.
 * For more information about Runsdata, welcome to http://www.runsdata.com
 * <p>
 * project: springboot
 * <p>
 * Revision History:
 * Date Version Name Description
 * 4/14/2020 1.0 zhuxiaowei Creation File
 */
package com.lyx.undergraduatejob.text;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Description:
 *
 *
 * @author zhuxiaowei
 * @date 4/14/2020 3:20 PM
 */
public class ManualSync {

    public static void main(String[] args) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            // 获取流
            InputStream inputStream = ManualSync.class.getResourceAsStream("/idNumber.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("User-Agent", "ShanXi-Society/1.6.0(Android; 23 6.0;Xiaomi Redmi Note 4X; Retrofit2/2.3.0)");
            bufferedReader.lines().forEach(line -> {
                ResponseEntity<String> response = restTemplate.exchange(
                        "https://api-app-sx.ssiid.com/foundation/v1.1/public/api/sx/syncUserAuth?idNumber=" + line.trim(),
                        HttpMethod.GET,
                        new HttpEntity<String>(requestHeaders),
                        String.class);
                System.out.println(response.getBody() + "-" + line);
            });
            System.out.println("done");
        }catch (Exception e){

        }

    }


}