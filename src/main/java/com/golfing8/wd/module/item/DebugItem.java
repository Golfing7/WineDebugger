package com.golfing8.wd.module.item;

import com.golfing8.kcommon.config.commented.Configuration;
import com.golfing8.kcommon.config.generator.Conf;
import com.golfing8.kcommon.config.generator.ConfigClass;
import com.golfing8.kcommon.struct.item.ItemStackBuilder;
import com.golfing8.kcommon.util.ItemUtil;
import com.golfing8.wd.module.DebuggerModule;
import de.tr7zw.kcommon.nbtapi.NBT;
import de.tr7zw.kcommon.nbtapi.NBTItem;
import de.tr7zw.kcommon.nbtapi.iface.ReadableNBT;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Represents a debugging tool.
 */
@Getter
public abstract class DebugItem extends ConfigClass implements Listener {
    private static final String DEBUG_ITEM_TAG = "debugItem";
    private final DebuggerModule module = DebuggerModule.get();
    private final DebugItemType type;

    @Conf
    private ItemStackBuilder debugItem = getDefaultItem();
    public ItemStackBuilder getDebugItem() {
        debugItem.extraData(DEBUG_ITEM_TAG, type.name());
        return debugItem;
    }

    public DebugItem(DebugItemType type) {
        this.type = type;

        Bukkit.getServer().getPluginManager().registerEvent(PlayerInteractEvent.class, this, EventPriority.LOWEST, (listener, event) -> {
            if (!(event instanceof PlayerInteractEvent)) {
                return;
            }

            PlayerInteractEvent playerInteractEvent = (PlayerInteractEvent) event;
            if (ItemUtil.isAirOrNull(((PlayerInteractEvent) event).getItem()))
                return;

            ReadableNBT nbt = NBT.readNbt(((PlayerInteractEvent) event).getItem());
            if (!nbt.hasTag(DEBUG_ITEM_TAG))
                return;

            onUse(playerInteractEvent);
        }, module.getPlugin());

        this.setRequireAnnotation(true);
        this.initConfig();
    }

    /**
     * Called when a player has used the item.
     *
     * @param event the event
     */
    public void onUse(PlayerInteractEvent event) {}

    protected abstract ItemStackBuilder getDefaultItem();
}
