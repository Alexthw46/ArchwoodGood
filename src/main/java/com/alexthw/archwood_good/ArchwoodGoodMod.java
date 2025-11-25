package com.alexthw.archwood_good;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ArchwoodGoodMod.MODID)
public class ArchwoodGoodMod {
    public static final String MODID = "archwood_good";

    public ArchwoodGoodMod(IEventBus modEventBus, ModContainer modContainer) {
        ArsNouveauRegistry.woodGoodsSetup();
    }

}
