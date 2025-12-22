package com.alexthw.archwood_good.integration;

import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.WALL_HANGING_SIGN;

public class MoonlightModule {

    public static void init() {

        WoodTypeRegistry woodReg = WoodTypeRegistry.INSTANCE;

        woodReg.addSimpleFinder("ars_nouveau", "archwood")
                .childBlockSuffix(WALL_HANGING_SIGN, "_hanging_wall_sign");
    }
}
