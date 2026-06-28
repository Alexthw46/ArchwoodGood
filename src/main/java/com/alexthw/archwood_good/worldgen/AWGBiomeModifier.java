package com.alexthw.archwood_good.worldgen;

import alexthw.ars_elemental.datagen.AETagsProvider;
import com.alexthw.archwood_good.ArchwoodGood;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class AWGBiomeModifier {

    // ArchWood Good
    public static final ResourceKey<BiomeModifier> COMMON_ORANGE_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, AWGConfiguredFeatures.COMMON_ORANGE_TREE_KEY.location());

    // Ars Nouveau


    public static ResourceKey<Biome> registerForAWG(String path) {
        return ResourceKey.create(Registries.BIOME, ArchwoodGood.res(path));
    }

    public static void bootstrap(BootstrapContext<BiomeModifier> contextBM) {

        HolderSet<Biome> COMMON_ORANGE_ARCHWOOD_TAG = contextBM.lookup(Registries.BIOME).getOrThrow(AETagsProvider.AEBiomeTagsProvider.FLASHING_TREE_COMMON_BIOME);

        try {
            Holder.Reference<PlacedFeature> TREESET_CMN = contextBM.lookup(Registries.PLACED_FEATURE).get(AWGPlacedFeatures.COMMON_ORANGE_PLACED_KEY).orElseThrow();
            contextBM.register(COMMON_ORANGE_MODIFIER, new BiomeModifiers.AddFeaturesBiomeModifier(COMMON_ORANGE_ARCHWOOD_TAG, HolderSet.direct(TREESET_CMN), GenerationStep.Decoration.VEGETAL_DECORATION));
        } catch (Exception ignored) {
        }
    }

}
