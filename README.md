# Copycat
基于Mybatis-Plus,通过自定义条件注解,实现自动包装QueryWrapper查询对象

一、需求描述
* 1.使用`QueryWrapper`对较少条件进行查询,如下:
```java
// 官方 Github Demo
List<User> userList = userMapper.selectList(
        new QueryWrapper<User>()
                .lambda()
                .ge(User::getAge, 18)
);
```
* 2.往往在真实情况下,前端一般有很多的查询条件传入后台,此时不得不手动的去包装我们的查询条件,如下:
```java
List<User> userList = this.userService.list(
    new QueryWrapper<User>()
            .lambda()
            .ge(User::getAge, 18)
            .likeLeft(User::getName, "copycat")
            .eq(User::getEmail, "capycat@baomidou.com")
            ...
);
```
二、Mybatis-Plus 的版本
```xml
<properties>
    <mybatis-plus.version>3.1.0</mybatis-plus.version>
</properties>
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>${mybatis-plus.version}</version>
</dependency>
```

# `CopyCat`基于自定义注解通过反射来实现自动包装查询条件,如下:
### 自定义查询对象(XxxQuery)
```java
/**
 * UserQuery
 *
 * @author WcJun
 * @date 2019/05/13
 * {@link com.photowey.copycat.criteria.query.AbstractQuery}
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserQuery extends AbstractQuery<User> implements Serializable {

    private static final long serialVersionUID = -4887537311252782223L;

    /**
     * 用户标识
     * 自动给 QueryWrapper 添加如下的语句:
     * queryWrapper.eq(null != value, columnName, value);
     */
    @Eq(alias = "id")
    private Long userId;
}
```
AbstractQuery ({@link com.photowey.copycat.criteria.query.AbstractQuery})
```java
/**
 * 抽象查询对象
 *
 * @author WcJun
 * @date 2019/05/12
 */
public abstract class AbstractQuery<T> extends PaginationQuery {

    // WRAPPER

    /**
     * 通过注解自动包装查询 Wrapper
     *
     * @return
     * @see {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    public QueryWrapper<T> autoWrapper() {
        return CriteriaAnnotationProcessorAdvisor.advise(this, new QueryWrapper<T>());
    }

    // PAGE

    /**
     * 如果需要分页的话
     * 获取分页对象
     *
     * @return IPage
     * @see {@link com.baomidou.mybatisplus.core.metadata.IPage}
     */
    public IPage populatePage() {
        return new Page(this.pageNo, this.pageSize);
    }
}
```

分页参数

```java
/**
 * 分页参数
 *
 * @author WcJun
 * @date 2019/05/12
 */
public abstract class PaginationQuery {

    /**
     * 是否采用分页
     */
    protected Boolean pageEnabled = Boolean.FALSE;

    // ================================================

    /**
     * 当前页(从自然页 1 开始)
     */
    protected Integer pageNo = 1;
    /**
     * 每页展示数量 默认 20
     */
    protected Integer pageSize = 20;

    // ================================================

    public Integer getPageNo() {
        return null == pageNo ? 1 : pageNo;
    }

    public Integer getPageSize() {
        return null == pageSize ? 10 : pageSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

```

###  使用
```java
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
```
测试时间  
```java
    /**
     * 测试 Timestamp
     * {@link com.photowey.copycat.criteria.annotaion.Timestamp}
     */
    @Test
    public void testQueryByCriteriaTimestampGe() throws ParseException {
        String birthDayStr = "2019-05-12 10:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date birthDay = format.parse(birthDayStr);
        UserQuery userQuery = new UserQuery().setAge(20).setBirthDayGe(birthDay);
        List<User> users = this.userService.list(userQuery.autoWrapper());
        Assert.assertEquals(3, users.size());
        users.forEach(System.out::println);
    }
```
详见:{@link com.photowey.copycat.service.UserServiceTest}  

## 单元测试还未写完 后续补上,里面还有一些 bug 和很多需要优化的地方...
## 欢迎 发送PR
## 待续...