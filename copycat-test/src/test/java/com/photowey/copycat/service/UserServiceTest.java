package com.photowey.copycat.service;

import com.photowey.copycat.domain.User;
import com.photowey.copycat.mapper.UserMapper;
import com.photowey.copycat.query.UserQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * UserServiceTest
 *
 * @Created by WcJun
 * @date 2019/05/13
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 官方 Demo
     */
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    /**
     * 测试 EQ
     * {@link com.photowey.copycat.criteria.annotaion.Eq}
     */
    @Test
    public void testQueryByCriteriaEq() {
        UserQuery userQuery = new UserQuery().setUserId(1L);
        User user = this.userService.getOne(userQuery.autoWrapper());
        Assert.assertEquals("查询错误", "Jone", user.getName());
    }

    /**
     * 测试 GE
     * {@link com.photowey.copycat.criteria.annotaion.Eq}
     */
    @Test
    public void testQueryByCriteriaGe() {
        UserQuery userQuery = new UserQuery().setAge(20);
        List<User> users = this.userService.list(userQuery.autoWrapper());
        Assert.assertEquals(4, users.size());
        users.forEach(System.out::println);
    }

    /**
     * 测试 Timestamp
     * {@link com.photowey.copycat.criteria.annotaion.Timestamp}
     */
    @Test
    public void testQueryByCriteriaTimestampGe() throws ParseException {
        String birthDayStr = "2019-05-11 10:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date birthDay = format.parse(birthDayStr);
        UserQuery userQuery = new UserQuery().setAge(20).setBirthDayGe(birthDay);
        List<User> users = this.userService.list(userQuery.autoWrapper());
        Assert.assertEquals(3, users.size());
        users.forEach(System.out::println);
    }

    /**
     * 测试 Timestamp
     * {@link com.photowey.copycat.criteria.annotaion.Timestamp}
     */
    @Test
    public void testQueryByCriteriaTimestampGt() throws ParseException {
        String birthDayStr = "2019-05-11 10:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date birthDay = format.parse(birthDayStr);
        UserQuery userQuery = new UserQuery().setAge(20).setBirthDay(birthDay);
        List<User> users = this.userService.list(userQuery.autoWrapper());
        Assert.assertEquals(2, users.size());
        users.forEach(System.out::println);
    }

    /**
     * 测试 Timestamp
     * {@link com.photowey.copycat.criteria.annotaion.Timestamp}
     */
    @Test
    public void testQueryByCriteriaTimestampLong() {
        UserQuery userQuery = new UserQuery().setAge(20).setBirthDayTimestamp(1557540000000L);
        List<User> users = this.userService.list(userQuery.autoWrapper());
        Assert.assertEquals(3, users.size());
        users.forEach(System.out::println);
    }

    /**
     * 测试 Like
     * {@link com.photowey.copycat.criteria.annotaion.Like}
     */
    @Test
    public void testQueryByCriteriaLike() {
        UserQuery userQuery = new UserQuery().setUserName("J");
        List<User> users = this.userService.list(userQuery.autoWrapper());
        Assert.assertEquals(2, users.size());
        users.forEach(System.out::println);
    }

    /**
     * 测试 NotLike
     * {@link com.photowey.copycat.criteria.annotaion.NotLike}
     */
    @Test
    public void testQueryByCriteriaNotLike() {
        UserQuery userQuery = new UserQuery().setUserNameNot("J");
        List<User> users = this.userService.list(userQuery.autoWrapper());
        Assert.assertEquals(3, users.size());
        users.forEach(System.out::println);
    }

    /**
     * 测试 In
     * {@link com.photowey.copycat.criteria.annotaion.In}
     */
    @Test
    public void testQueryByCriteriaIn() {
        List<Integer> ageIn = new ArrayList<>();
        ageIn.add(20);
        ageIn.add(21);
        UserQuery userQuery = new UserQuery().setAgeIn(ageIn);
        List<User> users = this.userService.list(userQuery.autoWrapper());
        Assert.assertEquals(2, users.size());
        users.forEach(System.out::println);
    }

    /**
     * 测试 NotIn
     * {@link com.photowey.copycat.criteria.annotaion.NotIn}
     */
    @Test
    public void testQueryByCriteriaNotIn() {
        List<Integer> ageIn = new ArrayList<>();
        ageIn.add(20);
        ageIn.add(21);
        UserQuery userQuery = new UserQuery().setAgeNotIn(ageIn);
        List<User> users = this.userService.list(userQuery.autoWrapper());
        Assert.assertEquals(3, users.size());
        users.forEach(System.out::println);
    }

    /**
     * 测试 OrderBy
     * {@link com.photowey.copycat.criteria.annotaion.OrderBy}
     */
    @Test
    public void testQueryByCriteriaOrderByDynamic() {
        List<Integer> ageIn = new ArrayList<>();
        ageIn.add(20);
        ageIn.add(21);
        UserQuery userQuery = new UserQuery().setAgeNotIn(ageIn).setAgeOrderBY(1);
        List<User> users = this.userService.list(userQuery.autoWrapper());
        Assert.assertEquals(3, users.size());
        users.forEach(System.out::println);
    }

    /**
     * 测试 OrderBy
     * {@link com.photowey.copycat.criteria.annotaion.OrderBy}
     * 即使 ageOrderBYStatic 属性 没有设置 值 也将会参与 排序
     * 即使 age: 属性没有填充值 - 依然会参与排序
     *
     * Execute SQL：
     *     SELECT
     *         id,
     *         name,
     *         age,
     *         email,
     *         birth_day
     *     FROM
     *         user
     *     WHERE
     *         age NOT IN (
     *             20,21
     *         )
     *     ORDER BY
     *         age DESC
     *
     * @since 1.1.0
     */
    @Test
    public void testQueryByCriteriaOrderByStatic() {
        List<Integer> ageIn = new ArrayList<>();
        ageIn.add(20);
        ageIn.add(21);
        UserQuery userQuery = new UserQuery().setAgeNotIn(ageIn);
        List<User> users = this.userService.list(userQuery.autoWrapper());
        Assert.assertEquals(3, users.size());
        users.forEach(System.out::println);
    }
}