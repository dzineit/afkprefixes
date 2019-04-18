package pw.ollie.luckpermsafk.listener;

import pw.ollie.luckpermsafk.LPAFKPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.UUID;

public final class LPAFKListener implements Listener {
    private final LPAFKPlugin plugin;

    public LPAFKListener(LPAFKPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("luckpermsafk.afk")) {
            return;
        }

        update(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("luckpermsafk.afk")) {
            return;
        }

        update(player.getUniqueId());
    }

    private void update(UUID playerId) {
        String oldPrefix = plugin.getAFKManager().endAFK(playerId);
        if (oldPrefix != null) {
            // todo set prefix back to old
        }
    }
}
