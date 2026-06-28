package com.alexthw.archwood_good.datagen;

import alexthw.ars_elemental.ArsElemental;
import com.alexthw.archwood_good.ArchwoodGood;
import com.alexthw.archwood_good.integration.ElementalModule;
import com.alexthw.archwood_good.registry.AWGBlockRegistry;
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
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.alexthw.archwood_good.datagen.Datagen.archwoodPlanks;
import static com.hollingsworth.arsnouveau.common.datagen.ItemTagProvider.SHADY_WIZARD_FRUITS;

public class AWGItemTagsProvider extends ItemTagsProvider {

    public static final TagKey<Item> FADING_ARCHWOOD_LOG_TAG = ItemTags.create(ArsNouveau.prefix("archwood_logs"));
    public static Map<String, TagKey<Item>> tagMap = new HashMap<>();

    public AWGItemTagsProvider(DataGenerator gen, CompletableFuture<HolderLookup.Provider> provider, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen.getPackOutput(), provider, blockTagsProvider.contentsGetter(), ArchwoodGood.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
            String woodTypeId = woodType.getId().toString();

            if (woodTypeId.matches("(" + ArchwoodGood.MODID + "|" + ArsNouveau.MODID + "|" + ArsElemental.MODID + "):(?!archwood).*")) {
                Item planks = woodType.planks.asItem();
                Item log = woodType.log.asItem();
                Item stripped_log = woodType.getItemOfThis(VanillaWoodChildKeys.STRIPPED_LOG);
                Item wood = woodType.getItemOfThis(VanillaWoodChildKeys.WOOD);
                Item stripped_wood = woodType.getItemOfThis(VanillaWoodChildKeys.STRIPPED_WOOD);
                Item slab = woodType.getItemOfThis(VanillaWoodChildKeys.SLAB);
                Item stairs = woodType.getItemOfThis(VanillaWoodChildKeys.STAIRS);
                Item leaves = woodType.getItemOfThis(VanillaWoodChildKeys.LEAVES);
                Item sapling = woodType.getItemOfThis(VanillaWoodChildKeys.SAPLING);
                Item sign = woodType.getItemOfThis(VanillaWoodChildKeys.SIGN);
                Item hangingSign = woodType.getItemOfThis(VanillaWoodChildKeys.HANGING_SIGN);

                // Tag Key - example: archwood_good:white_archwood_logs
                TagKey<Item> logTag = ItemTags.create(Utils.getID(log).withSuffix("s"));
                this.tag(logTag).add(log, stripped_log, wood, stripped_wood);

                tagMap.put(woodTypeId, logTag);
                this.tag(ItemTags.PLANKS).add(planks);

                if (sign != null) this.tag(ItemTags.SIGNS).add(sign);
                if (hangingSign != null) this.tag(ItemTags.HANGING_SIGNS).add(hangingSign);

            }

                /// Archwood Good
            if (woodTypeId.matches(ArchwoodGood.MODID + ":.*")) {
                Item log = woodType.log.asItem();
                Item stripped_log = woodType.getItemOfThis(VanillaWoodChildKeys.STRIPPED_LOG);
                Item wood = woodType.getItemOfThis(VanillaWoodChildKeys.WOOD);
                Item stripped_wood = woodType.getItemOfThis(VanillaWoodChildKeys.STRIPPED_WOOD);
                Item planks = woodType.planks.asItem();
                Item slab = woodType.getItemOfThis(VanillaWoodChildKeys.SLAB);
                Item stairs = woodType.getItemOfThis(VanillaWoodChildKeys.STAIRS);
                Item leaves = woodType.getItemOfThis(VanillaWoodChildKeys.LEAVES);
                Item sapling = woodType.getItemOfThis(VanillaWoodChildKeys.SAPLING);
                Block archfruit = woodType.getBlockOfThis("archfruit");

                // Tag Key - example: archwood_good:white_archwood_logs
//                TagKey<Item> logTag = ItemTags.create(Utils.getID(log).withSuffix("s"));
//                this.tag(logTag).add(log, stripped_log, wood, stripped_wood);
//                tagMap.put(woodTypeId, logTag);
//                this.tag(ItemTags.PLANKS).add(planks);
//                if (slab != null) {
//                    this.tag(ItemTags.SLABS).add(slab);
//                    this.tag(ItemTags.WOODEN_SLABS).add(slab);
//                }
//                if (stairs != null) {
//                    this.tag(ItemTags.STAIRS).add(stairs);
//                    this.tag(ItemTags.WOODEN_STAIRS).add(stairs);
//                }
//                logsTag(log, stripped_log);

                if (leaves != null) this.tag(ItemTags.LEAVES).add(leaves);

                if (archfruit != null) {
                    this.tag(SHADY_WIZARD_FRUITS).add(archfruit.asItem());
                    this.tag(Tags.Items.FOODS_FRUIT).add(archfruit.asItem());
                }
            }

        }
        copy(BlockTags.PLANKS, ItemTags.PLANKS);

        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);


        /// FADING_ARCHWOOD
        this.tag(FADING_ARCHWOOD_LOG_TAG).add(
                AWGBlockRegistry.FADING_ARCHWOOD_LOG.get().asItem(),
                AWGBlockRegistry.STRIPPED_FADING_ARCHWOOD_LOG.get().asItem(),
                AWGBlockRegistry.FADING_ARCHWOOD_WOOD.get().asItem(),
                AWGBlockRegistry.STRIPPED_FADING_ARCHWOOD_WOOD.get().asItem()
        );

//        copy(BlockTags.SLABS, ItemTags.SLABS);
//        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
//        copy(BlockTags.STAIRS, ItemTags.STAIRS);
//        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);

        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        tag(archwoodPlanks).add(
                AWGBlockRegistry.BLUE_ARCHWOOD_PLANK.get().asItem(),
                AWGBlockRegistry.RED_ARCHWOOD_PLANK.get().asItem(),
                AWGBlockRegistry.GREEN_ARCHWOOD_PLANK.get().asItem(),
                AWGBlockRegistry.PURPLE_ARCHWOOD_PLANK.get().asItem(),

                AWGBlockRegistry.ORANGE_ARCHWOOD_PLANK.get().asItem(),
                AWGBlockRegistry.WHITE_ARCHWOOD_PLANK.get().asItem()
        ).addOptional(ElementalModule.YELLOW_ARCHWOOD_PLANK.getId());
    }
}
