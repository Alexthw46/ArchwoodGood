package com.alexthw.archwood_good;

import com.alexthw.archwood_good.integration.ElementalModule;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

public class ContentSetup {

    public static void registers(IEventBus modEventBux) {
        modEventBux.addListener(ContentSetup::addBlocksToTile);
    }

    public static void addBlocksToTile(BlockEntityTypeAddBlocksEvent event) {
        // Archwood Good
        event.modify(BlockEntityType.SIGN, ContentRegistry.ORANGE_ARCHWOOD_SIGN.get(), ContentRegistry.ORANGE_ARCHWOOD_WALL_SIGN.get());
        event.modify(BlockEntityType.HANGING_SIGN, ContentRegistry.ORANGE_ARCHWOOD_HANGING_SIGN.get(), ContentRegistry.ORANGE_ARCHWOOD_HANGING_WALL_SIGN.get());

        event.modify(BlockEntityType.SIGN, ContentRegistry.WHITE_ARCHWOOD_SIGN.get(), ContentRegistry.WHITE_ARCHWOOD_WALL_SIGN.get());
        event.modify(BlockEntityType.HANGING_SIGN, ContentRegistry.WHITE_ARCHWOOD_HANGING_SIGN.get(), ContentRegistry.WHITE_ARCHWOOD_HANGING_WALL_SIGN.get());

        // Ars Nouveau
        event.modify(BlockEntityType.SIGN, ContentRegistry.BLUE_ARCHWOOD_SIGN.get(), ContentRegistry.BLUE_ARCHWOOD_WALL_SIGN.get());
        event.modify(BlockEntityType.HANGING_SIGN, ContentRegistry.BLUE_ARCHWOOD_HANGING_SIGN.get(), ContentRegistry.BLUE_ARCHWOOD_HANGING_WALL_SIGN.get());

        event.modify(BlockEntityType.SIGN, ContentRegistry.GREEN_ARCHWOOD_SIGN.get(), ContentRegistry.GREEN_ARCHWOOD_WALL_SIGN.get());
        event.modify(BlockEntityType.HANGING_SIGN, ContentRegistry.GREEN_ARCHWOOD_HANGING_SIGN.get(), ContentRegistry.GREEN_ARCHWOOD_HANGING_WALL_SIGN.get());

        event.modify(BlockEntityType.SIGN, ContentRegistry.PURPLE_ARCHWOOD_SIGN.get(), ContentRegistry.PURPLE_ARCHWOOD_WALL_SIGN.get());
        event.modify(BlockEntityType.HANGING_SIGN, ContentRegistry.PURPLE_ARCHWOOD_HANGING_SIGN.get(), ContentRegistry.PURPLE_ARCHWOOD_HANGING_WALL_SIGN.get());

        event.modify(BlockEntityType.SIGN, ContentRegistry.RED_ARCHWOOD_SIGN.get(), ContentRegistry.RED_ARCHWOOD_WALL_SIGN.get());
        event.modify(BlockEntityType.HANGING_SIGN, ContentRegistry.RED_ARCHWOOD_HANGING_SIGN.get(), ContentRegistry.RED_ARCHWOOD_HANGING_WALL_SIGN.get());

        // Ars Elemental
        if (ModList.get().isLoaded("ars_elemental")) {
            event.modify(BlockEntityType.SIGN, ElementalModule.YELLOW_ARCHWOOD_SIGN.get(), ElementalModule.YELLOW_ARCHWOOD_WALL_SIGN.get());
            event.modify(BlockEntityType.HANGING_SIGN, ElementalModule.YELLOW_ARCHWOOD_HANGING_SIGN.get(), ElementalModule.YELLOW_ARCHWOOD_WALL_HANGING_SIGN.get());
        }

    }
}
