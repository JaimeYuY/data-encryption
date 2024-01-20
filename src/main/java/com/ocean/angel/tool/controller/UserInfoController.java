package com.ocean.angel.tool.controller;

import cn.hutool.core.bean.BeanUtil;
import com.ocean.angel.tool.common.ResultBean;
import com.ocean.angel.tool.domain.dto.UserInfoDTO;
import com.ocean.angel.tool.domain.entity.UserInfo;
import com.ocean.angel.tool.service.UserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author Jaime.yu
 * @since 2024-01-18
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("save")
    public ResultBean<?> save(UserInfoDTO dto) {
        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(dto, userInfo);
        userInfo.setCreateTime(new Date());

        userInfoService.save(userInfo);
        return ResultBean.success();
    }

    @GetMapping("get")
    public ResultBean<UserInfo> get(Integer id) {
        UserInfo userInfo = userInfoService.getById(id);
        return ResultBean.success(userInfo);
    }
}

