package com.alexthw.archwood_good.integration;

import net.neoforged.fml.ModList;

public class CompatRegistry {

    public static void init() {
        if (ModList.get().isLoaded("ars_elemental")) ElementalModule.init();

        if (ModList.get().isLoaded("moonlight")) MoonlightModule.init();
    }
}
