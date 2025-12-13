package com.alexthw.archwood_good.datagen;

import alexthw.ars_elemental.ArsElemental;
import com.alexthw.archwood_good.ArchwoodGood;
import com.hollingsworth.arsnouveau.ArsNouveau;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class AWGLootTables extends LootTableProvider {

    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    public AWGLootTables(DataGenerator dataGeneratorIn, CompletableFuture<HolderLookup.Provider> provider) {
        super(dataGeneratorIn.getPackOutput(), new HashSet<>(), List.of(new SubProviderEntry(BlockLootTable::new, LootContextParamSets.BLOCK)), provider);
    }

    public static class BlockLootTable extends BlockLootSubProvider {
        public List<Block> list = new ArrayList<>();

        protected BlockLootTable(HolderLookup.Provider provider) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        protected void generate() {
            for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                String woodTypeId = woodType.getId().toString();
                // Archwood Good
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

                    if (Objects.nonNull(leaves) && Objects.nonNull(sapling))
                        registerLeavesAndSticks(leaves, sapling);

                    if (Objects.nonNull(planks)) registerDropSelf(planks);
                    if (Objects.nonNull(log)) registerDropSelf(log);
                    if (Objects.nonNull(stripped_log)) registerDropSelf(stripped_log);
                    if (Objects.nonNull(wood)) registerDropSelf(wood);
                    if (Objects.nonNull(stripped_wood)) registerDropSelf(stripped_wood);
                    if (Objects.nonNull(slab)) registerSlabItemTable(slab);
                    if (Objects.nonNull(stairs)) registerDropSelf(stairs);

                }

                // Ars Nouevau & Ars Elemental
                if (woodTypeId.matches("\\("+ ArsNouveau.MODID +"|"+ ArsElemental.MODID + "\\):.*")) {
                    Block planks = woodType.planks;
                    Block log = woodType.log;
                    Block stripped_log = woodType.getBlockOfThis(VanillaWoodChildKeys.STRIPPED_LOG);
                    Block wood = woodType.getBlockOfThis(VanillaWoodChildKeys.WOOD);
                    Block stripped_wood = woodType.getBlockOfThis(VanillaWoodChildKeys.STRIPPED_WOOD);
                    Block slab = woodType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
                    Block stairs = woodType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);

                    if (Objects.nonNull(planks)) registerDropSelf(planks);
                    if (Objects.nonNull(slab)) registerSlabItemTable(slab);
                    if (Objects.nonNull(stairs)) registerDropSelf(stairs);

                    // Only fading_archwood need the loot_tables
                    if (woodTypeId.matches(ArsNouveau.MODID + ":archwood")) {
                        if (Objects.nonNull(log)) registerDropSelf(log);
                        if (Objects.nonNull(stripped_log)) registerDropSelf(stripped_log);
                        if (Objects.nonNull(wood)) registerDropSelf(wood);
                        if (Objects.nonNull(stripped_wood)) registerDropSelf(stripped_wood);
                    }

                }
            }

        }

        public void registerDropSelf(Block block) {
            list.add(block);
            dropSelf(block);
        }


        protected void registerSlabItemTable(Block slab) {
            list.add(slab);
            this.add(slab, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                    .add(applyExplosionDecay(slab, LootItem.lootTableItem(slab).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(slab).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        }

        public void registerLeavesAndSticks(Block leaves, Block sapling) {
            list.add(leaves);
            this.add(leaves, l_state -> createLeavesDrops(l_state, sapling, DEFAULT_SAPLING_DROP_RATES));
        }


        @Override
        protected @NotNull Iterable<Block> getKnownBlocks() {
            return list;
        }

    }
}
