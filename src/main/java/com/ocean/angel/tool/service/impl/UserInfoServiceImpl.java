package com.ocean.angel.tool.service.impl;

import com.ocean.angel.tool.domain.entity.UserInfo;
import com.ocean.angel.tool.mapper.UserInfoMapper;
import com.ocean.angel.tool.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author Jaime.yu
 * @since 2024-01-18
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
