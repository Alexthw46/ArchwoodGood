package com.alexthw.archwood_good.datagen;

import alexthw.ars_elemental.registry.ModItems;
import com.alexthw.archwood_good.Registry;
import com.alexthw.archwood_good.integration.ElementalModule;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static com.hollingsworth.arsnouveau.common.datagen.RecipeDatagen.shapedWoodenStairs;

public class RecipeDataGen extends RecipeProvider {

    public RecipeDataGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
//                shapelessBuilder(BlockRegistry.ARCHWOOD_PLANK.get()).requires(Ingredient.of(archwoodPlanks)).save(recipeOutput); //TODO: need an item to bleach colored planks into plain planks
        shapelessBuilder(BlockRegistry.ARCHWOOD_PLANK.get(), 4).requires(Registry.ARCHWOOD_LOG.get()).save(recipeOutput);

        shapelessBuilder(Registry.BLUE_ARCHWOOD_PLANK, 4).requires(BlockRegistry.CASCADING_LOG.get()).save(recipeOutput);
        shapelessBuilder(Registry.RED_ARCHWOOD_PLANK, 4).requires(BlockRegistry.BLAZING_LOG.get()).save(recipeOutput);
        shapelessBuilder(Registry.GREEN_ARCHWOOD_PLANK, 4).requires(BlockRegistry.FLOURISHING_LOG.get()).save(recipeOutput);
        shapelessBuilder(Registry.PURPLE_ARCHWOOD_PLANK, 4).requires(BlockRegistry.VEXING_LOG.get()).save(recipeOutput);
        shapelessBuilder(ElementalModule.YELLOW_ARCHWOOD_PLANK.get(), 4).requires(ModItems.FLASHING_ARCHWOOD_LOG.get()).save(recipeOutput);

        shapedWoodenStairs(recipeOutput, Registry.BLUE_ARCHWOOD_STAIRS, Registry.BLUE_ARCHWOOD_PLANK);
        shapedWoodenStairs(recipeOutput, Registry.RED_ARCHWOOD_STAIRS, Registry.RED_ARCHWOOD_PLANK);
        shapedWoodenStairs(recipeOutput, Registry.GREEN_ARCHWOOD_STAIRS, Registry.GREEN_ARCHWOOD_PLANK);
        shapedWoodenStairs(recipeOutput, Registry.PURPLE_ARCHWOOD_STAIRS, Registry.PURPLE_ARCHWOOD_PLANK);
        shapedWoodenStairs(recipeOutput, ElementalModule.YELLOW_ARCHWOOD_STAIRS.get(), ElementalModule.YELLOW_ARCHWOOD_PLANK.get());

        shapedWoodenSlab(recipeOutput, Registry.BLUE_ARCHWOOD_SLAB, Registry.BLUE_ARCHWOOD_PLANK);
        shapedWoodenSlab(recipeOutput, Registry.RED_ARCHWOOD_SLAB, Registry.RED_ARCHWOOD_PLANK);
        shapedWoodenSlab(recipeOutput, Registry.GREEN_ARCHWOOD_SLAB, Registry.GREEN_ARCHWOOD_PLANK);
        shapedWoodenSlab(recipeOutput, Registry.PURPLE_ARCHWOOD_SLAB, Registry.PURPLE_ARCHWOOD_PLANK);
        shapedWoodenSlab(recipeOutput, ElementalModule.YELLOW_ARCHWOOD_SLAB.get(), ElementalModule.YELLOW_ARCHWOOD_PLANK.get());

    }

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
}
