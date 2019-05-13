package com.photowey.copycat.query;

import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.photowey.copycat.criteria.annotaion.*;
import com.photowey.copycat.criteria.enums.CompareEnum;
import com.photowey.copycat.criteria.enums.OrderByEnum;
import com.photowey.copycat.criteria.query.AbstractQuery;
import com.photowey.copycat.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * UserQuery
 * @author WcJun
 * @date 2019/05/13
 * @see
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserQuery extends AbstractQuery<User> implements Serializable {

    private static final long serialVersionUID = -4887537311252782223L;

    /**
     * 用户标识
     *
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

    @OrderBy(alias = "age", orderBy = OrderByEnum.DESC)
    private Integer ageOrderBY;
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
}
