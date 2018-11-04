package com.meiya.nettypackage11;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<DataInfo.Datas> {

/*    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DataInfo.Datas datas) throws Exception {
        System.out.println("来自服务端：" + channelHandlerContext.channel().remoteAddress());
        System.out.println("消息：" + datas.getDog().getHeight() + "年龄：" + datas.getDog().getAge());
    }*/

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DataInfo.Datas datas = DataInfo.Datas.newBuilder().setDataType(DataInfo.Datas.DataType.personType).setPerson(DataInfo.Person.newBuilder().setId(123).setGender(DataInfo.Person.Gender.female).setName("小明")).build();
        ctx.writeAndFlush(datas);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, DataInfo.Datas datas) throws Exception {

    }
}
