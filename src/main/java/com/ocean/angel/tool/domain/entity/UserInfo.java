package com.ocean.angel.tool.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.ocean.angel.tool.annotation.SensitiveBean;
import com.ocean.angel.tool.annotation.SensitiveField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author Jaime.yu
 * @since 2024-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user_info")
@SensitiveBean
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @SensitiveField
    private String pwd;

    /**
     * 手机号
     */
    @SensitiveField
    private String mobile;

    /**
     * 身份证号
     */
    @SensitiveField
    private String idCard;

    /**
     * 昵称
     */
    private String nick;

    /**
     * 创建时间
     */
    private Date createTime;

}
