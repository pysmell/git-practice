package com.meiya.netty权威指南学习.netty.package11;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import netty.codec.protobuf.SubscribeReqProto;
import netty.codec.protobuf.SubscribeRespProto;

import java.util.ArrayList;
import java.util.List;

public class SubReqClientHandler extends ChannelHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int i = 0; i < 10; i++) {
            ctx.write(req(i));
        }
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        SubscribeRespProto.SubscribeResp subscribeResp = (SubscribeRespProto.SubscribeResp) msg;
        System.out.println("receive service info:[" + subscribeResp + "]");

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private SubscribeReqProto.SubscribeReq req(int i) {

        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(i);
        builder.setUserName("linqw");
        builder.setProductName("Netty Book");
        List<String> arrayList = new ArrayList<>();
        arrayList.add("beijing");
        arrayList.add("xiamen");
        arrayList.add("shanghai");
        builder.addAllAddress(arrayList);
        return builder.build();
    }
}
