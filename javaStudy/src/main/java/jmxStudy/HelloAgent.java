package jmxStudy;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * agent层,使用JConsole进行控制，%JAVA_HOME%/bin%/jconsole.exe
 */
public class HelloAgent {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("jmxBean:name=hello");
        server.registerMBean(new Hello(), helloName);
        Thread.sleep(60*60*1000);
    }

}
