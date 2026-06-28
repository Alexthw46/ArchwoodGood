package com.alexthw.archwood_good;

import com.alexthw.archwood_good.registry.AWGBlockRegistry;
import com.alexthw.archwood_good.registry.AWGItemRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.alexthw.archwood_good.registry.AWGBlockRegistry.BLOCKS;
import static com.alexthw.archwood_good.registry.AWGBlockRegistry.ITEMS;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ArchwoodGood.MODID)
public class ArchwoodGood {
    public static final String MODID = "archwood_good";
    public static final Logger LOGGER = LogManager.getLogger("Archwood Good");

    public ArchwoodGood(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);

        AWGBlockRegistry.init(eventBus);
        AWGItemRegistry.init();

        AWGModSetup.registers(eventBus);

//        FEATURES.register(eventBus);

        AWGCreativeTabRegistry.TABS.register(eventBus);
    }

    public static ResourceLocation res(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

}
