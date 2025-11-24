package com.example.archwood_good;

import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;

public class ArsNouveauRegistry {


    public static void woodGoodsSetup() {
        EveryCompatAPI.registerModule(new ArchwoodGood(ArchwoodGoodMod.MODID));
    }
}
