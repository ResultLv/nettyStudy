package netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    //HttpObject-客户端和服务器端通讯的数据封装成HttpObject对象
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if (msg instanceof HttpRequest){
            System.out.println("msg 类型：" + msg.getClass());
            System.out.println("客户端地址：" + ctx.channel().remoteAddress());

            //回复信息给浏览器
            ByteBuf content = Unpooled.copiedBuffer( "hello, 我是服务器～", CharsetUtil.UTF_8);

            //构造http响应
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            //将构造好的response返回
            ctx.writeAndFlush(response);
        }
    }
}
