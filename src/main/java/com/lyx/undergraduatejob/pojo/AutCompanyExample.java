package com.lyx.undergraduatejob.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AutCompanyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AutCompanyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andProposerNameIsNull() {
            addCriterion("proposer_name is null");
            return (Criteria) this;
        }

        public Criteria andProposerNameIsNotNull() {
            addCriterion("proposer_name is not null");
            return (Criteria) this;
        }

        public Criteria andProposerNameEqualTo(String value) {
            addCriterion("proposer_name =", value, "proposerName");
            return (Criteria) this;
        }

        public Criteria andProposerNameNotEqualTo(String value) {
            addCriterion("proposer_name <>", value, "proposerName");
            return (Criteria) this;
        }

        public Criteria andProposerNameGreaterThan(String value) {
            addCriterion("proposer_name >", value, "proposerName");
            return (Criteria) this;
        }

        public Criteria andProposerNameGreaterThanOrEqualTo(String value) {
            addCriterion("proposer_name >=", value, "proposerName");
            return (Criteria) this;
        }

        public Criteria andProposerNameLessThan(String value) {
            addCriterion("proposer_name <", value, "proposerName");
            return (Criteria) this;
        }

        public Criteria andProposerNameLessThanOrEqualTo(String value) {
            addCriterion("proposer_name <=", value, "proposerName");
            return (Criteria) this;
        }

        public Criteria andProposerNameLike(String value) {
            addCriterion("proposer_name like", value, "proposerName");
            return (Criteria) this;
        }

        public Criteria andProposerNameNotLike(String value) {
            addCriterion("proposer_name not like", value, "proposerName");
            return (Criteria) this;
        }

        public Criteria andProposerNameIn(List<String> values) {
            addCriterion("proposer_name in", values, "proposerName");
            return (Criteria) this;
        }

        public Criteria andProposerNameNotIn(List<String> values) {
            addCriterion("proposer_name not in", values, "proposerName");
            return (Criteria) this;
        }

        public Criteria andProposerNameBetween(String value1, String value2) {
            addCriterion("proposer_name between", value1, value2, "proposerName");
            return (Criteria) this;
        }

        public Criteria andProposerNameNotBetween(String value1, String value2) {
            addCriterion("proposer_name not between", value1, value2, "proposerName");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andProposerPhoneIsNull() {
            addCriterion("proposer_phone is null");
            return (Criteria) this;
        }

        public Criteria andProposerPhoneIsNotNull() {
            addCriterion("proposer_phone is not null");
            return (Criteria) this;
        }

        public Criteria andProposerPhoneEqualTo(String value) {
            addCriterion("proposer_phone =", value, "proposerPhone");
            return (Criteria) this;
        }

        public Criteria andProposerPhoneNotEqualTo(String value) {
            addCriterion("proposer_phone <>", value, "proposerPhone");
            return (Criteria) this;
        }

        public Criteria andProposerPhoneGreaterThan(String value) {
            addCriterion("proposer_phone >", value, "proposerPhone");
            return (Criteria) this;
        }

        public Criteria andProposerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("proposer_phone >=", value, "proposerPhone");
            return (Criteria) this;
        }

        public Criteria andProposerPhoneLessThan(String value) {
            addCriterion("proposer_phone <", value, "proposerPhone");
            return (Criteria) this;
        }

        public Criteria andProposerPhoneLessThanOrEqualTo(String value) {
            addCriterion("proposer_phone <=", value, "proposerPhone");
            return (Criteria) this;
        }

        public Criteria andProposerPhoneLike(String value) {
            addCriterion("proposer_phone like", value, "proposerPhone");
            return (Criteria) this;
        }

        public Criteria andProposerPhoneNotLike(String value) {
            addCriterion("proposer_phone not like", value, "proposerPhone");
            return (Criteria) this;
        }

        public Criteria andProposerPhoneIn(List<String> values) {
            addCriterion("proposer_phone in", values, "proposerPhone");
            return (Criteria) this;
        }

        public Criteria andProposerPhoneNotIn(List<String> values) {
            addCriterion("proposer_phone not in", values, "proposerPhone");
            return (Criteria) this;
        }

        public Criteria andProposerPhoneBetween(String value1, String value2) {
            addCriterion("proposer_phone between", value1, value2, "proposerPhone");
            return (Criteria) this;
        }

        public Criteria andProposerPhoneNotBetween(String value1, String value2) {
            addCriterion("proposer_phone not between", value1, value2, "proposerPhone");
            return (Criteria) this;
        }

        public Criteria andLicenseCardIsNull() {
            addCriterion("license_card is null");
            return (Criteria) this;
        }

        public Criteria andLicenseCardIsNotNull() {
            addCriterion("license_card is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseCardEqualTo(String value) {
            addCriterion("license_card =", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardNotEqualTo(String value) {
            addCriterion("license_card <>", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardGreaterThan(String value) {
            addCriterion("license_card >", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardGreaterThanOrEqualTo(String value) {
            addCriterion("license_card >=", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardLessThan(String value) {
            addCriterion("license_card <", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardLessThanOrEqualTo(String value) {
            addCriterion("license_card <=", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardLike(String value) {
            addCriterion("license_card like", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardNotLike(String value) {
            addCriterion("license_card not like", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardIn(List<String> values) {
            addCriterion("license_card in", values, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardNotIn(List<String> values) {
            addCriterion("license_card not in", values, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardBetween(String value1, String value2) {
            addCriterion("license_card between", value1, value2, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardNotBetween(String value1, String value2) {
            addCriterion("license_card not between", value1, value2, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicensePictureIsNull() {
            addCriterion("license_picture is null");
            return (Criteria) this;
        }

        public Criteria andLicensePictureIsNotNull() {
            addCriterion("license_picture is not null");
            return (Criteria) this;
        }

        public Criteria andLicensePictureEqualTo(Integer value) {
            addCriterion("license_picture =", value, "licensePicture");
            return (Criteria) this;
        }

        public Criteria andLicensePictureNotEqualTo(Integer value) {
            addCriterion("license_picture <>", value, "licensePicture");
            return (Criteria) this;
        }

        public Criteria andLicensePictureGreaterThan(Integer value) {
            addCriterion("license_picture >", value, "licensePicture");
            return (Criteria) this;
        }

        public Criteria andLicensePictureGreaterThanOrEqualTo(Integer value) {
            addCriterion("license_picture >=", value, "licensePicture");
            return (Criteria) this;
        }

        public Criteria andLicensePictureLessThan(Integer value) {
            addCriterion("license_picture <", value, "licensePicture");
            return (Criteria) this;
        }

        public Criteria andLicensePictureLessThanOrEqualTo(Integer value) {
            addCriterion("license_picture <=", value, "licensePicture");
            return (Criteria) this;
        }

        public Criteria andLicensePictureIn(List<Integer> values) {
            addCriterion("license_picture in", values, "licensePicture");
            return (Criteria) this;
        }

        public Criteria andLicensePictureNotIn(List<Integer> values) {
            addCriterion("license_picture not in", values, "licensePicture");
            return (Criteria) this;
        }

        public Criteria andLicensePictureBetween(Integer value1, Integer value2) {
            addCriterion("license_picture between", value1, value2, "licensePicture");
            return (Criteria) this;
        }

        public Criteria andLicensePictureNotBetween(Integer value1, Integer value2) {
            addCriterion("license_picture not between", value1, value2, "licensePicture");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCompanyStatusIsNull() {
            addCriterion("company_status is null");
            return (Criteria) this;
        }

        public Criteria andCompanyStatusIsNotNull() {
            addCriterion("company_status is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyStatusEqualTo(Integer value) {
            addCriterion("company_status =", value, "companyStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyStatusNotEqualTo(Integer value) {
            addCriterion("company_status <>", value, "companyStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyStatusGreaterThan(Integer value) {
            addCriterion("company_status >", value, "companyStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("company_status >=", value, "companyStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyStatusLessThan(Integer value) {
            addCriterion("company_status <", value, "companyStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyStatusLessThanOrEqualTo(Integer value) {
            addCriterion("company_status <=", value, "companyStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyStatusIn(List<Integer> values) {
            addCriterion("company_status in", values, "companyStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyStatusNotIn(List<Integer> values) {
            addCriterion("company_status not in", values, "companyStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyStatusBetween(Integer value1, Integer value2) {
            addCriterion("company_status between", value1, value2, "companyStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("company_status not between", value1, value2, "companyStatus");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}