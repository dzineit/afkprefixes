package pw.ollie.luckpermsafk;

import pw.ollie.luckpermsafk.afk.AFKManager;
import pw.ollie.luckpermsafk.command.ToggleAFKCommand;
import pw.ollie.luckpermsafk.listener.LPAFKListener;

import org.bukkit.plugin.java.JavaPlugin;

public final class LPAFKPlugin extends JavaPlugin {
    private AFKManager afkManager;

    @Override
    public void onEnable() {
        this.afkManager = new AFKManager(this);

        this.getServer().getPluginManager().registerEvents(new LPAFKListener(this), this);
        this.getCommand("toggleafk").setExecutor(new ToggleAFKCommand(this));
    }

    @Override
    public void onDisable() {
    }

    public AFKManager getAFKManager() {
        return afkManager;
    }
}
