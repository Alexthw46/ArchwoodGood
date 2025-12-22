package com.alexthw.archwood_good;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.alexthw.archwood_good.ContentRegistry.BLOCKS;
import static com.alexthw.archwood_good.ContentRegistry.ITEMS;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ArchwoodGood.MODID)
public class ArchwoodGood {
    public static final String MODID = "archwood_good";
    public static final Logger LOGGER = LogManager.getLogger("Archwood Good");

    public ArchwoodGood(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);

        ContentRegistry.init(eventBus);
        ItemRegistry.init();

        ContentSetup.registers(eventBus);

        AWGCreativeTabRegistry.TABS.register(eventBus);
    }

    public static ResourceLocation res(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

}
