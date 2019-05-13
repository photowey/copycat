package com.photowey.copycat.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * User
 *
 * @author WcJun
 * @date 2019/05/13
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -172596476409374927L;
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Date birthDay;
}
