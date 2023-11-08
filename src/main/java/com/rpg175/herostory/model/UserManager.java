package com.rpg175.herostory.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户管理器
 */
public final class UserManager {
    /**
     * 用户字典
     */
    static private final Map<Integer, User> _userMap = new ConcurrentHashMap<>();

    /**
     * 私有化类默认构造器
     */
    private UserManager() {
    }

    /**
     * 添加用户
     *
     * @param u
     */
    static public void addUser(User u) {
        if (null != u) {
            _userMap.putIfAbsent(u.userId, u);
        }
    }

    /**
     * 移除用户
     *
     * @param userId
     */
    static public void removeByUserId(int userId) {
        _userMap.remove(userId);
    }

    /**
     * 列表用户
     *
     * @return
     */
    static public Collection<User> listUser() {
        return _userMap.values();
    }

    /**
     * 根据用户 Id 获取用户
     *
     * @param userId
     * @return
     */
    static public User getByUserId(int userId) {
        return _userMap.get(userId);
    }
}
