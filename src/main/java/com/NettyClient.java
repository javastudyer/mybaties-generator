///**
// * Copyright (C), 2015-2019, 南京云融金融信息服务有限公司
// * 文件名: NettyClient
// * 作者:   gaojing
// * 创建时间:     2019/7/24 14:38
// * 描述:
// * 历史修改:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.Channel;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.string.StringDecoder;
//import io.netty.handler.codec.string.StringEncoder;
//
//import java.util.Date;
//
//
///**
// * 〈一句话功能简述〉<br>
// * @author gaojing
// * @create 2019/7/24
// * @version 1.0
// */
//public class NettyClient {
//    public static void main(String[] args) {
//        Bootstrap bootstrap = new Bootstrap();
//        NioEventLoopGroup group = new NioEventLoopGroup();
//        bootstrap.group(group)
//                .channel(NioSocketChannel.class)
//                .handler(new ChannelInitializer<Channel>() {
//                    @Override
//                    protected void initChannel(Channel ch) {
//                        ch.pipeline().addLast(new StringEncoder());
//                        ch.pipeline().addLast(new StringDecoder());
//                        ch.pipeline().addLast(new SimpleChannelInboundHandler() {
//                            @Override
//                            protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
//                                System.out.println(o);
//                            }
//                        });
//                    }
//                });
//
//        Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();
//
//        while (true) {
//            channel.writeAndFlush(new Date() + ": hello world!");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}