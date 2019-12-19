package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.AutStudent;

import java.util.Map;

public interface IAutStudentService {

    PageInfo<AutStudent> queryAutStudent(Integer indexpage, AutStudent autStudent, String columnName, String sort);

    Map<String, String> updateAutStudent(AutStudent autStudent);

    Map<String, String>  addAutStudent(AutStudent autStudent);

    Map<String, String> deleteAutStudent(Integer id);

    boolean isAddAutStudent(Integer userid);
}
