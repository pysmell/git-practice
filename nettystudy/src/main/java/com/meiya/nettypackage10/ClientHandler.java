package com.meiya.nettypackage10;

import com.example.tutorial.AddressBookProtos;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

public class ClientHandler extends SimpleChannelInboundHandler<AddressBookProtos.AddressBook> {


  /*  @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, AddressBookProtos.AddressBook addressBook) throws Exception {
        List<AddressBookProtos.Person> list = addressBook.getPeopleList();

        System.out.println("来自服务端：" + channelHandlerContext.channel().remoteAddress());
        list.forEach(p->{
            System.out.println("name: " + p.getName() + "email: " + p.getEmail());
        });
    }
*/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        AddressBookProtos.AddressBook addressBook = AddressBookProtos.AddressBook.newBuilder().addPeople(AddressBookProtos.Person.newBuilder().setEmail("382394323@qq.com").setName("zhangsan").setId(12)).addPeople(AddressBookProtos.Person.newBuilder().setEmail("789@163.com").setName("lisi").setId(13)).build();
        ctx.writeAndFlush(addressBook);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, AddressBookProtos.AddressBook addressBook) throws Exception {

    }
}
















