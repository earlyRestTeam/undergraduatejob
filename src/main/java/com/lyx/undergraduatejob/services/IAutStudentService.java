package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.AutStudent;

import java.util.List;
import java.util.Map;

public interface IAutStudentService {

    PageInfo<AutStudent> queryAutStudentBefore(Integer indexpage,Integer userid, AutStudent autStudent, String columnName, String sort);

    Map<String, String> updateAutStudentBefore(AutStudent autStudent, Integer userid);

    Map<String, String>  addAutStudent(AutStudent autStudent, Integer userid);

    Map<String, String> deleteAutStudent(Integer id);

    boolean isAddAutStudent(Integer userid);

    PageInfo<AutStudent> queryAutStudentBack(Integer indexpage,AutStudent autStudent);

    Map<String, String> updateAutStudentBack(AutStudent autStudent);
}
