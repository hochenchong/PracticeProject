package chapter01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程创建
 * 1. 继承 Thread 类重写 run 方法，例如 MyThread
 * 2. 实现 Runnable 接口，例如 RunnableTask
 * 3. 使用 FutureTask（该类实现了 Runnable 接口），有返回值
 *
 * @author hochenchong
 * @date 2024/5/31
 */
public class ThreadTest {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("I am a child thread, " + this.getName());
        }
    }

    static class RunnableTask implements Runnable {
        @Override
        public void run() {
            System.out.println("I am a child thread, " + Thread.currentThread().getName());
        }
    }

    static class CallableTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "hello";
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        // 启动线程
        thread.start();

        RunnableTask runnableTask = new RunnableTask();
        new Thread(runnableTask, "RunnableTask-01").start();
        new Thread(runnableTask, "RunnableTask-02").start();

        FutureTask<String> futureTask = new FutureTask<>(new CallableTask());
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
