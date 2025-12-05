package com.alexthw.archwood_good.integration;

import com.hollingsworth.arsnouveau.ArsNouveau;
import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;

public class CompatRegistry {

    public static void init() {
        EveryCompatAPI.registerModule(new WoodGoodModule(ArsNouveau.MODID));
    }
}
