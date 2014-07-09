package com.sogou.bizdev.muse.adcoreweb.app.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServiceLocator {
    private static final Logger LOGGER = Logger.getLogger(ServiceLocator.class);

    private static ServiceLocator locator = null;

    private static BeanFactory factory = null;

    public BeanFactory getFactory() {
        if (factory == null) {
            // here just log out
            LOGGER.warn("ServiceLocator.factory is null maybe not config ContextLoaderListener correctly in web.xml");
            factory = new ClassPathXmlApplicationContext("appContext-proterozoic.xml");
        }

        return factory;
    }

    private ServiceLocator() {
    }

    public void setFactory(BeanFactory factory) {
        ServiceLocator.factory = factory;
    }

    public synchronized static ServiceLocator getInstance() {
        if (locator == null) {
            locator = new ServiceLocator();
        }

        return locator;
    }

    /**
     * 从现有bean容器根据beanName中获取bean
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
    	if(null == factory) {
    		throw new RuntimeException("factory is null,u should set it first.");
    	}

        return factory.getBean(beanName);
    }

    /**
     * 从现有bean容器根据beanName中获取bean
     * 如果容器不存在则根据servicelocator.properties的配置创建容器
     * @param beanName
     * @return
     */    
    public Object getBeanWithCreateContainer(String beanName) {
        return getFactory().getBean(beanName);
    }

}
