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
        com.lyx.undergraduatejob.pojo.redisTest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *Description:
 *
 *
 *@author liyouxian
 *@date 2020/7/10 16:55
 */
@Getter
@Setter
@ToString
public class UserBasicImage {
    private Long id;
    private Long userId;
    private String imageUrl;
}