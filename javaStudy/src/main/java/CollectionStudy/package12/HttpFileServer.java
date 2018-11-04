package CollectionStudy.package12;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * HTTP文件服务器服务端
 */
public class HttpFileServer {

    private static final String DEFAULT_URL = "/src/main/java/CollectionStudy/";

    public void run(final int port, final String url) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //将ByteBuf解码成HttpRequest(requestLine和header)和HttpContent(body)，可能存在多个HttpContent，因为body有可能过大，HttpContent实际是个ByteBuf，长度固定
                    socketChannel.pipeline().addLast(new HttpRequestDecoder());
                    //为此需要将HttpRequest和多个HttpContent合成一个完整的FullHttpRequest
                    socketChannel.pipeline().addLast(new HttpObjectAggregator(6553600));
                    //将FullHttpRequest编码成ByteBuf，编码成requestLine、header、body
                    socketChannel.pipeline().addLast(new HttpResponseDecoder());
                    //采用分块协议，可以防止内存溢出，分成块传输，10k的内容，可以只使用1k的块，重复使用
                    //主要作用是支持异步发送大的码流(大的文件传输)，但不占用过多的内存，防止发送java内存溢出
                    socketChannel.pipeline().addLast(new ChunkedWriteHandler());
                    socketChannel.pipeline().addLast(new HttpFileServerHandler(url));
                }
            });

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            System.out.println("HTTP 文件服务器启动，地址是：" + "http://localhost:" + port + url);
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int port = 8080;
        String url = DEFAULT_URL;

        if (args != null && args.length > 0) {
            try {

                port = Integer.valueOf(args[0]);

            } catch (NumberFormatException e) {
                //采用默认端口
            }
        }

        if (args != null && args.length > 1) {
            url = args[1];
        }

        new HttpFileServer().run(port, url);

    }

}
/**
 * HttpMethod:主要是对method的封装，包含method序列化的操作
 * HttpVersion:对version的封装，netty包含1.0和1.1的版本
 * QueryStringDecoder:主要是对url进行封装，解析path和url上面的参数(Tips：在tomcat中如果提交的post请求是application/x-www-form-urlencoded，则getParameter获取的是包含url后面和body里面所有的参数，而在netty中，获取的仅仅是url上面的参数)
 * HttpHeaders:包含对header的内容进行封装及操作
 * HttpContent：是对body进行封装，本质上就是一个ByteBuf。如果ByteBuf的长度是固定的，则请求的body
 * 过大可能包含多个HttpContent，其中最后一个为LastHttpContent(空的HttpContent)，用来说明body的结束
 * HttpRequest：主要包含对RequestLine和Header的组合
 * FullHttpRequest：主要包含对HttpRequest和HttpContent的组合
 */



































