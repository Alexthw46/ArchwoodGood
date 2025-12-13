package com.alexthw.archwood_good.datagen;

import alexthw.ars_elemental.ArsElemental;
import com.alexthw.archwood_good.ArchwoodGood;
import com.alexthw.archwood_good.ContentRegistry;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.hollingsworth.arsnouveau.common.datagen.RecipeDatagen.shapedWoodenStairs;

public class RecipeDataGen extends RecipeProvider {

    public RecipeDataGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {

        for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
            String woodTypeId = woodType.getId().toString();
            if (woodTypeId.matches("(" + ArchwoodGood.MODID + "|" + ArsNouveau.MODID + "|" + ArsElemental.MODID + "):(?!archwood).*")) {

                Block log = woodType.log;
                Block stripped_log = woodType.getBlockOfThis(VanillaWoodChildKeys.STRIPPED_LOG);
                Block wood = woodType.getBlockOfThis(VanillaWoodChildKeys.WOOD);
                Block stripped_wood = woodType.getBlockOfThis(VanillaWoodChildKeys.STRIPPED_WOOD);
                Block planks = woodType.planks;
                Block slab = woodType.getBlockOfThis(VanillaWoodChildKeys.SLAB);
                Block stairs = woodType.getBlockOfThis(VanillaWoodChildKeys.STAIRS);
                Block sapling = woodType.getBlockOfThis(VanillaWoodChildKeys.SAPLING);

                shapelessBuilder(planks, 4).requires(log).save(recipeOutput);

                if (Objects.nonNull(wood)) makeWood(log, wood, 3).save(recipeOutput);
                if (Objects.nonNull(stripped_wood)) strippedLogToWood(recipeOutput, stripped_log, stripped_wood);

                if (Objects.nonNull(stairs)) shapedWoodenStairs(recipeOutput, stairs, planks);
                if (Objects.nonNull(slab)) shapedWoodenSlab(recipeOutput, slab, planks);
            }

        }


//                shapelessBuilder(BlockRegistry.ARCHWOOD_PLANK.get()).requires(Ingredient.of(archwoodPlanks)).save(recipeOutput); //TODO: need an item to bleach colored planks into plain planks
        shapelessBuilder(BlockRegistry.ARCHWOOD_PLANK.get(), 4).requires(ContentRegistry.FADING_ARCHWOOD_LOG.get()).save(recipeOutput);
        makeWood(ContentRegistry.FADING_ARCHWOOD_LOG, ContentRegistry.FADING_ARCHWOOD_WOOD.get(), 3).save(recipeOutput);
        strippedLogToWood(recipeOutput, ContentRegistry.STRIPPED_FADING_ARCHWOOD_LOG, ContentRegistry.STRIPPED_FADING_ARCHWOOD_WOOD.get());

//        shapelessBuilder(ContentRegistry.BLUE_ARCHWOOD_PLANK, 4).requires(BlockRegistry.CASCADING_LOG.get()).save(recipeOutput);
//        shapelessBuilder(ContentRegistry.RED_ARCHWOOD_PLANK, 4).requires(BlockRegistry.BLAZING_LOG.get()).save(recipeOutput);
//        shapelessBuilder(ContentRegistry.GREEN_ARCHWOOD_PLANK, 4).requires(BlockRegistry.FLOURISHING_LOG.get()).save(recipeOutput);
//        shapelessBuilder(ContentRegistry.PURPLE_ARCHWOOD_PLANK, 4).requires(BlockRegistry.VEXING_LOG.get()).save(recipeOutput);
//        shapelessBuilder(ElementalModule.YELLOW_ARCHWOOD_PLANK.get(), 4).requires(ModItems.FLASHING_ARCHWOOD_LOG.get()).save(recipeOutput);
//
//        shapedWoodenStairs(recipeOutput, ContentRegistry.BLUE_ARCHWOOD_STAIRS, ContentRegistry.BLUE_ARCHWOOD_PLANK);
//        shapedWoodenStairs(recipeOutput, ContentRegistry.RED_ARCHWOOD_STAIRS, ContentRegistry.RED_ARCHWOOD_PLANK);
//        shapedWoodenStairs(recipeOutput, ContentRegistry.GREEN_ARCHWOOD_STAIRS, ContentRegistry.GREEN_ARCHWOOD_PLANK);
//        shapedWoodenStairs(recipeOutput, ContentRegistry.PURPLE_ARCHWOOD_STAIRS, ContentRegistry.PURPLE_ARCHWOOD_PLANK);
//        shapedWoodenStairs(recipeOutput, ElementalModule.YELLOW_ARCHWOOD_STAIRS.get(), ElementalModule.YELLOW_ARCHWOOD_PLANK.get());
//
//        shapedWoodenSlab(recipeOutput, ContentRegistry.BLUE_ARCHWOOD_SLAB, ContentRegistry.BLUE_ARCHWOOD_PLANK);
//        shapedWoodenSlab(recipeOutput, ContentRegistry.RED_ARCHWOOD_SLAB, ContentRegistry.RED_ARCHWOOD_PLANK);
//        shapedWoodenSlab(recipeOutput, ContentRegistry.GREEN_ARCHWOOD_SLAB, ContentRegistry.GREEN_ARCHWOOD_PLANK);
//        shapedWoodenSlab(recipeOutput, ContentRegistry.PURPLE_ARCHWOOD_SLAB, ContentRegistry.PURPLE_ARCHWOOD_PLANK);
//        shapedWoodenSlab(recipeOutput, ElementalModule.YELLOW_ARCHWOOD_SLAB.get(), ElementalModule.YELLOW_ARCHWOOD_PLANK.get());

    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                      RECIPE METHODS                      │
    //      └──────────────────────────────────────────────────────────┘
    public ShapelessRecipeBuilder shapelessBuilder(ItemLike result) {
        return shapelessBuilder(result, 1);
    }

    public ShapelessRecipeBuilder shapelessBuilder(ItemLike result, int resultCount) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultCount).unlockedBy("has_journal", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsRegistry.WORN_NOTEBOOK));
    }

    private static void shapedWoodenSlab(RecipeOutput recipeConsumer, ItemLike slab, ItemLike input) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slab, 6).define('#', input).pattern("###").group("wooden_slab")
                .unlockedBy("has_journal", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsRegistry.WORN_NOTEBOOK))
                .save(recipeConsumer);
    }

    public static ShapedRecipeBuilder makeWood(ItemLike logs, ItemLike wood, int count) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, wood, count).unlockedBy("has_journal", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsRegistry.WORN_NOTEBOOK))
                .pattern("xx ")
                .pattern("xx ").define('x', logs);
    }

    private static void strippedLogToWood(RecipeOutput recipeConsumer, ItemLike stripped, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 3).define('#', stripped).pattern("##").pattern("##").group("bark")
                .unlockedBy("has_journal", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsRegistry.WORN_NOTEBOOK))
                .save(recipeConsumer);
    }
}
