package com.meiya.nio2;

/**
 * 异步IO
 */
public class Test39 {
}
/**
 * 在同步文件I/O中,对I/O操作的请求将等待，直到I/O操作完成。
 * 在异步I/O中，I/O操作的请求由系统异步执行。
 * 当系统完成文件I/O时，他通知应用程序其请求的完成
 * java.nio.channels.AsynchronousFileChannel类表示异步文件通道。
 * AsynchronousFileChannel类的静态open()方法获取AsynchronousFileChannel类的实例。
 * 以下代码显示了如何获取WRITE的异步文件通道。
 * Path  path   = Paths.get("C:\\Java_Dev\\rainbow.txt");
 * AsynchronousFileChannel afc   = AsynchronousFileChannel.open(path, WRITE,  CREATE);
 *AsynchronousFileChannel提供了两种方法来处理异步文件I/O操作的结果。
 *Using a java.util.concurrent.Future object.
 *Using a java.nio.channels.CompletionHandler object.
 支持异步文件I/O操作的AsynchronousFileChannel类的每个方法有两个版本。

 一个版本返回一个Future对象，我们可以使用它来处理所请求的异步操作的结果。

 Future对象的get()方法返回写入文件通道的字节数。

 以下代码使用返回Future对象的write()方法的版本:

 ByteBuffer dataBuffer  = a buffer;
 long  startPosition = 0;
 Future<Integer> result = afc.write(dataBuffer, startPosition);
 一旦我们得到一个Future对象，我们可以使用轮询方法或阻塞等待方法来处理异步文件I/O的结果。

 下面的代码显示了轮询方法，它将继续调用Future对象的isDone()方法，以检查I/O操作是否完成:

 while (!result.isDone()) {
 }
 int writtenNumberOfBytes = result.get();
 AsynchronousFileChannel类的另一个版本的方法获得一个CompletionHandler对象，当请求的异步I/O操作完成或失败时，该对象的方法被调用。

 CompletionHandler接口有两个方法:completed()和failed()。

 当所请求的I/O操作成功完成时，将调用completed()方法。

 当请求的I/O操作时失败，则调用failed()方法。
 */