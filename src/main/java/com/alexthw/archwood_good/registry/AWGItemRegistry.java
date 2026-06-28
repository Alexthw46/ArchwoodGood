package com.alexthw.archwood_good.registry;

import com.alexthw.archwood_good.item.DawnberryItem;
import com.alexthw.archwood_good.lib.ContentName;
import com.hollingsworth.arsnouveau.setup.registry.ItemRegistryWrapper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.SignItem;

import static com.alexthw.archwood_good.registry.RegistryHelper.registerItemForAWG;
import static com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry.defaultItemProperties;
import static com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry.register;

public class AWGItemRegistry {

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                       Archwood Good                      │
    //      └──────────────────────────────────────────────────────────┘
    public static final ItemRegistryWrapper<SignItem> ORANGE_ARCHWOOD_SIGN = registerItemForAWG(ContentName.ORANGE_ARCHWOOD_SIGN,
            () -> new SignItem(defaultItemProperties().stacksTo(16), AWGBlockRegistry.ORANGE_ARCHWOOD_SIGN.get(), AWGBlockRegistry.ORANGE_ARCHWOOD_WALL_SIGN.get()));
    public static final ItemRegistryWrapper<HangingSignItem> ORANGE_ARCHWOOD_HANGING_SIGN = registerItemForAWG(ContentName.ORANGE_ARCHWOOD_HANGING_SIGN,
            () -> new HangingSignItem(AWGBlockRegistry.ORANGE_ARCHWOOD_HANGING_SIGN.get(), AWGBlockRegistry.ORANGE_ARCHWOOD_HANGING_WALL_SIGN.get(), defaultItemProperties().stacksTo(16)));

    public static final ItemRegistryWrapper<SignItem> WHITE_ARCHWOOD_SIGN = registerItemForAWG(ContentName.WHITE_ARCHWOOD_SIGN,
            () -> new SignItem(defaultItemProperties().stacksTo(16), AWGBlockRegistry.WHITE_ARCHWOOD_SIGN.get(), AWGBlockRegistry.WHITE_ARCHWOOD_WALL_SIGN.get()));
    public static final ItemRegistryWrapper<HangingSignItem> WHITE_ARCHWOOD_HANGING_SIGN = registerItemForAWG(ContentName.WHITE_ARCHWOOD_HANGING_SIGN,
            () -> new HangingSignItem(AWGBlockRegistry.WHITE_ARCHWOOD_HANGING_SIGN.get(), AWGBlockRegistry.WHITE_ARCHWOOD_HANGING_WALL_SIGN.get(), defaultItemProperties().stacksTo(16)));

    // PODS & FOODS
    public static final ItemRegistryWrapper<Item> DAWNBERRY_POD = registerItemForAWG("dawnberry_pod", () -> new DawnberryItem(AWGBlockRegistry.DAWNBERRY_POD.get()));

    public static FoodProperties LIGHTCHEE_FOOD = new FoodProperties.Builder().nutrition(4).saturationModifier(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 5 * 20), 1.0f).alwaysEdible().build();

    public static final ItemRegistryWrapper<Item> LIGHTCHEE_POD = registerItemForAWG("lightchee_pod", () -> new ItemNameBlockItem(AWGBlockRegistry.LIGHTCHEE_POD.get(),
            new Item.Properties().food(LIGHTCHEE_FOOD)));

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                       Ars Nouveau                        │
    //      └──────────────────────────────────────────────────────────┘
    public static final ItemRegistryWrapper<SignItem> BLUE_ARCHWOOD_SIGN = register("blue_archwood_sign",
            () -> new SignItem(defaultItemProperties().stacksTo(16), AWGBlockRegistry.BLUE_ARCHWOOD_SIGN.get(), AWGBlockRegistry.BLUE_ARCHWOOD_WALL_SIGN.get()));
    public static final ItemRegistryWrapper<HangingSignItem> BLUE_ARCHWOOD_HANGING_SIGN = register("blue_archwood_hanging_sign",
            () -> new HangingSignItem(AWGBlockRegistry.BLUE_ARCHWOOD_HANGING_SIGN.get(), AWGBlockRegistry.BLUE_ARCHWOOD_HANGING_WALL_SIGN.get(), defaultItemProperties().stacksTo(16)));

    public static final ItemRegistryWrapper<SignItem> GREEN_ARCHWOOD_SIGN = register("green_archwood_sign",
            () -> new SignItem(defaultItemProperties().stacksTo(16), AWGBlockRegistry.GREEN_ARCHWOOD_SIGN.get(), AWGBlockRegistry.GREEN_ARCHWOOD_WALL_SIGN.get()));
    public static final ItemRegistryWrapper<HangingSignItem> GREEN_ARCHWOOD_HANGING_SIGN = register("green_archwood_hanging_sign",
            () -> new HangingSignItem(AWGBlockRegistry.GREEN_ARCHWOOD_HANGING_SIGN.get(), AWGBlockRegistry.GREEN_ARCHWOOD_HANGING_WALL_SIGN.get(), defaultItemProperties().stacksTo(16)));

    public static final ItemRegistryWrapper<SignItem> PURPLE_ARCHWOOD_SIGN = register("purple_archwood_sign",
            () -> new SignItem(defaultItemProperties().stacksTo(16), AWGBlockRegistry.PURPLE_ARCHWOOD_SIGN.get(), AWGBlockRegistry.PURPLE_ARCHWOOD_WALL_SIGN.get()));
    public static final ItemRegistryWrapper<HangingSignItem> PURPLE_ARCHWOOD_HANGING_SIGN = register("purple_archwood_hanging_sign",
            () -> new HangingSignItem(AWGBlockRegistry.PURPLE_ARCHWOOD_HANGING_SIGN.get(), AWGBlockRegistry.PURPLE_ARCHWOOD_HANGING_WALL_SIGN.get(), defaultItemProperties().stacksTo(16)));

    public static final ItemRegistryWrapper<SignItem> RED_ARCHWOOD_SIGN = register("red_archwood_sign",
            () -> new SignItem(defaultItemProperties().stacksTo(16), AWGBlockRegistry.RED_ARCHWOOD_SIGN.get(), AWGBlockRegistry.RED_ARCHWOOD_WALL_SIGN.get()));
    public static final ItemRegistryWrapper<HangingSignItem> RED_ARCHWOOD_HANGING_SIGN = register("red_archwood_hanging_sign",
            () -> new HangingSignItem(AWGBlockRegistry.RED_ARCHWOOD_HANGING_SIGN.get(), AWGBlockRegistry.RED_ARCHWOOD_HANGING_WALL_SIGN.get(), defaultItemProperties().stacksTo(16)));

//      ┌──────────────────────────────────────────────────────────┐
//      │                       INITIZATION                        │
//      └──────────────────────────────────────────────────────────┘
    public static void init() {}

}