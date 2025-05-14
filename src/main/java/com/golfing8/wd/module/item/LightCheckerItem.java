package com.golfing8.wd.module.item;

import com.golfing8.kcommon.config.generator.Conf;
import com.golfing8.kcommon.config.lang.Message;
import com.golfing8.kcommon.struct.item.ItemStackBuilder;
import com.golfing8.shade.com.cryptomorin.xseries.XMaterial;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

public class LightCheckerItem extends DebugItem {
    @Conf
    private Message faceLightMessage = new Message("&aFace light: &7(Block: &e{BLOCK_LIGHT} &7Sky: &e{SKY_LIGHT}&7)");
    @Conf
    private Message playerLightMessage = new Message("&aPlayer light: &7(Block: &e{BLOCK_LIGHT} &7Sky: &e{SKY_LIGHT}&7)");

    public LightCheckerItem() {
        super(DebugItemType.LIGHT_CHECKER);
    }

    @Override
    public void onUse(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            Block shifted = event.getClickedBlock().getRelative(event.getBlockFace());
            faceLightMessage.send(event.getPlayer(),
                    "BLOCK_LIGHT", shifted.getLightFromBlocks(), "SKY_LIGHT", shifted.getLightFromSky()
            );
        } else {
            Block playerBlock = event.getPlayer().getLocation().getBlock();
            playerLightMessage.send(event.getPlayer(),
                    "BLOCK_LIGHT", playerBlock.getLightFromBlocks(), "SKY_LIGHT", playerBlock.getLightFromSky()
            );
        }
    }

    @Override
    protected ItemStackBuilder getDefaultItem() {
        return new ItemStackBuilder()
                .material(XMaterial.STICK)
                .name("&eLight Checker");
    }
}
