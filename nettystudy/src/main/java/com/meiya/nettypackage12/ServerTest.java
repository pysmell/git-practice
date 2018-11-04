package com.meiya.nettypackage12;

/*
* netty组件理解
* BootStrap or ServerBootstrap
* EventLoop
* EventLoopGroup·
* ChannelPipeline
* Channel
* Future or ChannelFuture
* ChannelInitializer
* ChannelHandler
* */
public class ServerTest {
}

/*
* Bootstrap,一个netty应用通常由一个BootStrap开始，他主要是配置整个netty程序，串联各个组件
*
* Handler，为了支持各种协议和处理数据的方式，便诞生了handler组件。handler主要用来处理各种事件，这里的事件很广泛，
* 比如可以是连接、数据接收、异常、数据转换等。
*
* ChannelInboundHandler,一个最常用的Handler。这个handler的作用就是处理接收到数据时的事件，
* 也就是我们的业务逻辑一般都是写在这个Handler里面的，ChannelInboundHandler就是用来处理我们的核心业务逻辑
*
* ChannelInitializer，当一个链接建立时，我们需要知道怎么来接收或者发送数据，当然，我们有各种各样的Handler实现来处理它
* 那么ChannelInitializer便是用来配置这些Handler，他会提供一个ChannelPiepline，并把handler加入到ChannelPiepline
*
* ChannelPipeline，一个netty应用基于ChannelPipeline机制，这种机制需要依赖EventLoop和EventLoopGroup，因为它们三个都和事件或者事件处理相关
*
* EventLoops（理解为线程）的目的是为Channel处理io操作，一个EventLoop可以为多个channel服务
* EventLoopGroup会包含多个EventLoop
* Channel代表了一个socket链接，它和EventLoop一起用来参与io处理
* Future，在netty中所有的io操作都是异步的，因此不能立刻得知消息是否被正确处理，但是我们可以过一会等它执行完成或者直接注册一个监听，具体的实现就是通过Future和ChannelFutures,
* 他们可以注册一个监听，当操作执行成功或失败时监听会自动触发。总之，所有的操作都会返回一个ChannelFuture
*
* 二、netty是如何处理链接请求和业务逻辑的？
*     netty是一个非阻塞的、事件驱动的、网络编程框架。netty会用线程来处理io事件，netty具体的操作：
*     一个Channel会对应一个EventLoop，而一个EventLoop会对应着一个线程，也就是说一个线程负责一个channel
*     当一个连接到达，Netty会注册一个channel，然后EventLoopGroup会分配一个EventLoop绑定到这个channel，
*     在这个channel的整个生命周期过程中，都会由绑定的这个EventLoop来为它服务，而这个EventLoop就是一个线程
*     EventLoop继承自EventLoopGroup，也就是说在某些情况下，我们可以把一个EventLoopGroup当做一个EventLoop来用
*
* 三、如何配置一个netty应用，
*    bootstrap有两种   一种用于客户端，一种用于server端
*     1.第一个最明显的区别是，ServerBootstrap用于Server端，通过调用bind()方法来绑定到一个端口监听连接；
*     Bootstrap用于Client端，需要调用connect()方法来连接服务器端，但我们也可以通过调用bind()方法返回的ChannelFuture
*     中获取Channel去connect服务器端。

      2.客户端的Bootstrap一般用一个EventLoopGroup，而服务器端的ServerBootstrap会用到两个（这两个也可以是同一个实例）。
      为何服务器端要用到两个EventLoopGroup呢？这么设计有明显的好处，如果一个ServerBootstrap有两个EventLoopGroup，那么就可以把第一个EventLoopGroup用来专门负责绑定到端口监听连接事件，而把第二个EventLoopGroup用来处理每个接收到的连接，下面我们用一幅图来展现一下这种模式：


     PS: 如果仅由一个EventLoopGroup处理所有请求和连接的话，在并发量很大的情况下，这个EventLoopGroup有可能会忙于处理已经接收到的连接而不能及时处理新的连接请求，用两个的话，会有专门的线程来处理连接请求，不会导致请求超时的情况，大大提高了并发处理能力。

      我们知道一个Channel需要由一个EventLoop来绑定，而且两者一旦绑定就不会再改变。一般情况下一个EventLoopGroup中的EventLoop数量会少于Channel数量，那么就很有可能出现一个多个Channel公用一个EventLoop的情况，这就意味着如果一个Channel中的EventLoop很忙的话，会影响到这个Eventloop对其它Channel的处理，这也就是为什么我们不能阻塞EventLoop的原因。

     四、netty如何处理数据
     我们的应用程序中用到的最多的应该就是ChannelHandler，我们可以这么想象，数据在一个ChannelPipeline中流动，而ChannelHandler便是其中的一个个的小阀门，这些数据都会经过每一个ChannelHandler并且被它处理。这里有一个公共接口ChannelHandler:
    ChannelHandler有两个子类ChannelInboundHandler和ChannelOutboundHandler，这两个类对应了两个数据流向，如果数据是从外部流入我们的应用程序，我们就看做是inbound，相反便是outbound。其实ChannelHandler和Servlet有些类似，一个ChannelHandler处理完接收到的数据会传给下一个Handler，或者什么不处理，直接传递给下一个
    一个ChannelPipeline可以把两种Handler（ChannelInboundHandler和ChannelOutboundHandler）混合在一起，当一个数据流进入ChannelPipeline时，它会从ChannelPipeline头部开始传给第一个ChannelInboundHandler，当第一个处理完后再传给下一个，一直传递到管道的尾部。与之相对应的是，当数据被写出时，它会从管道的尾部开始，先经过管道尾部的“最后”一个ChannelOutboundHandler，当它处理完成后会传递给前一个ChannelOutboundHandler
    数据在各个Handler之间传递，这需要调用方法中传递的ChanneHandlerContext来操作， 在netty的API中提供了两个基类分ChannelOutboundHandlerAdapter和ChannelOutboundHandlerAdapter，他们仅仅实现了调用ChanneHandlerContext来把消息传递给下一个Handler，因为我们只关心处理数据，因此我们的程序中可以继承这两个基类来帮助我们做这些，而我们仅需实现处理数据的部分即可。

     我们知道InboundHandler和OutboundHandler在ChannelPipeline中是混合在一起的，那么它们如何区分彼此呢？其实很容易，因为它们各自实现的是不同的接口，对于inbound event，Netty会自动跳过OutboundHandler,相反若是outbound event，ChannelInboundHandler会被忽略掉。

     当一个ChannelHandler被加入到ChannelPipeline中时，它便会获得一个ChannelHandlerContext的引用，而ChannelHandlerContext可以用来读写Netty中的数据流。因此，现在可以有两种方式来发送数据，一种是把数据直接写入Channel，一种是把数据写入ChannelHandlerContext，它们的区别是写入Channel的话，数据流会从Channel的头开始传递，而如果写入ChannelHandlerContext的话，数据流会流入管道中的下一个Handler。


     Encoders和Decoders
       因为我们在网络传输时只能传输字节流，因此，才发送数据之前，我们必须把我们的message型转换为bytes，与之对应，我们在接收数据后，必须把接收到的bytes再转换成message。我们把bytes to message这个过程称作Decode(解码成我们可以理解的)，把message to bytes这个过程成为Encode。

     Netty中提供了很多现成的编码/解码器，我们一般从他们的名字中便可知道他们的用途，如ByteToMessageDecoder、MessageToByteEncoder，如专门用来处理Google Protobuf协议的ProtobufEncoder、 ProtobufDecoder。

     我们前面说过，具体是哪种Handler就要看它们继承的是InboundAdapter还是OutboundAdapter，对于Decoders,很容易便可以
     知道它是继承自ChannelInboundHandlerAdapter或 ChannelInboundHandler，因为解码的意思是把ChannelPipeline
     传入的bytes解码成我们可以理解的message（即Java Object），而ChannelInboundHandler正是处理Inbound Event，
     而Inbound Event中传入的正是字节流。Decoder会覆盖其中的“ChannelRead()”方法，在这个方法中来调用具体的decode方法
     解码传递过来的字节流，然后通过调用ChannelHandlerContext.fireChannelRead(decodedMessage)方法把编码好的Message
     传递给下一个Handler。与之类似，Encoder就不必多少了。
* */























































