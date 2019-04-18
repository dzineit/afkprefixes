package pw.ollie.luckpermsafk.command;

import pw.ollie.luckpermsafk.afk.AFKManager;
import pw.ollie.luckpermsafk.LPAFKPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public final class ToggleAFKCommand implements CommandExecutor {
    private final LPAFKPlugin plugin;

    public ToggleAFKCommand(LPAFKPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (!(sender instanceof Player) || !sender.hasPermission("luckpermsafk.afk")) {
            sender.sendMessage(ChatColor.RED + "You can't do that.");
            return true;
        }
        UUID playerId = ((Player) sender).getUniqueId();
        AFKManager afkManager = plugin.getAFKManager();
        if (afkManager.isAFK(playerId)) {
            String oldPrefix = afkManager.endAFK(playerId);
            // todo reset prefix
        } else {
            // todo change prefix and provide old one
            afkManager.startAFK(playerId, null);
        }
        return true;
    }
}
