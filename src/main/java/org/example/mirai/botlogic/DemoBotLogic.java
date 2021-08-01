package org.example.mirai.botlogic;

import java.util.Arrays;

import org.example.mirai.botlogic.configuration.MiraiAdaptedApplicationContext;
import org.example.mirai.botlogic.service.DemoService;
import org.example.mirai.plugin.DemoPlugin;
import org.jetbrains.annotations.NotNull;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListenerHost;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.GroupMessageEvent;


/**
 * @author hundun
 * Created on 2021/06/29
 */
public class DemoBotLogic implements ListenerHost {
    
    DemoPlugin plugin;
    
    DemoService service;
    
    public DemoBotLogic(DemoPlugin plugin) {
        this.plugin = plugin;
        
        @SuppressWarnings("resource")
        MiraiAdaptedApplicationContext context = new MiraiAdaptedApplicationContext(true);
        // can add more bean to context
        context.registerBean(DemoPlugin.class, () -> plugin);
        context.refresh();
        
        // show context is work
        plugin.getLogger().info("ApplicationContext created, has beans = " + Arrays.toString(context.getBeanDefinitionNames()));
        
        // use bean
        this.service = context.getBean(DemoService.class);
        plugin.getLogger().info(service.checkIoc());
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
