package com.meiya.nettypackage11;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<DataInfo.Datas> {

   /* @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DataInfo.Datas datas) throws Exception {

        System.out.println("来自客户端：" + channelHandlerContext.channel().remoteAddress());

        System.out.println("消息：" + datas.getPerson().getId() + " name:" + datas.getPerson().getName());
    }
*/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DataInfo.Datas datas = DataInfo.Datas.newBuilder().setDataType(DataInfo.Datas.DataType.dogType).setDog(DataInfo.Dog.newBuilder().setAge(23).setColor("红色").setHeight(3.5f)).build();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, DataInfo.Datas datas) throws Exception {

    }
}
