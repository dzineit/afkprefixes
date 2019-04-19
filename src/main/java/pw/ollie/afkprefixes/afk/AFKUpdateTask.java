package pw.ollie.afkprefixes.afk;

import pw.ollie.afkprefixes.AFKPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.util.UUID;

public final class AFKUpdateTask extends BukkitRunnable {
    private final AFKPlugin plugin;

    public AFKUpdateTask(AFKPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        AFKManager manager = plugin.getAFKManager();
        for (UUID afk : manager.getAFKPlayers()) {
            LocalDateTime lastUpdate = manager.getLastAction(afk);
            if (lastUpdate.isBefore(LocalDateTime.now().minusMinutes(plugin.getMinutesToAFK()))) {
                Player player = plugin.getServer().getPlayer(afk);
                if (player == null) {
                    manager.stopTracking(afk);
                    continue;
                }

                String oldPrefix = plugin.getVaultChat().getPlayerPrefix(player);
                plugin.getVaultChat().setPlayerPrefix(player, ChatColor.RED + "*AFK*" + oldPrefix);
                manager.startAFK(afk, oldPrefix);
            }
        }
    }
}
