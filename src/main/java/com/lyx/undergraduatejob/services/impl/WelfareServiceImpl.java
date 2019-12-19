package com.lyx.undergraduatejob.services.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.WelfareMapper;
import com.lyx.undergraduatejob.pojo.Welfare;
import com.lyx.undergraduatejob.pojo.WelfareExample;
import com.lyx.undergraduatejob.services.WelfareServices;
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
 * @createTime 2019.12.18.16:02
 */
@Service
@CacheConfig(cacheNames = "welfare")
public class WelfareServiceImpl implements WelfareServices {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    WelfareMapper welfareMapper;


    /**
     * 查询 所有 福利
     * @return
     */
    @Override
    @Cacheable(key = "'AllWelfare'")
    public List<Welfare> queryAllWelfare() {
        List<Welfare> welfare = welfareMapper.selectByExample(new WelfareExample());
        return welfare;
    }
    /**
     * 分页查询所有福利 -- 后台使用
     * @param start
     * @param pageSize
     * @param keyWord
     * @return
     */
    @Override
    @Cacheable(key = "'pageWelfare'+#p0+'-'+#p1")
    public PageInfo<Welfare> queryWelfareByPage(int start, int pageSize, String keyWord) {

        PageHelper.startPage(start, pageSize);

        WelfareExample example = new WelfareExample();
        WelfareExample.Criteria criteria = example.createCriteria();
        if( !StringUtils.isEmpty(keyWord))
            criteria.andWelfareNameLike(keyWord+"%");
        List<Welfare> welfare = welfareMapper.selectByExample(example);
        PageInfo<Welfare> pageInfo = PageInfo.of(welfare);

        return pageInfo;
    }
    /**
     * 插入 福利
     * @param welfare
     * @return
     */
    @Override
    @CacheEvict(allEntries=true)
    public boolean insertWelfare(Welfare welfare) {
        WelfareExample example = new WelfareExample();
        example.createCriteria().andWelfareNameEqualTo(welfare.getWelfareName());
        List<Welfare> welfareList = welfareMapper.selectByExample(example);
        if( welfareList != null && welfareList.size() > 0 ){
            logger.error("insert Welfare error : name had! ---"+welfare);
            return false;
        }
        return welfareMapper.insert(welfare) > 0;
    }
    /**
     * 修改 福利
     * @param welfare
     * @return
     */
    @Override
    @CacheEvict(allEntries=true)
    public boolean updateWelfare(Welfare welfare) {
        return welfareMapper.updateByPrimaryKeySelective(welfare) > 0;
    }
    /**
     * 删除 福利 根据 id
     * @param id
     * @return
     */
    @Override
    @CacheEvict(allEntries=true)
    public boolean deleteWelfare(int id) {
//        Welfare welfare = new Welfare();
//        welfare.setId(id);
//        welfare.set
        return welfareMapper.deleteByPrimaryKey(id) > 0;
    }
}
