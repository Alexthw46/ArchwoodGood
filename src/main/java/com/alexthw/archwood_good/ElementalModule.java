package com.alexthw.archwood_good;

import alexthw.ars_elemental.registry.ModItems;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

public class ElementalModule {

    public static void init() {
    }

    public static DeferredHolder<Block, ? extends Block> YELLOW_ARCHWOOD_PLANK = addBlock("yellow_archwood_planks", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.ARCHWOOD_PLANK.get())));

    static DeferredHolder<Block, ? extends Block> addBlock(String name, Supplier<Block> blockSupp) {
        DeferredHolder<Block, ? extends Block> block = ModItems.BLOCKS.register(name, blockSupp);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
}
