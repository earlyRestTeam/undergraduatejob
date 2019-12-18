package com.lyx.undergraduatejob.search.entity;

/**
 * @createTime 2019.12.18.19:27
 */
public class JobListSearchEntity {
    private String keyWord;
    private Integer industriesId;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getIndustriesId() {
        return industriesId;
    }

    public void setIndustriesId(Integer industriesId) {
        this.industriesId = industriesId;
    }
}
