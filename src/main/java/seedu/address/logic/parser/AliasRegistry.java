package seedu.address.logic.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Registry for command aliases.
 */
public class AliasRegistry {
    private static final Logger logger = LogsCenter.getLogger(AliasRegistry.class);
    private final Map<String, String> aliasMap = new HashMap<>();

    /**
     * Adds an alias mapping. Returns true if successful, false if conflict or invalid input.
     */
    public boolean addAlias(String alias, String commandWord, Set<String> reservedWords) {
        if (alias == null || alias.isBlank() || commandWord == null || commandWord.isBlank()) {
            return false;
        }
        Set<String> reserved = (reservedWords != null) ? reservedWords : Set.of();
        if (reserved.contains(alias) || aliasMap.containsKey(alias)) {
            return false;
        }
        aliasMap.put(alias, commandWord);
        return true;
    }

    /**
     * Clears all stored aliases.
     */
    public void clear() {
        aliasMap.clear();
    }

    /**
     * Removes an alias. Returns true if removed, false if not found.
     */
    public boolean removeAlias(String alias) {
        return aliasMap.remove(alias) != null;
    }

    /**
     * Gets the command word for an alias, or null if not found.
     */
    public String getCommandWord(String alias) {
        return aliasMap.get(alias);
    }

    /**
     * Returns all aliases.
     */
    public Map<String, String> getAllAliases() {
        return new HashMap<>(aliasMap);
    }

    /**
     * Replaces all current aliases with validated entries from the given map.
     * Entries with blank keys/values or keys that clash with {@code reservedWords} are silently skipped.
     * If {@code aliases} is null the registry is simply cleared.
     */
    public void loadAliases(Map<String, String> aliases, Set<String> reservedWords) {
        aliasMap.clear();
        if (aliases == null) {
            return;
        }
        Set<String> reserved = (reservedWords != null) ? reservedWords : Set.of();
        aliases.forEach((alias, command) -> {
            if (alias != null && !alias.isBlank()
                    && command != null && !command.isBlank()
                    && !reserved.contains(alias)) {
                aliasMap.put(alias, command);
            } else {
                logger.fine("Skipped invalid or reserved alias during load: " + alias);
            }
        });
    }
}
