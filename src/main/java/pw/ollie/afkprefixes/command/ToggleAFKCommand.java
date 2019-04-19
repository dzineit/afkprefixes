package pw.ollie.afkprefixes.command;

import pw.ollie.afkprefixes.AFKPlugin;
import pw.ollie.afkprefixes.afk.AFKManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public final class ToggleAFKCommand implements CommandExecutor {
    private final AFKPlugin plugin;

    public ToggleAFKCommand(AFKPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (!(sender instanceof Player) || !sender.hasPermission("afkprefixes.afk")) {
            sender.sendMessage(ChatColor.RED + "You can't do that.");
            return true;
        }
        Player player = (Player) sender;
        UUID playerId = player.getUniqueId();
        AFKManager afkManager = plugin.getAFKManager();
        if (afkManager.isAFK(playerId)) {
            String oldPrefix = afkManager.endAFK(playerId);
            plugin.getVaultChat().setPlayerPrefix(player, oldPrefix);
        } else {
            String oldPrefix = plugin.getVaultChat().getPlayerPrefix(player);
            plugin.getVaultChat().setPlayerPrefix(player, ChatColor.RED + "*AFK*" + oldPrefix);
            afkManager.startAFK(playerId, oldPrefix);
        }
        return true;
    }
}
