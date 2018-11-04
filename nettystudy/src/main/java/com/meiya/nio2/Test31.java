package com.meiya.nio2;

import java.nio.file.*;

/**
 * 目录事件
 */
public class Test31 {

    public static void main(String[] args) {

        try(WatchService watchService = FileSystems.getDefault().newWatchService()) {

            Path dirPath = Paths.get("d:\\", "photo");


            //当注册目录时WatchKey就处于就绪状态，如果想要取消WatchKey.cancel()
            dirPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

            //当特定目录有事件发生WatchKey就会加到watchService的队列中
            while (true) {

                //watchKey的pollEvents()方法检索并删除所有挂起的事件
                WatchKey watchKey = watchService.take();

                //watchKey.pollEvents()如果没有获取到
                for (WatchEvent watchEvent : watchKey.pollEvents()) {

                    System.out.println("发生的目录：" + watchEvent.context().toString() + " 发生的事件: " + watchEvent.kind().name());

                }
                //事件处理完后，需要重置WatchKey对象，通过调用其reset()方法再次接收事件通知
                if (watchKey.isValid()) {  //isValid
                    System.out.println("重新监听");
                    watchKey.reset();

                }
            }


        } catch (Exception e) {

        }

    }

}
/**
 * 实现流程如下：
 * 1、为文件系统创建一个WatchService实例watcher
 * 2、为想监听的目录注册watcher，注册时，要注明监听那些事件
 * 3、在无限循环里面等待事件的发生时，key发出信号，并且加入到watcher的queue
 * 4、从watcher的queue查找到key，从中获取到文件名等相关信息
 * 5、遍历key的各种事件
 * 6、重置key，重新等待事件
 * 7、关闭服务
 */

/**
 * 以下类和接口
 * Watchable接口 实现此接口才能被监听
 * WatchService接口 监听服务 take()获取WatchKey，会阻塞  poll()获取WatchKey，不会阻塞，如果没有事件发生，返回null，poll(timeout, unit)
 * WatchKey接口 这是使用 WatchService 注册后被监控对象的唯一标识
 * WatchEvent接口 表示注册到监视服务的对象事件。它的kind()方法返回发生的事件的类型
 *                  context()方法返回一个Path对象，他表示事件发生的那个条目
 *                  count()方法返回特定通知的事件发生次数，如果返回一个大于1的值，那么就是个重复事件
 * StandardWatchEventKinds类定义了用于表示事件种类的常量
 * ENTRY_CREATE  创建目录实体，当目录中的文件重命名和移动文件到此目录时，也会触发此事件
 * ENTRY_DELETE  删除目录实体，当目录中的文件重命名和移动文件出此目录时，也会触发此事件
 * ENTRY_MODIFY  修改目录实体。修改事件在具有平台相关性，但是无论什么平台，只要修改了文件内容，
 *               就会触发这个事件。在有的平台上，改变文件属性也会触发这个事件。
 * OVERFLOW 表示丢失或丢弃的事件，注册时不要监听这个事件
 */

























































