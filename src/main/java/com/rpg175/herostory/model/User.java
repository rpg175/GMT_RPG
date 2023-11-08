package com.rpg175.herostory.model;

/**
 * 用户
 */
public class User {
    /**
     * 用户 Id
     */
    public int userId;

    /**
     * 影响形象
     */
    public String heroAvatar;
    public String userName;

    /**
     * 当前血量
     */
    public int currHp;

    /**
     * 移动状态
     */
    public final MoveState moveState = new MoveState();
}
