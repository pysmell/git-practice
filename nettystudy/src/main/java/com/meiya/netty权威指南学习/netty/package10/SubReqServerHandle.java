package com.meiya.netty权威指南学习.netty.package10;

import io.netty.channel.ChannelHandlerAdapter;

import io.netty.channel.ChannelHandlerContext;
import netty.codec.protobuf.SubscribeReqProto;
import netty.codec.protobuf.SubscribeRespProto;

public class SubReqServerHandle extends ChannelHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.close();

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;

        if ("linqw".equalsIgnoreCase(req.getUserName())) {
            System.out.println("Service accept client subscribe req : [" + req.toString() + "]");
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }

    }

    private SubscribeRespProto.SubscribeResp resp(int subReqID) {
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqID(subReqID);
        builder.setRespCode(0);
        builder.setDesc("Netty book order succeed");
        return builder.build();
    }
}
