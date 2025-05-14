package com.golfing8.wd.module.cmd.stress;

import com.golfing8.kcommon.command.Cmd;
import com.golfing8.kcommon.command.CommandContext;
import com.golfing8.kcommon.command.MCommand;
import com.golfing8.kcommon.command.argument.CommandArguments;
import com.golfing8.wd.module.DebuggerModule;
import com.golfing8.winespigot.util.profile.IMethodProfiler;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Chunk;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.jetbrains.annotations.NotNull;

@Cmd(
        name = "generateSkylightMap",
        description = "Stresses generateSkylightMap in your chunk",
        forPlayers = true
)
public class StressTestLightingCMD extends MCommand<DebuggerModule> {
    @Override
    protected void onRegister() {
        addArgument("executions", CommandArguments.POSITIVE_INTEGER);
    }

    @Override
    protected void execute(@NotNull CommandContext context) {
        int executions = context.next();

        EntityPlayer player = ((CraftPlayer) context.getPlayer()).getHandle();
        World world = player.world;
        Chunk chunk = world.getChunkAtWorldCoords(new BlockPosition(player.locX, player.locY, player.locZ));
        IMethodProfiler profiler = IMethodProfiler.getDefaultProfiler();
        for (int i = 0; i < executions; i++) {
            profiler.start("generateSkylightMap");
            chunk.initLighting();
            profiler.stop("generateSkylightMap");
        }
        profiler.dump(player);
    }
}
