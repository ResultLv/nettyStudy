package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient{

    private SocketChannel socketChannel;

    public NIOClient() throws IOException {
        socketChannel.open(new InetSocketAddress("127.0.0.1", 6666));

        socketChannel.configureBlocking(false);
    }

    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true);
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
        socketChannel.connect(address);
        String str = "我喜欢苏婉怡";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        //发送数据，将buffer的数据写入Channel
        socketChannel.write(buffer);
        buffer.clear();
        buffer.flip();
        socketChannel.close();

    }
}
