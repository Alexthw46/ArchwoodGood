package com.alexthw.archwood_good;

import com.alexthw.archwood_good.common.block.StrippablePlanks;
import com.alexthw.archwood_good.integration.CompatRegistry;
import com.alexthw.archwood_good.integration.ElementalModule;
import com.hollingsworth.arsnouveau.common.block.MagicLeaves;
import com.hollingsworth.arsnouveau.common.block.StrippableLog;
import com.hollingsworth.arsnouveau.common.world.tree.MagicTree;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistryWrapper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.hollingsworth.arsnouveau.setup.registry.BlockRegistry.*;
import static net.minecraft.world.level.block.state.properties.WoodType.register;

public class ContentRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(ArchwoodGood.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(ArchwoodGood.MODID);

    public static final BlockBehaviour.Properties WOOD_PROP = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).ignitedByLava().sound(SoundType.WOOD);
    public static final BlockBehaviour.Properties SAP_PROP = BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY);

    /// ─────────────────────────────────── Woodtypes ───────────────────────────────────

    // Archwood Good
    public static final WoodType ORANGE_ARCHWOOD = register(new WoodType("archwood_good:orange_archwood", BlockSetType.OAK));
    public static final WoodType WHITE_ARCHWOOD = register(new WoodType("archwood_good:white_archwood", BlockSetType.OAK));

    // Ars Nouveau
    public static final WoodType BLUE_ARCHWOOD = register(new WoodType("ars_nouveau:blue_archwood", BlockSetType.OAK));
    public static final WoodType RED_ARCHWOOD = register(new WoodType("ars_nouveau:red_archwood", BlockSetType.OAK));
    public static final WoodType GREEN_ARCHWOOD = register(new WoodType("ars_nouveau:green_archwood", BlockSetType.OAK));
    public static final WoodType PURPLE_ARCHWOOD = register(new WoodType("ars_nouveau:purple_archwood", BlockSetType.OAK));

    // Ars Elemental
    public static final WoodType YELLOW_ARCHWOOD = register(new WoodType("ars_nouveau:yellow_archwood", BlockSetType.OAK));


    /// ─────────────────────────────────── Archwood ────────────────────────────────────
    public static BlockRegistryWrapper<RotatedPillarBlock> STRIPPED_FADING_ARCHWOOD_LOG = registerBlockAndItem("stripped_archwood_log", () ->
            new RotatedPillarBlock(WOOD_PROP));

    public static BlockRegistryWrapper<StrippableLog> FADING_ARCHWOOD_LOG = registerBlockAndItem("archwood_log", () ->
            new StrippableLog(WOOD_PROP, () -> STRIPPED_FADING_ARCHWOOD_LOG.get()));

    public static BlockRegistryWrapper<RotatedPillarBlock> STRIPPED_FADING_ARCHWOOD_WOOD = registerBlockAndItem("stripped_archwood_wood", () ->
            new RotatedPillarBlock(WOOD_PROP));

    public static BlockRegistryWrapper<StrippableLog> FADING_ARCHWOOD_WOOD = registerBlockAndItem("archwood_wood", () ->
            new StrippableLog(WOOD_PROP, () -> STRIPPED_FADING_ARCHWOOD_WOOD.get()));

    public static BlockRegistryWrapper<SaplingBlock> FADING_ARCHWOOD_SAPLING = registerBlockAndItem("archwood_sapling", () ->
            new SaplingBlock(MagicTree.getGrower("archwood_tree", AWGWorldgenRegistry.CONFIGURED_FADING_TREE), SAP_PROP));

    public static BlockRegistryWrapper<MagicLeaves> FADING_ARCHWOOD_LEAVES = registerBlockAndItem("archwood_leaves", () ->
            createLeavesBlock(MapColor.COLOR_GRAY));

    /// ──────────────────────────────────── Planks ─────────────────────────────────────
    // Ars Nouveau
    public static BlockRegistryWrapper<Block> BLUE_ARCHWOOD_PLANK = registerBlockAndItem("blue_archwood_planks", () ->
            new StrippablePlanks(WOOD_PROP, ARCHWOOD_PLANK));

    public static BlockRegistryWrapper<Block> RED_ARCHWOOD_PLANK = registerBlockAndItem("red_archwood_planks", () ->
            new StrippablePlanks(WOOD_PROP, ARCHWOOD_PLANK));

    public static BlockRegistryWrapper<Block> GREEN_ARCHWOOD_PLANK = registerBlockAndItem("green_archwood_planks", () ->
            new StrippablePlanks(WOOD_PROP, ARCHWOOD_PLANK));

    public static BlockRegistryWrapper<Block> PURPLE_ARCHWOOD_PLANK = registerBlockAndItem("purple_archwood_planks", () ->
            new StrippablePlanks(WOOD_PROP, ARCHWOOD_PLANK));

    // Archwood Good
    public static BlockRegistryWrapper<Block> ORANGE_ARCHWOOD_PLANK = registerBlockAndItemForAWG("orange_archwood_planks", () ->
            new StrippablePlanks(WOOD_PROP, ARCHWOOD_PLANK));

    public static BlockRegistryWrapper<Block> WHITE_ARCHWOOD_PLANK = registerBlockAndItemForAWG("white_archwood_planks", () ->
            new StrippablePlanks(WOOD_PROP, ARCHWOOD_PLANK));

    /// ───────────────────────────────────── Slab ──────────────────────────────────────
    // Ars Nouveau
    public static BlockRegistryWrapper<Block> BLUE_ARCHWOOD_SLAB = registerBlockAndItem("blue_archwood_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(BLUE_ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> RED_ARCHWOOD_SLAB = registerBlockAndItem("red_archwood_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(RED_ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> GREEN_ARCHWOOD_SLAB = registerBlockAndItem("green_archwood_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(GREEN_ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> PURPLE_ARCHWOOD_SLAB = registerBlockAndItem("purple_archwood_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(PURPLE_ARCHWOOD_PLANK.get())));

    public static DeferredHolder<Block, ? extends Block> ORANGE_ARCHWOOD_SLAB = addBlockForAWG("orange_archwood_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ORANGE_ARCHWOOD_PLANK.get())));

    public static DeferredHolder<Block, ? extends Block> WHITE_ARCHWOOD_SLAB = addBlockForAWG("white_archwood_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_ARCHWOOD_PLANK.get())));

    /// ──────────────────────────────────── Stairs ─────────────────────────────────────
    // Ars Nouveau
    public static BlockRegistryWrapper<Block> BLUE_ARCHWOOD_STAIRS = registerBlockAndItem("blue_archwood_stairs", () ->
            new StairBlock(BLUE_ARCHWOOD_PLANK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(BLUE_ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> RED_ARCHWOOD_STAIRS = registerBlockAndItem("red_archwood_stairs", () ->
            new StairBlock(RED_ARCHWOOD_PLANK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(BLUE_ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> GREEN_ARCHWOOD_STAIRS = registerBlockAndItem("green_archwood_stairs", () ->
            new StairBlock(GREEN_ARCHWOOD_PLANK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(BLUE_ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> PURPLE_ARCHWOOD_STAIRS = registerBlockAndItem("purple_archwood_stairs", () ->
            new StairBlock(PURPLE_ARCHWOOD_PLANK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(BLUE_ARCHWOOD_PLANK.get())));

    // Archwood Good
    public static DeferredHolder<Block, ? extends Block> ORANGE_ARCHWOOD_STAIRS = addBlockForAWG("orange_archwood_stairs", () ->
            new StairBlock(ORANGE_ARCHWOOD_PLANK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(BLUE_ARCHWOOD_PLANK.get())));

    public static DeferredHolder<Block, ? extends Block> WHITE_ARCHWOOD_STAIRS = addBlockForAWG("white_archwood_stairs", () ->
            new StairBlock(WHITE_ARCHWOOD_PLANK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(BLUE_ARCHWOOD_PLANK.get())));

    /// ───────────────────────────────── Stripped Logs ─────────────────────────────────
    // Archwood Good
    public static DeferredHolder<Block, ? extends Block> STRIPPED_ORANGE_ARCHWOOD_LOG = addBlockForAWG("stripped_orange_archwood_log", () ->
            new RotatedPillarBlock(WOOD_PROP));
    public static DeferredHolder<Block, ? extends Block> STRIPPED_WHITE_ARCHWOOD_LOG = addBlockForAWG("stripped_white_archwood_log", () ->
            new RotatedPillarBlock(WOOD_PROP));

    /// ───────────────────────────────────── Logs ──────────────────────────────────────
    // Archwood Good
    public static DeferredHolder<Block, ? extends Block> ORANGE_ARCHWOOD_LOG = addBlockForAWG("orange_archwood_log", () ->
            new StrippableLog(WOOD_PROP, () -> STRIPPED_ORANGE_ARCHWOOD_LOG.get()));

    public static DeferredHolder<Block, ? extends Block> WHITE_ARCHWOOD_LOG = addBlockForAWG("white_archwood_log", () ->
            new StrippableLog(WOOD_PROP, () -> STRIPPED_WHITE_ARCHWOOD_LOG.get()));

    /// ──────────────────────────────── Stripped Woods ─────────────────────────────────
    public static DeferredHolder<Block, ? extends Block> STRIPPED_ORANGE_ARCHWOOD_WOOD = addBlockForAWG("stripped_orange_archwood_wood", () ->
            new RotatedPillarBlock(WOOD_PROP));

    public static DeferredHolder<Block, ? extends Block> STRIPPED_WHITE_ARCHWOOD_WOOD = addBlockForAWG("stripped_white_archwood_wood", () ->
            new RotatedPillarBlock(WOOD_PROP));

    /// ───────────────────────────────────── Woods ─────────────────────────────────────
    public static DeferredHolder<Block, ? extends Block> ORANGE_ARCHWOOD_WOOD = addBlockForAWG("orange_archwood_wood", () ->
            new StrippableLog(WOOD_PROP, () -> STRIPPED_ORANGE_ARCHWOOD_WOOD.get()));

    public static DeferredHolder<Block, ? extends Block> WHITE_ARCHWOOD_WOOD = addBlockForAWG("white_archwood_wood", () ->
            new StrippableLog(WOOD_PROP, () -> STRIPPED_WHITE_ARCHWOOD_WOOD.get()));

    /// ─────────────────────────────────── Saplings ────────────────────────────────────
    // Archwood Good
    public static BlockRegistryWrapper<SaplingBlock> ORANGE_ARCHWOOD_SAPLING = registerBlockAndItemForAWG("orange_archwood_sapling", () ->
            new SaplingBlock(MagicTree.getGrower("orange_archwood_tree", AWGWorldgenRegistry.CONFIGURED_FADING_TREE), SAP_PROP));

//    public static BlockRegistryWrapper<SaplingBlock> WHITE_ARCHWOOD_SAPLING = registerBlockAndItem("orange_archwood_sapling", () ->
//            new SaplingBlock(MagicTree.getGrower("orange_archwood_tree", AWGWorldgenRegistry.CONFIGURED_FADING_TREE), SAP_PROP));

    /// ──────────────────────────────────── Leaves ─────────────────────────────────────
    // Archwood Good
    public static BlockRegistryWrapper<MagicLeaves> ORANGE_ARCHWOOD_LEAVES = registerBlockAndItemForAWG("orange_archwood_leaves", () ->
            createLeavesBlock(MapColor.COLOR_ORANGE));

    public static BlockRegistryWrapper<MagicLeaves> WHITE_ARCHWOOD_LEAVES = registerBlockAndItemForAWG("white_archwood_leaves", () ->
            createLeavesBlock(MapColor.SNOW));

    /// ───────────────────────────────────── Sign ──────────────────────────────────────

    // Archwood Good
    public static final BlockRegistryWrapper<StandingSignBlock> ORANGE_ARCHWOOD_SIGN = registerBlockForAWG("orange_archwood_sign",
            () -> new StandingSignBlock(ORANGE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final BlockRegistryWrapper<WallSignBlock> ORANGE_ARCHWOOD_WALL_SIGN = registerBlockForAWG("orange_archwood_wall_sign",
            () -> new WallSignBlock(ORANGE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));

    public static final BlockRegistryWrapper<CeilingHangingSignBlock> ORANGE_ARCHWOOD_HANGING_SIGN = registerBlockForAWG("orange_archwood_hanging_sign",
            () -> new CeilingHangingSignBlock(ORANGE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final BlockRegistryWrapper<WallHangingSignBlock> ORANGE_ARCHWOOD_HANGING_WALL_SIGN = registerBlockForAWG("orange_archwood_wall_hanging_sign",
            () -> new WallHangingSignBlock(ORANGE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));

    public static final BlockRegistryWrapper<StandingSignBlock> WHITE_ARCHWOOD_SIGN = registerBlockForAWG("white_archwood_sign",
            () -> new StandingSignBlock(WHITE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final BlockRegistryWrapper<WallSignBlock> WHITE_ARCHWOOD_WALL_SIGN = registerBlockForAWG("white_archwood_wall_sign",
            () -> new WallSignBlock(WHITE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));

    public static final BlockRegistryWrapper<CeilingHangingSignBlock> WHITE_ARCHWOOD_HANGING_SIGN = registerBlockForAWG("white_archwood_hanging_sign",
            () -> new CeilingHangingSignBlock(WHITE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final BlockRegistryWrapper<WallHangingSignBlock> WHITE_ARCHWOOD_HANGING_WALL_SIGN = registerBlockForAWG("white_archwood_wall_hanging_sign",
            () -> new WallHangingSignBlock(WHITE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));

    // Ars Nouveau
    public static final BlockRegistryWrapper<StandingSignBlock> BLUE_ARCHWOOD_SIGN = registerBlock("blue_archwood_sign",
            () -> new StandingSignBlock(BLUE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final BlockRegistryWrapper<WallSignBlock> BLUE_ARCHWOOD_WALL_SIGN = registerBlock("blue_archwood_wall_sign",
            () -> new WallSignBlock(BLUE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final BlockRegistryWrapper<CeilingHangingSignBlock> BLUE_ARCHWOOD_HANGING_SIGN = registerBlock("blue_archwood_hanging_sign",
            () -> new CeilingHangingSignBlock(BLUE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final BlockRegistryWrapper<WallHangingSignBlock> BLUE_ARCHWOOD_HANGING_WALL_SIGN = registerBlock("blue_archwood_wall_hanging_sign",
            () -> new WallHangingSignBlock(BLUE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));

    public static final BlockRegistryWrapper<StandingSignBlock> GREEN_ARCHWOOD_SIGN = registerBlock("green_archwood_sign",
            () -> new StandingSignBlock(GREEN_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final BlockRegistryWrapper<WallSignBlock> GREEN_ARCHWOOD_WALL_SIGN = registerBlock("green_archwood_wall_sign",
            () -> new WallSignBlock(GREEN_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final BlockRegistryWrapper<CeilingHangingSignBlock> GREEN_ARCHWOOD_HANGING_SIGN = registerBlock("green_archwood_hanging_sign",
            () -> new CeilingHangingSignBlock(GREEN_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final BlockRegistryWrapper<WallHangingSignBlock> GREEN_ARCHWOOD_HANGING_WALL_SIGN = registerBlock("green_archwood_wall_hanging_sign",
            () -> new WallHangingSignBlock(GREEN_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));

    public static final BlockRegistryWrapper<StandingSignBlock> PURPLE_ARCHWOOD_SIGN = registerBlock("purple_archwood_sign",
            () -> new StandingSignBlock(PURPLE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final BlockRegistryWrapper<WallSignBlock> PURPLE_ARCHWOOD_WALL_SIGN = registerBlock("purple_archwood_wall_sign",
            () -> new WallSignBlock(PURPLE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final BlockRegistryWrapper<CeilingHangingSignBlock> PURPLE_ARCHWOOD_HANGING_SIGN = registerBlock("purple_archwood_hanging_sign",
            () -> new CeilingHangingSignBlock(PURPLE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final BlockRegistryWrapper<WallHangingSignBlock> PURPLE_ARCHWOOD_HANGING_WALL_SIGN = registerBlock("purple_archwood_wall_hanging_sign",
            () -> new WallHangingSignBlock(PURPLE_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));

    public static final BlockRegistryWrapper<StandingSignBlock> RED_ARCHWOOD_SIGN = registerBlock("red_archwood_sign",
            () -> new StandingSignBlock(RED_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final BlockRegistryWrapper<WallSignBlock> RED_ARCHWOOD_WALL_SIGN = registerBlock("red_archwood_wall_sign",
            () -> new WallSignBlock(RED_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final BlockRegistryWrapper<CeilingHangingSignBlock> RED_ARCHWOOD_HANGING_SIGN = registerBlock("red_archwood_hanging_sign",
            () -> new CeilingHangingSignBlock(RED_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final BlockRegistryWrapper<WallHangingSignBlock> RED_ARCHWOOD_HANGING_WALL_SIGN = registerBlock("red_archwood_wall_hanging_sign",
            () -> new WallHangingSignBlock(RED_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                           Init                           │
    //      └──────────────────────────────────────────────────────────┘
    public static void init(IEventBus bus) {
        // if Ars Elemental is loaded, register the blocks
        if (ModList.get().isLoaded("ars_elemental")) {
            ElementalModule.init();
        }
        // if Moonlight-lib is loaded, Init the module
        if (ModList.get().isLoaded("moonlight")) {
            CompatRegistry.init();
        }
    }

    private static DeferredHolder<Block, ? extends Block> addBlockForAWG(String name, Supplier<Block> blockSupp) {
        DeferredHolder<Block, ? extends Block> block = BLOCKS.register(name, blockSupp);
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    public static <T extends Block> BlockRegistryWrapper<T> registerBlockAndItemForAWG(String name, Supplier<T> blockSupp) {
        BlockRegistryWrapper<T> blockReg = new BlockRegistryWrapper<>(BLOCKS.register(name, blockSupp));
        ITEMS.register(name, () -> getDefaultBlockItem(blockReg.get()));
        return blockReg;
    }

    public static <T extends Block> BlockRegistryWrapper<T> registerBlockForAWG(String name, Supplier<T> blockSupp) {
        return new BlockRegistryWrapper<>(BLOCKS.register(name, blockSupp));
    }
}
