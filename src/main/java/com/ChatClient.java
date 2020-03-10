//package com;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.SelectionKey;
//import java.nio.channels.Selector;
//import java.nio.channels.SocketChannel;
//import java.nio.charset.Charset;
//import java.util.Iterator;
//import java.util.Scanner;
//
//public class ChatClient {
//    private Selector selector;
//    private SocketChannel socketChannel;
//    private Charset charset = Charset.forName("UTF-8");
//
//    public ChatClient(String serverIp, int port) {
//        init(serverIp, port);
//    }
//
//    private void init(String serverIp, int port) {
//        try {
//            socketChannel = SocketChannel.open();
//            socketChannel.configureBlocking(false);
//            socketChannel.connect(new InetSocketAddress(serverIp, port));
//            selector = Selector.open();
//            socketChannel.register(selector, SelectionKey.OP_CONNECT);
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void start() {
//        new Thread(new ClientThread()).start();
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            String line = scanner.nextLine();
//            if (line.equals("")) {
//                continue;
//            }
//            try {
//                socketChannel.write(charset.encode(line));
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        scanner.close();
//    }
//
//    private class ClientThread implements Runnable {
//        public void run() {
//            while (true) {
//                try {
//                    selector.select();
//                    Iterator<SelectionKey> ite = selector.selectedKeys()
//                            .iterator();
//                    while (ite.hasNext()) {
//                        SelectionKey key = ite.next();
//                        ite.remove();
//                        dealWithSelectionKey(key);
//                    }
//                }
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        private void dealWithSelectionKey(SelectionKey key) {
//            if (key.isConnectable()) {
//                SocketChannel channel = (SocketChannel) key.channel();
//                try {
//                    if (channel.isConnectionPending()) {
//                        channel.finishConnect();
//                    }
//                    channel.configureBlocking(false);
//                    channel.register(selector, SelectionKey.OP_READ);
//                }
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (key.isReadable()) {
//                SocketChannel channel = (SocketChannel) key.channel();
//                ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
//                StringBuilder sb = new StringBuilder();
//                try {
//                    while (channel.read(buffer) > 0) {
//                        buffer.flip();
//                        sb.append(charset.decode(buffer));
//                        buffer.clear();
//                    }
//                    String content = sb.toString();
//                    System.out.println(content);
//                    key.interestOps(SelectionKey.OP_READ);
//                }
//                catch (IOException e) {
//                    key.cancel();
//                    if (key.channel() != null) {
//                        try {
//                            key.channel().close();
//                        }
//                        catch (IOException e1) {
//                            e1.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//
//    }
//
//    public static void main(String[] args) {
//        new ChatClient("localhost", 9999).start();
//    }
//}
