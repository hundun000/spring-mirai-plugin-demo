package com.hundun.mirai.bot.export;

import java.util.Arrays;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



import net.mamoe.mirai.console.plugin.jvm.JvmPlugin;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListenerHost;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.GroupMessageEvent;


/**
 * @author hundun
 * Created on 2021/06/29
 */
public class DemoBotLogic implements ListenerHost {
    
    JvmPlugin parent;
    
    AnnotationConfigApplicationContext context;
    
    public DemoBotLogic(JvmPlugin plugin) {
        this.parent = plugin;
        
        context = new AnnotationConfigApplicationContext();
        context.setClassLoader(this.getClass().getClassLoader());
        context.scan("com.hundun.mirai.bot");
        context.refresh();
        parent.getLogger().info("ApplicationContext created, has beans = " + Arrays.toString(context.getBeanDefinitionNames()));

    }
    
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull GroupMessageEvent event) throws Exception { 
        if (event.getMessage().contentToString().equals("test")) {
            DemoBean bean = context.getBean(DemoBean.class);
            event.getGroup().sendMessage(bean.service());
        }
        return ListeningStatus.LISTENING;
    }
    
    
}
