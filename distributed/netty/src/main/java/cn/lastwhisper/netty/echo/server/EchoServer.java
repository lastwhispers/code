package cn.lastwhisper.netty.echo.server;

import cn.lastwhisper.netty.common.Constant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * echo服务器端
 *
 *  绑定到服务器将在其上监听并接受传入连接请求的端口；
 *  配置 Channel，以将有关的入站消息通知给 EchoServerHandler 实例
 *
 * @author lastwhisper
 * @date 2020/2/21
 */
public class EchoServer {

    public static void main(String[] args) throws Exception {
        new EchoServer().start(Constant.port);
    }

    public void start(int port) throws Exception {
        //1、创建ChannelHandler和EventLoopGroup
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        //2、创建ServerBootstrap
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(group)
                    //3、指定所使用的 NIO传输 Channel
                    .channel(NioServerSocketChannel.class)
                    //4、使用指定的端口设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    //5、添加一个EchoServerHandler到子Channel的ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            //EchoServerHandler 被标注为@Shareable，所以我们可以总是使用同样的实例
                            //这里对于所有的客户端连接来说，都会使用同一个 EchoServerHandler，因为其被标注为@Sharable，
                            //这将在后面的章节中讲到。
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            //6、异步地绑定服务器；调用 sync()方法阻塞等待直到绑定完成
            ChannelFuture cf = bootstrap.bind().sync();
            //7、获取 Channel 的CloseFuture，并且阻塞当前线程直到它完成
            cf.channel().closeFuture().sync();
        } finally {
            //8、关闭 EventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
        }

    }
}