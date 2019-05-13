package com.photowey.copycat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.photowey.copycat.domain.User;
import com.photowey.copycat.mapper.UserMapper;
import com.photowey.copycat.service.UserService;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author WcJun
 * @date 2019/05/13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
