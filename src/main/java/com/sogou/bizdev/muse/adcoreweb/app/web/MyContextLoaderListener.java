package com.sogou.bizdev.muse.adcoreweb.app.web;

import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.sogou.bizdev.muse.adcoreweb.app.common.ServiceLocator;


public class MyContextLoaderListener extends ContextLoaderListener {
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
       // super.contextInitialized(event);
        ApplicationContext factory = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        ServiceLocator.getInstance().setFactory(factory);
        
    }

}
