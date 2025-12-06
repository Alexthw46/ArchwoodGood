package com.alexthw.archwood_good;

import com.alexthw.archwood_good.common.block.StrippablePlanks;
import com.alexthw.archwood_good.integration.CompatRegistry;
import com.alexthw.archwood_good.integration.ElementalModule;
import com.hollingsworth.arsnouveau.common.block.MagicLeaves;
import com.hollingsworth.arsnouveau.common.block.StrippableLog;
import com.hollingsworth.arsnouveau.common.world.tree.MagicTree;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistryWrapper;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;

import static com.hollingsworth.arsnouveau.setup.registry.BlockRegistry.*;

public class Registry {

    public static final BlockBehaviour.Properties WOOD_PROP = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).ignitedByLava().sound(SoundType.WOOD);
    public static final BlockBehaviour.Properties SAP_PROP = BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY);

    /// ─────────────────────────────────── Archwood ────────────────────────────────────
    public static BlockRegistryWrapper<RotatedPillarBlock> STRIPPED_FADING_ARCHWOOD_LOG = registerBlockAndItem("stripped_archwood_log", () ->
            new RotatedPillarBlock(WOOD_PROP));

    public static BlockRegistryWrapper<StrippableLog> FADING_ARCHWOOD_LOG = registerBlockAndItem("archwood_log", () ->
            new StrippableLog(WOOD_PROP, () -> STRIPPED_FADING_ARCHWOOD_LOG.get()));

    public static BlockRegistryWrapper<SaplingBlock> FADING_ARCHWOOD_SAPLING = registerBlockAndItem("archwood_sapling", () ->
            new SaplingBlock(MagicTree.getGrower("archwood_tree", AWGWorldgenRegistry.CONFIGURED_FADING_TREE), SAP_PROP));

    public static BlockRegistryWrapper<MagicLeaves> FADING_ARCHWOOD_LEAVES = registerBlockAndItem("archwood_leaves", () ->
            createLeavesBlock(MapColor.COLOR_GRAY));

    /// ──────────────────────────────────── Planks ─────────────────────────────────────
    public static BlockRegistryWrapper<Block> BLUE_ARCHWOOD_PLANK = registerBlockAndItem("blue_archwood_planks", () ->
            new StrippablePlanks(WOOD_PROP, ARCHWOOD_PLANK));

    public static BlockRegistryWrapper<Block> RED_ARCHWOOD_PLANK = registerBlockAndItem("red_archwood_planks", () ->
            new StrippablePlanks(WOOD_PROP, ARCHWOOD_PLANK));

    public static BlockRegistryWrapper<Block> GREEN_ARCHWOOD_PLANK = registerBlockAndItem("green_archwood_planks", () ->
            new StrippablePlanks(WOOD_PROP, ARCHWOOD_PLANK));

    public static BlockRegistryWrapper<Block> PURPLE_ARCHWOOD_PLANK = registerBlockAndItem("purple_archwood_planks", () ->
            new StrippablePlanks(WOOD_PROP, ARCHWOOD_PLANK));

    /// ───────────────────────────────────── Slab ──────────────────────────────────────
    public static BlockRegistryWrapper<Block> BLUE_ARCHWOOD_SLAB = registerBlockAndItem("blue_archwood_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(BLUE_ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> RED_ARCHWOOD_SLAB = registerBlockAndItem("red_archwood_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(RED_ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> GREEN_ARCHWOOD_SLAB = registerBlockAndItem("green_archwood_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(GREEN_ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> PURPLE_ARCHWOOD_SLAB = registerBlockAndItem("purple_archwood_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(PURPLE_ARCHWOOD_PLANK.get())));

    /// ──────────────────────────────────── Stairs ─────────────────────────────────────
    public static BlockRegistryWrapper<Block> BLUE_ARCHWOOD_STAIRS = registerBlockAndItem("blue_archwood_stairs", () ->
            new StairBlock(BLUE_ARCHWOOD_PLANK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(BLUE_ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> RED_ARCHWOOD_STAIRS = registerBlockAndItem("red_archwood_stairs", () ->
            new StairBlock(RED_ARCHWOOD_PLANK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(BLUE_ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> GREEN_ARCHWOOD_STAIRS = registerBlockAndItem("green_archwood_stairs", () ->
            new StairBlock(GREEN_ARCHWOOD_PLANK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(BLUE_ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> PURPLE_ARCHWOOD_STAIRS = registerBlockAndItem("purple_archwood_stairs", () ->
            new StairBlock(PURPLE_ARCHWOOD_PLANK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(BLUE_ARCHWOOD_PLANK.get())));

    public static void init(IEventBus modEventBus) {
        // if Ars Elemental is loaded, register the blocks
        if (ModList.get().isLoaded("ars_elemental")) {
            ElementalModule.init();
        }
        // if Every Compat is loaded, register the module
        if (ModList.get().isLoaded("everycomp")) {
            CompatRegistry.init();
        }
    }
}
