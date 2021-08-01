package org.example.mirai.plugin;

import org.example.mirai.botlogic.DemoBotLogic;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;

public class DemoPlugin extends JavaPlugin {

    public static final DemoPlugin INSTANCE = new DemoPlugin(); 
    
    DemoBotLogic botLogic;
    
    public DemoPlugin() {
        super(new JvmPluginDescriptionBuilder(
                "org.example.DemoPlugin",
                "1.0.0"
            )
            .build());
    }
    
    @Override
    public void onEnable() {
        botLogic = new DemoBotLogic(this);
        GlobalEventChannel.INSTANCE.registerListenerHost(botLogic);
    }

}
