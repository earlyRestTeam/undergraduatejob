package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.AutCompany;
import com.lyx.undergraduatejob.pojo.AutStudent;

import java.util.Map;

/**
 * @Date 2019/12/18 15:41
 * @Version V1.0
 */
public interface IAutCompanyService {

    PageInfo<AutCompany> queryAutCompany(Integer indexpage, AutCompany autCompany, String columnName, String sort);

    Map<String, String> updateAutCompany(AutCompany autCompany);

    Map<String, String> addAutCompany(AutCompany autCompany);

    Map<String, String> deleteAutCompany(Integer id);

    boolean isAddAutCompany(Integer userid);

//    public Map<String, String> updateAutCompany(AutCompany autCompany,Integer companyid);
}
