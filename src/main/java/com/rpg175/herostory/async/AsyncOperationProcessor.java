package com.rpg175.herostory.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rpg175.herostory.GameMsgHandler;
import com.rpg175.herostory.MainThreadProcessor;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public final class AsyncOperationProcessor {
    private static final AsyncOperationProcessor _instance = new AsyncOperationProcessor();

    static private final Logger LOGGER = LoggerFactory.getLogger(AsyncOperationProcessor.class);
//    /**
//     * 创建一个单线程
//     */
//    private final ExecutorService _es = Executors.newSingleThreadExecutor(new ThreadFactory() {
//        @Override
//        public Thread newThread(Runnable r) {
//            Thread newThread = new Thread(r);
//            newThread.setName("AsyncOperationProcessor");
//            return newThread;
//        }
//    });

    /**
     * 将数据的IO操作，由单线程修改为多线程
     */
    private final ExecutorService[] _esArray = new ExecutorService[8];

    private AsyncOperationProcessor() {
        for (int i = 0; i < _esArray.length; i++) {
            // 线程名称
            final String threadName = MessageFormat.format("执行IO的异步线程，AsyncOperationProcessor[ {0} ]", i);
            // 创建单线程
            _esArray[i] = Executors.newSingleThreadExecutor((r) -> {
                Thread t = new Thread(r);
                t.setName(threadName);
                return t;
            });
        }
    }

    public static AsyncOperationProcessor getInstance() {
        return _instance;
    }

    public void process(IAsyncOperation asyncOperation) {
        if (asyncOperation == null) {
            return;
        }

        int bindId = Math.abs(asyncOperation.getBindId());
        int esIndex = bindId % _esArray.length;

        this._esArray[esIndex].submit(() -> {
            try {
                //异步执行io
                asyncOperation.doAsync();

                //执行完异步io后，回到主线程，继续执行,原始写法
//            MainThreadProcessor.getInstance().process(() -> {
//                asyncOperation.doFinish();
//            });

                //最优代码
                MainThreadProcessor.getInstance().process(asyncOperation::doFinish);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        });
    }
}
