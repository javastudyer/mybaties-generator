///**
// * Copyright (C), 2015-2019, 南京云融金融信息服务有限公司
// * 文件名: ThreadTest
// * 作者:   gaojing
// * 创建时间:     2019/8/2 10:18
// * 描述:
// * 历史修改:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.util.concurrent.*;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//
///**
// * 〈一句话功能简述〉<br>
// * @author gaojing
// * @create 2019/8/2
// * @version 1.0
// */
//public class ThreadTest{
//
//    private String name = "gaojing111";
//    volatile static AtomicInteger count = new AtomicInteger();
//
//    //创建可重入锁ReentrantLock 参数设置为true为公平锁（可以防止线程饥饿，但性能不如非公平锁），否则为非公平锁
//    private static Lock lock = new ReentrantLock(true);
//
//    //LockSupport 锁
//
//    //ReentrantLock 可重入锁  和condition一起使用，condition的await方法是使当前线程出入等待阶段，会释放锁资源，signal方法是唤醒等待的线程继续执行
//
//    volatile static int count1 = 0;
//    static class Test extends ThreadTest{
//        public final String name = "gaojing";
//    }
//
//    //继续Thread
//    static class ThreadTestThread extends Thread{
//
//        private CountDownLatch countDownLatch = null;
//
//        private CyclicBarrier cyclicBarrier = null;
//
//        public ThreadTestThread(CountDownLatch countDownLatch){
//            this.countDownLatch = countDownLatch;
//        }
//
//        public ThreadTestThread(CyclicBarrier cyclicBarrier){
//            this.cyclicBarrier = cyclicBarrier;
//        }
//
//        int a = 0;
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            try {
//                System.out.println(Thread.currentThread().getName()+"====is ready!!!");
//                this.cyclicBarrier.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (BrokenBarrierException e) {
//                e.printStackTrace();
//            }
//            for (int i = 0;i<10;i++){
//                System.out.println(a+++"============="+Thread.currentThread().getName());
//            }
//        }
//
//    }
//
//    //实现runable接口
//    static class ThreadTestRunable implements Runnable{
//        private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
//        int a = 0;
//        @Override
//        public void run() {
//            try {
//                reentrantReadWriteLock.writeLock().lock();
//                for (int i = 0; i < 10; i++) {
//                    System.out.println(a++ + "=============" + Thread.currentThread().getName());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                reentrantReadWriteLock.writeLock().unlock();
//            }
//        }
//    }
//
//    //实现callable接口
//    static class ThreadTestCallable implements Callable<Integer>{
//
//        int a = 0;
//        @Override
//        public Integer call() throws Exception {
//            for (int i = 0; i < 10; i++) {
//                System.out.println(a++ + "=============" + Thread.currentThread().getName());
//            }
//            return a;
//        }
//    }
//
//    public static void test11(Integer a){
//        System.out.println(a);
//    }
//
//    public static void main(String[] args) throws InterruptedException, ExecutionException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
////        CountDownLatch countDownLatch = new CountDownLatch(3);
////
////        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
////        ThreadTestThread threadTestThread = new ThreadTestThread(cyclicBarrier);
////        ThreadTestThread threadTestThread1 = new ThreadTestThread(cyclicBarrier);
////        //ThreadTestThread threadTestThread2 = new ThreadTestThread(cyclicBarrier);
////        threadTestThread.start();
////        threadTestThread1.start();
////        //threadTestThread2.start();
////
////
////        System.out.println("------等待执行完毕--------");
////        System.out.println("------全部执行完毕-----");
//
////        ThreadTestRunable threadTestRunable = new ThreadTestRunable();
////        ThreadTestRunable threadTestRunable1 = new ThreadTestRunable();
////        Thread thread = new Thread(threadTestRunable);
////        Thread thread1 = new Thread(threadTestRunable);
////        Thread thread2 = new Thread(threadTestRunable1);
////        Thread thread3 = new Thread(threadTestRunable1);
////        thread.start();
////        thread1.start();
////        thread2.start();
////        thread3.start();
//
////        ThreadTestCallable threadTestCallable = new ThreadTestCallable();
//////
//////        FutureTask<Integer> task = new FutureTask<Integer>(threadTestCallable);
//////        //创建一个线程去执行
////////        Thread thread = new Thread(task);
////////        thread.start();
//////
//////        //创建线程池去执行
//////        ExecutorService executorService = Executors.newFixedThreadPool(1);
//////        executorService.submit(task);
//////        //获取线程执行后的结果
//////        System.out.println(task.isCancelled());
//////        System.out.println(task.get());
//////        //关闭线程池
//////        executorService.shutdown();
//////
//////        ThreadTest test = new Test();
//////        System.out.println(test.name);
//
//        //java反射
////        Class clazz = Class.forName("com.MsgLoadFrame");
////        ClassLoader cl = clazz.getClassLoader();
////        System.out.println(clazz.getSuperclass());
////        Field field = clazz.getDeclaredField("XMLPROJECT");
////        field.setAccessible(true);
////        System.out.println(field.get("XMLPROJECT"));
//        Thread thread = new Thread();
//        System.out.println(thread.getId());
//
//        //原子类测试
////        for (int i = 0; i < 2 ;i++){
////            new Thread(){
////                @Override
////                public void run() {
////                   for (int j = 0; j < 100; j++) {
////                       System.out.println(count.getAndAdd(1));
////                   }
////                }
////            }.start();
////        }
////        Thread.sleep(1000);
////        System.out.println(count.get());
//
//       // System.out.println(TimeUnit.MINUTES.toSeconds(60));
//
//    }
//}