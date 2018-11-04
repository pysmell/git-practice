package com.meiya.netty权威指南学习.netty.package11;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import netty.codec.protobuf.SubscribeReqProto;
import netty.codec.protobuf.SubscribeRespProto;

public class SubReqServiceHandler extends ChannelHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        SubscribeReqProto.SubscribeReq subscribeReq = (SubscribeReqProto.SubscribeReq) msg;

        if ("linqw".equalsIgnoreCase(subscribeReq.getUserName())) {

            System.out.println("receive client info:[" + subscribeReq + "]");

            ctx.writeAndFlush(resp(subscribeReq.getSubReqID()));
        }

    }

    private SubscribeRespProto.SubscribeResp resp(int i) {
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqID(i);
        builder.setRespCode(0);
        builder.setDesc("Netty book send succeed");
        return builder.build();
    }
}
