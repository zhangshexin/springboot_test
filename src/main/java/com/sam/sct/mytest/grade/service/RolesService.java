package com.sam.sct.mytest.grade.service;

import com.sam.sct.mytest.entity.Roles;

/**
 * @author zhangshexin
 * @createTime 2019/5/22
 *
 * 角色
 */
public interface RolesService {
    /**
     * 根据角色id取角色相关信息
     */
    Roles getRoleById(int roleId);
}
