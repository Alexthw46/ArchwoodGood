package com.alexthw.archwood_good.datagen;

import com.alexthw.archwood_good.lib.ContentName;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.LEAVES;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SAPLING;

public class AWGCompostablesProvider extends DataMapProvider {

    /**
     * Create a new provider.
     *
     * @param packOutput     the output location
     * @param lookupProvider a {@linkplain CompletableFuture} supplying the registries
     */
    protected AWGCompostablesProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        Builder<Compostable, Item> compostable = builder(NeoForgeDataMaps.COMPOSTABLES);

        WoodType[] woodTypes = new WoodType[]{
                WoodTypeRegistry.INSTANCE.get(ContentName.ORANGE_ARCHWOOD),
                WoodTypeRegistry.INSTANCE.get(ContentName.WHITE_ARCHWOOD)
        };

        for (WoodType woodType : woodTypes) {
            if (woodType != null) {
                Block leaves = woodType.getBlockOfThis(LEAVES);
                Block sapling = woodType.getBlockOfThis(SAPLING);
                Block archfruit = woodType.getBlockOfThis("archfruit");

                if (sapling != null) compostable.add(Utils.getID(sapling), new Compostable(0.3F), false);
                if (archfruit != null) compostable.add(Utils.getID(archfruit), new Compostable(0.65F), false);
                if (leaves != null) compostable.add(Utils.getID(leaves), new Compostable(0.3F), false);
            }
        }

        compostable.build();

    }
}