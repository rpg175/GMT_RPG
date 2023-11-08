package com.rpg175.herostory.login.db;

public interface IUserDao {
    UserEntity getUserByName(String userName);

    void insertInto(UserEntity user);
}
