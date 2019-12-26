package com.lyx.undergraduatejob.services.security;


import com.lyx.undergraduatejob.search.entity.LoginEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class LoginEntityHelper {

    ThreadLocal<LoginOnlineEntity> entityThreadLocal;

    public Object getEntity(){
        //return entityThreadLocal.get();
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取在线 的 实体，若是不对应 返回null
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getEntityByClass(Class<T> clazz){
        Object entity = getEntity();
        if(entity.getClass() == clazz)
            return clazz.cast(entity);
        else
            return null;
    }


    class LoginOnlineEntity{
        private Integer id;
        private Integer companyId;
        private String entityName;
        private String headImg;

        public LoginOnlineEntity(Integer id, Integer companyId, String entityName, String headImg) {
            this.id = id;
            this.companyId = companyId;
            this.entityName = entityName;
            this.headImg = headImg;
        }
        public LoginOnlineEntity(Integer id, Integer companyId, String entityName) {
            this.id = id;
            this.companyId = companyId;
            this.entityName = entityName;
            this.headImg = null;
        }
        public LoginOnlineEntity(Integer id, String entityName) {
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
    }
}
