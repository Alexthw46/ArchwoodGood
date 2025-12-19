package com.alexthw.archwood_good.datagen;

import alexthw.ars_elemental.ArsElemental;
import com.alexthw.archwood_good.ArchwoodGood;
import com.alexthw.archwood_good.ContentRegistry;
import com.hollingsworth.arsnouveau.ArsNouveau;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class AWGBlockTagsProvider extends BlockTagsProvider {

    public static final TagKey<Block> FADING_ARCHWOOD_LOG_TAG = BlockTags.create(ArsNouveau.prefix("archwood_logs"));

    public AWGBlockTagsProvider(DataGenerator gen, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen.getPackOutput(), provider, ArchwoodGood.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
            String woodTypeId = woodType.getId().toString();

            /// Archwood Good
            if (woodTypeId.matches(ArchwoodGood.MODID + ":.*")) {
                Block planks = woodType.planks;
                Block log = woodType.log;
                Block stripped_log = woodType.getBlockOfThis(VanillaWoodChildKeys.STRIPPED_LOG);
                Block wood = woodType.getBlockOfThis(VanillaWoodChildKeys.WOOD);
                Block stripped_wood = woodType.getBlockOfThis(VanillaWoodChildKeys.STRIPPED_WOOD);
                Block slab = woodType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
                Block stairs = woodType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);
                Block leaves = woodType.getBlockOfThis(VanillaWoodChildKeys.LEAVES);
                Block sapling = woodType.getBlockOfThis(VanillaWoodChildKeys.SAPLING);

                // Tag Key
                TagKey<Block> logTag = BlockTags.create(Utils.getID(log).withSuffix("s"));
                this.tag(logTag).add(log, stripped_log, wood, stripped_wood);

                this.tag(BlockTags.PLANKS).add(planks);

                this.tag(BlockTags.MINEABLE_WITH_AXE).add(planks);
                if (Objects.nonNull(slab)) {
                    this.tag(BlockTags.MINEABLE_WITH_AXE).add(slab);
                    this.tag(BlockTags.SLABS).add(slab);
                    this.tag(BlockTags.WOODEN_SLABS).add(slab);
                }
                if (Objects.nonNull(stairs)) {
                    this.tag(BlockTags.MINEABLE_WITH_AXE).add(stairs);
                    this.tag(BlockTags.STAIRS).add(stairs);
                    this.tag(BlockTags.WOODEN_STAIRS).add(stairs);
                }

                logsTag(log, stripped_log);

                if (Objects.nonNull(leaves)) this.tag(BlockTags.LEAVES).add(leaves);
            }

            /// Ars Nouevau
            if (woodTypeId.matches(ArsNouveau.MODID + ":.*")) {
                Block planks = woodType.planks;
                Block slab = woodType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
                Block stairs = woodType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);

                this.tag(BlockTags.PLANKS).add(planks);

                this.tag(BlockTags.MINEABLE_WITH_AXE).add(planks);
                if (Objects.nonNull(slab)) {
                    this.tag(BlockTags.MINEABLE_WITH_AXE).add(slab);
                    this.tag(BlockTags.SLABS).add(slab);
                    this.tag(BlockTags.WOODEN_SLABS).add(slab);
                }
                if (Objects.nonNull(stairs)) {
                    this.tag(BlockTags.MINEABLE_WITH_AXE).add(stairs);
                    this.tag(BlockTags.STAIRS).add(stairs);
                    this.tag(BlockTags.WOODEN_STAIRS).add(stairs);
                }

            }

        }

        /// Ars Elemental
        WoodType elementalType = WoodTypeRegistry.INSTANCE.get(ArsElemental.prefix("yellow_archwood"));
        if (Objects.nonNull(elementalType)) {
            Block planks = elementalType.planks;
            Block slab = elementalType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
            Block stairs = elementalType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);

            this.tag(BlockTags.PLANKS).addOptional(Utils.getID(planks));

            this.tag(BlockTags.MINEABLE_WITH_AXE).addOptional(Utils.getID(planks));
            if (Objects.nonNull(slab)) {
                this.tag(BlockTags.MINEABLE_WITH_AXE).addOptional(Utils.getID(slab));
                this.tag(BlockTags.SLABS).addOptional(Utils.getID(slab));
                this.tag(BlockTags.WOODEN_SLABS).addOptional(Utils.getID(slab));
            }
            if (Objects.nonNull(stairs)) {
                this.tag(BlockTags.MINEABLE_WITH_AXE).addOptional(Utils.getID(stairs));
                this.tag(BlockTags.STAIRS).addOptional(Utils.getID(stairs));
                this.tag(BlockTags.WOODEN_STAIRS).addOptional(Utils.getID(stairs));
            }
        }

        /// FADING_ARCHWOOD
        this.tag(FADING_ARCHWOOD_LOG_TAG).add(
                ContentRegistry.FADING_ARCHWOOD_LOG.get(),
                ContentRegistry.STRIPPED_FADING_ARCHWOOD_LOG.get(),
                ContentRegistry.FADING_ARCHWOOD_WOOD.get(),
                ContentRegistry.STRIPPED_FADING_ARCHWOOD_WOOD.get()
        );
        logsTag(
                ContentRegistry.FADING_ARCHWOOD_LOG.get(),
                ContentRegistry.STRIPPED_FADING_ARCHWOOD_LOG.get()
        );
        this.tag(BlockTags.LEAVES).add(ContentRegistry.FADING_ARCHWOOD_LEAVES.get());
    }

    void logsTag(Block... blocks) {
        tag(BlockTags.LOGS).add(blocks);
        tag(BlockTags.LOGS_THAT_BURN).add(blocks);
        tag(BlockTags.MINEABLE_WITH_AXE).add(blocks);
    }
    void slabsTag(Block blocks) {
        tag(BlockTags.SLABS).add(blocks);
        tag(BlockTags.WOODEN_SLABS).add(blocks);
        tag(BlockTags.MINEABLE_WITH_AXE).add(blocks);
    }


    @Override
    public @NotNull String getName() {
        return "Archwood Good Block Tags";
    }
}
