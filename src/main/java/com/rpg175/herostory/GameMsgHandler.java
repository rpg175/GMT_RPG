package com.rpg175.herostory;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rpg175.herostory.model.UserManager;
import com.rpg175.herostory.msg.GameMsgProtocol;

/**
 * 自定义的消息处理器
 */
public class GameMsgHandler extends SimpleChannelInboundHandler<Object> {
    /**
     * 日志对象
     */
    static private final Logger LOGGER = LoggerFactory.getLogger(GameMsgHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        if (null == ctx) {
            return;
        }

        try {
            super.channelActive(ctx);
            Broadcaster.addChannel(ctx.channel());
        } catch (Exception ex) {
            // 记录错误日志
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        if (null == ctx) {
            return;
        }

        try {
            super.handlerRemoved(ctx);
            Broadcaster.removeChannel(ctx.channel());

            Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();

            if (null == userId) {
                return;
            }

            UserManager.removeByUserId(userId);

            GameMsgProtocol.UserQuitResult.Builder resultBuilder = GameMsgProtocol.UserQuitResult.newBuilder();
            resultBuilder.setQuitUserId(userId);

            GameMsgProtocol.UserQuitResult newResult = resultBuilder.build();
            Broadcaster.broadcast(newResult);
        } catch (Exception ex) {
            // 记录错误日志
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        if (null == ctx ||
            null == msg) {
            LOGGER.error("ctx null == ctx {} or  null == msg {}",null == ctx, null == msg);
            return;
        }

        if (msg instanceof GeneratedMessageV3) {
            MainThreadProcessor.getInstance().process(ctx,(GeneratedMessageV3) msg);
        }
//
//        final Class<?> msgClazz = msg.getClass();
//
//        LOGGER.info(
//            "收到客户端消息, msgClazz = {}, msgObj = {}",
//            msgClazz.getSimpleName(),
//            msg
//        );
//
//        try {
//            // 获取命令处理器
//            ICmdHandler<? extends GeneratedMessageV3> cmdHandler = CmdHandlerFactory.create(msgClazz);
//
//            if (null != cmdHandler) {
//                cmdHandler.handle(ctx, cast(msg));
//            }
//        } catch (Exception ex) {
//            // 记录错误日志
//            LOGGER.error(ex.getMessage(), ex);
//        }
    }


}
