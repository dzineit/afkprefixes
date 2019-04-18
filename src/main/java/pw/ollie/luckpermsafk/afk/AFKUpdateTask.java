package pw.ollie.luckpermsafk.afk;

import pw.ollie.luckpermsafk.LPAFKPlugin;

import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.util.UUID;

public final class AFKUpdateTask extends BukkitRunnable {
    private final LPAFKPlugin plugin;

    public AFKUpdateTask(LPAFKPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        AFKManager manager = plugin.getAFKManager();
        for (UUID afk : manager.getAFKPlayers()) {
            LocalDateTime lastUpdate = manager.getLastAction(afk);
            if (lastUpdate.isBefore(LocalDateTime.now().minusMinutes(plugin.getMinutesToAFK()))) {
                // todo set prefix and pass old one
                manager.startAFK(afk, null);
            }
        }
    }
}
