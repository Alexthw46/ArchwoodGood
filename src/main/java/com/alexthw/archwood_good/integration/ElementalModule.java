package com.alexthw.archwood_good.integration;

import alexthw.ars_elemental.registry.ModItems;
import com.alexthw.archwood_good.common.block.StrippablePlanks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static com.alexthw.archwood_good.ContentRegistry.WOOD_PROP;
import static com.alexthw.archwood_good.ContentRegistry.YELLOW_ARCHWOOD;
import static com.hollingsworth.arsnouveau.setup.registry.BlockRegistry.ARCHWOOD_PLANK;
import static com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry.defaultItemProperties;

public class ElementalModule {

    public static DeferredHolder<Block, ? extends Block> YELLOW_ARCHWOOD_PLANK = registerBlockAndItem("yellow_archwood_planks", () ->
            new StrippablePlanks(WOOD_PROP, ARCHWOOD_PLANK));

    public static DeferredHolder<Block, ? extends Block> YELLOW_ARCHWOOD_SLAB = registerBlockAndItem("yellow_archwood_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(YELLOW_ARCHWOOD_PLANK.get())));

    public static DeferredHolder<Block, ? extends Block> YELLOW_ARCHWOOD_STAIRS = registerBlockAndItem("yellow_archwood_stairs", () ->
            new StairBlock(YELLOW_ARCHWOOD_PLANK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(YELLOW_ARCHWOOD_PLANK.get())));

    // BLOCKS
    public static final DeferredHolder<Block, ? extends Block> YELLOW_ARCHWOOD_SIGN = registerBlock("yellow_archwood_sign",
            () -> new StandingSignBlock(YELLOW_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final DeferredHolder<Block, ? extends Block> YELLOW_ARCHWOOD_WALL_SIGN = registerBlock("yellow_archwood_wall_sign",
            () -> new WallSignBlock(YELLOW_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final DeferredHolder<Block, ? extends Block> YELLOW_ARCHWOOD_HANGING_SIGN = registerBlock("yellow_archwood_hanging_sign",
            () -> new CeilingHangingSignBlock(YELLOW_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final DeferredHolder<Block, ? extends Block> YELLOW_ARCHWOOD_WALL_HANGING_SIGN = registerBlock("yellow_archwood_wall_hanging_sign",
            () -> new WallHangingSignBlock(YELLOW_ARCHWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));

    // ITEMS
    public static final DeferredHolder<Item, ? extends Item> YELLOW_ARCHWOOD_SIGN_ITEM = registerItem("yellow_archwood_sign",
            () -> new SignItem(defaultItemProperties().stacksTo(16), YELLOW_ARCHWOOD_SIGN.get(), YELLOW_ARCHWOOD_WALL_SIGN.get()));
    public static final DeferredHolder<Item, ? extends Item> YELLOW_ARCHWOOD_HANGING_SIGN_ITEM = registerItem("yellow_archwood_hanging_sign",
            () -> new HangingSignItem(YELLOW_ARCHWOOD_HANGING_SIGN.get(), YELLOW_ARCHWOOD_WALL_HANGING_SIGN.get(), defaultItemProperties().stacksTo(16)));


    //      ┌──────────────────────────────────────────────────────────┐
    //      │                         METHODS                          │
    //      └──────────────────────────────────────────────────────────┘
    public static void init() {}

    private static DeferredHolder<Block, ? extends Block> registerBlockAndItem(String name, Supplier<Block> blockSupp) {
        DeferredHolder<Block, ? extends Block> block = ModItems.BLOCKS.register(name, blockSupp);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static DeferredHolder<Block, ? extends Block> registerBlock(String name, Supplier<Block> blockSupp) {
        return ModItems.BLOCKS.register(name, blockSupp);
    }

    private static DeferredHolder<Item, ? extends Item> registerItem(String name, Supplier<Item> itemSupp) {
        return ModItems.ITEMS.register(name, itemSupp);
    }
}
