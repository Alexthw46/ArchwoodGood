package com.alexthw.archwood_good;

import com.hollingsworth.arsnouveau.setup.registry.ItemRegistryWrapper;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;

import java.util.function.Supplier;

import static com.alexthw.archwood_good.ContentRegistry.ITEMS;
import static com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry.defaultItemProperties;
import static com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry.register;

public class ItemRegistry {

    // Archwood Good
    public static final ItemRegistryWrapper<SignItem> ORANGE_ARCHWOOD_SIGN = registerForAWG("orange_archwood_sign",
            () -> new SignItem(defaultItemProperties().stacksTo(16), ContentRegistry.ORANGE_ARCHWOOD_SIGN.get(), ContentRegistry.ORANGE_ARCHWOOD_WALL_SIGN.get()));
    public static final ItemRegistryWrapper<HangingSignItem> ORANGE_ARCHWOOD_HANGING_SIGN = registerForAWG("orange_archwood_hanging_sign",
            () -> new HangingSignItem(ContentRegistry.ORANGE_ARCHWOOD_HANGING_SIGN.get(), ContentRegistry.ORANGE_ARCHWOOD_HANGING_WALL_SIGN.get(), defaultItemProperties().stacksTo(16)));

    public static final ItemRegistryWrapper<SignItem> WHITE_ARCHWOOD_SIGN = registerForAWG("white_archwood_sign",
            () -> new SignItem(defaultItemProperties().stacksTo(16), ContentRegistry.WHITE_ARCHWOOD_SIGN.get(), ContentRegistry.WHITE_ARCHWOOD_WALL_SIGN.get()));
    public static final ItemRegistryWrapper<HangingSignItem> WHITE_ARCHWOOD_HANGING_SIGN = registerForAWG("white_archwood_hanging_sign",
            () -> new HangingSignItem(ContentRegistry.WHITE_ARCHWOOD_HANGING_SIGN.get(), ContentRegistry.WHITE_ARCHWOOD_HANGING_WALL_SIGN.get(), defaultItemProperties().stacksTo(16)));


    // Ars Nouveau
    public static final ItemRegistryWrapper<SignItem> BLUE_ARCHWOOD_SIGN = register("blue_archwood_sign",
            () -> new SignItem(defaultItemProperties().stacksTo(16), ContentRegistry.BLUE_ARCHWOOD_SIGN.get(), ContentRegistry.BLUE_ARCHWOOD_WALL_SIGN.get()));
    public static final ItemRegistryWrapper<HangingSignItem> BLUE_ARCHWOOD_HANGING_SIGN = register("blue_archwood_hanging_sign",
            () -> new HangingSignItem(ContentRegistry.BLUE_ARCHWOOD_HANGING_SIGN.get(), ContentRegistry.BLUE_ARCHWOOD_HANGING_WALL_SIGN.get(), defaultItemProperties().stacksTo(16)));

    public static final ItemRegistryWrapper<SignItem> GREEN_ARCHWOOD_SIGN = register("green_archwood_sign",
            () -> new SignItem(defaultItemProperties().stacksTo(16), ContentRegistry.GREEN_ARCHWOOD_SIGN.get(), ContentRegistry.GREEN_ARCHWOOD_WALL_SIGN.get()));
    public static final ItemRegistryWrapper<HangingSignItem> GREEN_ARCHWOOD_HANGING_SIGN = register("green_archwood_hanging_sign",
            () -> new HangingSignItem(ContentRegistry.GREEN_ARCHWOOD_HANGING_SIGN.get(), ContentRegistry.GREEN_ARCHWOOD_HANGING_WALL_SIGN.get(), defaultItemProperties().stacksTo(16)));

    public static final ItemRegistryWrapper<SignItem> PURPLE_ARCHWOOD_SIGN = register("purple_archwood_sign",
            () -> new SignItem(defaultItemProperties().stacksTo(16), ContentRegistry.PURPLE_ARCHWOOD_SIGN.get(), ContentRegistry.PURPLE_ARCHWOOD_WALL_SIGN.get()));
    public static final ItemRegistryWrapper<HangingSignItem> PURPLE_ARCHWOOD_HANGING_SIGN = register("purple_archwood_hanging_sign",
            () -> new HangingSignItem(ContentRegistry.PURPLE_ARCHWOOD_HANGING_SIGN.get(), ContentRegistry.PURPLE_ARCHWOOD_HANGING_WALL_SIGN.get(), defaultItemProperties().stacksTo(16)));

    public static final ItemRegistryWrapper<SignItem> RED_ARCHWOOD_SIGN = register("red_archwood_sign",
            () -> new SignItem(defaultItemProperties().stacksTo(16), ContentRegistry.RED_ARCHWOOD_SIGN.get(), ContentRegistry.RED_ARCHWOOD_WALL_SIGN.get()));
    public static final ItemRegistryWrapper<HangingSignItem> RED_ARCHWOOD_HANGING_SIGN = register("red_archwood_hanging_sign",
            () -> new HangingSignItem(ContentRegistry.RED_ARCHWOOD_HANGING_SIGN.get(), ContentRegistry.RED_ARCHWOOD_HANGING_WALL_SIGN.get(), defaultItemProperties().stacksTo(16)));

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                         METHODS                          │
    //      └──────────────────────────────────────────────────────────┘
    public static void init() {}

    public static <T extends Item> ItemRegistryWrapper<T> registerForAWG(String name, Supplier<T> item) {
        return new ItemRegistryWrapper<>(ITEMS.register(name, item));
    }
}
