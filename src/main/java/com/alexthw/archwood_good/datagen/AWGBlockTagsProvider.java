package com.alexthw.archwood_good.datagen;

import alexthw.ars_elemental.ArsElemental;
import com.alexthw.archwood_good.ArchwoodGood;
import com.alexthw.archwood_good.registry.AWGBlockRegistry;
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

import java.util.concurrent.CompletableFuture;

import static com.hollingsworth.arsnouveau.common.datagen.BlockTagProvider.MAGIC_PLANTS;

public class AWGBlockTagsProvider extends BlockTagsProvider {

    public static final TagKey<Block> FADING_ARCHWOOD_LOG_TAG = BlockTags.create(ArsNouveau.prefix("archwood_logs"));

    public AWGBlockTagsProvider(DataGenerator gen, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen.getPackOutput(), provider, ArchwoodGood.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
            String woodTypeId = woodType.getId().toString();

            if (woodTypeId.matches("(" + ArchwoodGood.MODID + "|" + ArsNouveau.MODID + "|" + ArsElemental.MODID + "):(?!archwood).*")) {
                Block planks = woodType.planks;
                Block log = woodType.log;
                Block stripped_log = woodType.getBlockOfThis(VanillaWoodChildKeys.STRIPPED_LOG);
                Block wood = woodType.getBlockOfThis(VanillaWoodChildKeys.WOOD);
                Block stripped_wood = woodType.getBlockOfThis(VanillaWoodChildKeys.STRIPPED_WOOD);
                Block slab = woodType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
                Block stairs = woodType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);
                Block leaves = woodType.getBlockOfThis(VanillaWoodChildKeys.LEAVES);
//                Block sapling = woodType.getBlockOfThis(VanillaWoodChildKeys.SAPLING);
                Block sign = woodType.getBlockOfThis(VanillaWoodChildKeys.SIGN);
                Block hangingSign = woodType.getBlockOfThis(VanillaWoodChildKeys.HANGING_SIGN);
                Block archfruit = woodType.getBlockOfThis("archfruit");

                if (woodTypeId.matches(ArsElemental.prefix("yellow_archwood").toString())) {
                    this.tag(BlockTags.PLANKS).addOptional(Utils.getID(planks));

                    this.tag(BlockTags.MINEABLE_WITH_AXE).addOptional(Utils.getID(planks));

                    if (slab != null) {
                        this.tag(BlockTags.MINEABLE_WITH_AXE).addOptional(Utils.getID(slab));
                        this.tag(BlockTags.SLABS).addOptional(Utils.getID(slab));
                        this.tag(BlockTags.WOODEN_SLABS).addOptional(Utils.getID(slab));
                    }
                    if (stairs != null) {
                        this.tag(BlockTags.MINEABLE_WITH_AXE).addOptional(Utils.getID(stairs));
                        this.tag(BlockTags.STAIRS).addOptional(Utils.getID(stairs));
                        this.tag(BlockTags.WOODEN_STAIRS).addOptional(Utils.getID(stairs));
                    }
                    if (sign != null) {
                        this.tag(BlockTags.MINEABLE_WITH_AXE).addOptional(Utils.getID(sign));
                        this.tag(BlockTags.SIGNS).addOptional(Utils.getID(sign));
                        this.tag(BlockTags.WALL_POST_OVERRIDE).addOptional(Utils.getID(sign));
                        this.tag(BlockTags.ALL_SIGNS).addOptional(Utils.getID(sign));
                        this.tag(BlockTags.ENCHANTMENT_POWER_TRANSMITTER).addOptional(Utils.getID(sign));
                        this.tag(BlockTags.STANDING_SIGNS).addOptional(Utils.getID(sign));
                    }
                    if (hangingSign != null) {
                        this.tag(BlockTags.MINEABLE_WITH_AXE).addOptional(Utils.getID(hangingSign));
                        this.tag(BlockTags.ALL_HANGING_SIGNS).addOptional(Utils.getID(hangingSign));
                        this.tag(BlockTags.CEILING_HANGING_SIGNS).addOptional(Utils.getID(hangingSign));
                        this.tag(BlockTags.ALL_SIGNS).addOptional(Utils.getID(hangingSign));
                        this.tag(BlockTags.ENCHANTMENT_POWER_TRANSMITTER).addOptional(Utils.getID(hangingSign));
                    }
                }
                else {
                    this.tag(BlockTags.PLANKS).add(planks);

                    this.tag(BlockTags.MINEABLE_WITH_AXE).add(planks);

                    if (slab != null) {
                        this.tag(BlockTags.MINEABLE_WITH_AXE).add(slab);
                        this.tag(BlockTags.SLABS).add(slab);
                        this.tag(BlockTags.WOODEN_SLABS).add(slab);
                    }
                    if (stairs != null) {
                        this.tag(BlockTags.MINEABLE_WITH_AXE).add(stairs);
                        this.tag(BlockTags.STAIRS).add(stairs);
                        this.tag(BlockTags.WOODEN_STAIRS).add(stairs);
                    }
                    if (sign != null) {
                        this.tag(BlockTags.MINEABLE_WITH_AXE).add(sign);
                        this.tag(BlockTags.SIGNS).add(sign);
                        this.tag(BlockTags.WALL_POST_OVERRIDE).add(sign);
                        this.tag(BlockTags.ALL_SIGNS).add(sign);
                        this.tag(BlockTags.ENCHANTMENT_POWER_TRANSMITTER).add(sign);
                        this.tag(BlockTags.STANDING_SIGNS).add(sign);
                    }
                    if (hangingSign != null) {
                        this.tag(BlockTags.MINEABLE_WITH_AXE).add(hangingSign);
                        this.tag(BlockTags.ALL_HANGING_SIGNS).add(hangingSign);
                        this.tag(BlockTags.CEILING_HANGING_SIGNS).add(hangingSign);
                        this.tag(BlockTags.ALL_SIGNS).add(hangingSign);
                        this.tag(BlockTags.ENCHANTMENT_POWER_TRANSMITTER).add(hangingSign);
                    }

                    if (archfruit != null) {
                        this.tag(MAGIC_PLANTS).add(archfruit);
                    }

                }

                /// Archwood Good
                if (woodTypeId.matches(ArchwoodGood.MODID + ":.*")) {
                    // Tag Key
                    TagKey<Block> logTag = BlockTags.create(Utils.getID(log).withSuffix("s"));
                    this.tag(logTag).add(log, stripped_log, wood, stripped_wood);

                    logsTag(log, stripped_log);

                    if (leaves != null) this.tag(BlockTags.LEAVES).add(leaves);
                }

                /// Ars Nouevau

                /// Ars Elemental

            }

        }

        /// FADING_ARCHWOOD
        this.tag(FADING_ARCHWOOD_LOG_TAG).add(
                AWGBlockRegistry.FADING_ARCHWOOD_LOG.get(),
                AWGBlockRegistry.STRIPPED_FADING_ARCHWOOD_LOG.get(),
                AWGBlockRegistry.FADING_ARCHWOOD_WOOD.get(),
                AWGBlockRegistry.STRIPPED_FADING_ARCHWOOD_WOOD.get()
        );
        logsTag(
                AWGBlockRegistry.FADING_ARCHWOOD_LOG.get(),
                AWGBlockRegistry.STRIPPED_FADING_ARCHWOOD_LOG.get()
        );
        this.tag(BlockTags.LEAVES).add(AWGBlockRegistry.FADING_ARCHWOOD_LEAVES.get());
    }

    public void logsTag(Block... blocks) {
        tag(BlockTags.LOGS).add(blocks);
        tag(BlockTags.LOGS_THAT_BURN).add(blocks);
        tag(BlockTags.MINEABLE_WITH_AXE).add(blocks);
    }
    public void slabsTag(Block blocks) {
        tag(BlockTags.SLABS).add(blocks);
        tag(BlockTags.WOODEN_SLABS).add(blocks);
        tag(BlockTags.MINEABLE_WITH_AXE).add(blocks);
    }


    @Override
    public @NotNull String getName() {
        return "Archwood Good Block Tags";
    }
}
