package com.lyx.undergraduatejob.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Users {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", userpwd='" + userpwd + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    private Integer userid;

    private String username;

    private String userpwd;

    private Integer age;

    private String sex;

    private String address;

    private Date birthday;

    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }



    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBirthday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return birthday != null ? sdf.format(birthday) : null;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public void setBirthday2(String birthday) {
        String format = "";
        if(birthday.indexOf("-") > -1){
            format = "yyyy-MM-dd";
        }else if(birthday.indexOf("/") > -1){
            format = "yyyy/MM/dd";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if(birthday != null){
            try {
                this.birthday = sdf.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }
}