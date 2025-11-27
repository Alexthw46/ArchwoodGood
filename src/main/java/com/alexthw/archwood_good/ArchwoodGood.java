package com.alexthw.archwood_good;

import com.hollingsworth.arsnouveau.ArsNouveau;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;

public class ArchwoodGood extends SimpleModule {

    public ArchwoodGood(String modid) {
        super(modid, "awg", ArsNouveau.MODID);

        WoodTypeRegistry woodReg = WoodTypeRegistry.INSTANCE;
        woodReg.addRemover(ArsNouveau.prefix("archwood"));
//        woodReg.addSimpleFinder("ars_nouveau", "purple_archwood").planks("archwood_planks");
//
//        woodReg.addSimpleFinder("ars_nouveau", "green_archwood").planks("archwood_planks");
//
//        woodReg.addSimpleFinder("ars_nouveau", "red_archwood").planks("archwood_planks");
//
//        woodReg.addSimpleFinder("ars_elemental", "yellow_archwood").planks("archwood_planks");
//
//        LeavesTypeRegistry leafReg = LeavesTypeRegistry.INSTANCE;
//
//        leafReg.addSimpleFinder("ars_nouveau", "purple_archwood");
//        leafReg.addSimpleFinder("ars_nouveau", "green_archwood");
//        leafReg.addSimpleFinder("ars_nouveau", "red_archwood");
//        leafReg.addSimpleFinder("ars_elemental", "yellow_archwood");
//
//        leafReg.addLeavesToWoodMapping("ars_nouveau:purple_archwood", "ars_nouveau:purple_archwood");
//        leafReg.addLeavesToWoodMapping("ars_nouveau:green_archwood", "ars_nouveau:green_archwood");
//        leafReg.addLeavesToWoodMapping("ars_nouveau:red_archwood", "ars_nouveau:red_archwood");
//        leafReg.addLeavesToWoodMapping("ars_elemental:yellow_archwood", "ars_elemental:yellow_archwood");
    }
}
