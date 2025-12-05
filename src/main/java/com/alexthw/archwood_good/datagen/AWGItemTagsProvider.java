package com.alexthw.archwood_good.datagen;

import alexthw.ars_elemental.ArsElemental;
import com.alexthw.archwood_good.ArchwoodGood;
import com.alexthw.archwood_good.Registry;
import com.alexthw.archwood_good.integration.ElementalModule;
import com.hollingsworth.arsnouveau.ArsNouveau;
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
                Registry.BLUE_ARCHWOOD_PLANK.get().asItem(),
                Registry.RED_ARCHWOOD_PLANK.get().asItem(),
                Registry.GREEN_ARCHWOOD_PLANK.get().asItem(),
                Registry.PURPLE_ARCHWOOD_PLANK.get().asItem()
        ).addOptional(ElementalModule.YELLOW_ARCHWOOD_PLANK.getId());
    }
}
