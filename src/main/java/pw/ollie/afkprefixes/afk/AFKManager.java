package pw.ollie.afkprefixes.afk;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class AFKManager {
    private final Map<UUID, LocalDateTime> lastActions;
    private final Map<UUID, String> oldPrefixes;

    public AFKManager() {
        this.lastActions = new HashMap<>();
        this.oldPrefixes = new HashMap<>();
    }

    public synchronized Set<UUID> getAFKPlayers() {
        return new HashSet<>(oldPrefixes.keySet());
    }

    public synchronized boolean isAFK(UUID playerId) {
        return oldPrefixes.containsKey(playerId);
    }

    public synchronized void startAFK(UUID playerId, String oldPrefix) {
        oldPrefixes.put(playerId, oldPrefix);
        lastActions.remove(playerId);
    }

    public synchronized String endAFK(UUID playerId) {
        updateLastAction(playerId);
        return oldPrefixes.remove(playerId);
    }

    public synchronized void stopTracking(UUID playerId) {
        lastActions.remove(playerId);
        oldPrefixes.remove(playerId);
    }

    public synchronized LocalDateTime getLastAction(UUID playerId) {
        return lastActions.get(playerId);
    }

    public synchronized void updateLastAction(UUID playerId) {
        lastActions.put(playerId, LocalDateTime.now());
    }
}
