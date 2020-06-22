package com.github.code13.springbootdatasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.code13.springbootdatasource.entity.YunUser;
import com.github.code13.springbootdatasource.mapper.YunUserMapper;
import com.github.code13.springbootdatasource.service.YunUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 云客应用管理员表 服务实现类
 * </p>
 *
 * @author code13
 * @since 2020-02-15
 */
@DS("tjyun_local")
//@DS("tjyun_dev")
//@DS("tjyun_uat")
@Service
public class YunUserServiceImpl extends ServiceImpl<YunUserMapper, YunUser> implements YunUserService {

}
