///**
// * Copyright (C), 2015-2019, 南京云融金融信息服务有限公司
// * 文件名: Demo_deadLock
// * 作者:   gaojing
// * 创建时间:     2019/3/5 9:47
// * 描述:
// * 历史修改:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com;
//
///**
// * 〈一句话功能简述〉<br>
// * @author gaojing
// * @create 2019/3/5
// * @version 1.0
// */
//public class Demo_deadLock {
//
//    public static void main(String[] args) {
//        Resource r1 = new Resource("1", 1);
//        Resource r2 = new Resource("2", 2);
//
////      循环次数多一些，为了得到上述情况
//        Thread thread1 = new Thread(()->{
//            for (int i = 0; i < 100; i++) {
//                //r1.doSome();
//                r1.cooperate(r2);
//            }
//
//        });
//        System.out.println("t1 over");
//        Thread thread2 = new Thread(()->{
//            for (int i = 0; i < 100; i++) {
//                //r2.doSome();
//                r2.cooperate(r1);
//            }
//
//        });
//        thread1.start();
//        thread2.start();
//        System.out.println("t2 over");
//
//    }
//
//}