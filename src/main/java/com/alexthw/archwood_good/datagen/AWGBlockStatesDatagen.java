package com.alexthw.archwood_good.datagen;

import alexthw.ars_elemental.ArsElemental;
import com.alexthw.archwood_good.Registry;
import com.alexthw.archwood_good.integration.ElementalModule;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.common.datagen.BlockStatesDatagen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class AWGBlockStatesDatagen /*extends BlockStatesDatagen*/ {
//    public static CompletableFuture<HolderLookup.Provider> provider;
//    public static PackOutput output;

    public static final String[] colors = {
            "blue", "red", "green", "purple"
    };

//    public AWGBlockStatesDatagen(PackOutput output, ExistingFileHelper exFileHelper) {
//        super(output, ArchwoodGood.MODID, exFileHelper);
//    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                      Archwood Good                       │
    //      └──────────────────────────────────────────────────────────┘
    public static void gatherDataForAWG(GatherDataEvent event, DataGenerator gen, ExistingFileHelper existingFileHelper,
                                        PackOutput output) {

        /// ──────────────────────────────── blockstates & models/block ────────────────────────────────
        gen.addProvider(event.includeClient(), new BlockStateProvider(output, ArsElemental.MODID, existingFileHelper) {
            @Override
            protected void registerStatesAndModels() {
            }
        });

        /// ──────────────────────────────── models/item ────────────────────────────────
        gen.addProvider(event.includeClient(), new ItemModelProvider(output, ArsElemental.MODID, existingFileHelper) {
            @Override
            protected void registerModels() {
            }
        });
    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                       Ars Nouveau                        │
    //      └──────────────────────────────────────────────────────────┘
    public static void gatherDataForAN(GatherDataEvent event, DataGenerator gen, ExistingFileHelper existingFileHelper,
                                       PackOutput output) {

        /// ──────────────────────────────── blockstates & models/block ────────────────────────────────
        gen.addProvider(event.includeClient(), new BlockStateProvider(output, ArsNouveau.MODID, existingFileHelper) {
            @Override
            protected void registerStatesAndModels() {
                simpleBlock(Registry.BLUE_ARCHWOOD_PLANK.get());
                simpleBlock(Registry.RED_ARCHWOOD_PLANK.get());
                simpleBlock(Registry.GREEN_ARCHWOOD_PLANK.get());
                simpleBlock(Registry.PURPLE_ARCHWOOD_PLANK.get());

                for (String color : colors) {
                    String planksPath = color + "_archwood_planks";
                    String slabPath = planksPath.replace("planks", "slab");

                    slabBlock((SlabBlock) BuiltInRegistries.BLOCK.get(ArsNouveau.prefix(slabPath)),
                            ArsNouveau.prefix("block/" + planksPath),
                            ArsNouveau.prefix("block/" + planksPath));

                    stairsBlock((StairBlock) BuiltInRegistries.BLOCK.get(ArsNouveau.prefix(planksPath.replace("planks", "stairs"))),
                            ArsNouveau.prefix("block/" + planksPath)
                    );
                }

            }
        });

        /// ──────────────────────────────── models/item ────────────────────────────────
        gen.addProvider(event.includeClient(), new ItemModelProvider(output, ArsNouveau.MODID, existingFileHelper) {
            @Override
            protected void registerModels() {
                getBuilder("archwood_log").parent(BlockStatesDatagen.getUncheckedModel("archwood_log"));
                getBuilder("stripped_archwood_log").parent(BlockStatesDatagen.getUncheckedModel("stripped_archwood_log"));

                simpleBlockItem(Registry.BLUE_ARCHWOOD_PLANK.get());
                simpleBlockItem(Registry.RED_ARCHWOOD_PLANK.get());
                simpleBlockItem(Registry.GREEN_ARCHWOOD_PLANK.get());
                simpleBlockItem(Registry.PURPLE_ARCHWOOD_PLANK.get());

                for (String color : colors) {
                    String slabPath = color + "_archwood_slab";
                    String stairsPath = slabPath.replace("slab", "stairs");

                    getBuilder(slabPath).parent(BlockStatesDatagen.getUncheckedModel(slabPath));
                    getBuilder(stairsPath).parent(BlockStatesDatagen.getUncheckedModel(stairsPath));
                }

            }
        });

    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                      Ars Elemental                       │
    //      └──────────────────────────────────────────────────────────┘
    public static void gatherDataForAE(GatherDataEvent event, DataGenerator gen, ExistingFileHelper existingFileHelper,
                                       PackOutput output) {

        /// ──────────────────────────────── blockstates & models/block ────────────────────────────────
        gen.addProvider(event.includeClient(), new BlockStateProvider(output, ArsElemental.MODID, existingFileHelper) {
            @Override
            protected void registerStatesAndModels() {
                simpleBlock(ElementalModule.YELLOW_ARCHWOOD_PLANK.get());

                String blockPath = ElementalModule.YELLOW_ARCHWOOD_PLANK.getId().getPath();
                slabBlock((SlabBlock) ElementalModule.YELLOW_ARCHWOOD_SLAB.get(),
                        ArsElemental.prefix("block/" + blockPath),
                        ArsElemental.prefix("block/" + blockPath));

                stairsBlock((StairBlock) ElementalModule.YELLOW_ARCHWOOD_STAIRS.get(),
                        ArsElemental.prefix("block/" + blockPath));
            }
        });

        /// ──────────────────────────────── models/item ────────────────────────────────
        gen.addProvider(event.includeClient(), new ItemModelProvider(output, ArsElemental.MODID, existingFileHelper) {
            @Override
            protected void registerModels() {
                simpleBlockItem(ElementalModule.YELLOW_ARCHWOOD_PLANK.get());

                getBuilder("yellow_archwood_slab").parent(AWGBlockStatesDatagen.getUncheckedModel(ArsElemental.MODID, "yellow_archwood_slab"));
                getBuilder("yellow_archwood_stairs").parent(AWGBlockStatesDatagen.getUncheckedModel(ArsElemental.MODID, "yellow_archwood_stairs"));
            }
        });
    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                      OTHER METHODS                       │
    //      └──────────────────────────────────────────────────────────┘
    public static ModelFile getUncheckedModel(String modId, String registry) {
        return new ModelFile.UncheckedModelFile(modId + ":block/" + registry);
    }


}
