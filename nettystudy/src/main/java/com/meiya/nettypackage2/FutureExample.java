package com.meiya.nettypackage2;

import java.util.concurrent.*;

/*
* feature 实现异步机制
* */
public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Runnable task1 = new Runnable() {
            public void run() {
                System.out.println("i am task1......");
            }
        };

        Callable<Integer> task2 = new Callable<Integer>() {
            public Integer call() throws Exception {
                System.out.println("执行");
                return new Integer(100);
            }
        };

        Future<?> f1 = executorService.submit(task1);

        Future<?> f2 = executorService.submit(task2);

        System.out.println("task1 is completed? " + f1.isDone());

        System.out.println("task2 is completed? " + f2.isDone());

        while (f1.isDone()) {
            System.out.println("task1 completed");
            break;
        }
        while (f2.isDone()) {
            System.out.println("return value by task2: " + f2.get());
            System.out.println("completed");
            break;
        }

        executorService.shutdown();
    }
}
/*
NIO和netty、NIO2的局别
* NIO是一个比较底层的APIs，它依赖于操作系统的IO APIs。Java实现了统一的接口来操作IO，其在所有操作系统中的工作
行为是一样的，这是很伟大的。使用NIO会经常发现代码在Linux上正常运行，但在Windows上就会出现问题。我建议你如果使用
NIO编写程序，就应该在所有的操作系统上进行测试来支持，使程序可以在任何操作系统上正常运行；即使在所有的Linux系统上
都测试通过了，也要在其他的操作系统上进行测试；你若不验证，以后就可能会出问题。
 NIO2看起来很理想，但是NIO2只支持Jdk1.7+，若你的程序在Java1.6上运行，则无法使用NIO2。另外，Java7的NIO2中没
有提供DatagramSocket的支持，所以NIO2只支持TCP程序，不支持UDP程序。
 Netty提供一个统一的接口，同一语义无论在Java6还是Java7的环境下都是可以运行的，开发者无需关心底层APIs就可以轻
松实现相关功能。

NIO的限制
扩展ByteBuffer
 ByteBuffer是一个数据容器，但是可惜的是JDK没有开发ByteBuffer实现的源码；ByteBuffer允许包装一个byte[]来获得一个
实例，如果你希望尽量减少内存拷贝，那么这种方式是非常有用的。若果你想将ByteBuffer重新实现，那么不要浪费你的时间
了，ByteBuffer的构造函数是私有的，所以它不能被扩展。Netty提供了自己的ByteBuffer实现，Netty通过一些简单的APIs对
ByteBuffer进行构造、使用和操作，以此来解决NIO中的一些限制。

NIO对缓冲区的聚合和分散操作可能会操作内存泄露
很多Channel的实现支持Gather和Scatter。这个功能允许从从多个ByteBuffer中读入或写入到过个ByteBuffer，这样做可以
提供性能。操作系统底层知道如何处理这些被写入/读出，并且能以最有效的方式处理。如果要分割的数据再多个不同的
ByteBuffer中，使用Gather/Scatter是比较好的方式。
例如，你可能希望header在一个ByteBuffer中，而body在另外的ByteBuffer中；
下图显示的是Scatter(分散)，将ScatteringByteBuffer中的数据分散读取到多个ByteBuffer中：
可惜Gather/Scatter功能会导致内存泄露，知道Java7才解决内存泄露问题。使用这个功能必须小心编码和Java版本
*
* */



















