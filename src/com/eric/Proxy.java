package com.eric;

/**
 * @Author: Eric
 * @Date: 2020/8/26 8:38 下午
 * @title: 代理模式
 * @Description: 在不修改原始类的前提下为其附加功能
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * dynamic proxy
 */
class MetricsCollectorProxy {
    private MetricsCollector metricsCollector;
    public MetricsCollectorProxy(){
        metricsCollector = new MetricsCollector();
    }

    private class DynamicProxyHandler implements InvocationHandler{

        private Object proxiedObject;

        public DynamicProxyHandler(Object proxiedObject){
            this.proxiedObject = proxiedObject;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long startTimestamp = System.currentTimeMillis();
            Object result = method.invoke(proxiedObject,args);
            long endTimestamp = System.currentTimeMillis();
            long responseTime = endTimestamp - startTimestamp;
            String apiName = proxiedObject.getClass() + ":" + method.getName();
            metricsCollector.doSomething();
            return result;
        }
    }

    public Object createProxy(Object proxyProject){
        Class<?>[] interfaces = proxyProject.getClass().getInterfaces();
        DynamicProxyHandler handler = new DynamicProxyHandler(proxyProject);
        return Proxy.newProxyInstance(proxyProject.getClass().getClassLoader(),interfaces,handler);
    }
}

class MetricsCollector{
    public void doSomething(){

    }
}