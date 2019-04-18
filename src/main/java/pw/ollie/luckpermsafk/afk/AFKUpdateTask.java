package pw.ollie.luckpermsafk.afk;

import pw.ollie.luckpermsafk.LPAFKPlugin;

import org.bukkit.scheduler.BukkitRunnable;

public final class AFKUpdateTask extends BukkitRunnable {
    private final LPAFKPlugin plugin;

    public AFKUpdateTask(LPAFKPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
    }
}
