package com.lyx.undergraduatejob.dao;




import com.lyx.undergraduatejob.pojo.Users;
import org.apache.ibatis.annotations.Delete;

import java.util.HashMap;
import java.util.List;

public interface IUserDao {



    int insert(Users u);


    @Delete("delete from tb_users where userid=#{userid}")
    int delete(int userid);

    int update(Users users);

    List<Users> query(HashMap<String, Object> param);



}
