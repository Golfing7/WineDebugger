package com.golfing8.wd.module.cmd.debug;

import com.golfing8.kcommon.command.Cmd;
import com.golfing8.kcommon.command.CommandContext;
import com.golfing8.kcommon.command.MCommand;
import com.golfing8.kcommon.command.argument.CommandArgument;
import com.golfing8.kcommon.command.argument.CommandArguments;
import com.golfing8.wd.module.DebuggerModule;
import com.golfing8.wd.module.item.DebugItemType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Cmd(
        name = "debugitem",
        description = "Get a debug item",
        forPlayers = true
)
public class DebugItemCMD extends MCommand<DebuggerModule> {
    @Override
    protected void onRegister() {
        addArgument("player", CommandArguments.PLAYER);
        addArgument("debug-item", CommandArgument.fromEnum(DebugItemType.class));
    }

    @Override
    protected void execute(@NotNull CommandContext context) {
        Player player = context.next();
        DebugItemType itemType = context.next();

        player.getInventory().addItem(itemType.get().getDebugItem().buildFromTemplate(player));
    }
}
