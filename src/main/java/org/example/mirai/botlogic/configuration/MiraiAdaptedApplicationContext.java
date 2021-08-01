package org.example.mirai.botlogic.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author hundun
 * Created on 2021/07/01
 */
public class MiraiAdaptedApplicationContext extends AnnotationConfigApplicationContext {
    
    /**
     * @param lateRefresh if you want to add more bean to context before refresh(), set true and then refresh() by manual. 
     */
    public MiraiAdaptedApplicationContext(boolean lateRefresh) {
        super();
        this.setClassLoader(this.getClass().getClassLoader());
        // can add more scanBasePackage
        this.scan(
                "org.example.mirai.botlogic"
                );
        if (!lateRefresh) {
            this.refresh();
        }
    }
}
