package com.alexthw.archwood_good.datagen;

import alexthw.ars_elemental.ArsElemental;
import com.alexthw.archwood_good.ArchwoodGood;
import com.alexthw.archwood_good.ContentRegistry;
import com.alexthw.archwood_good.integration.ElementalModule;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.common.datagen.BlockStatesDatagen;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Objects;

public class AWGBlockStatesDatagen<T extends ModelBuilder<T>> {

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                      Archwood Good                       │
    //      └──────────────────────────────────────────────────────────┘
    public static void gatherDataForAWG(GatherDataEvent event, DataGenerator gen, ExistingFileHelper existingFileHelper,
                                        PackOutput output) {

        /// ──────────────────────────────── blockstates & models/block ────────────────────────────────
        gen.addProvider(event.includeClient(), new BlockStateProvider(output, ArchwoodGood.MODID, existingFileHelper) {
            @Override
            protected void registerStatesAndModels() {
                for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                    String woodTypeId = woodType.getId().toString();
                    if (woodTypeId.matches(ArchwoodGood.MODID + ":.*")) {
                        Block log = woodType.log;
                        Block stripped_log = woodType.getBlockOfThis(VanillaWoodChildKeys.STRIPPED_LOG);
                        Block wood = woodType.getBlockOfThis(VanillaWoodChildKeys.WOOD);
                        Block stripped_wood = woodType.getBlockOfThis(VanillaWoodChildKeys.STRIPPED_WOOD);
                        Block planks = woodType.planks;
                        Block slab = woodType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
                        Block stairs = woodType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);
                        Block sapling = woodType.getBlockOfThis(VanillaWoodChildKeys.SAPLING);

                        generateLogFiles(log, models(), this);
                        if (Objects.nonNull(stripped_log))
                            generateStrippedLogFiles(stripped_log, models(), this);

                        if (Objects.nonNull(wood))
                            generateWoodFiles(wood, log, models(), this);
                        if (Objects.nonNull(stripped_wood))
                            generateWoodFiles(stripped_wood, stripped_log, models(), this);

                        if (Objects.nonNull(sapling))
                            simpleBlock(sapling, models()
                                    .cross(Utils.getID(sapling).toString(), Utils.getID(sapling).withPrefix("block/"))
                                    .renderType("cutout")
                            );

                        simpleBlock(planks);
                        slabBlock((SlabBlock) slab,
                                Utils.getID(planks).withPrefix("block/"),
                                Utils.getID(planks).withPrefix("block/")
                        );
                        stairsBlock((StairBlock) stairs,
                                Utils.getID(planks).withPrefix("block/")
                        );
                    }

                }

            }
        });

        /// ──────────────────────────────── models/item ────────────────────────────────
        gen.addProvider(event.includeClient(), new ItemModelProvider(output, ArchwoodGood.MODID, existingFileHelper) {
            @Override
            protected void registerModels() {

                for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                    String woodTypeId = woodType.getId().toString();
                    if (woodTypeId.matches(ArchwoodGood.MODID + ":.*")) {
                        Block log = woodType.log;
                        Block stripped_log = woodType.getBlockOfThis(VanillaWoodChildKeys.STRIPPED_LOG);
                        Block wood = woodType.getBlockOfThis(VanillaWoodChildKeys.WOOD);
                        Block stripped_wood = woodType.getBlockOfThis(VanillaWoodChildKeys.STRIPPED_WOOD);
                        Block planks = woodType.planks;
                        Block slab = woodType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
                        Block stairs = woodType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);
                        Block sapling = woodType.getBlockOfThis(VanillaWoodChildKeys.SAPLING);

                        simpleBlockItem(planks);

                        // LOG
                        getBuilder(Utils.getID(log).toString()).parent(getUncheckedModel(woodType.getNamespace(), Utils.getID(log).getPath()));
                        if (Objects.nonNull(stripped_log))
                            getBuilder(Utils.getID(stripped_log).toString()).parent(getUncheckedModel(woodType.getNamespace(), Utils.getID(stripped_log).getPath()));

                        // WOOD
                        if (Objects.nonNull(wood))
                            getBuilder(Utils.getID(wood).toString()).parent(getUncheckedModel(woodType.getNamespace(), Utils.getID(wood).getPath()));
                        if (Objects.nonNull(stripped_wood))
                            getBuilder(Utils.getID(stripped_wood).toString()).parent(getUncheckedModel(woodType.getNamespace(), Utils.getID(stripped_wood).getPath()));

                        // CHILDREN
                        if (Objects.nonNull(slab))
                            getBuilder(Utils.getID(slab).toString()).parent(getUncheckedModel(woodType.getNamespace(), Utils.getID(slab).getPath()));
                        if (Objects.nonNull(stairs))
                            getBuilder(Utils.getID(stairs).toString()).parent(getUncheckedModel(woodType.getNamespace(), Utils.getID(stairs).getPath()));
                        if (Objects.nonNull(sapling))
                            getBuilder(Utils.getID(sapling).toString()).parent(new ModelFile.UncheckedModelFile("item/generated"))
                                    .texture("layer0", Utils.getID(sapling).withPrefix("block/"));
                    }

                }

            }
        });
    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                       Ars Nouveau                        │
    //      └──────────────────────────────────────────────────────────┘
    public static final String[] colorsFromAN = {
            "blue", "red", "green", "purple"
    };

    public static void gatherDataForAN(GatherDataEvent event, DataGenerator gen, ExistingFileHelper existingFileHelper,
                                       PackOutput output) {

        /// ──────────────────────────────── blockstates & models/block ────────────────────────────────
        gen.addProvider(event.includeClient(), new BlockStateProvider(output, ArsNouveau.MODID, existingFileHelper) {
            @Override
            protected void registerStatesAndModels() {

//                for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
//                    String woodTypeId = woodType.getId().toString();
//                    if (woodTypeId.matches(ArsNouveau.MODID + ":.*")) {
//                        Block planks = woodType.planks;
//                        Block slab = woodType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
//                        Block stairs = woodType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);
//                        Block sapling = woodType.getBlockOfThis(VanillaWoodChildKeys.SAPLING);
//
//                        simpleBlock(planks);
//                    }
//                }

                simpleBlock(ContentRegistry.BLUE_ARCHWOOD_PLANK.get());
                simpleBlock(ContentRegistry.RED_ARCHWOOD_PLANK.get());
                simpleBlock(ContentRegistry.GREEN_ARCHWOOD_PLANK.get());
                simpleBlock(ContentRegistry.PURPLE_ARCHWOOD_PLANK.get());

                for (String color : colorsFromAN) {
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
                getBuilder("archwood_wood").parent(BlockStatesDatagen.getUncheckedModel("archwood_wood"));
                getBuilder("stripped_archwood_wood").parent(BlockStatesDatagen.getUncheckedModel("stripped_archwood_wood"));

                simpleBlockItem(ContentRegistry.BLUE_ARCHWOOD_PLANK.get());
                simpleBlockItem(ContentRegistry.RED_ARCHWOOD_PLANK.get());
                simpleBlockItem(ContentRegistry.GREEN_ARCHWOOD_PLANK.get());
                simpleBlockItem(ContentRegistry.PURPLE_ARCHWOOD_PLANK.get());

                for (String color : colorsFromAN) {
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

    /// Generate models/block & blockstates file for LOG
    public static void generateLogFiles(Block log, BlockModelProvider models, BlockStateProvider blockStateProvider) {
        ResourceLocation logResLoc = Utils.getID(log).withPrefix("block/");
        ResourceLocation archwoodLogTopLoc = ArsNouveau.prefix("block/archwood_log_top");
        ResourceLocation logId = Utils.getID(log);

        String[] suffixes = {"", "_1", "_2", "_3"};
        for (String suffix : suffixes) {
            models.cubeColumn(logId.withSuffix(suffix).toString(), logResLoc.withSuffix(suffix), archwoodLogTopLoc);
            models.cubeColumnHorizontal(logId.withSuffix("_horizontal" + suffix).toString(), logResLoc.withSuffix(suffix), archwoodLogTopLoc);
        }

        blockStateProvider.getVariantBuilder(log)
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.X).addModels(
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal"))).rotationX(90).rotationY(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal_1"))).rotationX(90).rotationY(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal_2"))).rotationX(90).rotationY(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal_3"))).rotationX(90).rotationY(90).buildLast()
                )
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y).addModels(
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc )).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_1"))).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_2"))).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_3"))).buildLast()
                )
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z).addModels(
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal"))).rotationX(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal_1"))).rotationX(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal_2"))).rotationX(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal_3"))).rotationX(90).buildLast()
                );
    }
    /// Generate models/block & blockstates file for WOOD & STRIPPED_WOOD
    public static void generateWoodFiles(Block wood, Block log, BlockModelProvider models, BlockStateProvider blockStateProvider) {
        ResourceLocation logResLoc = Utils.getID(log).withPrefix("block/");
        ResourceLocation woodResLoc = Utils.getID(wood).withPrefix("block/");
        ResourceLocation logId = Utils.getID(wood);

        String[] suffixes = {"", "_1", "_2", "_3"};
        for (String suffix : suffixes) {
            models.cubeColumn(logId.withSuffix(suffix).toString(), logResLoc.withSuffix(suffix), logResLoc.withSuffix(suffix));
            models.cubeColumnHorizontal(logId.withSuffix("_horizontal" + suffix).toString(), logResLoc.withSuffix(suffix), logResLoc.withSuffix(suffix));
        }

        blockStateProvider.getVariantBuilder(wood)
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.X).addModels(
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodResLoc.withSuffix("_horizontal"))).rotationX(90).rotationY(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodResLoc.withSuffix("_horizontal_1"))).rotationX(90).rotationY(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodResLoc.withSuffix("_horizontal_2"))).rotationX(90).rotationY(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodResLoc.withSuffix("_horizontal_3"))).rotationX(90).rotationY(90).buildLast()
                )
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y).addModels(
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodResLoc )).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodResLoc.withSuffix("_1"))).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodResLoc.withSuffix("_2"))).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodResLoc.withSuffix("_3"))).buildLast()
                )
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z).addModels(
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodResLoc.withSuffix("_horizontal"))).rotationX(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodResLoc.withSuffix("_horizontal_1"))).rotationX(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodResLoc.withSuffix("_horizontal_2"))).rotationX(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodResLoc.withSuffix("_horizontal_3"))).rotationX(90).buildLast()
                );
    }

    /// Generate models/block & blockstates file for STRIPPED_LOG
    public static void generateStrippedLogFiles(Block stripped_log, BlockModelProvider models, BlockStateProvider blockStateProvider) {
        ResourceLocation logResLoc = Utils.getID(stripped_log).withPrefix("block/");
        ResourceLocation strippedArchwoodLogTopLoc = ArsNouveau.prefix("block/stripped_archwood_log_top");

        models.cubeColumn(Utils.getID(stripped_log).toString(), logResLoc, strippedArchwoodLogTopLoc);
        models.cubeColumn(Utils.getID(stripped_log).withSuffix("_1").toString(), logResLoc.withSuffix("_1"), strippedArchwoodLogTopLoc);
        models.cubeColumn(Utils.getID(stripped_log).withSuffix("_2").toString(), logResLoc.withSuffix("_2"), strippedArchwoodLogTopLoc);
        models.cubeColumn(Utils.getID(stripped_log).withSuffix("_3").toString(), logResLoc.withSuffix("_3"), strippedArchwoodLogTopLoc);

        models.cubeColumnHorizontal(Utils.getID(stripped_log).withSuffix("_horizontal").toString(), logResLoc, strippedArchwoodLogTopLoc);
        models.cubeColumnHorizontal(Utils.getID(stripped_log).withSuffix("_horizontal_1").toString(), logResLoc.withSuffix("_1"), strippedArchwoodLogTopLoc);
        models.cubeColumnHorizontal(Utils.getID(stripped_log).withSuffix("_horizontal_2").toString(), logResLoc.withSuffix("_2"), strippedArchwoodLogTopLoc);
        models.cubeColumnHorizontal(Utils.getID(stripped_log).withSuffix("_horizontal_3").toString(), logResLoc.withSuffix("_3"), strippedArchwoodLogTopLoc);

        blockStateProvider.getVariantBuilder(stripped_log)
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.X).addModels(
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal"))).rotationX(90).rotationY(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal_1"))).rotationX(90).rotationY(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal_2"))).rotationX(90).rotationY(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal_3"))).rotationX(90).rotationY(90).buildLast()
                )
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y).addModels(
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc )).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_1"))).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_2"))).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_3"))).buildLast()
                )
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z).addModels(
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal"))).rotationX(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal_1"))).rotationX(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal_2"))).rotationX(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(logResLoc.withSuffix("_horizontal_3"))).rotationX(90).buildLast()
                );
    }
}
