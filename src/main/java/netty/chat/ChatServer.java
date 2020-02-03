package netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChatServer {

    private Integer port;

    public ChatServer(Integer port) {
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder", new StringEncoder());
                            pipeline.addLast("encoder", new StringDecoder());
                            pipeline.addLast(new ChatServerHandler());
                        }
                    });

            System.out.println("===== Netty服务器启动了 =====");
            ChannelFuture channelFuture = bootstrap.bind(port).sync();

            // Future-Listener监听机制，通过添加listener来监听future对象的返回
            channelFuture.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("----- 监听端口" + port + "成功 -----");
                    } else {
                        System.out.println("----- 监听端口" + port + "失败 -----");
                    }
                }
            });

            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new ChatServer(7000).run();
    }
}
