package cn.netwins.allmedianews.thread_pool;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by desaco on 2018/4/16.
 * 线程池结合Callable、Future和FutureTask使用
 * 接口回调需要加个Type或使用接口回调+ Rxjava
 */

public class ThreadPoolManager2<T> {//ThreadPoolManager2
    /**
     * 单例设计模式（饿汉式）
     * 单例首先私有化构造方法，然后饿汉式一开始就开始创建，并提供get方法
     */
    private static ThreadPoolManager2 mInstance = new ThreadPoolManager2();

    public static ThreadPoolManager2 getThreadPoolInstance() {
        return mInstance;
    }

    private int corePoolSize;//核心线程池的数量，同时能够执行的线程数量
    private int maximumPoolSize;//最大线程池数量，表示当缓冲队列满的时候能继续容纳的等待任务的数量
    private long keepAliveTime = 1;//存活时间
    private TimeUnit unit = TimeUnit.HOURS;
    private ThreadPoolExecutor executor;

    private ThreadPoolManager2() {
        /**
         * 给corePoolSize赋值：当前设备可用处理器核心数*2 + 1,能够让cpu的效率得到最大程度执行（有研究论证的）
         */
        corePoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        maximumPoolSize = corePoolSize; //虽然maximumPoolSize用不到，但是需要赋值，否则报错
        executor = new ThreadPoolExecutor(
                corePoolSize, //当某个核心任务执行完毕，会依次从缓冲队列中取出等待任务
                maximumPoolSize, //5,先corePoolSize,然后new LinkedBlockingQueue<Runnable>(),然后maximumPoolSize,但是它的数量是包含了corePoolSize的
                keepAliveTime, //表示的是maximumPoolSize当中等待任务的存活时间
                unit,
                new LinkedBlockingQueue<Runnable>(), //缓冲队列，用于存放等待任务，Linked的先进先出
                Executors.defaultThreadFactory(), //创建线程的工厂
                new ThreadPoolExecutor.AbortPolicy() //用来对超出maximumPoolSize的任务的处理策略
        );
    }

    /**
     * 执行任务,不需要返回值
     */
    public void execute(Runnable runnable) {
        if (runnable == null) {
            return;
        } else {
            executor.execute(runnable);
        }
    }

    /**
     * 执行任务, 需要返回值
     *
     * @param runnable
     */
    public void executeRunnableCallback(Runnable runnable) {
        Future<?> future = executor.submit(runnable);
        try {
            Object object = future.get();
            Log.e("desaco", "executeRunnableCallback() object=" + object);

            if (mCallback != null) {
//                mCallback.sendMsg("",object);
                mCallback.sendMsg("", "dsrgreg");
                Log.e("desaco", "executeRunnableCallback() mCallback不为空，" + object);
            } else {
                Log.e("desaco", "executeRunnableCallback() mCallback为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public synchronized void submitCallback(Callable<Object> callable) {//同步代码块
        try{
            Thread.sleep(15000);// 睡眠100毫秒
            Future<Object> future = executor.submit(callable);
            Object t = future.get();
            if (mCallback != null) {
//                mCallback.sendMsg("",object);
                mCallback.sendMsg("", t);
                Log.e("desaco", "executeRunnableCallback() mCallback不为空，" + t);
            } else {
                Log.e("desaco", "executeRunnableCallback() mCallback为空");
            }
        }catch (Exception e){

        }
    }

//    public void submit(Callable<T> callable) {
//        try{
//            Future<T> future = executor.submit(callable);
//            T t = future.get();
//        }catch (Exception e){
//
//        }
//
//    }

    /**
     * 从线程池中移除任务
     */
    public void remove(Runnable runnable) {
        if (runnable == null) {
            return;
        } else {
            executor.remove(runnable);
        }
    }

    public void cancel() {
//        executor.shutdown();
        executor.shutdownNow();
    }

    private ICallback mCallback;

    public void setInterfaceObject(ICallback iCallback) {
        mCallback = iCallback;
    }
}
