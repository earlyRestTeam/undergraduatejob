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
 * 2020/6/28 1.0 liyouxian Creation File
 */
package
        com.lyx.undergraduatejob.text;

import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *Description:
 *
 *
 *@author liyouxian
 *@date 2020/6/28 17:01
 */
@Getter
public class Student {

    private String gender;

    public static void main(String[] args) {
        String s = LocalDate.now().toString();
        System.out.println(s);

        long betweenMonth = ChronoUnit.MONTHS.between(LocalDate.now(),DateUtility.stringToLocalDate("2020-07-30", "yyyy-MM-dd"));
        System.out.println(betweenMonth);

    }
}