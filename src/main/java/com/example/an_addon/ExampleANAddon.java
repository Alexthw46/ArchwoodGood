package com.example.an_addon;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleANAddon.MODID)
public class ExampleANAddon {
    public static final String MODID = "an_addon";

    public ExampleANAddon(IEventBus modEventBus, ModContainer modContainer) {
        ArsNouveauRegistry.woodGoodsSetup();
    }

}
