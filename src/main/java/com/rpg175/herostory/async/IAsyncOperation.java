package com.rpg175.herostory.async;

public interface IAsyncOperation {
    /**
     * 获取绑定 Id，作用将某个操作，根据某种添加绑定到某个线程上执行！
     *
     * @return 绑定 Id
     */
    int getBindId();

    void doAsync();

    // 有一些异步操作不需要返回处理，所以使用default 定义一个空实现
    default void doFinish() {

    }
}
