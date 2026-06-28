package com.alexthw.archwood_good.worldgen.provider;

import com.alexthw.archwood_good.ArchwoodGood;
import com.alexthw.archwood_good.registry.AWGBlockRegistry;
import com.hollingsworth.arsnouveau.common.world.tree.AbstractSupplierBlockStateProvider;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import org.jetbrains.annotations.NotNull;

public class AWGSupplierBlockStateProvider extends AbstractSupplierBlockStateProvider {
    public AWGSupplierBlockStateProvider(String path) {
        this(ArchwoodGood.res(path));
    }

    public AWGSupplierBlockStateProvider(ResourceLocation path) {
        super(path);
    }

    public static final MapCodec<AWGSupplierBlockStateProvider> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.STRING.fieldOf("key").forGetter(d -> d.key.getPath()))
            .apply(instance, AWGSupplierBlockStateProvider::new));

    @Override
    protected @NotNull BlockStateProviderType<?> type() {
        return AWGBlockRegistry.stateProviderType.value();
    }

}
