package com.alexthw.archwood_good;

import com.hollingsworth.arsnouveau.setup.registry.CreativeTabRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AWGCreativeTabRegistry {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ArchwoodGood.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS = TABS.register("general", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.archwood_good"))
            .icon(() -> ContentRegistry.FADING_ARCHWOOD_LOG.get().asItem().getDefaultInstance())
            .displayItems((params, output) -> {
                for (DeferredHolder<Block, ? extends Block> entry : ContentRegistry.BLOCKS.getEntries()) {
                    var item = entry.get().asItem();
                    output.accept(item.getDefaultInstance());
                }

            }).withTabsBefore(CreativeTabRegistry.GLYPHS.getKey())
            .build());


}
