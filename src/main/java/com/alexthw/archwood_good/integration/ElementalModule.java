package com.alexthw.archwood_good.integration;

import alexthw.ars_elemental.registry.ModItems;
import com.alexthw.archwood_good.common.block.StrippablePlanks;
import com.hollingsworth.arsnouveau.common.block.ModBlock;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static com.alexthw.archwood_good.Registry.WOOD_PROP;
import static com.hollingsworth.arsnouveau.setup.registry.BlockRegistry.ARCHWOOD_PLANK;
import static com.hollingsworth.arsnouveau.setup.registry.BlockRegistry.LOG_PROP;

public class ElementalModule {

    public static void init() {
    }

    public static DeferredHolder<Block, ? extends Block> YELLOW_ARCHWOOD_PLANK = addBlock("yellow_archwood_planks", () ->
            new StrippablePlanks(WOOD_PROP, ARCHWOOD_PLANK));

    public static DeferredHolder<Block, ? extends Block> YELLOW_ARCHWOOD_SLAB = addBlock("yellow_archwood_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(YELLOW_ARCHWOOD_PLANK.get())));

    public static DeferredHolder<Block, ? extends Block> YELLOW_ARCHWOOD_STAIRS = addBlock("yellow_archwood_stairs", () ->
            new StairBlock(YELLOW_ARCHWOOD_PLANK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(YELLOW_ARCHWOOD_PLANK.get())));

    private static DeferredHolder<Block, ? extends Block> addBlock(String name, Supplier<Block> blockSupp) {
        DeferredHolder<Block, ? extends Block> block = ModItems.BLOCKS.register(name, blockSupp);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
}
