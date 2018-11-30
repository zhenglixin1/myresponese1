package com.itxin.domain;

import java.io.Serializable;
import java.util.Date;

public class student implements Serializable {
    private Integer id;
    private String stuCode;
    private String name;
    private Integer age;
    private Date birthday;
    private  String sex;

    public student() {
    }

    public student(Integer id, String stuCode, String name, Integer age, Date birthday, String sex) {
        this.id = id;
        this.stuCode = stuCode;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", stuCode='" + stuCode + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuCode() {
        return stuCode;
    }

    public void setStuCode(String stuCode) {
        this.stuCode = stuCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
