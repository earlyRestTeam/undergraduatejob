package com.lyx.undergraduatejob.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ResumeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResumeExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNull() {
            addCriterion("avatar is null");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNotNull() {
            addCriterion("avatar is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarEqualTo(String value) {
            addCriterion("avatar =", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotEqualTo(String value) {
            addCriterion("avatar <>", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThan(String value) {
            addCriterion("avatar >", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThanOrEqualTo(String value) {
            addCriterion("avatar >=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThan(String value) {
            addCriterion("avatar <", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThanOrEqualTo(String value) {
            addCriterion("avatar <=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLike(String value) {
            addCriterion("avatar like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotLike(String value) {
            addCriterion("avatar not like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarIn(List<String> values) {
            addCriterion("avatar in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotIn(List<String> values) {
            addCriterion("avatar not in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarBetween(String value1, String value2) {
            addCriterion("avatar between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotBetween(String value1, String value2) {
            addCriterion("avatar not between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andAgeIsNull() {
            addCriterion("age is null");
            return (Criteria) this;
        }

        public Criteria andAgeIsNotNull() {
            addCriterion("age is not null");
            return (Criteria) this;
        }

        public Criteria andAgeEqualTo(Integer value) {
            addCriterion("age =", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotEqualTo(Integer value) {
            addCriterion("age <>", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThan(Integer value) {
            addCriterion("age >", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("age >=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThan(Integer value) {
            addCriterion("age <", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThanOrEqualTo(Integer value) {
            addCriterion("age <=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeIn(List<Integer> values) {
            addCriterion("age in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotIn(List<Integer> values) {
            addCriterion("age not in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeBetween(Integer value1, Integer value2) {
            addCriterion("age between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("age not between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(Integer value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(Integer value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(Integer value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(Integer value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(Integer value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<Integer> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<Integer> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(Integer value1, Integer value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(Integer value1, Integer value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andWeiXinIsNull() {
            addCriterion("wei_xin is null");
            return (Criteria) this;
        }

        public Criteria andWeiXinIsNotNull() {
            addCriterion("wei_xin is not null");
            return (Criteria) this;
        }

        public Criteria andWeiXinEqualTo(String value) {
            addCriterion("wei_xin =", value, "weiXin");
            return (Criteria) this;
        }

        public Criteria andWeiXinNotEqualTo(String value) {
            addCriterion("wei_xin <>", value, "weiXin");
            return (Criteria) this;
        }

        public Criteria andWeiXinGreaterThan(String value) {
            addCriterion("wei_xin >", value, "weiXin");
            return (Criteria) this;
        }

        public Criteria andWeiXinGreaterThanOrEqualTo(String value) {
            addCriterion("wei_xin >=", value, "weiXin");
            return (Criteria) this;
        }

        public Criteria andWeiXinLessThan(String value) {
            addCriterion("wei_xin <", value, "weiXin");
            return (Criteria) this;
        }

        public Criteria andWeiXinLessThanOrEqualTo(String value) {
            addCriterion("wei_xin <=", value, "weiXin");
            return (Criteria) this;
        }

        public Criteria andWeiXinLike(String value) {
            addCriterion("wei_xin like", value, "weiXin");
            return (Criteria) this;
        }

        public Criteria andWeiXinNotLike(String value) {
            addCriterion("wei_xin not like", value, "weiXin");
            return (Criteria) this;
        }

        public Criteria andWeiXinIn(List<String> values) {
            addCriterion("wei_xin in", values, "weiXin");
            return (Criteria) this;
        }

        public Criteria andWeiXinNotIn(List<String> values) {
            addCriterion("wei_xin not in", values, "weiXin");
            return (Criteria) this;
        }

        public Criteria andWeiXinBetween(String value1, String value2) {
            addCriterion("wei_xin between", value1, value2, "weiXin");
            return (Criteria) this;
        }

        public Criteria andWeiXinNotBetween(String value1, String value2) {
            addCriterion("wei_xin not between", value1, value2, "weiXin");
            return (Criteria) this;
        }

        public Criteria andQqIsNull() {
            addCriterion("qq is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("qq is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(String value) {
            addCriterion("qq =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(String value) {
            addCriterion("qq <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(String value) {
            addCriterion("qq >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(String value) {
            addCriterion("qq >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(String value) {
            addCriterion("qq <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(String value) {
            addCriterion("qq <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLike(String value) {
            addCriterion("qq like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotLike(String value) {
            addCriterion("qq not like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<String> values) {
            addCriterion("qq in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<String> values) {
            addCriterion("qq not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(String value1, String value2) {
            addCriterion("qq between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(String value1, String value2) {
            addCriterion("qq not between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andNationIsNull() {
            addCriterion("nation is null");
            return (Criteria) this;
        }

        public Criteria andNationIsNotNull() {
            addCriterion("nation is not null");
            return (Criteria) this;
        }

        public Criteria andNationEqualTo(String value) {
            addCriterion("nation =", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotEqualTo(String value) {
            addCriterion("nation <>", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationGreaterThan(String value) {
            addCriterion("nation >", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationGreaterThanOrEqualTo(String value) {
            addCriterion("nation >=", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLessThan(String value) {
            addCriterion("nation <", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLessThanOrEqualTo(String value) {
            addCriterion("nation <=", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLike(String value) {
            addCriterion("nation like", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotLike(String value) {
            addCriterion("nation not like", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationIn(List<String> values) {
            addCriterion("nation in", values, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotIn(List<String> values) {
            addCriterion("nation not in", values, "nation");
            return (Criteria) this;
        }

        public Criteria andNationBetween(String value1, String value2) {
            addCriterion("nation between", value1, value2, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotBetween(String value1, String value2) {
            addCriterion("nation not between", value1, value2, "nation");
            return (Criteria) this;
        }

        public Criteria andNativePlaceIsNull() {
            addCriterion("native_place is null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceIsNotNull() {
            addCriterion("native_place is not null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceEqualTo(String value) {
            addCriterion("native_place =", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceNotEqualTo(String value) {
            addCriterion("native_place <>", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceGreaterThan(String value) {
            addCriterion("native_place >", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceGreaterThanOrEqualTo(String value) {
            addCriterion("native_place >=", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceLessThan(String value) {
            addCriterion("native_place <", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceLessThanOrEqualTo(String value) {
            addCriterion("native_place <=", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceLike(String value) {
            addCriterion("native_place like", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceNotLike(String value) {
            addCriterion("native_place not like", value, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceIn(List<String> values) {
            addCriterion("native_place in", values, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceNotIn(List<String> values) {
            addCriterion("native_place not in", values, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceBetween(String value1, String value2) {
            addCriterion("native_place between", value1, value2, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andNativePlaceNotBetween(String value1, String value2) {
            addCriterion("native_place not between", value1, value2, "nativePlace");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolIsNull() {
            addCriterion("graduate_school is null");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolIsNotNull() {
            addCriterion("graduate_school is not null");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolEqualTo(String value) {
            addCriterion("graduate_school =", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolNotEqualTo(String value) {
            addCriterion("graduate_school <>", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolGreaterThan(String value) {
            addCriterion("graduate_school >", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolGreaterThanOrEqualTo(String value) {
            addCriterion("graduate_school >=", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolLessThan(String value) {
            addCriterion("graduate_school <", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolLessThanOrEqualTo(String value) {
            addCriterion("graduate_school <=", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolLike(String value) {
            addCriterion("graduate_school like", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolNotLike(String value) {
            addCriterion("graduate_school not like", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolIn(List<String> values) {
            addCriterion("graduate_school in", values, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolNotIn(List<String> values) {
            addCriterion("graduate_school not in", values, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolBetween(String value1, String value2) {
            addCriterion("graduate_school between", value1, value2, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolNotBetween(String value1, String value2) {
            addCriterion("graduate_school not between", value1, value2, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIsNull() {
            addCriterion("specialty is null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIsNotNull() {
            addCriterion("specialty is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyEqualTo(String value) {
            addCriterion("specialty =", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotEqualTo(String value) {
            addCriterion("specialty <>", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyGreaterThan(String value) {
            addCriterion("specialty >", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyGreaterThanOrEqualTo(String value) {
            addCriterion("specialty >=", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLessThan(String value) {
            addCriterion("specialty <", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLessThanOrEqualTo(String value) {
            addCriterion("specialty <=", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLike(String value) {
            addCriterion("specialty like", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotLike(String value) {
            addCriterion("specialty not like", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIn(List<String> values) {
            addCriterion("specialty in", values, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotIn(List<String> values) {
            addCriterion("specialty not in", values, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyBetween(String value1, String value2) {
            addCriterion("specialty between", value1, value2, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotBetween(String value1, String value2) {
            addCriterion("specialty not between", value1, value2, "specialty");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(String value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(String value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(String value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(String value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(String value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(String value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLike(String value) {
            addCriterion("education like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotLike(String value) {
            addCriterion("education not like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<String> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<String> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(String value1, String value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(String value1, String value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andJobIsNull() {
            addCriterion("job is null");
            return (Criteria) this;
        }

        public Criteria andJobIsNotNull() {
            addCriterion("job is not null");
            return (Criteria) this;
        }

        public Criteria andJobEqualTo(String value) {
            addCriterion("job =", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotEqualTo(String value) {
            addCriterion("job <>", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobGreaterThan(String value) {
            addCriterion("job >", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobGreaterThanOrEqualTo(String value) {
            addCriterion("job >=", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobLessThan(String value) {
            addCriterion("job <", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobLessThanOrEqualTo(String value) {
            addCriterion("job <=", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobLike(String value) {
            addCriterion("job like", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotLike(String value) {
            addCriterion("job not like", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobIn(List<String> values) {
            addCriterion("job in", values, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotIn(List<String> values) {
            addCriterion("job not in", values, "job");
            return (Criteria) this;
        }

        public Criteria andJobBetween(String value1, String value2) {
            addCriterion("job between", value1, value2, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotBetween(String value1, String value2) {
            addCriterion("job not between", value1, value2, "job");
            return (Criteria) this;
        }

        public Criteria andJobTypeIsNull() {
            addCriterion("job_type is null");
            return (Criteria) this;
        }

        public Criteria andJobTypeIsNotNull() {
            addCriterion("job_type is not null");
            return (Criteria) this;
        }

        public Criteria andJobTypeEqualTo(String value) {
            addCriterion("job_type =", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeNotEqualTo(String value) {
            addCriterion("job_type <>", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeGreaterThan(String value) {
            addCriterion("job_type >", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeGreaterThanOrEqualTo(String value) {
            addCriterion("job_type >=", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeLessThan(String value) {
            addCriterion("job_type <", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeLessThanOrEqualTo(String value) {
            addCriterion("job_type <=", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeLike(String value) {
            addCriterion("job_type like", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeNotLike(String value) {
            addCriterion("job_type not like", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeIn(List<String> values) {
            addCriterion("job_type in", values, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeNotIn(List<String> values) {
            addCriterion("job_type not in", values, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeBetween(String value1, String value2) {
            addCriterion("job_type between", value1, value2, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeNotBetween(String value1, String value2) {
            addCriterion("job_type not between", value1, value2, "jobType");
            return (Criteria) this;
        }

        public Criteria andSalaryIsNull() {
            addCriterion("salary is null");
            return (Criteria) this;
        }

        public Criteria andSalaryIsNotNull() {
            addCriterion("salary is not null");
            return (Criteria) this;
        }

        public Criteria andSalaryEqualTo(Integer value) {
            addCriterion("salary =", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotEqualTo(Integer value) {
            addCriterion("salary <>", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryGreaterThan(Integer value) {
            addCriterion("salary >", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("salary >=", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLessThan(Integer value) {
            addCriterion("salary <", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLessThanOrEqualTo(Integer value) {
            addCriterion("salary <=", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryIn(List<Integer> values) {
            addCriterion("salary in", values, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotIn(List<Integer> values) {
            addCriterion("salary not in", values, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryBetween(Integer value1, Integer value2) {
            addCriterion("salary between", value1, value2, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotBetween(Integer value1, Integer value2) {
            addCriterion("salary not between", value1, value2, "salary");
            return (Criteria) this;
        }

        public Criteria andCloseTypeIsNull() {
            addCriterion("close_type is null");
            return (Criteria) this;
        }

        public Criteria andCloseTypeIsNotNull() {
            addCriterion("close_type is not null");
            return (Criteria) this;
        }

        public Criteria andCloseTypeEqualTo(Integer value) {
            addCriterion("close_type =", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeNotEqualTo(Integer value) {
            addCriterion("close_type <>", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeGreaterThan(Integer value) {
            addCriterion("close_type >", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("close_type >=", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeLessThan(Integer value) {
            addCriterion("close_type <", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeLessThanOrEqualTo(Integer value) {
            addCriterion("close_type <=", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeIn(List<Integer> values) {
            addCriterion("close_type in", values, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeNotIn(List<Integer> values) {
            addCriterion("close_type not in", values, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeBetween(Integer value1, Integer value2) {
            addCriterion("close_type between", value1, value2, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("close_type not between", value1, value2, "closeType");
            return (Criteria) this;
        }

        public Criteria andJobAddressIsNull() {
            addCriterion("job_address is null");
            return (Criteria) this;
        }

        public Criteria andJobAddressIsNotNull() {
            addCriterion("job_address is not null");
            return (Criteria) this;
        }

        public Criteria andJobAddressEqualTo(String value) {
            addCriterion("job_address =", value, "jobAddress");
            return (Criteria) this;
        }

        public Criteria andJobAddressNotEqualTo(String value) {
            addCriterion("job_address <>", value, "jobAddress");
            return (Criteria) this;
        }

        public Criteria andJobAddressGreaterThan(String value) {
            addCriterion("job_address >", value, "jobAddress");
            return (Criteria) this;
        }

        public Criteria andJobAddressGreaterThanOrEqualTo(String value) {
            addCriterion("job_address >=", value, "jobAddress");
            return (Criteria) this;
        }

        public Criteria andJobAddressLessThan(String value) {
            addCriterion("job_address <", value, "jobAddress");
            return (Criteria) this;
        }

        public Criteria andJobAddressLessThanOrEqualTo(String value) {
            addCriterion("job_address <=", value, "jobAddress");
            return (Criteria) this;
        }

        public Criteria andJobAddressLike(String value) {
            addCriterion("job_address like", value, "jobAddress");
            return (Criteria) this;
        }

        public Criteria andJobAddressNotLike(String value) {
            addCriterion("job_address not like", value, "jobAddress");
            return (Criteria) this;
        }

        public Criteria andJobAddressIn(List<String> values) {
            addCriterion("job_address in", values, "jobAddress");
            return (Criteria) this;
        }

        public Criteria andJobAddressNotIn(List<String> values) {
            addCriterion("job_address not in", values, "jobAddress");
            return (Criteria) this;
        }

        public Criteria andJobAddressBetween(String value1, String value2) {
            addCriterion("job_address between", value1, value2, "jobAddress");
            return (Criteria) this;
        }

        public Criteria andJobAddressNotBetween(String value1, String value2) {
            addCriterion("job_address not between", value1, value2, "jobAddress");
            return (Criteria) this;
        }

        public Criteria andPartFullIsNull() {
            addCriterion("part_full is null");
            return (Criteria) this;
        }

        public Criteria andPartFullIsNotNull() {
            addCriterion("part_full is not null");
            return (Criteria) this;
        }

        public Criteria andPartFullEqualTo(Integer value) {
            addCriterion("part_full =", value, "partFull");
            return (Criteria) this;
        }

        public Criteria andPartFullNotEqualTo(Integer value) {
            addCriterion("part_full <>", value, "partFull");
            return (Criteria) this;
        }

        public Criteria andPartFullGreaterThan(Integer value) {
            addCriterion("part_full >", value, "partFull");
            return (Criteria) this;
        }

        public Criteria andPartFullGreaterThanOrEqualTo(Integer value) {
            addCriterion("part_full >=", value, "partFull");
            return (Criteria) this;
        }

        public Criteria andPartFullLessThan(Integer value) {
            addCriterion("part_full <", value, "partFull");
            return (Criteria) this;
        }

        public Criteria andPartFullLessThanOrEqualTo(Integer value) {
            addCriterion("part_full <=", value, "partFull");
            return (Criteria) this;
        }

        public Criteria andPartFullIn(List<Integer> values) {
            addCriterion("part_full in", values, "partFull");
            return (Criteria) this;
        }

        public Criteria andPartFullNotIn(List<Integer> values) {
            addCriterion("part_full not in", values, "partFull");
            return (Criteria) this;
        }

        public Criteria andPartFullBetween(Integer value1, Integer value2) {
            addCriterion("part_full between", value1, value2, "partFull");
            return (Criteria) this;
        }

        public Criteria andPartFullNotBetween(Integer value1, Integer value2) {
            addCriterion("part_full not between", value1, value2, "partFull");
            return (Criteria) this;
        }

        public Criteria andFreeTimeIsNull() {
            addCriterion("free_time is null");
            return (Criteria) this;
        }

        public Criteria andFreeTimeIsNotNull() {
            addCriterion("free_time is not null");
            return (Criteria) this;
        }

        public Criteria andFreeTimeEqualTo(String value) {
            addCriterion("free_time =", value, "freeTime");
            return (Criteria) this;
        }

        public Criteria andFreeTimeNotEqualTo(String value) {
            addCriterion("free_time <>", value, "freeTime");
            return (Criteria) this;
        }

        public Criteria andFreeTimeGreaterThan(String value) {
            addCriterion("free_time >", value, "freeTime");
            return (Criteria) this;
        }

        public Criteria andFreeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("free_time >=", value, "freeTime");
            return (Criteria) this;
        }

        public Criteria andFreeTimeLessThan(String value) {
            addCriterion("free_time <", value, "freeTime");
            return (Criteria) this;
        }

        public Criteria andFreeTimeLessThanOrEqualTo(String value) {
            addCriterion("free_time <=", value, "freeTime");
            return (Criteria) this;
        }

        public Criteria andFreeTimeLike(String value) {
            addCriterion("free_time like", value, "freeTime");
            return (Criteria) this;
        }

        public Criteria andFreeTimeNotLike(String value) {
            addCriterion("free_time not like", value, "freeTime");
            return (Criteria) this;
        }

        public Criteria andFreeTimeIn(List<String> values) {
            addCriterion("free_time in", values, "freeTime");
            return (Criteria) this;
        }

        public Criteria andFreeTimeNotIn(List<String> values) {
            addCriterion("free_time not in", values, "freeTime");
            return (Criteria) this;
        }

        public Criteria andFreeTimeBetween(String value1, String value2) {
            addCriterion("free_time between", value1, value2, "freeTime");
            return (Criteria) this;
        }

        public Criteria andFreeTimeNotBetween(String value1, String value2) {
            addCriterion("free_time not between", value1, value2, "freeTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNull() {
            addCriterion("push_time is null");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNotNull() {
            addCriterion("push_time is not null");
            return (Criteria) this;
        }

        public Criteria andPushTimeEqualTo(Date value) {
            addCriterion("push_time =", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotEqualTo(Date value) {
            addCriterion("push_time <>", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThan(Date value) {
            addCriterion("push_time >", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("push_time >=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThan(Date value) {
            addCriterion("push_time <", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThanOrEqualTo(Date value) {
            addCriterion("push_time <=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeIn(List<Date> values) {
            addCriterion("push_time in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotIn(List<Date> values) {
            addCriterion("push_time not in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeBetween(Date value1, Date value2) {
            addCriterion("push_time between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotBetween(Date value1, Date value2) {
            addCriterion("push_time not between", value1, value2, "pushTime");
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

        public Criteria andPushStatusIsNull() {
            addCriterion("push_status is null");
            return (Criteria) this;
        }

        public Criteria andPushStatusIsNotNull() {
            addCriterion("push_status is not null");
            return (Criteria) this;
        }

        public Criteria andPushStatusEqualTo(Integer value) {
            addCriterion("push_status =", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusNotEqualTo(Integer value) {
            addCriterion("push_status <>", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusGreaterThan(Integer value) {
            addCriterion("push_status >", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("push_status >=", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusLessThan(Integer value) {
            addCriterion("push_status <", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusLessThanOrEqualTo(Integer value) {
            addCriterion("push_status <=", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusIn(List<Integer> values) {
            addCriterion("push_status in", values, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusNotIn(List<Integer> values) {
            addCriterion("push_status not in", values, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusBetween(Integer value1, Integer value2) {
            addCriterion("push_status between", value1, value2, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("push_status not between", value1, value2, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andAulStatusIsNull() {
            addCriterion("aul_status is null");
            return (Criteria) this;
        }

        public Criteria andAulStatusIsNotNull() {
            addCriterion("aul_status is not null");
            return (Criteria) this;
        }

        public Criteria andAulStatusEqualTo(Integer value) {
            addCriterion("aul_status =", value, "aulStatus");
            return (Criteria) this;
        }

        public Criteria andAulStatusNotEqualTo(Integer value) {
            addCriterion("aul_status <>", value, "aulStatus");
            return (Criteria) this;
        }

        public Criteria andAulStatusGreaterThan(Integer value) {
            addCriterion("aul_status >", value, "aulStatus");
            return (Criteria) this;
        }

        public Criteria andAulStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("aul_status >=", value, "aulStatus");
            return (Criteria) this;
        }

        public Criteria andAulStatusLessThan(Integer value) {
            addCriterion("aul_status <", value, "aulStatus");
            return (Criteria) this;
        }

        public Criteria andAulStatusLessThanOrEqualTo(Integer value) {
            addCriterion("aul_status <=", value, "aulStatus");
            return (Criteria) this;
        }

        public Criteria andAulStatusIn(List<Integer> values) {
            addCriterion("aul_status in", values, "aulStatus");
            return (Criteria) this;
        }

        public Criteria andAulStatusNotIn(List<Integer> values) {
            addCriterion("aul_status not in", values, "aulStatus");
            return (Criteria) this;
        }

        public Criteria andAulStatusBetween(Integer value1, Integer value2) {
            addCriterion("aul_status between", value1, value2, "aulStatus");
            return (Criteria) this;
        }

        public Criteria andAulStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("aul_status not between", value1, value2, "aulStatus");
            return (Criteria) this;
        }

        public Criteria andResumeUrlIsNull() {
            addCriterion("resume_url is null");
            return (Criteria) this;
        }

        public Criteria andResumeUrlIsNotNull() {
            addCriterion("resume_url is not null");
            return (Criteria) this;
        }

        public Criteria andResumeUrlEqualTo(String value) {
            addCriterion("resume_url =", value, "resumeUrl");
            return (Criteria) this;
        }

        public Criteria andResumeUrlNotEqualTo(String value) {
            addCriterion("resume_url <>", value, "resumeUrl");
            return (Criteria) this;
        }

        public Criteria andResumeUrlGreaterThan(String value) {
            addCriterion("resume_url >", value, "resumeUrl");
            return (Criteria) this;
        }

        public Criteria andResumeUrlGreaterThanOrEqualTo(String value) {
            addCriterion("resume_url >=", value, "resumeUrl");
            return (Criteria) this;
        }

        public Criteria andResumeUrlLessThan(String value) {
            addCriterion("resume_url <", value, "resumeUrl");
            return (Criteria) this;
        }

        public Criteria andResumeUrlLessThanOrEqualTo(String value) {
            addCriterion("resume_url <=", value, "resumeUrl");
            return (Criteria) this;
        }

        public Criteria andResumeUrlLike(String value) {
            addCriterion("resume_url like", value, "resumeUrl");
            return (Criteria) this;
        }

        public Criteria andResumeUrlNotLike(String value) {
            addCriterion("resume_url not like", value, "resumeUrl");
            return (Criteria) this;
        }

        public Criteria andResumeUrlIn(List<String> values) {
            addCriterion("resume_url in", values, "resumeUrl");
            return (Criteria) this;
        }

        public Criteria andResumeUrlNotIn(List<String> values) {
            addCriterion("resume_url not in", values, "resumeUrl");
            return (Criteria) this;
        }

        public Criteria andResumeUrlBetween(String value1, String value2) {
            addCriterion("resume_url between", value1, value2, "resumeUrl");
            return (Criteria) this;
        }

        public Criteria andResumeUrlNotBetween(String value1, String value2) {
            addCriterion("resume_url not between", value1, value2, "resumeUrl");
            return (Criteria) this;
        }

        public Criteria andSkill1IsNull() {
            addCriterion("skill1 is null");
            return (Criteria) this;
        }

        public Criteria andSkill1IsNotNull() {
            addCriterion("skill1 is not null");
            return (Criteria) this;
        }

        public Criteria andSkill1EqualTo(String value) {
            addCriterion("skill1 =", value, "skill1");
            return (Criteria) this;
        }

        public Criteria andSkill1NotEqualTo(String value) {
            addCriterion("skill1 <>", value, "skill1");
            return (Criteria) this;
        }

        public Criteria andSkill1GreaterThan(String value) {
            addCriterion("skill1 >", value, "skill1");
            return (Criteria) this;
        }

        public Criteria andSkill1GreaterThanOrEqualTo(String value) {
            addCriterion("skill1 >=", value, "skill1");
            return (Criteria) this;
        }

        public Criteria andSkill1LessThan(String value) {
            addCriterion("skill1 <", value, "skill1");
            return (Criteria) this;
        }

        public Criteria andSkill1LessThanOrEqualTo(String value) {
            addCriterion("skill1 <=", value, "skill1");
            return (Criteria) this;
        }

        public Criteria andSkill1Like(String value) {
            addCriterion("skill1 like", value, "skill1");
            return (Criteria) this;
        }

        public Criteria andSkill1NotLike(String value) {
            addCriterion("skill1 not like", value, "skill1");
            return (Criteria) this;
        }

        public Criteria andSkill1In(List<String> values) {
            addCriterion("skill1 in", values, "skill1");
            return (Criteria) this;
        }

        public Criteria andSkill1NotIn(List<String> values) {
            addCriterion("skill1 not in", values, "skill1");
            return (Criteria) this;
        }

        public Criteria andSkill1Between(String value1, String value2) {
            addCriterion("skill1 between", value1, value2, "skill1");
            return (Criteria) this;
        }

        public Criteria andSkill1NotBetween(String value1, String value2) {
            addCriterion("skill1 not between", value1, value2, "skill1");
            return (Criteria) this;
        }

        public Criteria andSkill2IsNull() {
            addCriterion("skill2 is null");
            return (Criteria) this;
        }

        public Criteria andSkill2IsNotNull() {
            addCriterion("skill2 is not null");
            return (Criteria) this;
        }

        public Criteria andSkill2EqualTo(String value) {
            addCriterion("skill2 =", value, "skill2");
            return (Criteria) this;
        }

        public Criteria andSkill2NotEqualTo(String value) {
            addCriterion("skill2 <>", value, "skill2");
            return (Criteria) this;
        }

        public Criteria andSkill2GreaterThan(String value) {
            addCriterion("skill2 >", value, "skill2");
            return (Criteria) this;
        }

        public Criteria andSkill2GreaterThanOrEqualTo(String value) {
            addCriterion("skill2 >=", value, "skill2");
            return (Criteria) this;
        }

        public Criteria andSkill2LessThan(String value) {
            addCriterion("skill2 <", value, "skill2");
            return (Criteria) this;
        }

        public Criteria andSkill2LessThanOrEqualTo(String value) {
            addCriterion("skill2 <=", value, "skill2");
            return (Criteria) this;
        }

        public Criteria andSkill2Like(String value) {
            addCriterion("skill2 like", value, "skill2");
            return (Criteria) this;
        }

        public Criteria andSkill2NotLike(String value) {
            addCriterion("skill2 not like", value, "skill2");
            return (Criteria) this;
        }

        public Criteria andSkill2In(List<String> values) {
            addCriterion("skill2 in", values, "skill2");
            return (Criteria) this;
        }

        public Criteria andSkill2NotIn(List<String> values) {
            addCriterion("skill2 not in", values, "skill2");
            return (Criteria) this;
        }

        public Criteria andSkill2Between(String value1, String value2) {
            addCriterion("skill2 between", value1, value2, "skill2");
            return (Criteria) this;
        }

        public Criteria andSkill2NotBetween(String value1, String value2) {
            addCriterion("skill2 not between", value1, value2, "skill2");
            return (Criteria) this;
        }

        public Criteria andSkill3IsNull() {
            addCriterion("skill3 is null");
            return (Criteria) this;
        }

        public Criteria andSkill3IsNotNull() {
            addCriterion("skill3 is not null");
            return (Criteria) this;
        }

        public Criteria andSkill3EqualTo(String value) {
            addCriterion("skill3 =", value, "skill3");
            return (Criteria) this;
        }

        public Criteria andSkill3NotEqualTo(String value) {
            addCriterion("skill3 <>", value, "skill3");
            return (Criteria) this;
        }

        public Criteria andSkill3GreaterThan(String value) {
            addCriterion("skill3 >", value, "skill3");
            return (Criteria) this;
        }

        public Criteria andSkill3GreaterThanOrEqualTo(String value) {
            addCriterion("skill3 >=", value, "skill3");
            return (Criteria) this;
        }

        public Criteria andSkill3LessThan(String value) {
            addCriterion("skill3 <", value, "skill3");
            return (Criteria) this;
        }

        public Criteria andSkill3LessThanOrEqualTo(String value) {
            addCriterion("skill3 <=", value, "skill3");
            return (Criteria) this;
        }

        public Criteria andSkill3Like(String value) {
            addCriterion("skill3 like", value, "skill3");
            return (Criteria) this;
        }

        public Criteria andSkill3NotLike(String value) {
            addCriterion("skill3 not like", value, "skill3");
            return (Criteria) this;
        }

        public Criteria andSkill3In(List<String> values) {
            addCriterion("skill3 in", values, "skill3");
            return (Criteria) this;
        }

        public Criteria andSkill3NotIn(List<String> values) {
            addCriterion("skill3 not in", values, "skill3");
            return (Criteria) this;
        }

        public Criteria andSkill3Between(String value1, String value2) {
            addCriterion("skill3 between", value1, value2, "skill3");
            return (Criteria) this;
        }

        public Criteria andSkill3NotBetween(String value1, String value2) {
            addCriterion("skill3 not between", value1, value2, "skill3");
            return (Criteria) this;
        }

        public Criteria andSkill4IsNull() {
            addCriterion("skill4 is null");
            return (Criteria) this;
        }

        public Criteria andSkill4IsNotNull() {
            addCriterion("skill4 is not null");
            return (Criteria) this;
        }

        public Criteria andSkill4EqualTo(String value) {
            addCriterion("skill4 =", value, "skill4");
            return (Criteria) this;
        }

        public Criteria andSkill4NotEqualTo(String value) {
            addCriterion("skill4 <>", value, "skill4");
            return (Criteria) this;
        }

        public Criteria andSkill4GreaterThan(String value) {
            addCriterion("skill4 >", value, "skill4");
            return (Criteria) this;
        }

        public Criteria andSkill4GreaterThanOrEqualTo(String value) {
            addCriterion("skill4 >=", value, "skill4");
            return (Criteria) this;
        }

        public Criteria andSkill4LessThan(String value) {
            addCriterion("skill4 <", value, "skill4");
            return (Criteria) this;
        }

        public Criteria andSkill4LessThanOrEqualTo(String value) {
            addCriterion("skill4 <=", value, "skill4");
            return (Criteria) this;
        }

        public Criteria andSkill4Like(String value) {
            addCriterion("skill4 like", value, "skill4");
            return (Criteria) this;
        }

        public Criteria andSkill4NotLike(String value) {
            addCriterion("skill4 not like", value, "skill4");
            return (Criteria) this;
        }

        public Criteria andSkill4In(List<String> values) {
            addCriterion("skill4 in", values, "skill4");
            return (Criteria) this;
        }

        public Criteria andSkill4NotIn(List<String> values) {
            addCriterion("skill4 not in", values, "skill4");
            return (Criteria) this;
        }

        public Criteria andSkill4Between(String value1, String value2) {
            addCriterion("skill4 between", value1, value2, "skill4");
            return (Criteria) this;
        }

        public Criteria andSkill4NotBetween(String value1, String value2) {
            addCriterion("skill4 not between", value1, value2, "skill4");
            return (Criteria) this;
        }

        public Criteria andWorkExperienceIsNull() {
            addCriterion("work_experience is null");
            return (Criteria) this;
        }

        public Criteria andWorkExperienceIsNotNull() {
            addCriterion("work_experience is not null");
            return (Criteria) this;
        }

        public Criteria andWorkExperienceEqualTo(Integer value) {
            addCriterion("work_experience =", value, "workExperience");
            return (Criteria) this;
        }

        public Criteria andWorkExperienceNotEqualTo(Integer value) {
            addCriterion("work_experience <>", value, "workExperience");
            return (Criteria) this;
        }

        public Criteria andWorkExperienceGreaterThan(Integer value) {
            addCriterion("work_experience >", value, "workExperience");
            return (Criteria) this;
        }

        public Criteria andWorkExperienceGreaterThanOrEqualTo(Integer value) {
            addCriterion("work_experience >=", value, "workExperience");
            return (Criteria) this;
        }

        public Criteria andWorkExperienceLessThan(Integer value) {
            addCriterion("work_experience <", value, "workExperience");
            return (Criteria) this;
        }

        public Criteria andWorkExperienceLessThanOrEqualTo(Integer value) {
            addCriterion("work_experience <=", value, "workExperience");
            return (Criteria) this;
        }

        public Criteria andWorkExperienceIn(List<Integer> values) {
            addCriterion("work_experience in", values, "workExperience");
            return (Criteria) this;
        }

        public Criteria andWorkExperienceNotIn(List<Integer> values) {
            addCriterion("work_experience not in", values, "workExperience");
            return (Criteria) this;
        }

        public Criteria andWorkExperienceBetween(Integer value1, Integer value2) {
            addCriterion("work_experience between", value1, value2, "workExperience");
            return (Criteria) this;
        }

        public Criteria andWorkExperienceNotBetween(Integer value1, Integer value2) {
            addCriterion("work_experience not between", value1, value2, "workExperience");
            return (Criteria) this;
        }

        public Criteria andResumeVipIsNull() {
            addCriterion("resume_vip is null");
            return (Criteria) this;
        }

        public Criteria andResumeVipIsNotNull() {
            addCriterion("resume_vip is not null");
            return (Criteria) this;
        }

        public Criteria andResumeVipEqualTo(Integer value) {
            addCriterion("resume_vip =", value, "resumeVip");
            return (Criteria) this;
        }

        public Criteria andResumeVipNotEqualTo(Integer value) {
            addCriterion("resume_vip <>", value, "resumeVip");
            return (Criteria) this;
        }

        public Criteria andResumeVipGreaterThan(Integer value) {
            addCriterion("resume_vip >", value, "resumeVip");
            return (Criteria) this;
        }

        public Criteria andResumeVipGreaterThanOrEqualTo(Integer value) {
            addCriterion("resume_vip >=", value, "resumeVip");
            return (Criteria) this;
        }

        public Criteria andResumeVipLessThan(Integer value) {
            addCriterion("resume_vip <", value, "resumeVip");
            return (Criteria) this;
        }

        public Criteria andResumeVipLessThanOrEqualTo(Integer value) {
            addCriterion("resume_vip <=", value, "resumeVip");
            return (Criteria) this;
        }

        public Criteria andResumeVipIn(List<Integer> values) {
            addCriterion("resume_vip in", values, "resumeVip");
            return (Criteria) this;
        }

        public Criteria andResumeVipNotIn(List<Integer> values) {
            addCriterion("resume_vip not in", values, "resumeVip");
            return (Criteria) this;
        }

        public Criteria andResumeVipBetween(Integer value1, Integer value2) {
            addCriterion("resume_vip between", value1, value2, "resumeVip");
            return (Criteria) this;
        }

        public Criteria andResumeVipNotBetween(Integer value1, Integer value2) {
            addCriterion("resume_vip not between", value1, value2, "resumeVip");
            return (Criteria) this;
        }

        public Criteria andVipStartTimeIsNull() {
            addCriterion("vip_start_time is null");
            return (Criteria) this;
        }

        public Criteria andVipStartTimeIsNotNull() {
            addCriterion("vip_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andVipStartTimeEqualTo(Date value) {
            addCriterion("vip_start_time =", value, "vipStartTime");
            return (Criteria) this;
        }

        public Criteria andVipStartTimeNotEqualTo(Date value) {
            addCriterion("vip_start_time <>", value, "vipStartTime");
            return (Criteria) this;
        }

        public Criteria andVipStartTimeGreaterThan(Date value) {
            addCriterion("vip_start_time >", value, "vipStartTime");
            return (Criteria) this;
        }

        public Criteria andVipStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("vip_start_time >=", value, "vipStartTime");
            return (Criteria) this;
        }

        public Criteria andVipStartTimeLessThan(Date value) {
            addCriterion("vip_start_time <", value, "vipStartTime");
            return (Criteria) this;
        }

        public Criteria andVipStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("vip_start_time <=", value, "vipStartTime");
            return (Criteria) this;
        }

        public Criteria andVipStartTimeIn(List<Date> values) {
            addCriterion("vip_start_time in", values, "vipStartTime");
            return (Criteria) this;
        }

        public Criteria andVipStartTimeNotIn(List<Date> values) {
            addCriterion("vip_start_time not in", values, "vipStartTime");
            return (Criteria) this;
        }

        public Criteria andVipStartTimeBetween(Date value1, Date value2) {
            addCriterion("vip_start_time between", value1, value2, "vipStartTime");
            return (Criteria) this;
        }

        public Criteria andVipStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("vip_start_time not between", value1, value2, "vipStartTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeIsNull() {
            addCriterion("vip_end_time is null");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeIsNotNull() {
            addCriterion("vip_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeEqualTo(Date value) {
            addCriterion("vip_end_time =", value, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeNotEqualTo(Date value) {
            addCriterion("vip_end_time <>", value, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeGreaterThan(Date value) {
            addCriterion("vip_end_time >", value, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("vip_end_time >=", value, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeLessThan(Date value) {
            addCriterion("vip_end_time <", value, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("vip_end_time <=", value, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeIn(List<Date> values) {
            addCriterion("vip_end_time in", values, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeNotIn(List<Date> values) {
            addCriterion("vip_end_time not in", values, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeBetween(Date value1, Date value2) {
            addCriterion("vip_end_time between", value1, value2, "vipEndTime");
            return (Criteria) this;
        }

        public Criteria andVipEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("vip_end_time not between", value1, value2, "vipEndTime");
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