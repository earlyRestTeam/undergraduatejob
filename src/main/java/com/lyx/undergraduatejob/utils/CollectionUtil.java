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
 * 2020/7/21 1.0 liyouxian Creation File
 */
package
        com.lyx.undergraduatejob.utils;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *Description:
 *
 *
 *@author liyouxian
 *@date 2020/7/21 11:15
 */
public class CollectionUtil {

    private CollectionUtil() {
    }

    /**
     * 找出两个集合中不同的元素
     *
     * @param collmax
     * @param collmin
     * @return
     */
    public static Collection getDifferent(Collection collmax, Collection collmin) {
        //使用LinkedList防止差异过大时,元素拷贝
        Collection csReturn = new LinkedList();
        Collection max = collmax;
        Collection min = collmin;
        //先比较大小,这样会减少后续map的if判断次数
        if (collmax.size() < collmin.size()) {
            max = collmin;
            min = collmax;
        }
        //直接指定大小,防止再散列
        Map<Object, Integer> map = new HashMap<Object, Integer>(max.size());
        for (Object object : max) {
            map.put(object, 1);
        }
        for (Object object : min) {
            if (map.get(object) == null) {
                csReturn.add(object);
            } else {
                map.put(object, 2);
            }
        }
        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                csReturn.add(entry.getKey());
            }
        }
        return csReturn;
    }

    /**
     * 找出两个集合中相同的元素
     *
     * @param collmax
     * @param collmin
     * @return
     */
    public static Collection getSame(Collection collmax, Collection collmin) {
        //使用LinkedList防止差异过大时,元素拷贝
        Collection csReturn = new LinkedList();
        Collection max = collmax;
        Collection min = collmin;
        //先比较大小,这样会减少后续map的if判断次数
        if (collmax.size() < collmin.size()) {
            max = collmin;
            min = collmax;
        }
        //直接指定大小,防止再散列
        Map<Object, Integer> map = new HashMap<Object, Integer>(max.size());
        for (Object object : max) {
            map.put(object, 1);
        }
        for (Object object : min) {
            if (map.get(object) != null) {
                csReturn.add(object);
            }
        }
        return csReturn;
    }

    /**
     * 获取两个集合的不同元素,去除重复
     *
     * @param collmax
     * @param collmin
     * @return
     */
    public static Collection getDifferentNoDuplicate(Collection collmax, Collection collmin) {
        return new HashSet(getDifferent(collmax, collmin));
    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
//        list1.add("5");


        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");

        for (String s :list2) {
            boolean contains = list1.contains(s);
            if(!contains){
                System.out.println("s" + contains);
            }

        }
    }


//    @Transactional(rollbackFor = Exception.class)
//    public void delete(Long... id) {
//        for (Long key : id) {
//            UserBasicImage userBasicImage = load(key);
//            if (userBasicImage != null) {
//                userBasicImage.setIsDelete("1");
//                UserInfo userInfo = extendUserInfoService.getByUserId(userBasicImage.getUserId());
//                if (userInfo != null) {
//                    userInfo.setBasicImageNumber(userInfo.getBasicImageNumber() - 1);
//                    extendUserInfoService.update(userInfo);
//                }
//                proxySelf.save(userBasicImage);
//                //部分场景无法进入aop，需要以下侵入式操作
//                if (userBasicImage.getUserId() != null) {
//                    deleteCache(userBasicImage.getUserId());
//                }
//                // 删除成功后调用远程接口并删除远程服务器上的图片
//                String imageUrl = userBasicImage.getImageUrl();
//                HttpFileUtil.deleteImage(removeUrl, imageUrl.substring(imageUrl.lastIndexOf("/") + 1));
//            }
//        }
//    }
//
//    //部分场景无法进入aop，需要以下侵入式操作
//    private void deleteCache(Long id) {
//        try {
//            redisTemplate.delete("strategy::strategy_userId_" + id);
//            redisTemplate.delete("userBasicImage::userBasicImageList_" + id);
//        } catch (Exception ex) {
//            logger.debug("failed to delete cache:" + ex.getMessage());
//        }
//    }
}