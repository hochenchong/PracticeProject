package chapter06;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

/**
 * NonReentrantLock 自定义锁实现 生产-消费模型
 *
 * @author hochenchong
 * @date 2024/6/3
 */
public class NonReentrantLockExample {
    final static NonReentrantLock lock = new NonReentrantLock();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();
    final static Queue<String> queue = new LinkedBlockingQueue<>();
    final static int queueSize = 10;

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            // 获取独占锁
            lock.lock();
            try {
                // 如果队列满了，等待
                while (queue.size() == queueSize) {
                    notEmpty.await();
                }
                // 添加元素到队列
                queue.add("ele");
                System.out.println(Thread.currentThread() + ": production ele");
                // 唤醒消费线程
                notFull.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
        });

        Thread consumer = new Thread(() -> {
            lock.lock();
            try {
                // 队列空，等待
                while (queue.isEmpty()) {
                    notFull.await();
                }
                // 消费一个元素
                String ele = queue.poll();
                System.out.println(Thread.currentThread() + ": consumption ele");
                // 唤醒生产线程
                notEmpty.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
        });
        producer.start();
        consumer.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
