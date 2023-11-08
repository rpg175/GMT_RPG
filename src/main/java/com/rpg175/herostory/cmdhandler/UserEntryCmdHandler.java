package com.rpg175.herostory.cmdhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rpg175.herostory.Broadcaster;
import com.rpg175.herostory.login.LoginService;
import com.rpg175.herostory.model.User;
import com.rpg175.herostory.model.UserManager;
import com.rpg175.herostory.msg.GameMsgProtocol;

/**
 * 用户入场
 */
public class UserEntryCmdHandler implements ICmdHandler<GameMsgProtocol.UserEntryCmd> {
    private static Logger LOGGER = LoggerFactory.getLogger(UserEntryCmdHandler.class);

    @Override
    public void handle(ChannelHandlerContext ctx, GameMsgProtocol.UserEntryCmd cmd) {
        if (null == ctx ||
                null == cmd) {
            return;
        }

        // 获取用户 Id 和英雄形象，登录后已经获取到 userId和heroAvatar
        Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();
        if (null == userId) {
            LOGGER.error(" ctx.channel().attr(AttributeKey.valueOf(\"userId\")),我空了{}", userId);
            return;
        }

        User existUser = UserManager.getByUserId(userId);
        if (null == existUser) {
            LOGGER.error("我空了,UserManager.getByUserId {}", userId);
            return;
        }

        // 获取英雄形象
        String heroAvatar = existUser.heroAvatar;

        GameMsgProtocol.UserEntryResult.Builder resultBuilder = GameMsgProtocol.UserEntryResult.newBuilder();
        resultBuilder.setUserId(userId);
        resultBuilder.setHeroAvatar(heroAvatar);

        // 构建结果并广播
        GameMsgProtocol.UserEntryResult newResult = resultBuilder.build();
        Broadcaster.broadcast(newResult);
    }
}
