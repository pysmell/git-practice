package com.meiya.nio2;

/**
 * 文件属性
 */
public class Test32 {
}

/**
 * 最顶层的是FileAttributeView 表示所有文件属性视图的父视图
 * BasicFileAttributeView：基础文件属性，包括文件修改时间、创建时间、最后修改时间、文件大小、是否为目录、是否为快捷方式等，这也是最常用的
 * FileOwnerAttributeView：文件主人的相关属性，其只有两个功能，一个是获取当前文件的所属人，另一个修改当前文件的所属人
 * DosFileAttributeView：获取和修改文件的DOS属性，比如检查文件是否为隐藏、是否是归档文件，其使用程度仅次于BasicFileAttributeView
 * UserDefinedFileAttributeView: 用户自定义文件属性，一般OS在开发层面都允许用户自定义文件的一些属性，自定义属性
 * 必然使用键值对来表示，键是属性的名称（需要自定义），而值是属性的值（属性的值可以是任意类型的，因此需要用二进制字节来保存，读取和保存时需要用到ByteBuffer）、
 * 对于最常用的BasicFileAttributeView和DosFileAttributeView，需要通过各自的readAttributes方法分别获得BasicFileAttribute和
 * DosFileAttribute对象才能真正的访问其中的属性以及修改属性，其他几种不常用的文件属性可以直接通过视图访问和修改属性
 * PosixFileAttributeView扩展了BasicFileAttributeView并添加了对支持POSIX标准(如UNIX)的系统上可用的属性的支持。 它允许我们管理所有者，组和[相关访问]权限。
 * ACL代表访问控制列表。AclFileAttributeView管理文件的ACL。
 */

/**
 * 通过Files的工厂方法获取一个具体的文件属性视图对象：
 * 1）访问文件属性在NIO.2中必然有两要素，一个是目标文件，另一个是属性的类型
 * 2）Files的工具方法
 * static <V extends FileAttributeView> V Files.getFileAttributeView(Path path, Class<V> type, LinkOption... options);
 * path目标文件
 * type是属性的类型
 * options是链接选项 ，默认情况下，如果文件是符号链接，则读取符号链接的目标属性。
 * 如果我们指定NOFOLLOW_LINKS作为选项，则读取符号链接的属性，而不是其目标的属性。
 */

/**
 * BasicFileAttributeView：只读视图（只能查看属性，不能修改的属性）
 * 1）基础文件属性不能直接通过视图来访问，必须要通过视图的readAttribute方法获得对应的BasicFileAttributes属性集合对象，
 * 然后用该属性集合对象才能正常的访问和修改文件属性：BasicFileAttribute BasicFileAttributeView.readAttributes();
 *
 *  BasicFileAttriubtes中共有8大方法获取文件的基础属性信息：3时间-4类型-1大小（都是BasicFileAttributes的对象方法）
 *      i. 查看时间：
 * a. FileTime lastModifiedTime();  // 最后修改时间
 * b. FileTime lastAccessTime();  // 最近访问时间
 * c. FileTime creationTime();  // 创建时间
 * 一般会调用FileTime的toMillis得到毫秒时间：long FileTime.toMillis();
 * ii. 查看文件类型：
 * a. boolean isRegularFile();  // 是否是正常文件（txt、exe等非目录类型的文件）
 * b. boolean isDirectory();  // 是否是目录
 * c. boolean isSymbolicLink();  // 是否为符号链接，即快捷方式
 * d. boolean isOther();  // 其它类型（特别是Unix中的块设备文件等，但是在Windows中不常见）
 * iii. 文件大小：long size();  // 文件的字节大小
 *
 */

/**
 * DosFileAttributeView：可查看可修改的视图
 * 查看属性和BasicFileAttributeView一样，必须通过readAttributes获得DosFileAttributes集合，然后调用集合的方法查看属性：
 * DosFileAttributes DosFileAttributeView.readAttributes();
 * 由于DosFileAttributes直接继承自BasicFileAttributes，因此BasicFileAttributes中的查看功能DosFileAttributes里全都有，这里只介绍DosFileAttributes里独有的查看功能：都是DosFileAttributes的对象方法
 * i. boolean isReadOnly(); // 是否是只读
 * ii. boolean isHidden();  // 是否是隐藏文件
 * iii. boolean isArchive(); // 是否是归档文件（即压缩文件）
 * iv. boolean isSystem();  // 是否是操作系统组件（Windows操作系统的组件）
 * ！！由于DosFileAttributes是Dos系统特有的，因此如果专门是Windows上的应用可以直接用DosFileAttributeView，但如果是跨平台的或者在其它平台上运行的最好是只用BasicFileAttributeView；
 * 3) 修改Dos文件属性直接使用视图修改：都是DosFileAttributeView的对象方法，和查看是相对应的
 * i. void setReadOnly(boolean value);  // 设置只读
 * ii. void setHidden(boolean value); // 设置隐藏
 * iii. void setArchive(boolean value);  // 设置归档
 */

/**
 *自定义属性——UserDefinedFileAttributeView：
 *1) Java允许用视图UserDefinedFileAttributeView为文件自定义属性；
 *2) 属性用键值对定义，键即属性的名称，是String类型的，键所对应的值就是属性值，直接用二进制字节码来表示（这样可以表示任意类型的数据）；
 *！！接下来介绍的都是UserDefinedFileAttributeView的对象方法；
 *3) 添加属性：int write(String name, ByteBuffer src);  // 直接向视图中写入一个键值对
 *4) 读取属性：int read(String name, ByteBuffer dst);  // 读取一个键值对
 *！！上面的返回值都表示写入/读取了多少个字节；
 *5) 属性值的大小：int size(String name);  // 字节
 *6) 列出所有的自定义属性名：List<String> list();
 */


































