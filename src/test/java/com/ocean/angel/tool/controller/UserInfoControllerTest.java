package com.ocean.angel.tool.controller;

import com.ocean.angel.tool.common.ResultBean;
import com.ocean.angel.tool.domain.dto.UserInfoDTO;
import com.ocean.angel.tool.domain.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class UserInfoControllerTest {

    @Resource
    private UserInfoController userInfoController;

    @Test
    void save() {
        UserInfoDTO dto = new UserInfoDTO();
        dto.setIdCard("3421202199107173538");
        dto.setUsername("Jaime.yu");
        dto.setNick("君莫笑");
        dto.setMobile("15755141030");
        dto.setPwd("qwer1234");
        ResultBean<?> result = userInfoController.save(dto);
        log.info("{}", result.toString());
    }

    @Test
    void get() {
        Integer id = 1;
        ResultBean<UserInfo> result = userInfoController.get(id);
        log.info("{}", result.toString());
    }
}
