package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.AutCompanyMapper;
import com.lyx.undergraduatejob.pojo.AutCompany;
import com.lyx.undergraduatejob.pojo.AutCompanyExample;

import com.lyx.undergraduatejob.services.IAutCompanyService;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2019/12/18 15:44
 * @Version V1.0
 */
@Service
public class AutCompanyServiceImpl implements IAutCompanyService {
    Logger logger = LoggerFactory.getLogger(IJobServices.class);

    @Autowired
    AutCompanyMapper autCompanyMapper;

    public PageInfo<AutCompany> queryAutCompanyBefore(Integer indexpage,Integer companyid, AutCompany autCompany ){
        return queryAutCompanyBefore(indexpage,companyid,autCompany,"create_time","ASC");
    }

    /**
     * 除了时间以外的条件都调这个查询,前台
     * @param indexpage
     * @param companyid
     * @param autCompany
     * @param columnName 数据库的列名
     * @param sort ASC/DESC
     * @return
     */
    @Override
    public PageInfo<AutCompany> queryAutCompanyBefore(Integer indexpage,Integer companyid, AutCompany autCompany, String columnName, String sort) {
        if (companyid != autCompany.getCompanyId()){
            logger.warn("非法访问,公司id："+companyid);
            return null;
        }

        indexpage = indexpage == null ? 1 : indexpage;

        AutCompanyExample example = new AutCompanyExample();
        AutCompanyExample.Criteria criteria = example.createCriteria();
        if (autCompany.getId() != null){
            criteria.andIdEqualTo(autCompany.getId());
        }
        if (autCompany.getCompanyId() != null){
            criteria.andCompanyIdEqualTo(autCompany.getCompanyId());
        }
        if (autCompany.getProposerName() != null){
            criteria.andProposerNameLike(autCompany.getProposerName()+"%");
        }
        if (autCompany.getProposerPhone() != null){
            criteria.andProposerPhoneLike(autCompany.getProposerPhone()+"%");
        }
        if (autCompany.getIdCard() != null){
            criteria.andIdCardLike(autCompany.getIdCard()+"%");
        }
        if (autCompany.getLicenseCard() != null){
            criteria.andLicenseCardLike(autCompany.getLicenseCard()+"%");
        }
        if (autCompany.getLicensePicture() != null){
            criteria.andLicensePictureEqualTo(autCompany.getLicensePicture());
        }
        if (autCompany.getCompanyStatus() != null){
            criteria.andCompanyStatusEqualTo(autCompany.getCompanyStatus());
        }
        if (autCompany.getStatus() != null){
            criteria.andStatusEqualTo(autCompany.getStatus());
        }
        if (columnName != null){
            if (sort != null) {
                example.setOrderByClause(columnName + " " + sort);
            } else {
                example.setOrderByClause(columnName + " ASC");
            }
        }
        PageHelper.startPage(indexpage,10);
        List<AutCompany> autCompanies = autCompanyMapper.selectByExample(example);
        PageInfo<AutCompany> info = new PageInfo<>(autCompanies,10);

        return info;
    }

    /**
     * 后台查询
     * @param autCompany
     * @return
     */
    @Override
    public List<AutCompany> queryAutCompanyBack(AutCompany autCompany) {
        AutCompanyExample example = new AutCompanyExample();
        AutCompanyExample.Criteria criteria = example.createCriteria();
        if (autCompany.getId() != null){
            criteria.andIdEqualTo(autCompany.getId());
        }
        if (autCompany.getCompanyId() != null){
            criteria.andCompanyIdEqualTo(autCompany.getCompanyId());
        }
        if (autCompany.getProposerName() != null){
            criteria.andProposerNameLike(autCompany.getProposerName()+"%");
        }
        if (autCompany.getProposerPhone() != null){
            criteria.andProposerPhoneLike(autCompany.getProposerPhone()+"%");
        }
        if (autCompany.getIdCard() != null){
            criteria.andIdCardLike(autCompany.getIdCard()+"%");
        }
        if (autCompany.getLicenseCard() != null){
            criteria.andLicenseCardLike(autCompany.getLicenseCard()+"%");
        }
        if (autCompany.getLicensePicture() != null){
            criteria.andLicensePictureEqualTo(autCompany.getLicensePicture());
        }
        if (autCompany.getCompanyStatus() != null){
            criteria.andCompanyStatusEqualTo(autCompany.getCompanyStatus());
        }
        if (autCompany.getStatus() != null){
            criteria.andStatusEqualTo(autCompany.getStatus());
        }

        List<AutCompany> autCompanies = autCompanyMapper.selectByExample(example);

        return autCompanies;
    }

    @Override
    public boolean isAddAutCompany(Integer cmpanyid) {
        AutCompanyExample example = new AutCompanyExample();
        List<Integer> status = new ArrayList<>();
        status.add(1);
        status.add(2);
        example.or().andCompanyIdEqualTo(cmpanyid).andStatusIn(status);

        List<AutCompany> autCompanies = autCompanyMapper.selectByExample(example);
        if (autCompanies.isEmpty() && autCompanies.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 后台修改
     * @param autCompany
     * @return
     */
    @Override
    public Map<String, String> updateAutCompany(AutCompany autCompany) {

        Map<String,String> result = new HashMap<>();
        if (autCompany.getId() == null){
            result.put(StaticPool.ERROR,"修改失败");
        } else {
            int i = autCompanyMapper.updateByPrimaryKeySelective(autCompany);
            if (i > 0){
                result.put(StaticPool.SUCCESS,"修改成功");
            } else {
                logger.warn("update error "+autCompany);
                result.put(StaticPool.ERROR,"系统繁忙");
            }
        }

        return result;
    }

//    /**
//     * 前台修改
//     * @param autCompany
//     * @return
//     */
//    @Override
//    public Map<String, String> updateAutCompany(AutCompany autCompany, Integer companyid) {
//        Map<String,String> result = new HashMap<>();
//        if (autCompany.getId() == null){
//            result.put(StaticPool.ERROR,"修改失败");
//        } else {
//            int i = autCompanyMapper.updateByPrimaryKeySelective(autCompany);
//            if (i > 0){
//                result.put(StaticPool.SUCCESS,"修改成功");
//            } else {
//                logger.warn("update error "+autCompany);
//                result.put(StaticPool.ERROR,"系统繁忙");
//            }
//        }
//
//        return result;
//    }

    @Override
    public Map<String, String> addAutCompany(AutCompany autCompany, Integer companyid) {
        Map<String,String> result = new HashMap<>();
        if (companyid != autCompany.getCompanyId()){
            result.put(StaticPool.ERROR,"非法访问");
            logger.warn("非法访问,公司id："+companyid);
            return result;
        }
        if (! isAddAutCompany(autCompany.getId())) {
            result.put(StaticPool.ERROR,"你的认证已通过或请耐心等待认证");
        } else {
            int i = autCompanyMapper.insertSelective(autCompany);
            if (i > 0) {
                result.put(StaticPool.SUCCESS,"添加成功");
            } else {
                logger.warn("insert error "+autCompany);
                result.put(StaticPool.ERROR,"系统繁忙");
            }
        }
        return result;
    }

    @Override
    public Map<String, String> deleteAutCompany(Integer id) {
        Map<String,String> result = new HashMap<>();
        int i = autCompanyMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            result.put(StaticPool.SUCCESS,"删除成功");
        } else {
            logger.warn("delete error "+ id);
            result.put(StaticPool.ERROR,"系统繁忙");
        }
        return result;
    }

}
