package com.photowey.copycat.query;

import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.photowey.copycat.criteria.annotaion.*;
import com.photowey.copycat.criteria.enums.CompareEnum;
import com.photowey.copycat.criteria.enums.HandleOrderByEnum;
import com.photowey.copycat.criteria.enums.OrderByEnum;
import com.photowey.copycat.criteria.query.AbstractQuery;
import com.photowey.copycat.domain.User;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Like(alias = "name", like = SqlLike.RIGHT)
    private String userName;

    @NotLike(alias = "name")
    private String userNameNot;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserQuery userQuery = (UserQuery) o;
        return Objects.equals(userId, userQuery.userId) &&
                Objects.equals(age, userQuery.age) &&
                Objects.equals(ageIn, userQuery.ageIn) &&
                Objects.equals(ageNotIn, userQuery.ageNotIn) &&
                Objects.equals(ageOrderBY, userQuery.ageOrderBY) &&
                Objects.equals(ageOrderBYStatic, userQuery.ageOrderBYStatic) &&
                Objects.equals(birthDayGe, userQuery.birthDayGe) &&
                Objects.equals(birthDay, userQuery.birthDay) &&
                Objects.equals(birthDayTimestamp, userQuery.birthDayTimestamp) &&
                Objects.equals(userName, userQuery.userName) &&
                Objects.equals(userNameNot, userQuery.userNameNot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, age, ageIn, ageNotIn, ageOrderBY, ageOrderBYStatic, birthDayGe, birthDay, birthDayTimestamp, userName, userNameNot);
    }

    public String toString() {
        return "UserQuery(userId=" + this.getUserId() + ", age=" + this.getAge() + ", ageIn=" + this.getAgeIn() + ", ageNotIn=" + this.getAgeNotIn() + ", ageOrderBY=" + this.getAgeOrderBY() + ", ageOrderBYStatic=" + this.getAgeOrderBYStatic() + ", birthDayGe=" + this.getBirthDayGe() + ", birthDay=" + this.getBirthDay() + ", birthDayTimestamp=" + this.getBirthDayTimestamp() + ", userName=" + this.getUserName() + ", userNameNot=" + this.getUserNameNot() + ")";
    }

    public UserQuery() {
    }

    public UserQuery(Long userId, Integer age, List<Integer> ageIn, List<Integer> ageNotIn, Integer ageOrderBY, Integer ageOrderBYStatic, Date birthDayGe, Date birthDay, Long birthDayTimestamp, String userName, String userNameNot) {
        this.userId = userId;
        this.age = age;
        this.ageIn = ageIn;
        this.ageNotIn = ageNotIn;
        this.ageOrderBY = ageOrderBY;
        this.ageOrderBYStatic = ageOrderBYStatic;
        this.birthDayGe = birthDayGe;
        this.birthDay = birthDay;
        this.birthDayTimestamp = birthDayTimestamp;
        this.userName = userName;
        this.userNameNot = userNameNot;
    }
}
