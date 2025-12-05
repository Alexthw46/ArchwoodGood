package com.alexthw.archwood_good;

import com.alexthw.archwood_good.integration.CompatRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ArchwoodGood.MODID)
public class ArchwoodGood {
    public static final String MODID = "archwood_good";

    public ArchwoodGood(IEventBus modEventBus, ModContainer modContainer) {
        Registry.init(modEventBus);
        CompatRegistry.init();
    }

    public static ResourceLocation res(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

}
