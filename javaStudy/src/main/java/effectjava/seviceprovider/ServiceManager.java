package effectjava.seviceprovider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceManager {

    private static final Map<String, SubwayProviderInterface> PROVIDER_INTERFACE_MAP = new ConcurrentHashMap<>();

    private ServiceManager() {
    }

    public static void registerProvider(String name, SubwayProviderInterface providerInterface) {
        PROVIDER_INTERFACE_MAP.put(name, providerInterface);
    }

    public static SubWayInterface getService(String name) {
        SubwayProviderInterface subwayProviderInterface = PROVIDER_INTERFACE_MAP.get(name);
        if (subwayProviderInterface == null) {
            throw new IllegalArgumentException(
                    "No provider registered with name:" + name);
        }
        return subwayProviderInterface.getService();
    }

}















