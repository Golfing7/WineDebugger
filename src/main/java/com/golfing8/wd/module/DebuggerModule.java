package com.golfing8.wd.module;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;
import com.golfing8.kcommon.module.Module;
import com.golfing8.kcommon.module.ModuleInfo;
import com.golfing8.wd.module.cmd.debug.DebugItemCMD;
import com.golfing8.wd.module.cmd.stress.StressTestCMD;
import com.golfing8.wd.module.item.DebugItemType;
import org.bukkit.plugin.Plugin;

@ModuleInfo(
        name = "debugger"
)
public class DebuggerModule extends Module {
    private ProtocolManager protocolManager;
    private PacketListener packetListener;

    @Override
    public void onEnable() {
        addCommand(new StressTestCMD());
        addCommand(new DebugItemCMD());

        DebugItemType.init(this);

        this.protocolManager = ProtocolLibrary.getProtocolManager();
        this.packetListener = registerPacketListeners();
    }

    @Override
    public void onDisable() {
        this.protocolManager.removePacketListener(packetListener);
    }

    private PacketListener registerPacketListeners() {
        PacketListener listener;
        this.protocolManager.addPacketListener(listener = new PacketAdapter(getPlugin(), ListenerPriority.NORMAL, PacketType.Play.Server.SPAWN_ENTITY){

            public void onPacketSending(PacketEvent event) {
                int noop = 0;
            }
        });
        return listener;
    }
}
