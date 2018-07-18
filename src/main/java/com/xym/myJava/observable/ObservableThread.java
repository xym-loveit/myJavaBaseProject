package com.xym.myJava.observable;

/**
 * 具备任务监控的线程执行类
 *
 * @author xym
 * @create 2018-07-18 9:56
 */
public class ObservableThread<T> extends Thread implements Observable {

    private final Task<T> task;

    private final TaskLifecycle<T> lifecycle;

    private Cycle cycle;

    /**
     * 指定task的实现，默认情况下使用EmptyLifecycle
     *
     * @param task
     */
    public ObservableThread(Task task) {
        this(task, new TaskLifecycle.EmptyLifecycle());
    }

    /**
     * 指定task的同时，指定TaskLifecycle
     *
     * @param task
     * @param lifecycle
     */
    public ObservableThread(Task task, TaskLifecycle<T> lifecycle) {
        super();
        if (task == null) {
            throw new IllegalArgumentException("the task is required.");
        }

        this.task = task;
        this.lifecycle = lifecycle;
    }


    /**
     * 重写父类run方法，并且将其修饰为final类型不允许子类再次进行重写
     * run方法在线程的运行期间，可监控任务在执行过程中的各个生命周期阶段
     * 任务每经过一个阶段相当于发生了一次事件
     */
    @Override
    public final void run() {
        this.update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNNING, null, null);
            T result = this.task.call();
            this.update(Cycle.DONE, result, null);
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }

    }


    /**
     * update方法用于通知事件的监听者，此时任务在执行过程中发生了什么，最主要的通知是异常处理，如果监听者，也就是 lifecycle在响应
     * 某个事件的过程中出现了意外，则会导致任务的正常执行受到影响，因此需要进行异常捕获，并忽略这些异常信息以保证lifecycle的实现
     * 不影响任务的正确执行，但是如果任务执行过程中抛出了异常，那么就需要继续抛出，保持与call方法同样的意图
     *
     * @param cycle
     * @param result
     * @param e
     */
    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (lifecycle == null) {
            return;
        }

        try {
            switch (cycle) {
                case STARTED:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifecycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.lifecycle.onError(currentThread(), e);
                    break;
            }
        } catch (Exception ex) {
            if (cycle == Cycle.ERROR) {
                throw ex;
            }
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }
}
