///**
// * Copyright (C), 2015-2019, 南京云融金融信息服务有限公司
// * 文件名: NettyServer
// * 作者:   gaojing
// * 创建时间:     2019/7/24 14:40
// * 描述:
// * 历史修改:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.string.StringDecoder;
//import io.netty.handler.codec.string.StringEncoder;
//
///**
// * 〈一句话功能简述〉<br>
// * @author gaojing
// * @create 2019/7/24
// * @version 1.0
// */
//public class NettyServer {
//    public static void main(String[] args) {
//        ServerBootstrap serverBootstrap = new ServerBootstrap();
//        NioEventLoopGroup boos = new NioEventLoopGroup();
//        NioEventLoopGroup worker = new NioEventLoopGroup();
//        try {
//            ChannelFuture f = serverBootstrap
//                    .group(boos, worker)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
//                        @Override
//                        protected void initChannel(NioSocketChannel ch) {
//                            ch.pipeline().addLast(new StringEncoder());
//                            ch.pipeline().addLast(new StringDecoder());
//                            ch.pipeline().addLast(new ChannelHandler());
////                            ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
////                                @Override
////                                protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
////                                    System.out.println(s);
////                                }
////                            });
//                        }
//                    })
//                    .bind(8000).sync();
//            f.channel().closeFuture().sync();
//            System.out.println(Thread.currentThread().getName() + ",服务器开始监听端口，等待客户端连接.........");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            boos.shutdownGracefully();
//            worker.shutdownGracefully();
//        }
//    }
//
//    private static class ChannelHandler extends SimpleChannelInboundHandler{
//        @Override
//        protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
////            Channel channel = channelHandlerContext.channel();
////            ChannelFuture channelFuture = channel.writeAndFlush("收到了");
////            System.out.println(channelFuture.isDone());
//            channelHandlerContext.writeAndFlush("收到了"+System.currentTimeMillis());
//            System.out.println(o);
//        }
//    }
//}