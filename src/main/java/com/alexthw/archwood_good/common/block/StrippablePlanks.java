package com.alexthw.archwood_good.common.block;

import com.hollingsworth.arsnouveau.setup.registry.BlockRegistryWrapper;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class StrippablePlanks extends Block {
    Supplier<Block> strippedState;

    public StrippablePlanks(Properties properties, Supplier<Block> stateSupplier) {
        super(properties);
        this.strippedState = stateSupplier;
    }

    public StrippablePlanks(Properties properties, BlockRegistryWrapper<? extends Block> ro) {
        super(properties);
        this.strippedState = ro::get;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(@NotNull BlockState state, @NotNull UseOnContext context, @NotNull ItemAbility itemAbility, boolean simulate) {
        return itemAbility == ItemAbilities.AXE_STRIP ? strippedState.get().defaultBlockState() : null;
    }
}
