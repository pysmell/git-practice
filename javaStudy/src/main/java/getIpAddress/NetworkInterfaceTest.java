package getIpAddress;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * NetworkInterface使用，获得本机在局域网内ip地址
 */
public class NetworkInterfaceTest {

    public static void main(String[] args) {

        try {
            Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            while (interfaceEnumeration.hasMoreElements()) {
                NetworkInterface networkInterface = interfaceEnumeration.nextElement();
                Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();
                while (inetAddressEnumeration.hasMoreElements()) {
                    InetAddress address = inetAddressEnumeration.nextElement();

                    if (address instanceof Inet4Address) { //只关心IPv4地址
                        System.out.println("网卡接口名称：" + address.getHostName());
                        System.out.println("网卡接口地址：" + address.getHostAddress());
                        System.out.println("---" + address.getCanonicalHostName());
                        System.out.println(address.isSiteLocalAddress());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
}
/**
 * NetworkInterface所有方法
 * 得到当前机器上所有的网络接口
 * public static Enumeration<NetworkInterface> getNetworkInterfaces()
 * 返回绑定到该网卡的所有的IP地址，一个网络接口可以绑定多个IP地址，通常情况下，一个网络接口都是只对应一个Ip地址
 * public Enumeration<InetAddress> getInetAddresses()
 * 返回的是一个绑定到该网络接口的所有 InterfaceAddress 的集合，相比于 InetAddress 的区别在于它除了具有一个 IP 地址（InetAddress），还包括了该地址对应的广播地址和掩码长度
 * public List<InterfaceAddress> getInterfaceAddresses()
 */


























































