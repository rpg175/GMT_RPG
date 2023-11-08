package com.rpg175.herostory;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rpg175.herostory.cmdhandler.CmdHandlerFactory;
import com.rpg175.herostory.cmdhandler.ICmdHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public final class MainThreadProcessor {
    static private final MainThreadProcessor _instance = new MainThreadProcessor();

    static private final Logger LOGGER = LoggerFactory.getLogger(GameMsgHandler.class);

    /**
     * 创建一个单线程
     */
    private final ExecutorService _es = Executors.newSingleThreadExecutor(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread newThread = new Thread(r);
            newThread.setName("MainThreadProcessor");
            return newThread;
        }
    });

    private MainThreadProcessor() {
    }

    static public MainThreadProcessor getInstance() {
        return _instance;
    }

    /**
     * 处理消息
     *
     * @param ctx 客户端信道上下文
     * @param msg 消息对象
     */
    public void process(ChannelHandlerContext ctx, GeneratedMessageV3 msg) {
        // 单线程处理消息业务逻辑，避免多线程脏读，
        // 要保证所以的数据都在内存中处理，
        // 如果出现IO会阻塞消息队列
        this._es.submit(() -> {
            final Class<?> msgClazz = msg.getClass();

            LOGGER.info(
                    "收到客户端消息, msgClazz = {}, msgObj = {}",
                    msgClazz.getSimpleName(),
                    msg
            );

            try {
                // 获取命令处理器
                ICmdHandler<? extends GeneratedMessageV3> cmdHandler = CmdHandlerFactory.create(msgClazz);

                if (null == cmdHandler) {
                    LOGGER.error("为找到相对应的指令处理器，msgClazz = {}", msgClazz.getName());
                    return;
                }

                cmdHandler.handle(ctx, cast(msg));
            } catch (Exception ex) {
                // 记录错误日志
                LOGGER.error(ex.getMessage(), ex);
            }
        });
    }

    public void process(Runnable r) {
        if (null != r) {
            this._es.submit(r);
        }
    }

    /**
     * 转型为命令对象
     *
     * @param msg    消息对象
     * @param <TCmd> 消息类
     * @return 命令对象
     */
    @SuppressWarnings("unchecked")
    static private <TCmd extends GeneratedMessageV3> TCmd cast(Object msg) {
        if (!(msg instanceof GeneratedMessageV3)) {
            return null;
        } else {
            return (TCmd) msg;
        }
    }

}
