package hello.proxy.config;

import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.app.v1.*;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppProxyConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace){
        return new OrderControllerInterfaceProxy(new OrderControllerV1Impl(orderService(logTrace)), logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        return new OrderServiceInterfaceProxy(new OrderServiceV1Impl(orderRepository(logTrace)), logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(orderRepository, logTrace);
    }

}
