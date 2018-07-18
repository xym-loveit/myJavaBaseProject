package com.xym.myJava.observable;

/**
 * 任务运行的生命周期接口
 *
 * @author xym
 * @create 2018-07-18 9:47
 */
public interface TaskLifecycle<T> {

    /**
     * 任务启动时会触发onStart方法
     *
     * @param thread
     */
    void onStart(Thread thread);

    /**
     * 任务正在运行时会触发onRunning方法
     *
     * @param thread
     */
    void onRunning(Thread thread);

    /**
     * 任务运行结束时会触发onFinish方法，其中result是任务结束后的结果
     *
     * @param thread
     * @param result
     */
    void onFinish(Thread thread, T result);

    /**
     * 任务执行报错时，会触发onError
     *
     * @param thread
     * @param e
     */
    void onError(Thread thread, Exception e);

    /**
     * 生命周期接口的空实现（adapter），主要为了使用者保持对Thread类的使用习惯
     */
    class EmptyLifecycle<T> implements TaskLifecycle<T> {

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }
}
