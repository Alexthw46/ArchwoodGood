package com.alexthw.archwood_good;

import com.hollingsworth.arsnouveau.ArsNouveau;
import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;

public class ArsNouveauRegistry {

    public static void woodGoodsSetup() {
        EveryCompatAPI.registerModule(new ArchwoodGood(ArsNouveau.MODID));
    }
}
