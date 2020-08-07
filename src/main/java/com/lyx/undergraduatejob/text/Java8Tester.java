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

import java.util.Optional;

/**
 *Description:
 *
 *
 *@author liyouxian
 *@date 2020/6/28 16:26
 */
public class Java8Tester {
    public static void main(String args[]){

//        Java8Tester java8Tester = new Java8Tester();
//        Integer value1 = null;
//        Integer value2 = new Integer(10);
//
//        // Optional.ofNullable - 允许传递为 null 参数
//        Optional<Integer> a = Optional.ofNullable(value1);
//
//        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
//        Optional<Integer> b = Optional.of(value2);
//        System.out.println(java8Tester.sum(a,b));
        String gender = getGender(null);
        System.out.println(gender);

    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b){

        // Optional.isPresent - 判断值是否存在

        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }


    public static String getGender(Student student)
    {
        System.out.println();
        return Optional.ofNullable(student).map(u -> u.getGender()).orElse("Unkown");

    }
}