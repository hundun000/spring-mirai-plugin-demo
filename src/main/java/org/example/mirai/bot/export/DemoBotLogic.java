package org.example.mirai.bot.export;

import java.util.Arrays;

import org.example.mirai.bot.configuration.MiraiAdaptedApplicationContext;
import org.example.mirai.bot.service.DemoService;
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
    
    DemoService service;
    
    public DemoBotLogic(JvmPlugin plugin) {
        this.parent = plugin;
        
        @SuppressWarnings("resource")
        MiraiAdaptedApplicationContext context = new MiraiAdaptedApplicationContext(false);
        // show context is work
        parent.getLogger().info("ApplicationContext created, has beans = " + Arrays.toString(context.getBeanDefinitionNames()));
        
        // use bean
        this.service = context.getBean(DemoService.class);
        parent.getLogger().info(service.check());
        
    }

    
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull GroupMessageEvent event) throws Exception { 
        // use service for event
        if (event.getMessage().contentToString().equals("test")) {
            event.getGroup().sendMessage(service.work());
        }
        return ListeningStatus.LISTENING;
    }
    
    
}
