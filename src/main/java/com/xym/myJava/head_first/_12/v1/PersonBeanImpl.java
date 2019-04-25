package com.xym.myJava.head_first._12.v1;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-17 17:13
 */
public class PersonBeanImpl implements PersonBean {
    /**
     * 人名
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 兴趣
     */
    private String interests;
    /**
     * 评分
     */
    private int rating;
    /**
     * 评分人数
     */
    private int ratingCount = 0;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public String getInterests() {
        return interests;
    }

    @Override
    public int getHotOrNotRating() {
        if (ratingCount == 0) {
            return 0;
        }
        return rating / ratingCount;

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void setInterests(String interests) {
        this.interests = interests;
    }

    @Override
    public void setHotOrNotRating(int rating) {
        this.rating = this.rating + rating;
        ratingCount++;
    }


}
