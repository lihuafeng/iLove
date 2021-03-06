package com.love.ilove.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author dengtianjiao
 */
@Data
@TableName
@NoArgsConstructor
public class User implements UserDetails {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    @TableField(exist = false)
    private String oldPwd;

    private boolean enabled = true;

    private boolean accountNonLocked = true;

    private boolean accountNonExpired = true;

    @TableField(exist = false)
    private List<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles!=null){
            return roles.stream().collect(() -> new ArrayList<SimpleGrantedAuthority>(),
                    (list, role) -> list.add(new SimpleGrantedAuthority("ROLE_"+role)),
                    (list1, list2) -> list1.addAll(list2));
        }
        return null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
