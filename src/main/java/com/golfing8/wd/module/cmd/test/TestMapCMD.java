package com.golfing8.wd.module.cmd.test;

import com.golfing8.kcommon.command.Cmd;
import com.golfing8.kcommon.command.CommandContext;
import com.golfing8.kcommon.command.MCommand;
import com.golfing8.wd.module.DebuggerModule;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.block.CraftBlock;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

@Cmd(
        name = "map",
        description = "Test spawning a map item frame",
        forPlayers = true,
        async = true
)
public class TestMapCMD extends MCommand<DebuggerModule> {
    @Override
    protected void execute(@NotNull CommandContext context) {
        Player player = context.getPlayer();
        Location location = player.getLocation();
        BlockFace direction = BlockFace.NORTH;

        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                for (int z = 0; z < 7; z++) {
                    ItemStack item = new ItemStack(Items.FILLED_MAP);
                    item.setData(1);
                    EntityItemFrame frame = new EntityItemFrame(((CraftWorld) player.getWorld()).getHandle());
                    frame.setItem(item);
                    frame.setLocation(location.getX() + x, location.getY() + y, location.getZ() + z, 0.0f, 0.0f);
                    frame.setDirection(CraftBlock.blockFaceToNotch((BlockFace) direction));
                    PacketPlayOutSpawnEntity spawnPacket = new PacketPlayOutSpawnEntity(frame, 71, frame.direction.b());
                    BlockPosition position = frame.getBlockPosition();
                    spawnPacket.a(MathHelper.d((float)(position.getX() * 32)));
                    spawnPacket.b(MathHelper.d((float)(position.getY() * 32)));
                    spawnPacket.c(MathHelper.d((float)(position.getZ() * 32)));
                    PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
                    connection.sendPacket(spawnPacket);
                    connection.sendPacket(new PacketPlayOutEntityMetadata(frame.getId(), frame.getDataWatcher(), true));
                    connection.sendPacket(new PacketPlayOutMap(1, (byte) 3, Collections.emptyList(), new byte[128 * 128], 0, 0, 128, 128));
                }
            }
        }
    }
}
