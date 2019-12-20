package com.lyx.undergraduatejob.services;

import com.lyx.undergraduatejob.pojo.RelationWelfare;
import com.lyx.undergraduatejob.pojo.Welfare;

import java.util.List;
import java.util.Map;

public interface RelationWealfareServices {
    /**
     * 通过拥有者类型，拥有者id查询图片
     * @param owner_id
     * @param owner_type
     * @return
     */
    List<Welfare> queryWelfarebyOwnerIdAndOwnerType(Integer owner_id,Integer owner_type);

    /**
     * 为某个公司或者职位增加福利
     * @param welfare_id
     * @param owner_id
     * @param owner_type
     * @return
     */
    Map<String,String> addWelfareRelation(Integer welfare_id,Integer owner_id,Integer owner_type);

    /**
     * 通过福利id删除福利
     * @param welfare_id
     * @return
     */
    Map<String,String> deleteWelfareRelationbywelfare_id(Integer welfare_id);

    /**
     * 修改福利
     * @param relationWelfare
     * @return
     */
    Map<String,String> updateWelfareRelation(RelationWelfare relationWelfare);



}
