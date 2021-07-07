package com.hundun.mirai.springplugindemo.bot.export;

import java.io.IOException;
import java.util.Arrays;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hundun.mirai.springplugindemo.bot.configuration.MiraiAdaptedApplicationContext;
import com.hundun.mirai.springplugindemo.bot.service.DemoService;

import net.mamoe.mirai.console.plugin.jvm.JvmPlugin;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListenerHost;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.utils.ExternalResource;


/**
 * @author hundun
 * Created on 2021/06/29
 */
public class DemoBotLogic implements ListenerHost {
    
    JvmPlugin plugin;
    
    DemoService service;
    
    ExternalResource demoExternalResource;
    
    public DemoBotLogic(JvmPlugin plugin) {
        this.plugin = plugin;
        
        @SuppressWarnings("resource")
        MiraiAdaptedApplicationContext context = new MiraiAdaptedApplicationContext();
        this.service = context.getBean(DemoService.class);
        
        // show spring is work
        plugin.getLogger().info("ApplicationContext created, has beans = " + Arrays.toString(context.getBeanDefinitionNames()));
        plugin.getLogger().info(service.check());
        
        demoExternalResource = ExternalResource.create(plugin.resolveDataFile("demo.png"));
    }

    
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull GroupMessageEvent event) throws Exception { 
        // use service for event
        if (event.getMessage().contentToString().equals("test service")) {
            event.getGroup().sendMessage(service.work());
        }
        
        // test close ExternalResource every time
        if (event.getMessage().contentToString().equals("test ExternalResource")) {
            Image image = event.getGroup().uploadImage(demoExternalResource);
            try {
                demoExternalResource.close();
            } catch (IOException e) {
                plugin.getLogger().error("Image externalResource.close error" + e.getMessage());
            }
            event.getGroup().sendMessage(image);
        }
        
        return ListeningStatus.LISTENING;
    }
    
    
}
