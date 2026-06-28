package com.alexthw.archwood_good.worldgen;

import alexthw.ars_elemental.common.entity.mages.EntityMageBase;
import com.alexthw.archwood_good.ArchwoodGood;
import com.hollingsworth.arsnouveau.common.entity.WealdWalker;
import com.hollingsworth.arsnouveau.setup.registry.BiomeRegistry;
import com.hollingsworth.arsnouveau.setup.registry.ModEntities;
import com.hollingsworth.arsnouveau.setup.registry.SoundRegistry;
import com.hollingsworth.arsnouveau.setup.registry.WorldgenRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static alexthw.ars_elemental.registry.ModEntities.*;
import static alexthw.ars_elemental.world.ModWorldgen.*;
import static com.hollingsworth.arsnouveau.setup.registry.BiomeRegistry.globalOverworldGeneration;
import static com.hollingsworth.arsnouveau.setup.registry.BiomeRegistry.softDisks;
import static net.minecraft.data.worldgen.placement.CavePlacements.CAVE_VINES;

public class AWGBiomes {

    // Archwood Good
    public static final ResourceKey<Biome> ORANGE_FOREST_KEY = registerForAWG("orange_forest");
    public static final ResourceKey<Biome> WHITE_FOREST_KEY = registerForAWG("white_forest");
    public static final ResourceKey<Biome> FADING_FOREST_KEY = registerForAWG("fading_forest");

    // Ars Nouveau
    public static final ResourceKey<Biome> BLAZING_FOREST_KEY = registerForAWG("blazing_forest");
    public static final ResourceKey<Biome> CASCADING_FOREST_KEY = registerForAWG("cascading_forest");
    public static final ResourceKey<Biome> FLOURISHING_FOREST_KEY = registerForAWG("flourishing_forest");
    public static final ResourceKey<Biome> VEXING_CAVES_KEY = registerForAWG("vexing_caves");

    // Ars Elemental
    public static final ResourceKey<Biome> FLASHING_FOREST_KEY = registerForAWG("flashing_forest");

    public static final ResourceLocation[] ArchwoodBiomes = new ResourceLocation[]{
            ORANGE_FOREST_KEY.location(),

            BLAZING_FOREST_KEY.location(),
            CASCADING_FOREST_KEY.location(),
            FLOURISHING_FOREST_KEY.location(),
            VEXING_CAVES_KEY.location(),

            FLASHING_FOREST_KEY.location()
    };

    public static ResourceKey<Biome> registerForAWG(String path) {
        return ResourceKey.create(Registries.BIOME, ArchwoodGood.res(path));
    }

    public static void registerBiomes(BootstrapContext<Biome> context) {

        BiomeRegistry.bootstrap(context);

        // Archwood Good
        context.register(ORANGE_FOREST_KEY, orangeArchwoodForest(context));

        // Ars Nouveau
        context.register(BLAZING_FOREST_KEY, blazingArchwoodForest(context));
        context.register(CASCADING_FOREST_KEY, cascadingArchwoodForest(context));
        context.register(FLOURISHING_FOREST_KEY, flourishArchwoodForest(context));
        context.register(VEXING_CAVES_KEY, vexingCaves(context));

        // Ars Elemental
        context.register(FLASHING_FOREST_KEY, flashingArchwoodForest(context));

    }

    private static Biome orangeArchwoodForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = archwoodSpawns(FIRE_MAGE.get(), ModEntities.ENTITY_BLAZING_WEALD.get(), EntityType.HUSK);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.ARMADILLO, 6, 1, 2));

        BiomeGenerationSettings.Builder biomeBuilder = getArchwoodBiomeBuilder(CLUSTER_BLAZING_CONFIGURED, context, BLACKSTONE_ROCK_PLACED, VegetationPlacements.TREES_WINDSWEPT_SAVANNA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, SCATTERED_TORCHFLOWERS);
        biomeBuilder.addFeature(GenerationStep.Decoration.LAKES, LAVA_POOLS);

        return new Biome.BiomeBuilder().hasPrecipitation(false)
                .downfall(0.1f)
                .temperature(0.9f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(7978751)
                        .waterFogColor(329011)
                        .skyColor(7978751)
                        .grassColorOverride(13269556)
                        .foliageColorOverride(12679744)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundRegistry.ARIA_BIBLIO)).build())
                .build();
    }

    private static Biome blazingArchwoodForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = archwoodSpawns(FIRE_MAGE.get(), ModEntities.ENTITY_BLAZING_WEALD.get(), EntityType.HUSK);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.ARMADILLO, 6, 1, 2));

        BiomeGenerationSettings.Builder biomeBuilder = getArchwoodBiomeBuilder(CLUSTER_BLAZING_CONFIGURED, context, BLACKSTONE_ROCK_PLACED, VegetationPlacements.TREES_WINDSWEPT_SAVANNA);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, SCATTERED_TORCHFLOWERS);
        biomeBuilder.addFeature(GenerationStep.Decoration.LAKES, LAVA_POOLS);

        return new Biome.BiomeBuilder().hasPrecipitation(false)
                .downfall(0.1f)
                .temperature(0.9f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(7978751)
                        .waterFogColor(329011)
                        .skyColor(7978751)
                        .grassColorOverride(13269556)
                        .foliageColorOverride(12679744)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundRegistry.ARIA_BIBLIO)).build())
                .build();

    }

    private static Biome cascadingArchwoodForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = archwoodSpawns(WATER_MAGE.get(), ModEntities.ENTITY_CASCADING_WEALD.get(), EntityType.DROWNED);
        // add fish
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.COD, 15, 3, 6));
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.SALMON, 15, 1, 5));
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.TROPICAL_FISH, 25, 8, 8));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 6, 1, 3));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        softDisks(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LESS_MANGROVE_PLACED);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, POOLS_WITH_DRIP_PLACED);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.WARM_OCEAN_VEGETATION)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEA_PICKLE);
        addDefaultExtraVegetation(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, WorldgenRegistry.PLACED_LIGHTS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CLUSTER_CASCADING_CONFIGURED);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WorldgenRegistry.PLACED_MOJANK_GRASS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WorldgenRegistry.PLACED_MOJANK_FLOWERS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PLACED_MOJANK_FLOREST_FLOWERS);

        return new Biome.BiomeBuilder().hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(7978751)
                        .waterFogColor(329011)
                        .skyColor(7978751)
                        .grassColorOverride(1149867)
                        .foliageColorOverride(2210437)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundRegistry.ARIA_BIBLIO)).build())
                .build();
    }

    private static Biome flourishArchwoodForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = archwoodSpawns(EARTH_MAGE.get(), ModEntities.ENTITY_FLOURISHING_WEALD.get(), EntityType.BOGGED);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PANDA, 6, 1, 3));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addLightBambooVegetation(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CLUSTER_FLOURISHING_CONFIGURED);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, SPARSE_JUNGLE);
        BiomeDefaultFeatures.addWarmFlowers(biomeBuilder);
        BiomeDefaultFeatures.addJungleGrass(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.LUSH_CAVES_CEILING_VEGETATION);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CAVE_VINES);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.ROOTED_AZALEA_TREE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.SPORE_BLOSSOM);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.CLASSIC_VINES);
        addDefaultExtraVegetation(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, WorldgenRegistry.PLACED_LIGHTS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WorldgenRegistry.PLACED_MOJANK_GRASS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WorldgenRegistry.PLACED_MOJANK_FLOWERS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PLACED_MOJANK_FLOREST_FLOWERS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, SCATTERED_BLOSSOMS);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.6f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(7978751)
                        .waterFogColor(329011)
                        .skyColor(7978751)
                        .grassColorOverride(1346066)
                        .foliageColorOverride(30464)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundRegistry.ARIA_BIBLIO)).build())
                .build();
    }

    public static Biome flashingArchwoodForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = archwoodSpawns(AIR_MAGE.get(), FLASHING_WEALD_WALKER.get(), null);
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.BREEZE, 5, 1, 1));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 6, 1, 2));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(FLASHJACK_ENTITY.get(), 35, 1, 2));
        BiomeGenerationSettings.Builder biomeBuilder = getArchwoodBiomeBuilder(CLUSTER_FLASHING_CONFIGURED, context, QUARTZ_ROCK_PLACED, ALT_WINDSWEPT);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, SCATTERED_SPARKFLOWERS);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.4f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(7978751)
                        .waterFogColor(329011)
                        .skyColor(7978751)
                        .grassColorOverride(13414701)
                        .foliageColorOverride(13084948)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundRegistry.ARIA_BIBLIO)).build())
                .build();
    }

    private static MobSpawnSettings.Builder archwoodSpawns(EntityType<EntityMageBase> mage, EntityType<WealdWalker> ww, EntityType<?> biomeMob) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.STARBUNCLE_TYPE.get(), 2, 3, 5));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.ENTITY_DRYGMY.get(), 2, 1, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.WHIRLISPRIG_TYPE.get(), 2, 1, 3));
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 6, 2, 3));

        BiomeDefaultFeatures.caveSpawns(spawnBuilder);
        // unwrap the monster method so we can override the biome-specific dominant mob
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 60, 4, 4));
        if (biomeMob != null)
            spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(biomeMob, 60, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 20, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 60, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 60, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 40, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 5, 1, 1));

        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(mage, 4, 1, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ww, 10, 1, 3));
        return spawnBuilder;
    }

    private static BiomeGenerationSettings.Builder getArchwoodBiomeBuilder(ResourceKey<PlacedFeature> archwoodCluster, BootstrapContext<Biome> context, ResourceKey<PlacedFeature> rock, ResourceKey<PlacedFeature> vanillatree) {
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);

        if (rock != null)
            biomeBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, rock);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        softDisks(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, vanillatree);
        addDefaultExtraVegetation(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, WorldgenRegistry.PLACED_LIGHTS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, archwoodCluster);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WorldgenRegistry.PLACED_MOJANK_GRASS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WorldgenRegistry.PLACED_MOJANK_FLOWERS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PLACED_MOJANK_FLOREST_FLOWERS);
        return biomeBuilder;
    }

    static void addDefaultExtraVegetation(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PLACED_MOJANK_PUMPKINS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PLACED_MOJANK_SUGAR_CANE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PLACED_MOJANK_BROWN_MUSHROOM);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PLACED_MOJANK_RED_MUSHROOM);
    }

    public static Biome vexingCaves(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 50, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 55, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 50, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 50, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 30, 1, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 15, 1, 1));

        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.STARBUNCLE_TYPE.get(), 2, 3, 5));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.ENTITY_DRYGMY.get(), 2, 1, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.WHIRLISPRIG_TYPE.get(), 2, 1, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.ENTITY_VEXING_WEALD.get(), 10, 1, 3));

        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.WILDEN_HUNTER.get(), 100, 3, 5));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.WILDEN_STALKER.get(), 100, 1, 3));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.WILDEN_GUARDIAN.get(), 80, 2, 5));


        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(AIR_MAGE.get(), 4, 1, 3));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(FIRE_MAGE.get(), 4, 1, 3));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(WATER_MAGE.get(), 4, 1, 3));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EARTH_MAGE.get(), 4, 1, 3));

        BiomeGenerationSettings.Builder biomeBuilder = getArchwoodBiomeBuilder(CLUSTER_VEXING_CONFIGURED, context, SOURCESTONE_FORMATION_PLACED, CavePlacements.ROOTED_AZALEA_TREE);
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, CAVE_CARVER);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ORE_SOURCESTONE_PLACED);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CEILING_BERRY_CAVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PLACED_BERRY_BUSH_CAVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PLACED_LIGHTS_UNDERGROUND);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VEXING_CONFIGURED_CAVE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VEXING_CONFIGURED_SHORT);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, SOURCESTONE_FORMATION_PLACED);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.5f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(12080623)
                        .waterFogColor(11832560)
                        .skyColor(10979583)
                        .grassColorOverride(6566546)
                        .foliageColorOverride(8535186)
                        .fogColor(14386175)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundRegistry.WILD_HUNT)).build())
                .build();
    }


}
