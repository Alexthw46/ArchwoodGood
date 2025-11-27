package com.alexthw.archwood_good;

import alexthw.ars_elemental.ArsElemental;
import com.hollingsworth.arsnouveau.ArsNouveau;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ArchwoodGoodMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Datagen {
    public static CompletableFuture<HolderLookup.Provider> provider;
    public static PackOutput output;

    //use runData configuration to generate stuff, event.includeServer() for data, event.includeClient() for assets
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        provider = event.getLookupProvider();
        output = gen.getPackOutput();

        var blockTags = gen.addProvider(event.includeServer(), new BlockTagsProvider(output, provider, ArchwoodGoodMod.MODID, existingFileHelper) {
            @Override
            protected void addTags(HolderLookup.@NotNull Provider provider) {
                // Add the archwood planks to the wooden planks tag
                this.tag(BlockTags.PLANKS)
                        .add(Registry.BLUE_ARCHWOOD_PLANK.get())
                        .add(Registry.RED_ARCHWOOD_PLANK.get())
                        .add(Registry.GREEN_ARCHWOOD_PLANK.get())
                        .add(Registry.PURPLE_ARCHWOOD_PLANK.get())
                        .addOptional(ElementalModule.YELLOW_ARCHWOOD_PLANK.getId())
                ;
            }
        });

        gen.addProvider(event.includeServer(), new ItemTagsProvider(output, provider, blockTags.contentsGetter(), ArchwoodGoodMod.MODID, existingFileHelper) {
            @Override
            protected void addTags(HolderLookup.@NotNull Provider provider) {
                copy(BlockTags.PLANKS, ItemTags.PLANKS);
            }
        });

        gen.addProvider(event.includeClient(), new BlockStateProvider(output, ArsNouveau.MODID, existingFileHelper) {
            @Override
            protected void registerStatesAndModels() {
                simpleBlock(Registry.BLUE_ARCHWOOD_PLANK.get());
                simpleBlock(Registry.RED_ARCHWOOD_PLANK.get());
                simpleBlock(Registry.GREEN_ARCHWOOD_PLANK.get());
                simpleBlock(Registry.PURPLE_ARCHWOOD_PLANK.get());
                simpleBlock(ElementalModule.YELLOW_ARCHWOOD_PLANK.get());
            }
        });

        gen.addProvider(event.includeClient(), new BlockStateProvider(output, ArsElemental.MODID, existingFileHelper) {
            @Override
            protected void registerStatesAndModels() {
                simpleBlock(ElementalModule.YELLOW_ARCHWOOD_PLANK.get());
            }
        });

        gen.addProvider(event.includeClient(), new ItemModelProvider(output, ArsNouveau.MODID, existingFileHelper) {
            @Override
            protected void registerModels() {
                simpleBlockItem(Registry.BLUE_ARCHWOOD_PLANK.get());
                simpleBlockItem(Registry.RED_ARCHWOOD_PLANK.get());
                simpleBlockItem(Registry.GREEN_ARCHWOOD_PLANK.get());
                simpleBlockItem(Registry.PURPLE_ARCHWOOD_PLANK.get());
                simpleBlockItem(ElementalModule.YELLOW_ARCHWOOD_PLANK.get());
            }
        });

        gen.addProvider(event.includeClient(), new ItemModelProvider(output, ArsElemental.MODID, existingFileHelper) {
            @Override
            protected void registerModels() {
                simpleBlockItem(ElementalModule.YELLOW_ARCHWOOD_PLANK.get());
            }
        });
    }

}