package com.lyx.undergraduatejob.pojo;

import java.io.Serializable;

public class TbArea implements Serializable {
    private Integer id;

    private String name;

    private Integer parentid;

    private String shortname;

    private Integer leveltype;

    private String citycode;

    private String zipcode;

    private String mergername;

    private String lng;

    private String lat;

    private String pinyin;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public Integer getLeveltype() {
        return leveltype;
    }

    public void setLeveltype(Integer leveltype) {
        this.leveltype = leveltype;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMergername() {
        return mergername;
    }

    public void setMergername(String mergername) {
        this.mergername = mergername;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", parentid=").append(parentid);
        sb.append(", shortname=").append(shortname);
        sb.append(", leveltype=").append(leveltype);
        sb.append(", citycode=").append(citycode);
        sb.append(", zipcode=").append(zipcode);
        sb.append(", mergername=").append(mergername);
        sb.append(", lng=").append(lng);
        sb.append(", lat=").append(lat);
        sb.append(", pinyin=").append(pinyin);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}