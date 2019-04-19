package pw.ollie.afkprefixes.listener;

import pw.ollie.afkprefixes.AFKPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public final class AFKListener implements Listener {
    private final AFKPlugin plugin;

    public AFKListener(AFKPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("afkprefixes.afk")) {
            return;
        }

        update(player);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("afkprefixes.afk")) {
            return;
        }

        update(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        String oldPrefix = plugin.getAFKManager().endAFK(playerId);
        if (oldPrefix != null) {
            plugin.getVaultChat().setPlayerPrefix(player, oldPrefix);
        }
    }

    private void update(Player player) {
        String oldPrefix = plugin.getAFKManager().endAFK(player.getUniqueId());
        plugin.getVaultChat().setPlayerPrefix(player, oldPrefix);
    }
}
