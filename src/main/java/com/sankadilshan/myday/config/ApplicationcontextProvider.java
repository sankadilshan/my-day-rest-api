package com.sankadilshan.myday.config;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApplicationcontextProvider implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public ApplicationcontextProvider(ApplicationContext applicationContext){
        this.applicationContext= applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext= applicationContext;
    }
}
