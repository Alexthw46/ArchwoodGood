package com.alexthw.archwood_good.misc;

import com.hollingsworth.arsnouveau.setup.registry.BlockRegistryWrapper;
import net.minecraft.resources.ResourceLocation;

public class UtilityGeneral {
    public static ResourceLocation getId(BlockRegistryWrapper<?> block) {
        return block.getResourceLocation();
    }

    public static String getPathFromId(BlockRegistryWrapper<?> block) {
        return block.getResourceLocation().getPath();
    }


}
