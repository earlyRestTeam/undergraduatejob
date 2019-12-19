package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.AutStudentMapper;
import com.lyx.undergraduatejob.pojo.AutStudent;
import com.lyx.undergraduatejob.pojo.AutStudentExample;
import com.lyx.undergraduatejob.services.IAutStudentService;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.hibernate.validator.constraints.EAN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2019/12/18 10:56
 * @Version V1.0
 */
@Service
public class AutStudnetServiceImpl implements IAutStudentService {
    Logger logger = LoggerFactory.getLogger(IJobServices.class);
    @Autowired
    AutStudentMapper autStudentMapper;

    /**
     * 除了时间以外的条件都调这个查询,
     * @param indexpage
     * @param autStudent
     * @param columnName 数据库的列名
     * @param sort ASC/DESC
     * @return
     */
    @Override
    public PageInfo<AutStudent> queryAutStudent(Integer indexpage, AutStudent autStudent, String columnName, String sort) {
        indexpage = indexpage == null ? 1 : indexpage;

        AutStudentExample example = new AutStudentExample();
        AutStudentExample.Criteria criteria = example.createCriteria();
        if (autStudent.getId() != null){
            criteria.andIdEqualTo(autStudent.getId());
        }
        if (autStudent.getUserId() != null){
            criteria.andUserIdEqualTo(autStudent.getUserId());
        }
        if (autStudent.getProposerName() != null){
            criteria.andProposerNameLike(autStudent.getProposerName()+"%");
        }
        if (autStudent.getProposerPhone() != null){
            criteria.andProposerPhoneLike(autStudent.getProposerPhone()+"%");
        }
        if (autStudent.getIdCard() != null){
            criteria.andIdCardLike(autStudent.getIdCard()+"%");
        }
        if (autStudent.getStudentNum() != null){
            criteria.andStudentNumLike(autStudent.getStudentNum()+"%");
        }
        if (autStudent.getSchool() != null){
            criteria.andSchoolLike(autStudent.getSchool()+"%");
        }
        if (autStudent.getSpecialty() != null){
            criteria.andSpecialtyLike(autStudent.getSpecialty()+"%");
        }
        if (autStudent.getEducation() != null){
            criteria.andEducationLike(autStudent.getEducation()+"%");
        }
        if (autStudent.getStatus() != null){
            criteria.andStatusEqualTo(autStudent.getStatus());
        }
        if (autStudent.getStudentStatus() != null){
            criteria.andStudentStatusEqualTo(autStudent.getStudentStatus());
        }
        if (autStudent.getRelatedPicture() != null){
            criteria.andRelatedPictureEqualTo(autStudent.getRelatedPicture());
        }
        if (columnName != null){
            if (sort != null) {
                example.setOrderByClause(columnName + " " + sort);
            } else {
                example.setOrderByClause(columnName + " ASC");
            }
        }

        PageHelper.startPage(indexpage,10);
        List<AutStudent> autStudents = autStudentMapper.selectByExample(example);
        PageInfo<AutStudent> info = new PageInfo<>(autStudents,10);

        return info;
    }

    /**
     * 用户有状态为已通过/未审核的认证申请则不能添加认证
     * @param userid
     * @return
     */
    @Override
    public boolean isAddAutStudent(Integer userid) {
        AutStudentExample example = new AutStudentExample();
        List<Integer> status = new ArrayList<>();
        status.set(0,1);
        status.set(1,2);
        example.or().andUserIdEqualTo(userid).andStatusIn(status);

        List<AutStudent> autStudents = autStudentMapper.selectByExample(example);
        if (autStudents != null && autStudents.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 所有修改都调这个方法，不要修改的属性就不要设值
     * @param autStudent
     * @return
     */
    @Override
    public Map<String, String> updateAutStudent(AutStudent autStudent) {
        Map<String,String> result = new HashMap<>();
        if (autStudent.getId() == null){
            result.put(StaticPool.ERROR,"修改失败");
        } else {
            int i = autStudentMapper.updateByPrimaryKeySelective(autStudent);
            if (i > 0){
                result.put(StaticPool.SUCCESS,"修改成功");
            } else {
                logger.warn("update error "+autStudent);
                result.put(StaticPool.ERROR,"系统繁忙");
            }
        }

        return result;
    }

    @Override
    public Map<String, String> addAutStudent(AutStudent autStudent) {
        Map<String,String> result = new HashMap<>();
        if (! isAddAutStudent(autStudent.getId())) {
            result.put(StaticPool.ERROR,"你的认证已通过或请耐心等待认证");
        } else {
            int i = autStudentMapper.insertSelective(autStudent);
            if (i > 0) {
                result.put(StaticPool.SUCCESS,"添加成功");
            } else {
                logger.warn("insert error "+autStudent);
                result.put(StaticPool.ERROR,"系统繁忙");
            }
        }
        return result;
    }

    @Override
    public Map<String, String> deleteAutStudent(Integer id) {
        Map<String,String> result = new HashMap<>();
        int i = autStudentMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            result.put(StaticPool.SUCCESS,"删除成功");
        } else {
            logger.warn("delete error "+ id);
            result.put(StaticPool.ERROR,"系统繁忙");
        }
        return result;
    }
}
