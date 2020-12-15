package com.lifeng.stu_client.uilt;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceInfoUtil implements ApplicationListener<WebServerInitializedEvent> {
    /**
     * 声明event对象，该对象用于获取原型服务器的本地端口
     * @param webServerInitializedEvent
     */

    private static WebServerInitializedEvent event;
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        ServiceInfoUtil.event=event;
    }
    /**
     * 获取端口号
     */
    public static int getport(){
        int port = event.getWebServer().getPort();
        return port;
    }
}
