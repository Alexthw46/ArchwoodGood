package com.alexthw.archwood_good.datagen;

import com.alexthw.archwood_good.ArchwoodGood;
import com.alexthw.archwood_good.ContentRegistry;
import com.alexthw.archwood_good.integration.ElementalModule;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.alexthw.archwood_good.datagen.Datagen.archwoodPlanks;

public class AWGItemTagsProvider extends ItemTagsProvider {

    public AWGItemTagsProvider(DataGenerator gen, CompletableFuture<HolderLookup.Provider> provider, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen.getPackOutput(), provider, blockTagsProvider.contentsGetter(), ArchwoodGood.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        tag(archwoodPlanks).add(
                ContentRegistry.BLUE_ARCHWOOD_PLANK.get().asItem(),
                ContentRegistry.RED_ARCHWOOD_PLANK.get().asItem(),
                ContentRegistry.GREEN_ARCHWOOD_PLANK.get().asItem(),
                ContentRegistry.PURPLE_ARCHWOOD_PLANK.get().asItem(),

                ContentRegistry.ORANGE_ARCHWOOD_PLANK.get().asItem(),
                ContentRegistry.WHITE_ARCHWOOD_PLANK.get().asItem()
        ).addOptional(ElementalModule.YELLOW_ARCHWOOD_PLANK.getId());
    }
}
