package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.AdvertisingMapper;
import com.lyx.undergraduatejob.pojo.Advertising;
import com.lyx.undergraduatejob.pojo.AdvertisingExample;
import com.lyx.undergraduatejob.services.IAdvertisingService;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * @ClassName AdvertisingServicesImpl
 * @Date 2019/12/18
 * @Version V1.0
 **/
@Service
public class AdvertisingServicesImpl implements IAdvertisingService {

    Logger logger = LoggerFactory.getLogger(IAdvertisingService.class);

    @Autowired
    AdvertisingMapper advertisingMapper;

    public List<Advertising> Advertising(Advertising advertising, String keyword){
        if (keyword == null){
            keyword = "";
        }
        AdvertisingExample example = new AdvertisingExample();
        AdvertisingExample.Criteria criteria = example.createCriteria();
        if (advertising.getId() != null){
            criteria.andIdEqualTo(advertising.getId());
        }
        if (advertising.getStatus() != null){
            criteria.andStatusEqualTo(advertising.getStatus());
        }
        criteria.andTitleLike(keyword+"%");
        List<Advertising> advertisings = advertisingMapper.selectByExample(example);
        return advertisings;
    }


    AdvertisingExample example = new AdvertisingExample();

    @Override
    public PageInfo queryAdvertiser(Integer indexpage,Advertising advertising,@RequestParam(required = false) String keyword) {
        if (indexpage == null){
            indexpage = 1;
        }
        PageHelper.startPage(indexpage,10);
        List<Advertising> advertising1 = Advertising(advertising, keyword);
        PageInfo info = new PageInfo(advertising1,5);
        return info;
    }

    @Override
    public List<Advertising> queryAdvertising(Advertising advertising, String keyword) {
        List<Advertising> advertising1 = Advertising(advertising, keyword);
        return advertising1;
    }

    @Override
    public Map<String,Object> addAdvertising(Advertising advertising) {
        Map<String,Object> res = new HashMap<>();
        advertising.setStatus(Byte.valueOf("1"));
        advertising.setCreateTime(new Date());
        int insert = advertisingMapper.insert(advertising);
        if (insert > 0){
            res.put(StaticPool.SUCCESS,"添加成功");
        }else {
            logger.warn("insert error "+advertising);
            res.put(StaticPool.ERROR,"添加失败");
        }
        return res;
    }

    @Override
    public Advertising updateAdvertising(Advertising advertising) {
        AdvertisingExample example = new AdvertisingExample();
        if (advertising.getId() != null){
            example.createCriteria().andIdEqualTo(advertising.getId());
        }
        Advertising adv = advertisingMapper.selectByPrimaryKey(advertising.getId());
        if (adv != null){
            return adv;
        }else{
            logger.warn("服务繁忙 "+advertising);
            return null;
        }
    }

    public Map<String,Object> update(Advertising advertising){
        Map<String,Object> res = new HashMap<>();
        advertising.setStatus(Byte.valueOf("1"));
        advertising.setCreateTime(new Date());
        int i = advertisingMapper.updateByPrimaryKey(advertising);
        if (i > 0){
            res.put(StaticPool.SUCCESS,"成功");
        }else{
            logger.warn("服务繁忙 "+advertising);
            res.put(StaticPool.ERROR,"失败");
        }
        return res;
    }

    @Override
    public Map<String,Object> deleteAdvertising(Advertising advertising) {
        Map<String,Object> res = new HashMap<>();
        advertising.setStatus(Byte.valueOf("0"));
        advertising.setEndTime(new Date());
        int i = advertisingMapper.updateByPrimaryKeySelective(advertising);
        if (i > 0){
            res.put(StaticPool.SUCCESS,"成功");
        }else {
            logger.warn("服务繁忙 "+advertising);
            res.put(StaticPool.ERROR,"失败");
        }
        return res;
    }

    @Override
    public boolean deleteAdvertising(Integer[] ids) {
        for (int i=0; i<ids.length; i++){
            Advertising advertising = new Advertising();
            advertising.setId(ids[i]);
            Map<String, Object> res = deleteAdvertising(advertising);
        }
        return true;
    }
}
