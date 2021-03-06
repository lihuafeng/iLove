package com.love.ilove.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.love.ilove.enums.RoleStatus;
import lombok.Data;

/**
 * @author: Jerry
 * @Date: 2019-04-18 17:45
 */
@Data
@TableName
public class Role {
    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    @TableField("status")
    RoleStatus roleStatus;
}
