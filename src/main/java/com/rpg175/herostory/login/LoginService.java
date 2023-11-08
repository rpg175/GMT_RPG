package com.rpg175.herostory.login;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rpg175.herostory.MySqlSessionFactory;
import com.rpg175.herostory.async.AsyncOperationProcessor;
import com.rpg175.herostory.async.IAsyncOperation;
import com.rpg175.herostory.login.db.IUserDao;
import com.rpg175.herostory.login.db.UserEntity;

import java.util.function.Function;

public final class LoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
    private static final LoginService _instance = new LoginService();

    private LoginService() {
    }

    public static LoginService getInstance() {
        return _instance;
    }

    public void userLogin(String userName, String password, Function<UserEntity, Void> callback) {
        if (null == userName || null == password) {
            return;
        }

        AsyncOperationProcessor.getInstance().process(new AsyncGetUserEntity(userName, password) {
            @Override
            public void doFinish() {
                if (null != callback) {
                    callback.apply(this.getUserEntity());
                }
            }
        });
    }

    static private class AsyncGetUserEntity implements IAsyncOperation {
        private final String _userName;

        private final String _password;

        private UserEntity _userEntity;

        UserEntity getUserEntity() {
            return _userEntity;
        }

        AsyncGetUserEntity(String userName, String password) {
            _userName = userName;
            _password = password;
        }

        @Override
        public int getBindId() {
            if (null == _userName) {
                return 0;
            } else {
                return _userName.charAt(_userName.length() - 1);
            }
        }

        @Override
        public void doAsync() {
            LOGGER.info("当前线程 = {}", Thread.currentThread().getName());

            try (SqlSession mySqlSession = MySqlSessionFactory.openSession()) {
                IUserDao dao = mySqlSession.getMapper(IUserDao.class);
                UserEntity userEntity = dao.getUserByName(_userName);
                if (null != userEntity) {
                    if (!_password.equals(userEntity.password)) {
                        throw new RuntimeException("密码错误");
                    }
                } else {
                    userEntity = new UserEntity();
                    userEntity.userName = _userName;
                    userEntity.password = _password;
                    userEntity.heroAvatar = "Hero_+x";
                    dao.insertInto(userEntity);
                }

                this._userEntity = userEntity;
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }
}
