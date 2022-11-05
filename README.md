# Mybatis-Plus 

> 新项目地址:
>
> [mybatis-plus-ext](https://github.com/photowey/mybatis-plus-ext)
>
> https://github.com/photowey/mybatis-plus-ext
>
> - 发布 `v1.0.0`
> - 开发 `v1.1.0`

# 单表查询扩展(Copycat)
> 基于 `Mybatis-Plus` , 通过自定义条件注解,实现自动包装 `QueryWrapper `查询对象


## v1.0.0
- 实现了对 Mybatis-plus 单表查询的扩展
-- -
## v1.1.0
- 升级 Mybatis-Plus 版本
- 升级 Spring 版本
- 优化包引用
    * mybatis-plus-core
    * mybatis-plus-extension    
    * spring-core    
    * slf4j-api
- 优化 OrderBy 注解
    * 新增 一个 OrderBy 处理类型 {@link com.photowey.copycat.criteria.enums.HandleOrderByEnum}  
    来区分是静态的排序, 还是根据前端传入的字段动态排序  
    {@link com.photowey.copycat.service.UserServiceTest#testQueryByCriteriaOrderByStatic}
- 时间查询(@TimeStamp) 
    * 支持 {@link java.time.LocalDateTime}  
    * 依然默认采用 {@link java.util.Date}
-- -
## v1.2.0
- 时间查询(@TimeStamp) 
    * 支持 {@link java.time.LocalDate}  
    * 支持 {@link java.time.LocalTime}  
    * 支持 {@link java.time.ZonedDateTime}  
    * 依然默认采用 {@link java.util.Date}
-- -

## v1.3.0

- 增加 ShutdownHook 手动释放 Processor 缓存
  * 添加 {@link com.photowey.copycat.criteria.hook.CopycatShutdownHook}

-- -

## v1.3.1

- 合并-PR
- 增加 License 文件头
- 更新 test 模块

## v1.3.2

- 新增 `Select` 注解(静态属性)
- 新增 `DynacmicSelect` 注解

-- -

## 一、需求描述

* 1.1.使用`QueryWrapper`对较少条件进行查询,如下:

```java
List<User> userList=userMapper.selectList(
        new QueryWrapper<User>()
        .lambda()
        .ge(User::getAge,18)
        );
```
* 1.2.往往在真实情况下,前端一般有很多的查询条件传入后台,此时不得不手动的去包装我们的查询条件,如下:
```java
List<User> userList = this.userService.list(
    new QueryWrapper<User>()
            .lambda()
            .ge(User::getAge, 18)
            .likeLeft(User::getName, "ext")
            .eq(User::getEmail, "ext@baomidou.com")
            ...
);
```



## 二、轻量级 引用

```xml
<properties>
    <mybatis-plus.version>3.4.0</mybatis-plus.version>
    <spring-core.version>5.2.8.RELEASE</spring-core.version>
    <slf4j-log.version>1.7.25</slf4j-log.version>
</properties>

<!-- mybatis-plus -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-core</artifactId>
    <version>${mybatis-plus.version}</version>
</dependency>
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-extension</artifactId>
    <version>${mybatis-plus.version}</version>
</dependency>

<!-- spring-core -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>${spring-core.version}</version>
</dependency>

<!-- log -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>${slf4j-log.version}</version>
</dependency>
```



## 三、实现原理

#### 	3.1.基于自定义注解通过反射来实现



## 四、查询使用

### 	4.1.自定义查询对象(`XxxQuery`) 

> 继承 内置的 `AbstractQuery`

```java
/**
 * XxxQuery
 *
 * @author WcJun
 * @date 2019/05/13
 * {@link com.photowey.copycat.criteria.query.AbstractQuery}
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class XxxQuery extends AbstractQuery<User> implements Serializable {

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


### 4.2.内置抽象的 `AbstractQuery`

> {@link com.photowey.copycat.criteria.query.AbstractQuery}
>
> **AbstractQuery**

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



### 4.3.分页参数(PaginationQuery)
> {@link com.photowey.copycat.criteria.query.PaginationQuery}

```java
/**
 * 分页参数
 *
 * @author WcJun
 * @date 2019/05/12
 */
public abstract class PaginationQuery {

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

        if (null != pageNo && pageNo < 1) {
            return 1;
        }

        return null == pageNo ? 1 : pageNo;
    }

    public Integer getPageSize() {

        if (null != pageSize && pageSize < 1) {
            return 1;
        }

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




## 五、测试

#### 5.1.测试等于(@Eq)

```java
/**
 * 测试 @Eq
 * {@link com.photowey.copycat.criteria.annotaion.Eq}
 */
@Test
public void testQueryByEq() {
    UserQuery userQuery = new UserQuery().setUserId(1L);
    User user = this.userService.getOne(userQuery.autoWrapper());
    Assert.assertEquals("查询错误", "Jone", user.getName());
}
```



#### 5.2.测试时间(@Timestamp)

> 一些场景下，前端时间传值，可能是采用的是时间戳 - 因此 就有了 @Timestamp 的使用场景

```java
/**
 * 测试 Timestamp
 * {@link com.photowey.copycat.criteria.annotaion.Timestamp}
 * {@link java.time.LocalDateTime}
 * ==>  Preparing: SELECT id,name,age,email,birth_day FROM user WHERE (age >= ? AND birth_day > ?) ORDER BY age DESC
 * ==> Parameters: 20(Integer), 2019-05-11T10:00(LocalDateTime)
 *
 * @since 1.1.0
 */
@Test
public void testQueryByTimestampGe() throws ParseException {
    String birthDayStr = "2019-05-12 10:00:00";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date birthDay = format.parse(birthDayStr);
    // 模拟前端传参
    UserQuery userQuery = new UserQuery().setAge(20).setBirthDayGe(birthDay);
    
    List<User> users = this.userService.list(userQuery.autoWrapper());
    Assert.assertEquals(3, users.size());
    users.forEach(System.out::println);
}

/**
 * 测试 Timestamp
 * {@link com.photowey.copycat.criteria.annotaion.Timestamp}
 * {@link java.time.LocalDate}
 * ==>  Preparing: SELECT id,name,age,email,birth_day FROM user WHERE (age >= ? AND birth_day > ?) ORDER BY age DESC
 * ==> Parameters: 20(Integer), 2019-05-11(LocalDate)
 *
 * @since 1.2.0
 */
@Test
public void testQueryByCriteriaTimestampByLocalDate() {
    UserQuery userQuery = new UserQuery().setAge(20).setBirthDayTimestampLocalDate(1557540000000L);
    List<User> users = this.userService.list(userQuery.autoWrapper());
    users.forEach(System.out::println);
}

/**
 * 测试 Timestamp
 * {@link com.photowey.copycat.criteria.annotaion.Timestamp}
 * {@link java.time.LocalTime}
 * ==>  Preparing: SELECT id,name,age,email,birth_day FROM user WHERE (age >= ? AND birth_day > ?) ORDER BY age DESC
 * ==> Parameters: 20(Integer), 10:00(LocalTime)
 *
 * @since 1.2.0
 */
@Test
public void testQueryByCriteriaTimestampByLocalTime() {
    UserQuery userQuery = new UserQuery().setAge(20).setBirthDayTimestampLocalTime(1557540000000L);
    List<User> users = this.userService.list(userQuery.autoWrapper());
    users.forEach(System.out::println);
}

/**
 * 测试 Timestamp
 * {@link com.photowey.copycat.criteria.annotaion.Timestamp}
 * {@link java.time.ZonedDateTime}
 * ==>  Preparing: SELECT id,name,age,email,birth_day FROM user WHERE (age >= ? AND birth_day > ?) ORDER BY age DESC
 * ==> Parameters: 20(Integer), 2019-05-11T10:00+08:00[Asia/Shanghai](ZonedDateTime)
 *
 * @since 1.2.0
 */
@Test
public void testQueryByCriteriaTimestampByZonedDateTime() {
    UserQuery userQuery = new UserQuery().setAge(20).setBirthDayTimestampZonedDateTime(1557540000000L);
    List<User> users = this.userService.list(userQuery.autoWrapper());
    users.forEach(System.out::println);
}
```
### 具体见 : 测试类(UserServiceTest) {@link com.photowey.copycat.service.UserServiceTest} 
## 欢迎 发送PR