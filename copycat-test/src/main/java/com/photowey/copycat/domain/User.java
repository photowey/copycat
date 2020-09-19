package com.photowey.copycat.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User
 *
 * @author WcJun
 * @date 2019/05/13
 */
public class User implements Serializable {

    private static final long serialVersionUID = -172596476409374927L;
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Date birthDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
