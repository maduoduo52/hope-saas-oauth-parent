package com.hope.saas.api.util.util;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author Maduo
 * @date 2020/3/20 17:43
 */
@Slf4j
public class IPUtil extends ClassicConverter {

    public static String IP = "";

    static {
        try {
            if (isWindowsOS()) {
                IP = InetAddress.getLocalHost().getHostAddress();
            } else {
                IP = getLinuxLocalIp();
            }
        } catch (Exception e) {
        }
    }

    /**
     * 判断操作系统是否是Windows
     *
     * @return
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }


    /**
     * 获取Linux下的IP地址
     *
     * @return IP地址
     * @throws SocketException
     */
    public static String getLinuxLocalIp() {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                ip = ipaddress;
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            ip = "127.0.0.1";
            log.error("", ex);
        }
        return ip;
    }

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        if (IP == null) {
            try {
                /**
                 * IP 只获取1次
                 */
                if (isWindowsOS()) {
                    IP = InetAddress.getLocalHost().getHostAddress();
                } else {
                    IP = getLinuxLocalIp();
                }

            } catch (Exception e) {
                log.error("", e);
            }
        }
        return IP;
    }
}
