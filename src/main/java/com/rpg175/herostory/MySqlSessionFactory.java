package com.rpg175.herostory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public final class MySqlSessionFactory {
    private static SqlSessionFactory _sqlSessionFactory;

    private MySqlSessionFactory() {
    }

    public static void init() {
        try {
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            _sqlSessionFactory = builder.build(Resources.getResourceAsReader("MyBatisConfig.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSession openSession() {
        if (null == _sqlSessionFactory) {
            throw new RuntimeException(("_sqlSessionFactory 尚未初始化"));
        }
        
        return _sqlSessionFactory.openSession(true);
    }
}
