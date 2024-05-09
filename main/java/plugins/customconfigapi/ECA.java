package plugins.customconfigapi;

import org.bukkit.plugin.Plugin;

public class ECA {
    private static Plugin plugin;
    public static void setPlugin(Plugin plugin){
        ECA.plugin = plugin;
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
