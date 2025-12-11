package com.alexthw.archwood_good.datagen;

import com.alexthw.archwood_good.ArchwoodGood;
import com.hollingsworth.arsnouveau.ArsNouveau;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ArchwoodGood.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Datagen {
    public static CompletableFuture<HolderLookup.Provider> provider;
    public static PackOutput output;

    static TagKey<Item> archwoodPlanks = ItemTags.create(ArsNouveau.prefix("archwood_planks"));

    //use runData configuration to generate stuff, event.includeServer() for data, event.includeClient() for assets
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        provider = event.getLookupProvider();
        output = gen.getPackOutput();

        BlockTagsProvider blockTagsProvider = new AWGBlockTagsProvider(gen, provider, existingFileHelper);

        gen.addProvider(event.includeServer(), blockTagsProvider);
        gen.addProvider(event.includeServer(), new AWGItemTagsProvider(gen, provider, blockTagsProvider, existingFileHelper));
        gen.addProvider(event.includeServer(), new RecipeDataGen(output, provider));
        gen.addProvider(event.includeServer(), new AWGLootTables(gen, provider));

        AWGBlockStatesDatagen.gatherDataForAWG(event, gen, existingFileHelper, output);
        AWGBlockStatesDatagen.gatherDataForAN(event, gen, existingFileHelper, output);
        AWGBlockStatesDatagen.gatherDataForAE(event, gen, existingFileHelper, output);

        /// Work In Progress - Xel'Bayria
//        gen.addProvider(event.includeServer(), new AWGPlacedFeatureTagsProvider(output, provider, existingFileHelper));

//        gen.addProvider(event.includeServer(), new AWGWorldgenProvider(output, provider));
//        gen.addProvider(event.includeServer(), new AWGBiomeTagsProvider(output, provider, existingFileHelper));

        /// ─────────────────────────────── Worldprovider ───────────────────────────────
//        DatapackBuiltinEntriesProvider datapackProvider = new AWGWorldgenProvider(output, provider);
//        event.getGenerator().addProvider(event.includeServer(), datapackProvider);
//        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
//        event.getGenerator().addProvider(event.includeServer(), new AWGBiomeTagProvider(output, lookupProvider, existingFileHelper));

    }


}