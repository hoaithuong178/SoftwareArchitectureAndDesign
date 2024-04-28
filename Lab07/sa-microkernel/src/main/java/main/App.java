import vn.edu.ueh.bit.core.Language;

import java.io.FileReader;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        try {
            // Load properties file
            Properties properties = new Properties();
            properties.load(new FileReader("src/main/resources/application.properties"));

            // Create and configure PluginManager
            PluginManager pluginManager = new PluginManager();
            pluginManager.configure(properties);

            // Load and greet using each plugin
            properties.forEach((key, value) -> {
                Language languagePlugin = pluginManager.loadPlugin(value.toString(), Language.class);
                if (languagePlugin != null) {
                    System.out.println(languagePlugin.sayHello("Some people " + key));
                } else {
                    System.err.println("Failed to load plugin for language: " + key);
                }
            });

            // Example usage of specific language plugins
            Language defaultLanguagePlugin = pluginManager.loadPlugin("DEFAULT_LANGUAGE_PLUGIN_FILE_PATH", Language.class);
            if (defaultLanguagePlugin != null) {
                System.out.println(defaultLanguagePlugin.sayHello("nguyen van teo"));
            } else {
                System.err.println("Failed to load default language plugin");
            }

            Language japaneseLanguagePlugin = pluginManager.loadPlugin("JAPANESE_LANGUAGE_PLUGIN_FILE_PATH", Language.class);
            if (japaneseLanguagePlugin != null) {
                System.out.println(japaneseLanguagePlugin.sayHello("than thi det"));
            } else {
                System.err.println("Failed to load Japanese language plugin");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
