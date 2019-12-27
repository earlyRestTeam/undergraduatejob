package com.lyx.undergraduatejob.services.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @createTime 2019.12.27.14:45
 */
public class OnlineEntity implements UserDetails {
    private Integer id;
    private Integer companyId;
    private String entityName;
    private String headImg;
    private String roles;
    private String nickName;

    public OnlineEntity(Integer id, Integer companyId, String entityName, String headImg, String roles, String nickName) {
        this.id = id;
        this.companyId = companyId;
        this.entityName = entityName;
        this.headImg = headImg;
        this.roles = roles;
        this.nickName = nickName;
    }

    public OnlineEntity(Integer id, Integer companyId, String entityName, String headImg, String roles) {
        this.id = id;
        this.companyId = companyId;
        this.entityName = entityName;
        this.headImg = headImg;
        this.roles = roles;
    }

    public OnlineEntity(Integer id, Integer companyId, String entityName, String headImg) {
        this.id = id;
        this.companyId = companyId;
        this.entityName = entityName;
        this.headImg = headImg;
    }
    public OnlineEntity(Integer id, Integer companyId, String entityName) {
        this.id = id;
        this.companyId = companyId;
        this.entityName = entityName;
        this.headImg = null;
    }
    public OnlineEntity(Integer id, String entityName) {
        this.id = id;
        this.companyId = null;
        this.entityName = entityName;
        this.headImg = null;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] split = roles.split(",");
        List<SimpleGrantedAuthority> collect = Stream.of(split).map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return entityName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
