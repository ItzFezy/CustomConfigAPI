package plugins.customconfigapi;

import com.google.common.base.Charsets;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import static plugins.customconfigapi.ECA.*;

public class Config {
    private static File file;
    private static String ConfigName;


    private static YamlConfiguration newConfig;
    public Config(String configName){
        ConfigName = configName;
        File config = new File(getPlugin().getDataFolder(), ConfigName);
        file = config;
    }
    public YamlConfiguration getConfig(){
        File config = new File(getPlugin().getDataFolder(), ConfigName);
        file = config;
        return YamlConfiguration.loadConfiguration(config);
    }
    public void saveDefaultCustomConfig(){
        if (!file.exists()){
            getPlugin().saveResource(ConfigName, false);
        }
    }
    public void reloadCustomConfig() {
        newConfig = YamlConfiguration.loadConfiguration(file);
        InputStream defConfigStream = getPlugin().getResource(ConfigName);
        if (defConfigStream != null) {
            newConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
        }
    }

    public void saveCustomConfig() {
        try {
            getConfig().save(file);
        } catch (IOException var2) {
            getPlugin().getLogger().log(Level.SEVERE, "Could not save config to " + file, var2);
        }
    }
}
