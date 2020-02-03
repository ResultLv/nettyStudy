package netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) throws Exception{
        //创建BossGroup 和 WorkerGroup的线程组
        //BoosGroup只负责处理连接请求，和客户端的业务处理会交给WorkerGroup完成
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            //创建服务器端启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            //使用链式编程进行设置
            bootstrap.group(bossGroup, workerGroup) //设置两个线程组
                    .channel(NioServerSocketChannel.class)  //使用NioServerSocketChannel作为服务器端通道实现
//                    .option(ChannelOption.SO_BACKLOG, 128)  //设置线程队列的连接个数
//                    .childOption(ChannelOption.SO_KEEPALIVE, true)  //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { //给WorkerGroup的 EventLoop对应的管道设置业务处理器
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("====== Server is ready =======");

            //启动服务器 绑定端口同步，返回一个ChannelFuture对象
            ChannelFuture cf = bootstrap.bind(6668).sync();

            //Future-Listener监听机制，通过添加listener来监听future对象的返回
            cf.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()){
                        System.out.println("监听端口 6668 成功");
                    }else{
                        System.out.println("监听端口 6668 失败");
                    }
                }
            });

            //对关闭通道进行监听
            cf.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
