package IOStudy.inputstreamstudy;

/**
 * ByteArrayOutputStream使用
 * 字节数组输出流会在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组缓冲区中
 */
public class ByteArrayOutputStreamTest {


















}
/**
 * 构造方法
 * ①创建一个32字节(默认大小)的缓冲区
 * OutputStream bOut = new ByteArrayOutputStream();
 * ②创建一个大小为n字节的缓冲区
 * OutputStream bOut = new ByteArrayOutputStream(int n);
 *
 * 方法
 * public void reset();
 * 将此字节输出流的count字段重置为零，从而丢弃输出流中已累积的所有数据输出
 * public byte[] toByteArray()
 * 创建一个新分配的字节数组。数组的大小和当前输出流的大小，内容是当前输出流的拷贝
 * public String toString()
 * 将缓冲区的内容转换为字符串，根据平台的默认字符编码将字节转成字符
 * public void write(int w);
 * 将指定的字节写入此字节数组输出流
 * public void write(byte[]b,int off, int len)
 * 将指定字节数组中从偏移量off开始的len个字节写入此字节数组输出流
 * public void writeTo(OutputStream outSt)
 * 将此字节数组输出流的全部内容写入到指定的输出流参数中
 */









































