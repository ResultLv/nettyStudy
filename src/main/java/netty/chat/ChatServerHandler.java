package netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 定义一个Channel组，管理所有Channel
     * GlobalEventExecutor.INSTANCE是全局事件执行器，是一个单例
     */
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /** handlerAdded表示连接建立后即执行
     *  讲当前Channel加入到ChannelGroup
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.add(channel);
        // 通知所有其他Channel有新的连接加入进来了
        channelGroup.writeAndFlush("[用户]" + channel.remoteAddress() + " 加入聊天\n");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 通知所有其他Channel当前连接已断开 退出了聊天
        channelGroup.writeAndFlush("[用户]" + channel.remoteAddress() + " 退出聊天\n");

        System.out.println("channelGroup size: " + channelGroup.size());
    }

    /** 表示Channel处于活动状态 服务端提示 xxx上线了*/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 上线了～" + dateFormat());
    }

    /** 表示Channel处于不活动状态 服务端提示 xxx离线了*/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 离线了...");
    }

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        final Channel channel = ctx.channel();
        // 遍历channelGroup 根据不同情况 发送不同的消息
        channelGroup.forEach(ch -> {
            if (ch != channel){
                ch.writeAndFlush("用户[" + ch.remoteAddress() + "] 发送了消息：" + msg);
            }else{
                ch.writeAndFlush("我发送了消息：" + msg);
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    private String dateFormat(){
        return this.dateFormat(null);
    }

    private String dateFormat(String pattern){
        pattern = StringUtils.isBlank(pattern) ? "yyyy-MM-dd HH:mm:ss" : pattern;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }
}
