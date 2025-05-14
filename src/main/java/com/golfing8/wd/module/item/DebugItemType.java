package com.golfing8.wd.module.item;

import com.golfing8.kcommon.config.commented.Configuration;
import com.golfing8.wd.module.DebuggerModule;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
public enum DebugItemType {
    LIGHT_CHECKER(LightCheckerItem::new),
    ;

    final Supplier<DebugItem> supplier;
    DebugItem cachedItem;

    public DebugItem get() {
        Preconditions.checkNotNull(cachedItem);
        return cachedItem;
    }

    public static void init(DebuggerModule module) {
        Configuration rootConfig = module.getMainConfig();
        boolean save = false;
        if (!rootConfig.isConfigurationSection("debug-items")) {
            rootConfig.createSection("debug-items");
            save = true;
        }

        for (DebugItemType type : values()) {
            if (type.cachedItem != null)
                type.cachedItem.unregister();

            type.cachedItem = type.supplier.get();
            save |= type.cachedItem.loadValues(rootConfig.getConfigurationSection("debug-items." + type.name()));
        }
        if (save) {
            rootConfig.save();
        }
    }
}
