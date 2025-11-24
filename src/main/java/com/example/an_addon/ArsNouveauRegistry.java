package com.example.an_addon;

import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;

public class ArsNouveauRegistry {


    public static void woodGoodsSetup() {
        EveryCompatAPI.registerModule(new ArchwoodGood(ExampleANAddon.MODID));
    }
}
