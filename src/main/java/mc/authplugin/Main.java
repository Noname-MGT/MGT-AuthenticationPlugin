package mc.authplugin;

import mc.authplugin.commands.TokenCMD;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static JavaPlugin plugin;
    public static String apiAccessToken = "";

    @Override
    public void onEnable() {
        plugin = this;


        if(getConfig().contains("apiAccessToken")){
            apiAccessToken = getConfig().getString("apiAccessToken");
            System.out.println("API-AccessToken: "+apiAccessToken);
        }else{
            System.out.println("API-AccessToken: could not be loaded...");
            getConfig().set("apiAccessToken", "unset");
            saveConfig();
        }


        getCommand("token").setExecutor(new TokenCMD());

        NetworkAdapter.registerAuthenticator();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new MainListener(), this);

    }

    @Override
    public void onDisable() {
        NetworkAdapter.deregisterAuthenticator();
    }


}
