package netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {
    public static void main(String[] args) throws Exception{
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            //创建服务器端启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            //使用链式编程进行设置
            bootstrap.group(bossGroup, workerGroup) //设置两个线程组
                    .channel(NioServerSocketChannel.class)  //使用NioServerSocketChannel作为服务器端通道实现
//                    .option(ChannelOption.SO_BACKLOG, 128)  //设置线程队列的连接个数
//                    .childOption(ChannelOption.SO_KEEPALIVE, true)  //设置保持活动连接状态
                    .childHandler(new TestServerInitializer());
            System.out.println("====== Server is ready =======");

            //启动服务器 绑定端口同步，返回一个ChannelFuture对象
            ChannelFuture cf = bootstrap.bind(1314).sync();
            cf.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()){
                        System.out.println("绑定 1314 端口成功");
                    }else{
                        System.out.println("绑定 1314 端口失败");
                    }
                }
            });

            cf.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
