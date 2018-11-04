package getIpAddress;

import java.net.*;
import java.util.Enumeration;
import java.util.List;

public class InterfaceAddressTest {

    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            while (interfaceEnumeration.hasMoreElements()) {
                NetworkInterface networkInterface = interfaceEnumeration.nextElement();
                List<InterfaceAddress> inetAddressEnumeration = networkInterface.getInterfaceAddresses();
                for (InterfaceAddress interfaceAddress : inetAddressEnumeration) {

                    System.out.println("ip address" + interfaceAddress.getAddress());
                    System.out.println("broadcast" + interfaceAddress.getBroadcast());
                    System.out.println("networkPrefixLength" + interfaceAddress.getNetworkPrefixLength());
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }


    }

}
