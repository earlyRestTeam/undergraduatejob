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
 * 2020/7/10 1.0 liyouxian Creation File
 */
package
        com.lyx.undergraduatejob.text;

import com.lyx.undergraduatejob.pojo.redisTest.UserBasicImage;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.List;

/**
 *Description:
 *
 *
 *@author liyouxian
 *@date 2020/7/10 16:52
 */
public class RedisTest {
    @Cacheable(value = "userBasicImage", key = "'userBasicImageList_' + T(String).valueOf(#userId)")
    public static List<UserBasicImage> getBasicImageByUserId(Long userId) {
        UserBasicImage userBasicImage = new UserBasicImage();
        userBasicImage.setId(1281488450381414400L);
        userBasicImage.setImageUrl("http/qwqwqwqwq");
        userBasicImage.setUserId(userId);
        List<UserBasicImage> list = new ArrayList<>();
        list.add(userBasicImage);
        return list;
    }


    public static void main(String[] args) {
        List<UserBasicImage> basicImageByUserId = getBasicImageByUserId(1281488450381444444L);
        System.out.println(basicImageByUserId.get(0).toString());
    }

}