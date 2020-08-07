/**
 * Copyright(c) Runsdata Technology Co.,Ltd.
 * All Rights Reserved.
 * <p>
 * This software is the confidential and proprietary information of
 * Runsdata
 * Technology Co.,Ltd.("Confidential Information").You shall not
 * disclose
 * such Confidential Information and shall use it only inaccordance
 * with the
 * terms of the license agreement you entered into with Runsdata
 * Technology Co.,Ltd.
 * For more information about Runsdata,welcome to
 * http://www.runsdata.com
 * <p>
 * project:undergraduatejob
 * <p>
 * Revision History:
 * Date Version Name Description
 * 2020/8/5 1.0 liyouxian Creation File
 */
package
        com.lyx.undergraduatejob.text;


import com.alibaba.fastjson.JSONObject;
import com.lyx.undergraduatejob.text.entities.Result;

/**
 *Description:
 *
 *
 *@author liyouxian
 *@date 2020/8/5 16:06
 */
public class JsonToEntity {
    public static void main(String[] args) {

        String json = "{\"resultCode\":0,\"message\":null,\"data\":{\"result\":\"true\",\"list\":[{\"ywjblsh\":\"1289438162430472192\",\"aac003\":\"王雅婕\",\"aae013\":\"三级经办完成，失败办结\",\"aae055\":1.596264999E12,\"aaa121\":\"A20001\",\"aae160\":\"户口本没有首页重新提交\",\"aae020\":\"人员信息登记\",\"aae099\":\"7\",\"aac002\":\"140110199711212588\"},{\"ywjblsh\":\"1290437080073166848\",\"aac003\":\"王雅婕\",\"aae013\":\"三级经办完成，成功办结\",\"aae055\":1.59649915E12,\"aaa121\":\"A20001\",\"aae020\":\"人员信息登记\",\"aae099\":\"6\",\"aac002\":\"140110199711212588\"}]},\"accessory\":null}";
        System.out.println(json);
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(jsonObject);
        String list = jsonObject.getString("data");
        System.out.println(list);
    }
}