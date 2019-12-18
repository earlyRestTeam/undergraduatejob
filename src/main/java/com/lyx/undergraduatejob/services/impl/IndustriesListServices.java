package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.IndustriesListMapper;
import com.lyx.undergraduatejob.pojo.IndustriesList;
import com.lyx.undergraduatejob.pojo.IndustriesListExample;
import com.lyx.undergraduatejob.pojo.Welfare;
import com.lyx.undergraduatejob.pojo.WelfareExample;
import com.lyx.undergraduatejob.services.Industries_listServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * @createTime 2019.12.18.17:23
 */
@Service
@CacheConfig(cacheNames = "IndustriesLis")
public class IndustriesListServices implements Industries_listServices {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IndustriesListMapper mapper;
    /**
     * 查询 所有 的 行业
     * @return
     */
    @Override
    @Cacheable(key="'IndustriesListAll'")
    public List<IndustriesList> queryALL() {
        return mapper.selectByExample(new IndustriesListExample());
    }
    /**
     * 分页查询 行业 -- 供 后台 使用
     * @param start
     * @param pageSize
     * @param keyWord
     * @return
     */
    @Override
    @Cacheable(key="'IndustriesListPage'+#p0+'-'+#p1")
    public PageInfo<IndustriesList> queryByPage(Integer start, Integer pageSize, String keyWord) {
        PageHelper.startPage(start, pageSize);

        IndustriesListExample example = new IndustriesListExample();
        IndustriesListExample.Criteria criteria = example.createCriteria();
        if( !StringUtils.isEmpty(keyWord))
            criteria.andIndustriesNameLike(keyWord+"%");
        List<IndustriesList> industriesLists = mapper.selectByExample(example);
        PageInfo<IndustriesList> pageInfo = PageInfo.of(industriesLists);

        return pageInfo;
    }
    /**
     * 修改 行业
     * @param industriesList
     * @return
     */
    @Override
    @CacheEvict(allEntries=true)
    public boolean updateIndustries(IndustriesList industriesList) {
        return mapper.updateByPrimaryKeySelective(industriesList) > 0;
    }
    /**
     * 增加 行业
     * @param industriesList
     * @return
     */
    @Override
    @CacheEvict(allEntries=true)
    public boolean addIndustriesList(IndustriesList industriesList) {
        IndustriesListExample example = new IndustriesListExample();
        example.createCriteria()
                .andIndustriesNameEqualTo(industriesList.getIndustriesName());
        List<IndustriesList> industriesLists = mapper.selectByExample(example);
        if( industriesLists != null && industriesLists.size() > 0 ){
            logger.error("insert Welfare error : name had! ---"+industriesList);
            return false;
        }
        return mapper.insert(industriesList) > 0;
    }
    /**
     * 删除 行业 根据 id
     * @param id
     * @return
     */
    @Override
    @CacheEvict(allEntries=true)
    public boolean deleteIndustriesList(int id) {
        return mapper.deleteByPrimaryKey(id) > 0;
    }
}
