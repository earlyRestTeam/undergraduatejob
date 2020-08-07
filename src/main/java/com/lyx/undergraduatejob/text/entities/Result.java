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
        com.lyx.undergraduatejob.text.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *Description:
 *
 *
 *@author liyouxian
 *@date 2020/8/5 16:26
 */
@Setter
@Getter
public class Result {
    private String resultCode;
    private String message;
    private String data;
    private String accessory;
}