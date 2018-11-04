package com.meiya.nettypackage11;

        import io.netty.bootstrap.ServerBootstrap;
        import io.netty.channel.ChannelFuture;
        import io.netty.channel.EventLoopGroup;
        import io.netty.channel.nio.NioEventLoopGroup;
        import io.netty.channel.socket.nio.NioServerSocketChannel;
        import io.netty.handler.logging.LogLevel;
        import io.netty.handler.logging.LoggingHandler;

/*
* netty对protocol Buffer多协议的支持
* */
public class ServerTest {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup, workerLoopGroup).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ServerChannelInitilizer());

            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();

            channelFuture.channel().closeFuture().sync();

        } finally {
            eventLoopGroup.shutdownGracefully();

            workerLoopGroup.shutdownGracefully();
        }

    }

}
