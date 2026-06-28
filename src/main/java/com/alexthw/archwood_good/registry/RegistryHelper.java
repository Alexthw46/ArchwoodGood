package com.alexthw.archwood_good.registry;

import com.hollingsworth.arsnouveau.setup.registry.BlockRegistryWrapper;
import com.hollingsworth.arsnouveau.setup.registry.ItemRegistryWrapper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.alexthw.archwood_good.registry.AWGBlockRegistry.BLOCKS;
import static com.alexthw.archwood_good.registry.AWGBlockRegistry.ITEMS;
import static com.hollingsworth.arsnouveau.setup.registry.BlockRegistry.getDefaultBlockItem;

public class RegistryHelper {

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                      BLOCK REGISTER                      │
    //      └──────────────────────────────────────────────────────────┘
    public static DeferredHolder<Block, ? extends Block> addBlockForAWG(String path, Supplier<Block> blockSupp) {
        DeferredHolder<Block, ? extends Block> blockHolder = BLOCKS.register(path, blockSupp);
        ITEMS.register(path, () -> new BlockItem(blockHolder.get(), new Item.Properties()));
        return blockHolder;
    }

    public static <T extends Block> BlockRegistryWrapper<T> registerBlockAndItemForAWG(String path, Supplier<T> blockSupp) {
        BlockRegistryWrapper<T> blockReg = new BlockRegistryWrapper<>(BLOCKS.register(path, blockSupp));
        ITEMS.register(path, () -> getDefaultBlockItem(blockReg.get()));
        return blockReg;
    }

    public static <T extends Block> BlockRegistryWrapper<T> registerBlockAndItemForAWG(String path, Supplier<T> blockSupp, Function<BlockRegistryWrapper<T>, Item> blockItemFunc) {
        BlockRegistryWrapper<T> blockReg = new BlockRegistryWrapper<>(BLOCKS.register(path, blockSupp));
        ITEMS.register(path, () -> blockItemFunc.apply(blockReg));
        return blockReg;
    }

    public static <T extends Block> BlockRegistryWrapper<T> registerBlockForAWG(String path, Supplier<T> blockSupp) {
        return new BlockRegistryWrapper<>(BLOCKS.register(path, blockSupp));
    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                      ITEM REGISTER                       │
    //      └──────────────────────────────────────────────────────────┘
    public static <T extends Item> ItemRegistryWrapper<T> registerItemForAWG(String name, Supplier<T> item) {
        return new ItemRegistryWrapper<>(ITEMS.register(name, item));
    }
}
