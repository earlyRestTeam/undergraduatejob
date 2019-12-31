package com.lyx.undergraduatejob.services.impl;

import com.lyx.undergraduatejob.mapper.RelationWelfareMapper;
import com.lyx.undergraduatejob.mapper.WelfareMapper;
import com.lyx.undergraduatejob.pojo.RelationWelfare;
import com.lyx.undergraduatejob.pojo.RelationWelfareExample;
import com.lyx.undergraduatejob.pojo.Welfare;
import com.lyx.undergraduatejob.pojo.WelfareExample;
import com.lyx.undergraduatejob.services.RelationWealfareServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RelationWelafareServiceImpl implements RelationWealfareServices {

    @Autowired
    RelationWelfareMapper relationWelfareMapper;

    @Autowired
    WelfareMapper welfareMapper;

    /**
     * 通过拥有者类型，拥有者id查询图片
     * @param owner_id
     * @param owner_type
     * @return
     */
    @Override
    public List<Welfare> queryWelfarebyOwnerIdAndOwnerType(Integer owner_id, Integer owner_type) {
        RelationWelfareExample example = new RelationWelfareExample();
        example.or().andOwnerIdEqualTo(owner_id).andOwnerTypeEqualTo(owner_type);
        List<RelationWelfare> relationWelfares = relationWelfareMapper.selectByExample(example);
        ArrayList<Integer> welfaresidlist = new ArrayList<>();
        if (!relationWelfares.isEmpty()){
            for (int i = 0; i <relationWelfares.size() ; i++) {
                welfaresidlist.add(relationWelfares.get(i).getWelfareId());
            }
            WelfareExample example1 = new WelfareExample();
            example1.or().andIdIn(welfaresidlist);
            return welfareMapper.selectByExample(example1);
        }else {
            return new ArrayList<>();
        }
    }

    /**
     * 为某个公司或者职位增加福利
     *
     * @param welfare_id
     * @param owner_id
     * @param owner_type
     * @return
     */
    @Override
    public Map<String, String> addWelfareRelation(Integer welfare_id, Integer owner_id, Integer owner_type) {




        return null;
    }

    /**
     * 通过福利id删除福利
     *
     * @param welfare_id
     * @return
     */
    @Override
    public Map<String, String> deleteWelfareRelationbywelfare_id(Integer welfare_id) {

        return null;
    }

    /**
     * 修改福利
     *
     * @param relationWelfare
     * @return
     */
    @Override
    public Map<String, String> updateWelfareRelation(RelationWelfare relationWelfare) {
        return null;
    }
}
