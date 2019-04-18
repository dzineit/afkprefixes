package pw.ollie.luckpermsafk;

import pw.ollie.luckpermsafk.afk.AFKManager;
import pw.ollie.luckpermsafk.afk.AFKUpdateTask;
import pw.ollie.luckpermsafk.command.ToggleAFKCommand;
import pw.ollie.luckpermsafk.listener.LPAFKListener;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class LPAFKPlugin extends JavaPlugin {
    private AFKManager afkManager;
    private AFKUpdateTask task;

    private int minutesToAfk;

    @Override
    public void onEnable() {
        this.saveResource("config.yml", false);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(this.getDataFolder(), "config.yml"));
        this.minutesToAfk = config.getInt("Minutes-to-AFK", 5);

        this.afkManager = new AFKManager();

        this.getServer().getPluginManager().registerEvents(new LPAFKListener(this), this);
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
}
