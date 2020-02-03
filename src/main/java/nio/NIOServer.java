package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
    public static void main(String[] args) throws Exception {
        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //创建Selector对象
        Selector selector = Selector.open();

        //绑定端口6666，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //将ServerSocketChannel注册到Selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }

            //如果返回>0，则代表有连接事件发生，获取关注事件的集合
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                //根据key 对发生的事件做相应的处理
                if (key.isAcceptable()){
                    //给该客户端生成一个ServerSocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("客户端连接成功");
                    //将socketChannel注册到Selector，关注事件为read，并关联一个ByteBuffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()){
                    //通过key反向获取channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    while(channel.read(buffer)>0){
                        System.out.println("来自客户端的消息:" + new String(buffer.array()));
                        buffer.flip();
                        buffer.clear();
                    }
                }
                //手动从当前集合中删除selectionKey，防止重复操作
                keyIterator.remove();
            }
        }
    }
}
