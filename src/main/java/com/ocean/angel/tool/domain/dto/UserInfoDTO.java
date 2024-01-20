package com.ocean.angel.tool.domain.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author Jaime.yu
 * @since 2024-01-18
 */
@Data
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 昵称
     */
    private String nick;

}
