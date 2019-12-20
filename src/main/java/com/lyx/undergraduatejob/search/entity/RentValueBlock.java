package com.lyx.undergraduatejob.search.entity;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * @createTime 2019.12.17.22:37
 */
public class RentValueBlock{
    public static final Map<String, RentValueBlock> salaryArea;
    public static final Map<String, RentValueBlock> ageArea;
    public static final RentValueBlock ALL = new RentValueBlock("*",-1,-1);

    /**
     * 初始化
     */
    static {
        salaryArea = ImmutableMap.<String,RentValueBlock>builder()
                .put("* - 1000",new RentValueBlock("* - 1000",-1,1000))
                .put("1000 - 3000",new RentValueBlock("1000 - 3000",1000,3000))
                .put("3000 - 5000",new RentValueBlock("3000 - 5000",3000,5000))
                .put("5000 - *",new RentValueBlock("5000 - *",5000,-1))
                .put("*",ALL).build();

        ageArea = ImmutableMap.<String,RentValueBlock>builder()
                .put("16 - 20",new RentValueBlock("16 - 20",16,20))
                .put("21 - 30",new RentValueBlock("21 - 30",21,31))
                .put("31 - 40",new RentValueBlock("31 - 40",31,40))
                .put("41 - 50",new RentValueBlock("41 - 50",41,50))
                .put("50 - *",new RentValueBlock("50 - *",50,-1))
                .put("*",ALL).build();
    }

    public RentValueBlock(String key, int min, int max) {
        this.key = key;
        this.max = max;
        this.min = min;
    }

    private String key;
    private int max;
    private int min;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
    public static RentValueBlock getRentValueBlock(String key){
        RentValueBlock res = salaryArea.get(key);
        return res == null ? ALL : res;
    }

    public static RentValueBlock getAgeArea(String key){
        RentValueBlock res = ageArea.get(key);
        return res == null ? ALL : res;
    }
}
