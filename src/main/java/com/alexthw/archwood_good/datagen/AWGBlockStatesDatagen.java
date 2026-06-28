package com.alexthw.archwood_good.datagen;

import alexthw.ars_elemental.ArsElemental;
import com.alexthw.archwood_good.ArchwoodGood;
import com.alexthw.archwood_good.registry.AWGBlockRegistry;
import com.alexthw.archwood_good.registry.AWGItemRegistry;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.common.datagen.BlockStatesDatagen;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class AWGBlockStatesDatagen {

    private static final ResourceLocation GENERATED = ResourceLocation.withDefaultNamespace("item/generated");

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
                        Block leaves = woodType.getBlockOfThis(VanillaWoodChildKeys.LEAVES);
                        Block planks = woodType.planks;
                        Block slab = woodType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
                        Block stairs = woodType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);
                        Block sapling = woodType.getBlockOfThis(VanillaWoodChildKeys.SAPLING);
                        Block sign = woodType.getBlockOfThis(VanillaWoodChildKeys.SIGN);
                        Block wallSign = woodType.getBlockOfThis(VanillaWoodChildKeys.WALL_SIGN);
                        Block hangingSign = woodType.getBlockOfThis(VanillaWoodChildKeys.HANGING_SIGN);
                        Block wallHangingSign = woodType.getBlockOfThis(VanillaWoodChildKeys.WALL_HANGING_SIGN);

                        generateLogFiles(log, models(), this);
                        if (stripped_log != null)
                            generateStrippedLogFiles(stripped_log, models(), this);

                        if (wood != null)
                            generateWoodFiles(wood, log, models(), this);
                        if (stripped_wood != null)
                            generateWoodFiles(stripped_wood, stripped_log, models(), this);

                        if (sapling != null)
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

                        if (leaves != null) generateLeavesFiles(leaves, models(), this);

                        if (sign != null)
                            signBlock((StandingSignBlock) sign, (WallSignBlock) wallSign, Utils.getID(planks).withPrefix("block/"));

                        if (hangingSign != null)
                            hangingSignBlock((CeilingHangingSignBlock) hangingSign, (WallHangingSignBlock) wallHangingSign, Utils.getID(planks).withPrefix("block/"));
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
                        Block leaves = woodType.getBlockOfThis(VanillaWoodChildKeys.LEAVES);
                        Block planks = woodType.planks;
                        Block slab = woodType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
                        Block stairs = woodType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);
                        Block sapling = woodType.getBlockOfThis(VanillaWoodChildKeys.SAPLING);
                        Block sign = woodType.getBlockOfThis(VanillaWoodChildKeys.SIGN);
                        Block hangingSign = woodType.getBlockOfThis(VanillaWoodChildKeys.HANGING_SIGN);

                        simpleBlockItem(planks);

                        // LOG
                        getBuilder(Utils.getID(log).toString()).parent(getUncheckedModel(Utils.getID(log)));
                        if (stripped_log != null)
                            getBuilder(Utils.getID(stripped_log).toString()).parent(getUncheckedModel(Utils.getID(stripped_log)));

                        // WOOD
                        if (wood != null)
                            getBuilder(Utils.getID(wood).toString()).parent(getUncheckedModel(Utils.getID(wood)));
                        if (stripped_wood != null)
                            getBuilder(Utils.getID(stripped_wood).toString()).parent(getUncheckedModel(Utils.getID(stripped_wood)));

                        // LEAVES
                        if (leaves != null)
                            simpleBlockItem(leaves);

                        // CHILDREN
                        if (slab != null)
                            getBuilder(Utils.getID(slab).toString()).parent(getUncheckedModel(Utils.getID(slab)));
                        if (stairs != null)
                            getBuilder(Utils.getID(stairs).toString()).parent(getUncheckedModel(Utils.getID(stairs)));
                        if (sapling != null)
                            getBuilder(Utils.getID(sapling).toString()).parent(new ModelFile.UncheckedModelFile("item/generated"))
                                    .texture("layer0", Utils.getID(sapling).withPrefix("block/"));
                        if (sign != null)
                            getBuilder(Utils.getID(sign).toString()).parent(new ModelFile.UncheckedModelFile("item/generated"))
                                    .texture("layer0", Utils.getID(sign).withPrefix("item/"));
                        if (hangingSign != null)
                            getBuilder(Utils.getID(hangingSign).toString()).parent(new ModelFile.UncheckedModelFile("item/generated"))
                                    .texture("layer0", Utils.getID(hangingSign).withPrefix("item/"));

                    }

                }

                // ARCHFRUITS
                withExistingParent(Utils.getID(AWGItemRegistry.DAWNBERRY_POD).getPath(), GENERATED).texture("layer0", Utils.getID(AWGItemRegistry.DAWNBERRY_POD).withPrefix("item/archfruit/"));
                withExistingParent(Utils.getID(AWGItemRegistry.LIGHTCHEE_POD).getPath(), GENERATED).texture("layer0", Utils.getID(AWGItemRegistry.LIGHTCHEE_POD).withPrefix("item/archfruit/"));

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

                for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                    String woodTypeId = woodType.getId().toString();
                    if (woodTypeId.matches(ArsNouveau.MODID + ":(?!archwood).*")) {
                        Block planks = woodType.planks;
                        Block slab = woodType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
                        Block stairs = woodType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);
                        Block sign = woodType.getBlockOfThis(VanillaWoodChildKeys.SIGN);
                        Block wallSign = woodType.getBlockOfThis(VanillaWoodChildKeys.WALL_SIGN);
                        Block hangingSign = woodType.getBlockOfThis(VanillaWoodChildKeys.HANGING_SIGN);
                        Block wallHangingSign = woodType.getBlockOfThis(VanillaWoodChildKeys.WALL_HANGING_SIGN);

                        simpleBlock(planks);

                        slabBlock((SlabBlock) slab,
                                Utils.getID(planks).withPrefix("block/"),
                                Utils.getID(planks).withPrefix("block/"));

                        stairsBlock((StairBlock) stairs,
                                Utils.getID(planks).withPrefix("block/")
                        );

                        signBlock((StandingSignBlock) sign, (WallSignBlock) wallSign,
                                Utils.getID(planks).withPrefix("block/")
                        );
                        hangingSignBlock((CeilingHangingSignBlock) hangingSign, (WallHangingSignBlock) wallHangingSign,
                                Utils.getID(planks).withPrefix("block/")
                        );
                    }
                }

                logBlock(AWGBlockRegistry.FADING_ARCHWOOD_LOG.get());
                logBlock(AWGBlockRegistry.STRIPPED_FADING_ARCHWOOD_LOG.get());

                axisBlock(AWGBlockRegistry.FADING_ARCHWOOD_WOOD.get(),
                        ArsNouveau.prefix("block/archwood_log"),
                        ArsNouveau.prefix("block/archwood_log_top")
                );
                axisBlock(AWGBlockRegistry.STRIPPED_FADING_ARCHWOOD_WOOD.get(),
                        ArsNouveau.prefix("block/stripped_archwood_log"),
                        ArsNouveau.prefix("block/stripped_archwood_log_top")
                );

                Block saplingBlock = AWGBlockRegistry.FADING_ARCHWOOD_SAPLING.get();
                simpleBlock(saplingBlock, models()
                        .cross(Utils.getID(saplingBlock).toString(), Utils.getID(saplingBlock).withPrefix("block/"))
                        .renderType("cutout")
                );

                simpleBlock(AWGBlockRegistry.FADING_ARCHWOOD_LEAVES.get());

            }
        });

        /// ──────────────────────────────── models/item ────────────────────────────────
        gen.addProvider(event.includeClient(), new ItemModelProvider(output, ArsNouveau.MODID, existingFileHelper) {
            @Override
            protected void registerModels() {

                for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                    String woodTypeId = woodType.getId().toString();
                    if (woodTypeId.matches(ArsNouveau.MODID + ":(?!archwood).*")) {
                        Block planks = woodType.planks;
                        Block slab = woodType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
                        Block stairs = woodType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);
//                        Block sapling = woodType.getBlockOfThis(VanillaWoodChildKeys.SAPLING);
                        Block sign = woodType.getBlockOfThis(VanillaWoodChildKeys.SIGN);
                        Block hangingSign = woodType.getBlockOfThis(VanillaWoodChildKeys.HANGING_SIGN);

                        simpleBlockItem(planks);

                        if (slab != null)
                            getBuilder(Utils.getID(slab).getPath()).parent(getUncheckedModel(Utils.getID(slab)));
                        if (stairs != null)
                            getBuilder(Utils.getID(stairs).getPath()).parent(getUncheckedModel(Utils.getID(stairs)));

                        if (sign != null)
                            getBuilder(Utils.getID(sign).getPath()).parent(new ModelFile.UncheckedModelFile("item/generated"))
                                    .texture("layer0", Utils.getID(sign).withPrefix("item/"));
                        if (hangingSign != null)
                            getBuilder(Utils.getID(hangingSign).getPath()).parent(new ModelFile.UncheckedModelFile("item/generated"))
                                    .texture("layer0", Utils.getID(hangingSign).withPrefix("item/"));

                    }
                }

                getBuilder("archwood_log").parent(BlockStatesDatagen.getUncheckedModel("archwood_log"));
                getBuilder("stripped_archwood_log").parent(BlockStatesDatagen.getUncheckedModel("stripped_archwood_log"));
                getBuilder("archwood_wood").parent(BlockStatesDatagen.getUncheckedModel("archwood_wood"));
                getBuilder("stripped_archwood_wood").parent(BlockStatesDatagen.getUncheckedModel("stripped_archwood_wood"));

                simpleBlockItem(AWGBlockRegistry.FADING_ARCHWOOD_LEAVES.get());

                getBuilder(Utils.getID(AWGBlockRegistry.FADING_ARCHWOOD_SAPLING.get()).toString()).parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", Utils.getID(AWGBlockRegistry.FADING_ARCHWOOD_SAPLING.get()).withPrefix("block/"));

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
                WoodType woodType = WoodTypeRegistry.INSTANCE.get(ArsElemental.prefix("yellow_archwood"));
                if (woodType != null) {
                    Block planks = woodType.planks;
                    Block slab = woodType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
                    Block stairs = woodType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);
                    Block sign = woodType.getBlockOfThis(VanillaWoodChildKeys.SIGN);
                    Block wallSign = woodType.getBlockOfThis(VanillaWoodChildKeys.WALL_SIGN);
                    Block hangingSign = woodType.getBlockOfThis(VanillaWoodChildKeys.HANGING_SIGN);
                    Block wallHangingSign = woodType.getBlockOfThis(VanillaWoodChildKeys.WALL_HANGING_SIGN);

                    simpleBlock(planks);

                    slabBlock((SlabBlock) slab,
                            Utils.getID(planks).withPrefix("block/"),
                            Utils.getID(planks).withPrefix("block/"));

                    stairsBlock((StairBlock) stairs, Utils.getID(planks).withPrefix("block/"));

                    signBlock((StandingSignBlock) sign, (WallSignBlock) wallSign,
                            Utils.getID(planks).withPrefix("block/")
                    );
                    hangingSignBlock((CeilingHangingSignBlock) hangingSign, (WallHangingSignBlock) wallHangingSign,
                            Utils.getID(planks).withPrefix("block/")
                    );
                }

            }
        });

        /// ──────────────────────────────── models/item ────────────────────────────────
        gen.addProvider(event.includeClient(), new ItemModelProvider(output, ArsElemental.MODID, existingFileHelper) {
            @Override
            protected void registerModels() {
                WoodType woodType = WoodTypeRegistry.INSTANCE.get(ArsElemental.prefix("yellow_archwood"));
                if (woodType != null) {
                    Block planks = woodType.planks;
                    Block slab = woodType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
                    Block stairs = woodType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);
                    Block sign = woodType.getBlockOfThis(VanillaWoodChildKeys.SIGN);
                    Block hangingSign = woodType.getBlockOfThis(VanillaWoodChildKeys.HANGING_SIGN);

                    simpleBlockItem(planks);

                    if (slab != null)
                        getBuilder(Utils.getID(slab).getPath()).parent(getUncheckedModel(Utils.getID(slab)));
                    if (stairs != null)
                        getBuilder(Utils.getID(stairs).getPath()).parent(getUncheckedModel(Utils.getID(stairs)));

                    if (sign != null)
                        getBuilder(Utils.getID(sign).getPath()).parent(new ModelFile.UncheckedModelFile("item/generated"))
                                .texture("layer0", Utils.getID(sign).withPrefix("item/"));
                    if (hangingSign != null)
                        getBuilder(Utils.getID(hangingSign).getPath()).parent(new ModelFile.UncheckedModelFile("item/generated"))
                                .texture("layer0", Utils.getID(hangingSign).withPrefix("item/"));
                }

            }
        });
    }

//      ┌──────────────────────────────────────────────────────────┐
//      │                         METHODS                          │
//      └──────────────────────────────────────────────────────────┘
    public static ModelFile getUncheckedModel(ResourceLocation registry) {
        return new ModelFile.UncheckedModelFile(registry.getNamespace() + ":block/" + registry.getPath());
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
        ResourceLocation woodId = Utils.getID(wood);
        ResourceLocation woodFileLoc = woodId.withPrefix("block/");
        ResourceLocation logFileLoc = Utils.getID(log).withPrefix("block/");

        String[] suffixes = {"", "_1", "_2", "_3"};
        for (String suffix : suffixes) {
            models.cubeColumn(woodId.withSuffix(suffix).toString(), logFileLoc.withSuffix(suffix), logFileLoc.withSuffix(suffix));
            models.cubeColumnHorizontal(woodId.withSuffix("_horizontal" + suffix).toString(), logFileLoc.withSuffix(suffix), logFileLoc.withSuffix(suffix));
        }

        blockStateProvider.getVariantBuilder(wood)
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.X).addModels(
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodFileLoc.withSuffix("_horizontal"))).rotationX(90).rotationY(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodFileLoc.withSuffix("_horizontal_1"))).rotationX(90).rotationY(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodFileLoc.withSuffix("_horizontal_2"))).rotationX(90).rotationY(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodFileLoc.withSuffix("_horizontal_3"))).rotationX(90).rotationY(90).buildLast()
                )
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y).addModels(
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodFileLoc )).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodFileLoc.withSuffix("_1"))).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodFileLoc.withSuffix("_2"))).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodFileLoc.withSuffix("_3"))).buildLast()
                )
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z).addModels(
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodFileLoc.withSuffix("_horizontal"))).rotationX(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodFileLoc.withSuffix("_horizontal_1"))).rotationX(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodFileLoc.withSuffix("_horizontal_2"))).rotationX(90).buildLast(),
                        ConfiguredModel.builder().modelFile(models.getExistingFile(woodFileLoc.withSuffix("_horizontal_3"))).rotationX(90).buildLast()
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

    /// Generate models/block & blockstates file for LEAVES
    public static void generateLeavesFiles(Block leaves, BlockModelProvider models, BlockStateProvider blockStateProvider) {
        ResourceLocation leavesId = Utils.getID(leaves);
        ResourceLocation leavesFileLoc = leavesId.withPrefix("block/");

        String[] suffixes = {"", "_1", "_2"};

        for (String suffix : suffixes) {
            models.leaves(leavesId.withSuffix(suffix).toString(), leavesFileLoc.withSuffix(suffix));
        }

        blockStateProvider.getVariantBuilder(leaves).partialState().setModels(
                ConfiguredModel.builder().modelFile(models.getExistingFile(leavesFileLoc)).buildLast(),
                ConfiguredModel.builder().modelFile(models.getExistingFile(leavesFileLoc.withSuffix("_1"))).buildLast(),
                ConfiguredModel.builder().modelFile(models.getExistingFile(leavesFileLoc.withSuffix("_2"))).buildLast()
        );

    }
}
