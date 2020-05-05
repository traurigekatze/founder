package com.kerry.founder.mixed.domain;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/5
 * **********学海无涯苦作舟**********
 */
public class MixedUser {

    private Integer id;

    private String name;

    private String birthday;

    private String sex;

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public MixedUser(Integer id, String name, String birthday, String sex) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "MixedUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
