package com.alexthw.archwood_good;

import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistryWrapper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;

import static com.hollingsworth.arsnouveau.setup.registry.BlockRegistry.registerBlockAndItem;

public class Registry {

    public static BlockRegistryWrapper<Block> BLUE_ARCHWOOD_PLANK = registerBlockAndItem("blue_archwood_planks", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> RED_ARCHWOOD_PLANK = registerBlockAndItem("red_archwood_planks", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> GREEN_ARCHWOOD_PLANK = registerBlockAndItem("green_archwood_planks", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.ARCHWOOD_PLANK.get())));

    public static BlockRegistryWrapper<Block> PURPLE_ARCHWOOD_PLANK = registerBlockAndItem("purple_archwood_planks", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.ARCHWOOD_PLANK.get())));

    public static void init(IEventBus modEventBus) {
        // if Ars Elemental is loaded, register the blocks
        if (ModList.get().isLoaded("ars_elemental")) {
            ElementalModule.init();
        }
    }
}
