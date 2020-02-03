package netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel ch) throws Exception {
        //向管道加入处理器
        ChannelPipeline pipeline = ch.pipeline();

        //加入解码器
        pipeline.addLast("http编解码器", new HttpServerCodec());
        //加入业务处理器
        pipeline.addLast("http业务 处理器", new TestHttpServerHandler());
    }
}
