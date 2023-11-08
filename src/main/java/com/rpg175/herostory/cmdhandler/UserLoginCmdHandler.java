package com.rpg175.herostory.cmdhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rpg175.herostory.Broadcaster;
import com.rpg175.herostory.async.AsyncOperationProcessor;
import com.rpg175.herostory.login.LoginService;
import com.rpg175.herostory.login.db.UserEntity;
import com.rpg175.herostory.model.User;
import com.rpg175.herostory.model.UserManager;
import com.rpg175.herostory.msg.GameMsgProtocol;

public class UserLoginCmdHandler implements ICmdHandler<GameMsgProtocol.UserLoginCmd> {

    static private final Logger LOGGER = LoggerFactory.getLogger(UserLoginCmdHandler.class);

    @Override
    public void handle(ChannelHandlerContext ctx, GameMsgProtocol.UserLoginCmd cmd) {
        LOGGER.info("userName = {},passward = {}", cmd.getUserName(), cmd.getPassword());

        // 实际业务需求，在登录访问mysql的io，使用异步逻辑.....
        // 异步执行完毕后，会回到主线程执行
        LoginService.getInstance().userLogin(cmd.getUserName(), cmd.getPassword(), (userEntity) -> {
            GameMsgProtocol.UserLoginResult.Builder resultBuilder = GameMsgProtocol.UserLoginResult.newBuilder();

            LOGGER.info("当前线程 = {}", Thread.currentThread().getName());

            if (null == userEntity) {
                resultBuilder.setUserId(-1);
                resultBuilder.setUserName("");
                resultBuilder.setHeroAvatar("");
            } else {
                User newUser = new User();
                newUser.userId = userEntity.userId;
                newUser.userName = userEntity.userName;
                newUser.heroAvatar = userEntity.heroAvatar;
                newUser.currHp = 100;
                UserManager.addUser(newUser);

                // 将用户 Id 保存至 Session
                ctx.channel().attr(AttributeKey.valueOf("userId")).set(newUser.userId);

                resultBuilder.setUserId(userEntity.userId);
                resultBuilder.setUserName(userEntity.userName);
                resultBuilder.setHeroAvatar(userEntity.heroAvatar);
            }

            GameMsgProtocol.UserLoginResult newResult = resultBuilder.build();
            ctx.writeAndFlush(newResult);

            return null;
        });
    }
}
