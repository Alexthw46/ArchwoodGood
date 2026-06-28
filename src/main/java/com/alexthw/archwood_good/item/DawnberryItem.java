package com.alexthw.archwood_good.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class DawnberryItem extends ItemNameBlockItem {

    public static final FoodProperties DAWNBERRY_FOOD = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.6F)
            .alwaysEdible()
            .build();

    public DawnberryItem(Block block) {
        super(block, new Item.Properties().food(DAWNBERRY_FOOD));
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity) {

        if (livingEntity instanceof Player) {
            livingEntity.igniteForSeconds(5F);  // Sets the player on fire for 5 seconds (100 fire ticks)
            livingEntity.getActiveEffects().removeIf(effect -> !effect.getEffect().isBound());
        }

        return super.finishUsingItem(stack, level, livingEntity);
    }
}
