package jmxStudy;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * 通过jmx提供的工具页访问,http://localhost:8082/页面进行访问
 */
public class HelloPageAgent {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {

        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("jmxBean:name=hello");
        server.registerMBean(new Hello(), helloName);
        ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
        HtmlAdaptorServer adaptorServer = new HtmlAdaptorServer();
        server.registerMBean(adaptorServer,adapterName);
        adaptorServer.start();
    }
}










































