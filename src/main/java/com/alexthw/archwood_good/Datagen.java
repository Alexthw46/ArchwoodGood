package com.alexthw.archwood_good;

import alexthw.ars_elemental.ArsElemental;
import alexthw.ars_elemental.registry.ModItems;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
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

    static TagKey<Item> archwoodPlanks = ItemTags.create(ArsNouveau.prefix("archwood_planks"));

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
                tag(archwoodPlanks).add(
                        Registry.BLUE_ARCHWOOD_PLANK.get().asItem(),
                        Registry.RED_ARCHWOOD_PLANK.get().asItem(),
                        Registry.GREEN_ARCHWOOD_PLANK.get().asItem(),
                        Registry.PURPLE_ARCHWOOD_PLANK.get().asItem()
                ).addOptional(ElementalModule.YELLOW_ARCHWOOD_PLANK.getId());
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

        gen.addProvider(event.includeServer(), new RecipeProvider(output, provider) {
            @Override
            protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
                shapelessBuilder(BlockRegistry.ARCHWOOD_PLANK.get()).requires(Ingredient.of(archwoodPlanks)).save(recipeOutput);

                shapelessBuilder(Registry.BLUE_ARCHWOOD_PLANK, 4).requires(BlockRegistry.CASCADING_LOG.get()).save(recipeOutput);
                shapelessBuilder(Registry.RED_ARCHWOOD_PLANK,4).requires(BlockRegistry.BLAZING_LOG.get()).save(recipeOutput);
                shapelessBuilder(Registry.GREEN_ARCHWOOD_PLANK,4).requires(BlockRegistry.FLOURISHING_LOG.get()).save(recipeOutput);
                shapelessBuilder(Registry.PURPLE_ARCHWOOD_PLANK,4).requires(BlockRegistry.VEXING_LOG.get()).save(recipeOutput);
                shapelessBuilder(ElementalModule.YELLOW_ARCHWOOD_PLANK.get(),4).requires(ModItems.FLASHING_ARCHWOOD_LOG.get()).save(recipeOutput);

            }

            public ShapelessRecipeBuilder shapelessBuilder(ItemLike result) {
                return shapelessBuilder(result, 1);
            }

            public ShapelessRecipeBuilder shapelessBuilder(ItemLike result, int resultCount) {
                return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultCount).unlockedBy("has_journal", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsRegistry.WORN_NOTEBOOK));
            }
        });
    }


}