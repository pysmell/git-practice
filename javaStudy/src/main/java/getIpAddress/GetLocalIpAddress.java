package getIpAddress;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 获取本地地址
 */
public class GetLocalIpAddress {

    public static void main(String[] args) {

        try {
            Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            while (interfaceEnumeration.hasMoreElements()) {
                NetworkInterface networkInterface = interfaceEnumeration.nextElement();
                Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();
                while (inetAddressEnumeration.hasMoreElements()) {
                    InetAddress address = inetAddressEnumeration.nextElement();

                    if (address.isSiteLocalAddress()) { //只关心本地地址
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
