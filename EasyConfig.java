package plugins.customconfigapi;

import com.google.common.base.Charsets;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import static plugins.customconfigapi.ECA.*;

public class EasyConfig {

        private static File file;
        private static String ConfigName;

        private static YamlConfiguration newConfig;
        public static YamlConfiguration getConfig(String configName){
                ConfigName = configName;
                File Config = new File(getPlugin().getDataFolder(), ConfigName);
                file = Config;
                return YamlConfiguration.loadConfiguration(Config);
        }
        public static void saveDefaultCustomConfig(String configName){
                if (!file.exists()){
                        getPlugin().saveResource(configName, false);
                }
        }
        public static void reloadCustomConfig(String configName) {
                newConfig = YamlConfiguration.loadConfiguration(file);
                InputStream defConfigStream = getPlugin().getResource(configName);
                if (defConfigStream != null) {
                        newConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
                }
        }

        public static void saveCustomConfig(String configName) {
                try {
                        getConfig(configName).save(file);
                } catch (IOException var2) {
                        getPlugin().getLogger().log(Level.SEVERE, "Could not save config to " + file, var2);
                }

        }
}
