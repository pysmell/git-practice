package com.meiya.netty权威指南学习.netty.package9;

import com.google.protobuf.InvalidProtocolBufferException;
import netty.codec.protobuf.SubscribeReqProto;

import java.util.ArrayList;
import java.util.List;

/**
 * protobuf基础知识学习
 */
public class TestSubscribeReqProto {

    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {

        return SubscribeReqProto.SubscribeReq.parseFrom(body);

    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("Linqiwen");
        builder.setProductName("Netty Book");
        List<String> address = new ArrayList<>();
        address.add("xiamen haichang");
        address.add("NanJing huawei");
        address.add("hanzhou baidu");
        builder.addAllAddress(address);

        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {

        SubscribeReqProto.SubscribeReq req = createSubscribeReq();

        System.out.println("Before encode:" + req.toString());

        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));

        System.out.println("After decode:" + req2.toString());

        System.out.println("Assert equal : --->" + req.equals(req2));
    }
}


















