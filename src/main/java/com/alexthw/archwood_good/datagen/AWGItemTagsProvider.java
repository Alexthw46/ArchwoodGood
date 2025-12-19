package com.alexthw.archwood_good.datagen;

import com.alexthw.archwood_good.ArchwoodGood;
import com.alexthw.archwood_good.ContentRegistry;
import com.alexthw.archwood_good.integration.ElementalModule;
import com.hollingsworth.arsnouveau.ArsNouveau;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.alexthw.archwood_good.datagen.Datagen.archwoodPlanks;

public class AWGItemTagsProvider extends ItemTagsProvider {

    public static final TagKey<Item> FADING_ARCHWOOD_LOG_TAG = ItemTags.create(ArsNouveau.prefix("archwood_logs"));

    public AWGItemTagsProvider(DataGenerator gen, CompletableFuture<HolderLookup.Provider> provider, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen.getPackOutput(), provider, blockTagsProvider.contentsGetter(), ArchwoodGood.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
            String woodTypeId = woodType.getId().toString();

            /// Archwood Good
            if (woodTypeId.matches(ArchwoodGood.MODID + ":.*")) {
                Item planks = woodType.planks.asItem();
                Item log = woodType.log.asItem();
                Item stripped_log = woodType.getItemOfThis(VanillaWoodChildKeys.STRIPPED_LOG);
                Item wood = woodType.getItemOfThis(VanillaWoodChildKeys.WOOD);
                Item stripped_wood = woodType.getItemOfThis(VanillaWoodChildKeys.STRIPPED_WOOD);
                Item slab = woodType.getItemOfThis(VanillaWoodChildKeys.SLAB);
                Item stairs = woodType.getItemOfThis(VanillaWoodChildKeys.STAIRS);
                Item leaves = woodType.getItemOfThis(VanillaWoodChildKeys.LEAVES);
                Item sapling = woodType.getItemOfThis(VanillaWoodChildKeys.SAPLING);

                // Tag Key
                TagKey<Item> logTag = ItemTags.create(Utils.getID(log).withSuffix("s"));
                this.tag(logTag).add(log, stripped_log, wood, stripped_wood);

                this.tag(ItemTags.PLANKS).add(planks);

                if (Objects.nonNull(slab)) {
                    this.tag(ItemTags.SLABS).add(slab);
                    this.tag(ItemTags.WOODEN_SLABS).add(slab);
                }
                if (Objects.nonNull(stairs)) {
                    this.tag(ItemTags.STAIRS).add(stairs);
                    this.tag(ItemTags.WOODEN_STAIRS).add(stairs);
                }

//                logsTag(log, stripped_log);

                if (Objects.nonNull(leaves)) this.tag(ItemTags.LEAVES).add(leaves);
            }

            /// Ars Nouevau
            if (woodTypeId.matches(ArsNouveau.MODID + ":.*")) {
                Item planks = woodType.planks.asItem();
                Item slab = woodType.getItemOfThis(VanillaWoodChildKeys.SLAB);
                Item stairs = woodType.getItemOfThis(VanillaWoodChildKeys.STAIRS);

                this.tag(ItemTags.PLANKS).add(planks);

                if (Objects.nonNull(slab)) {
                    this.tag(ItemTags.SLABS).add(slab);
                    this.tag(ItemTags.WOODEN_SLABS).add(slab);
                }
                if (Objects.nonNull(stairs)) {
                    this.tag(ItemTags.STAIRS).add(stairs);
                    this.tag(ItemTags.WOODEN_STAIRS).add(stairs);
                }

            }

        }

        /// FADING_ARCHWOOD
        this.tag(FADING_ARCHWOOD_LOG_TAG).add(
                ContentRegistry.FADING_ARCHWOOD_LOG.get().asItem(),
                ContentRegistry.STRIPPED_FADING_ARCHWOOD_LOG.get().asItem(),
                ContentRegistry.FADING_ARCHWOOD_WOOD.get().asItem(),
                ContentRegistry.STRIPPED_FADING_ARCHWOOD_WOOD.get().asItem()
        );

//        copy(BlockTags.SLABS, ItemTags.SLABS);
//        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
//        copy(BlockTags.STAIRS, ItemTags.STAIRS);
//        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);

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
