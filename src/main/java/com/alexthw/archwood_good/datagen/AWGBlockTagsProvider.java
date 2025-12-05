package com.alexthw.archwood_good.datagen;

import alexthw.ars_elemental.ArsElemental;
import com.alexthw.archwood_good.ArchwoodGood;
import com.alexthw.archwood_good.Registry;
import com.alexthw.archwood_good.integration.ElementalModule;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class AWGBlockTagsProvider extends BlockTagsProvider {

    public AWGBlockTagsProvider(DataGenerator gen, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen.getPackOutput(), provider, ArchwoodGood.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        this.tag(BlockTags.PLANKS)
                .add(Registry.BLUE_ARCHWOOD_PLANK.get())
                .add(Registry.RED_ARCHWOOD_PLANK.get())
                .add(Registry.GREEN_ARCHWOOD_PLANK.get())
                .add(Registry.PURPLE_ARCHWOOD_PLANK.get())
                .addOptional(ElementalModule.YELLOW_ARCHWOOD_PLANK.getId());

        logsTag(Registry.ARCHWOOD_LOG.get(),
                Registry.STRIPPED_ARCHWOOD_LOG.get()
        );

        this.tag(BlockTags.LEAVES).add(Registry.ARCHWOOD_LEAVES.get());
    }

    void logsTag(Block... blocks) {
        tag(BlockTags.LOGS).add(blocks);
        tag(BlockTags.LOGS_THAT_BURN).add(blocks);
        tag(BlockTags.MINEABLE_WITH_AXE).add(blocks);
    }


    @Override
    public @NotNull String getName() {
        return "Archwood Good Block Tags";
    }
}
