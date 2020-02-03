package netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyServer {

    private Integer port;

    public MyServer(Integer port) {
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
//                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline  = ch.pipeline();
                            // 因为websocket基于http协议 需要使用http解码器
                            pipeline.addLast(new HttpServerCodec());
                            // 以块的方式写 添加ChunkedWriteHandler处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                            /*
                            * http数据在传输过程中是分段的，HttpObjectAggregator处理器用于多段聚合
                            * 因此浏览器发送大量数据时会发出多次http请求
                            */
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            /*
                             * websocket的数据以 帧 的形式传递
                             * 浏览器请求时 ws://localhost:7000/hello 表示请求的url
                             * WebSocketServerProtocolHandler的核心功能是将http协议升级为ws协议，保持长连接
                             * 通过状态码 101（Switching Protocols）切换并升级协议
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            // 添加自定义的业务处理handler
                            pipeline.addLast(new MyTextWebSocketFrameHandler());
                        }
                    });

            System.out.println("===== Websocket服务器启动了 =====");
            // 绑定端口并启动服务器
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new MyServer(7000).run();
    }
}
