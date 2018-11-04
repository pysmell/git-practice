package com.meiya.nettypackage10;

import com.example.tutorial.AddressBookProtos;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

public class ServerHandler extends SimpleChannelInboundHandler<AddressBookProtos.AddressBook> {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, AddressBookProtos.AddressBook addressBook) throws Exception {

    }

  /*  @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, AddressBookProtos.AddressBook addressBook) throws Exception {
        List<AddressBookProtos.Person> list = addressBook.getPeopleList();
        list.forEach(p->{
            System.out.println("name:" + p.getName() + "email:" + p.getEmail());
        });
    }*/
}
