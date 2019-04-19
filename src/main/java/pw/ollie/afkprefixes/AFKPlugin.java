package pw.ollie.afkprefixes;

import net.milkbowl.vault.chat.Chat;

import pw.ollie.afkprefixes.afk.AFKManager;
import pw.ollie.afkprefixes.afk.AFKUpdateTask;
import pw.ollie.afkprefixes.command.ToggleAFKCommand;
import pw.ollie.afkprefixes.listener.AFKListener;

import org.bukkit.Server;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class AFKPlugin extends JavaPlugin {
    private AFKManager afkManager;
    private AFKUpdateTask task;

    private Chat vaultChat;

    private int minutesToAfk;

    @Override
    public void onEnable() {
        Server server = this.getServer();
        RegisteredServiceProvider<Chat> chatProvider = server.getServicesManager().getRegistration(Chat.class);
        if (chatProvider != null) {
            this.vaultChat = chatProvider.getProvider();
        }

        this.saveResource("config.yml", false);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(this.getDataFolder(), "config.yml"));
        this.minutesToAfk = config.getInt("Minutes-to-AFK", 5);

        this.afkManager = new AFKManager();

        server.getPluginManager().registerEvents(new AFKListener(this), this);
        this.getCommand("toggleafk").setExecutor(new ToggleAFKCommand(this));

        this.task = new AFKUpdateTask(this);
        this.task.runTaskTimer(this, 20 * 30, 20 * 30);
    }

    @Override
    public void onDisable() {
        this.task.cancel();
    }

    public AFKManager getAFKManager() {
        return afkManager;
    }

    public int getMinutesToAFK() {
        return minutesToAfk;
    }

    public Chat getVaultChat() {
        return vaultChat;
    }
}
