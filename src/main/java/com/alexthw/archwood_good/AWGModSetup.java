package com.alexthw.archwood_good;

import com.alexthw.archwood_good.integration.ElementalModule;
import com.alexthw.archwood_good.registry.AWGBlockRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

public class AWGModSetup {

    public static void registers(IEventBus eventBux) {
        eventBux.addListener(AWGModSetup::addBlocksToTile);
        AWGBlockRegistry.BS_PROVIDERS.register(eventBux);
    }

    public static void addBlocksToTile(BlockEntityTypeAddBlocksEvent event) {
        // Archwood Good
        event.modify(BlockEntityType.SIGN, AWGBlockRegistry.ORANGE_ARCHWOOD_SIGN.get(), AWGBlockRegistry.ORANGE_ARCHWOOD_WALL_SIGN.get());
        event.modify(BlockEntityType.HANGING_SIGN, AWGBlockRegistry.ORANGE_ARCHWOOD_HANGING_SIGN.get(), AWGBlockRegistry.ORANGE_ARCHWOOD_HANGING_WALL_SIGN.get());

        event.modify(BlockEntityType.SIGN, AWGBlockRegistry.WHITE_ARCHWOOD_SIGN.get(), AWGBlockRegistry.WHITE_ARCHWOOD_WALL_SIGN.get());
        event.modify(BlockEntityType.HANGING_SIGN, AWGBlockRegistry.WHITE_ARCHWOOD_HANGING_SIGN.get(), AWGBlockRegistry.WHITE_ARCHWOOD_HANGING_WALL_SIGN.get());

        //      ┌──────────────────────────────────────────────────────────┐
        //      │                       Ars Nouveau                        │
        //      └──────────────────────────────────────────────────────────┘
        event.modify(BlockEntityType.SIGN, AWGBlockRegistry.BLUE_ARCHWOOD_SIGN.get(), AWGBlockRegistry.BLUE_ARCHWOOD_WALL_SIGN.get());
        event.modify(BlockEntityType.HANGING_SIGN, AWGBlockRegistry.BLUE_ARCHWOOD_HANGING_SIGN.get(), AWGBlockRegistry.BLUE_ARCHWOOD_HANGING_WALL_SIGN.get());

        event.modify(BlockEntityType.SIGN, AWGBlockRegistry.GREEN_ARCHWOOD_SIGN.get(), AWGBlockRegistry.GREEN_ARCHWOOD_WALL_SIGN.get());
        event.modify(BlockEntityType.HANGING_SIGN, AWGBlockRegistry.GREEN_ARCHWOOD_HANGING_SIGN.get(), AWGBlockRegistry.GREEN_ARCHWOOD_HANGING_WALL_SIGN.get());

        event.modify(BlockEntityType.SIGN, AWGBlockRegistry.PURPLE_ARCHWOOD_SIGN.get(), AWGBlockRegistry.PURPLE_ARCHWOOD_WALL_SIGN.get());
        event.modify(BlockEntityType.HANGING_SIGN, AWGBlockRegistry.PURPLE_ARCHWOOD_HANGING_SIGN.get(), AWGBlockRegistry.PURPLE_ARCHWOOD_HANGING_WALL_SIGN.get());

        event.modify(BlockEntityType.SIGN, AWGBlockRegistry.RED_ARCHWOOD_SIGN.get(), AWGBlockRegistry.RED_ARCHWOOD_WALL_SIGN.get());
        event.modify(BlockEntityType.HANGING_SIGN, AWGBlockRegistry.RED_ARCHWOOD_HANGING_SIGN.get(), AWGBlockRegistry.RED_ARCHWOOD_HANGING_WALL_SIGN.get());

        //      ┌──────────────────────────────────────────────────────────┐
        //      │                      Ars Elemental                       │
        //      └──────────────────────────────────────────────────────────┘
        if (ModList.get().isLoaded("ars_elemental")) {
            event.modify(BlockEntityType.SIGN, ElementalModule.YELLOW_ARCHWOOD_SIGN.get(), ElementalModule.YELLOW_ARCHWOOD_WALL_SIGN.get());
            event.modify(BlockEntityType.HANGING_SIGN, ElementalModule.YELLOW_ARCHWOOD_HANGING_SIGN.get(), ElementalModule.YELLOW_ARCHWOOD_WALL_HANGING_SIGN.get());
        }

    }
}
