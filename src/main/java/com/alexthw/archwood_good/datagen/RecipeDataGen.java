package com.alexthw.archwood_good.datagen;

import alexthw.ars_elemental.ArsElemental;
import com.alexthw.archwood_good.ArchwoodGood;
import com.alexthw.archwood_good.registry.AWGBlockRegistry;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static com.alexthw.archwood_good.datagen.AWGItemTagsProvider.tagMap;
import static com.hollingsworth.arsnouveau.common.datagen.RecipeDatagen.shapedWoodenStairs;

public class RecipeDataGen extends RecipeProvider {

    public static Ingredient ARCHWOOD_LOGS = Ingredient.of(AWGItemTagsProvider.FADING_ARCHWOOD_LOG_TAG);

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
                Block sign = woodType.getBlockOfThis(VanillaWoodChildKeys.SIGN);
                Block hangingSign = woodType.getBlockOfThis(VanillaWoodChildKeys.HANGING_SIGN);

                Ingredient logTag = Ingredient.of(ItemTags.create(Utils.getID(log).withSuffix("s")));
                shapelessBuilder(planks, 4).requires(logTag).save(recipeOutput);

                if (wood != null) {
                    makeWood(log, wood, 3).save(recipeOutput);
                }
                if (stripped_wood != null) strippedLogToWood(recipeOutput, stripped_log, stripped_wood);

                if (stairs != null) shapedWoodenStairs(recipeOutput, stairs, planks);
                if (slab != null) shapedWoodenSlab(recipeOutput, slab, planks);

                if (sign != null && planks != null) createSignRecipe(sign, planks, recipeOutput);
                if (hangingSign != null && stripped_log != null) hangingSign(recipeOutput, hangingSign.asItem(), stripped_log.asItem());

            }

        }

        shapelessBuilder(BlockRegistry.ARCHWOOD_PLANK.get(), 4).requires(ARCHWOOD_LOGS).save(recipeOutput);

        makeWood(AWGBlockRegistry.FADING_ARCHWOOD_LOG, AWGBlockRegistry.FADING_ARCHWOOD_WOOD.get(), 3).save(recipeOutput);
        strippedLogToWood(recipeOutput, AWGBlockRegistry.STRIPPED_FADING_ARCHWOOD_LOG, AWGBlockRegistry.STRIPPED_FADING_ARCHWOOD_WOOD.get());

        hangingSign(recipeOutput, ItemsRegistry.ARCHWOOD_HANGING_SIGN, AWGBlockRegistry.STRIPPED_FADING_ARCHWOOD_LOG.asItem());

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

    private static void strippedLogToWood(RecipeOutput recipeOutput, ItemLike stripped, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 3).define('#', stripped).pattern("##").pattern("##").group("bark")
                .unlockedBy("has_journal", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsRegistry.WORN_NOTEBOOK))
                .save(recipeOutput);
    }

    public static void createSignRecipe(Block sign, Block planks, RecipeOutput recipeOutput) {
        signBuilder(sign.asItem(), Ingredient.of(planks.asItem())).unlockedBy("has_journal", InventoryChangeTrigger.TriggerInstance.hasItems(planks.asItem())).save(recipeOutput);
    }

    public static void createHangingSignRecipe(Block hangingSign, String woodTypeId, RecipeOutput recipeOutput) {
        TagKey<Item> tagKey = tagMap.get(woodTypeId);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, hangingSign.asItem(), 6)
        .group("hanging_sign")
        .define('#', Ingredient.of(tagKey))
        .define('X', Items.CHAIN)
        .pattern("X X")
        .pattern("###")
        .pattern("###")
        .unlockedBy("has_journal", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsRegistry.WORN_NOTEBOOK))
        .save(recipeOutput);
    }
}
