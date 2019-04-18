package pw.ollie.luckpermsafk.afk;

import pw.ollie.luckpermsafk.LPAFKPlugin;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class AFKManager {
    private final LPAFKPlugin plugin;
    private final Map<UUID, Date> lastActions;
    private final Map<UUID, String> oldPrefixes;

    public AFKManager(LPAFKPlugin plugin) {
        this.plugin = plugin;
        this.lastActions = new HashMap<>();
        this.oldPrefixes = new HashMap<>();
    }

    public Set<UUID> getAFKPlayers() {
        return new HashSet<>(oldPrefixes.keySet());
    }

    public synchronized boolean isAFK(UUID playerId) {
        return oldPrefixes.containsKey(playerId);
    }

    public synchronized void startAFK(UUID playerId, String oldPrefix) {
        oldPrefixes.put(playerId, oldPrefix);
    }

    public synchronized String endAFK(UUID playerId) {
        updateLastAction(playerId);
        return oldPrefixes.remove(playerId);
    }

    public synchronized Date getLastAction(UUID playerId) {
        return lastActions.get(playerId);
    }

    public synchronized void updateLastAction(UUID playerId) {
        lastActions.put(playerId, new Date());
    }
}
