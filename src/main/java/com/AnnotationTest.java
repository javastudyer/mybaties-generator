///**
// * Copyright (C), 2015-2019, 南京云融金融信息服务有限公司
// * 文件名: AnnotationTest
// * 作者:   gaojing
// * 创建时间:     2019/5/14 10:22
// * 描述:
// * 历史修改:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.lang.invoke.MethodType;
//import java.net.URL;
//import java.net.URLConnection;
//
///**
// * 〈注解测试〉<br>
// * @author gaojing
// * @create 2019/5/14
// * @version 1.0
// */
//public class AnnotationTest {
//
//    //下载百度页面元素dom
//    public static void main(String[] args) throws IOException {
//        URL url = new URL("https://download.tortoisegit.org/tgit/previews/");
//        URLConnection u = url.openConnection();
//
//
//        u.connect();
//
//        InputStream in = u.getInputStream();
//        byte [] b = new byte[1024];
//        File file = new File("D:\\1113.txt");
//        OutputStream outputStream = new FileOutputStream(file);
//        int a = 0;
//        while ((a = in.read(b))!=-1){
//            outputStream.write(b,0,a);
//        }
//        in.close();
//        outputStream.close();
//        System.out.println(u);
//    }
//}