package com.alexthw.archwood_good.integration;

import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.neoforged.fml.ModList;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.WALL_HANGING_SIGN;

public class MoonlightModule {

    public static void init() {

        WoodTypeRegistry woodReg = WoodTypeRegistry.INSTANCE;

        //      ┌──────────────────────────────────────────────────────────┐
        //      │                       Ars Nouveau                        │
        //      └──────────────────────────────────────────────────────────┘
        woodReg.addSimpleFinder("ars_nouveau", "archwood")
                .childBlockSuffix(WALL_HANGING_SIGN, "_hanging_wall_sign");

        woodReg.addSimpleFinder("ars_nouveau", "blue_archwood")
                .childBlock("archfruit", "frostaya_pod");

        woodReg.addSimpleFinder("ars_nouveau", "green_archwood")
                .childBlock("archfruit", "mendosteen_pod");

        woodReg.addSimpleFinder("ars_nouveau", "red_archwood")
                .childBlock("archfruit", "bombegranate_pod");

        woodReg.addSimpleFinder("ars_nouveau", "purple_archwood")
                .childBlock("archfruit", "bastion_pod");

        //      ┌──────────────────────────────────────────────────────────┐
        //      │                      Ars Elemental                       │
        //      └──────────────────────────────────────────────────────────┘
        if (ModList.get().isLoaded("ars_elemental")) {
            woodReg.addSimpleFinder("ars_elemental", "yellow_archwood")
                    .childBlock("archfruit", "flashpine_pod");
        }

        //      ┌──────────────────────────────────────────────────────────┐
        //      │                      Archwood Good                       │
        //      └──────────────────────────────────────────────────────────┘
        woodReg.addSimpleFinder("archwood_good", "white_archwood")
                .childBlock("archfruit", "lightchee_pod");

        woodReg.addSimpleFinder("archwood_good", "orange_archwood")
                .childBlock("archfruit", "dawnberry_pod");

    }
}
