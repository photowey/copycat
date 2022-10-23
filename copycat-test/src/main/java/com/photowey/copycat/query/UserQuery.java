/*
 * Copyright © 2019 photowey (photowey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.photowey.copycat.query;

import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.photowey.copycat.criteria.annotaion.*;
import com.photowey.copycat.criteria.enums.CompareEnum;
import com.photowey.copycat.criteria.enums.HandleOrderByEnum;
import com.photowey.copycat.criteria.enums.OrderByEnum;
import com.photowey.copycat.criteria.query.AbstractQuery;
import com.photowey.copycat.domain.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * UserQuery
 *
 * @author WcJun
 * @date 2019/05/13
 * @see
 */
public class UserQuery extends AbstractQuery<User> implements Serializable {

    private static final long serialVersionUID = -4887537311252782223L;

    /**
     * 用户标识
     */
    @Eq(alias = "id")
    private Long userId;
    /**
     * 年龄
     */
    @Ge(alias = "age")
    private Integer age;

    /**
     * 年龄
     */
    @In(alias = "age")
    private List<Integer> ageIn;

    /**
     * 年龄
     */
    @NotIn(alias = "age")
    private List<Integer> ageNotIn;
    /**
     * 默认采用 动态排序
     *
     * @since 1.0.0
     */
    @OrderBy(alias = "age", orderBy = OrderByEnum.DESC, handleType = HandleOrderByEnum.DYNAMIC)
    private Integer ageOrderBY;

    /**
     * 默认采用 静态排序
     *
     * @since 1.1.0
     */
    @OrderBy(alias = "age", orderBy = OrderByEnum.DESC, handleType = HandleOrderByEnum.STATIC)
    private Integer ageOrderBYStatic;

    /**
     * 生日
     */
    @Ge(alias = "birth_day")
    private Date birthDayGe;

    /**
     * 生日
     */
    @Gt(alias = "birth_day")
    private Date birthDay;

    /**
     * 生日
     */
    @Timestamp(alias = "birth_day", compare = CompareEnum.GT, clazz = Date.class)
    private Long birthDayTimestamp;

    /**
     * 生日
     */
    @Timestamp(alias = "birth_day", compare = CompareEnum.GT, clazz = LocalDateTime.class)
    private Long birthDayTimestampLocalDateTime;

    /**
     * 生日
     */
    @Timestamp(alias = "birth_day", compare = CompareEnum.GT, clazz = LocalDate.class)
    private Long birthDayTimestampLocalDate;

    /**
     * 生日
     */
    @Timestamp(alias = "birth_day", compare = CompareEnum.GT, clazz = LocalTime.class)
    private Long birthDayTimestampLocalTime;

    /**
     * 生日
     */
    @Timestamp(alias = "birth_day", compare = CompareEnum.GT, clazz = ZonedDateTime.class)
    private Long birthDayTimestampZonedDateTime;

    @Like(alias = "name", like = SqlLike.RIGHT)
    private String userName;

    @NotLike(alias = "name")
    private String userNameNot;

    @Select(value = {"id", "name"})
    private String fields;

    public Long getUserId() {
        return this.userId;
    }

    public Integer getAge() {
        return this.age;
    }

    public List<Integer> getAgeIn() {
        return this.ageIn;
    }

    public List<Integer> getAgeNotIn() {
        return this.ageNotIn;
    }

    public Integer getAgeOrderBY() {
        return this.ageOrderBY;
    }

    public Integer getAgeOrderBYStatic() {
        return this.ageOrderBYStatic;
    }

    public Date getBirthDayGe() {
        return this.birthDayGe;
    }

    public Date getBirthDay() {
        return this.birthDay;
    }

    public Long getBirthDayTimestamp() {
        return this.birthDayTimestamp;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserNameNot() {
        return this.userNameNot;
    }

    public UserQuery setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserQuery setAge(Integer age) {
        this.age = age;
        return this;
    }

    public UserQuery setAgeIn(List<Integer> ageIn) {
        this.ageIn = ageIn;
        return this;
    }

    public UserQuery setAgeNotIn(List<Integer> ageNotIn) {
        this.ageNotIn = ageNotIn;
        return this;
    }

    public UserQuery setAgeOrderBY(Integer ageOrderBY) {
        this.ageOrderBY = ageOrderBY;
        return this;
    }

    public UserQuery setAgeOrderBYStatic(Integer ageOrderBYStatic) {
        this.ageOrderBYStatic = ageOrderBYStatic;
        return this;
    }

    public UserQuery setBirthDayGe(Date birthDayGe) {
        this.birthDayGe = birthDayGe;
        return this;
    }

    public UserQuery setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    public UserQuery setBirthDayTimestamp(Long birthDayTimestamp) {
        this.birthDayTimestamp = birthDayTimestamp;
        return this;
    }

    public UserQuery setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserQuery setUserNameNot(String userNameNot) {
        this.userNameNot = userNameNot;
        return this;
    }

    public Long getBirthDayTimestampLocalDateTime() {
        return birthDayTimestampLocalDateTime;
    }

    public UserQuery setBirthDayTimestampLocalDateTime(Long birthDayTimestampLocalDateTime) {
        this.birthDayTimestampLocalDateTime = birthDayTimestampLocalDateTime;
        return this;
    }

    public Long getBirthDayTimestampLocalDate() {
        return birthDayTimestampLocalDate;
    }

    public UserQuery setBirthDayTimestampLocalDate(Long birthDayTimestampLocalDate) {
        this.birthDayTimestampLocalDate = birthDayTimestampLocalDate;
        return this;
    }

    public Long getBirthDayTimestampLocalTime() {
        return birthDayTimestampLocalTime;
    }

    public UserQuery setBirthDayTimestampLocalTime(Long birthDayTimestampLocalTime) {
        this.birthDayTimestampLocalTime = birthDayTimestampLocalTime;
        return this;
    }

    public Long getBirthDayTimestampZonedDateTime() {
        return birthDayTimestampZonedDateTime;
    }

    public UserQuery setBirthDayTimestampZonedDateTime(Long birthDayTimestampZonedDateTime) {
        this.birthDayTimestampZonedDateTime = birthDayTimestampZonedDateTime;
        return this;
    }

    public String getFields() {
        return fields;
    }

    public UserQuery setFields(String fields) {
        this.fields = fields;

        return this;
    }
}

