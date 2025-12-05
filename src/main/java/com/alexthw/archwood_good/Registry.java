package com.alexthw.archwood_good;

import com.alexthw.archwood_good.integration.ElementalModule;
import com.alexthw.archwood_good.integration.CompatRegistry;
import com.hollingsworth.arsnouveau.common.block.MagicLeaves;
import com.hollingsworth.arsnouveau.common.block.StrippableLog;
import com.hollingsworth.arsnouveau.common.world.tree.MagicTree;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistryWrapper;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;

import static com.hollingsworth.arsnouveau.setup.registry.BlockRegistry.*;

public class Registry {
    /// ─────────────────────────────────── Archwood ────────────────────────────────────
    public static BlockRegistryWrapper<RotatedPillarBlock> STRIPPED_ARCHWOOD_LOG = registerBlockAndItem("stripped_archwood_log", () ->
            new RotatedPillarBlock(LOG_PROP));

    public static BlockRegistryWrapper<StrippableLog> ARCHWOOD_LOG = registerBlockAndItem("archwood_log", () ->
            new StrippableLog(LOG_PROP, () -> STRIPPED_ARCHWOOD_LOG.get()));

    public static BlockRegistryWrapper<SaplingBlock> ARCHWOOD_SAPLING = registerBlockAndItem("archwood_sapling", () ->
            new SaplingBlock(MagicTree.getGrower("archwood_tree", AWGWorldgenRegistry.CONFIGURED_ARCHWOOD_TREE), SAP_PROP));

    public static BlockRegistryWrapper<MagicLeaves> ARCHWOOD_LEAVES = registerBlockAndItem("archwood_leaves", () ->
            createLeavesBlock(MapColor.COLOR_GRAY));

    /// ──────────────────────────────────── Planks ─────────────────────────────────────
    public static BlockRegistryWrapper<Block> BLUE_ARCHWOOD_PLANK = registerBlockAndItem("blue_archwood_planks", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> RED_ARCHWOOD_PLANK = registerBlockAndItem("red_archwood_planks", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> GREEN_ARCHWOOD_PLANK = registerBlockAndItem("green_archwood_planks", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> PURPLE_ARCHWOOD_PLANK = registerBlockAndItem("purple_archwood_planks", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.ARCHWOOD_PLANK.get())));

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
        if (ModList.get().isLoaded("everycomp")) {
            CompatRegistry.init();
        }
    }
}
