package net.frozenblock.liukrast.worldgen;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.frozenblock.liukrast.worldgen.biome.MesaBiome;
import net.frozenblock.liukrast.worldgen.biome.SnowyPlains;
import net.frozenblock.liukrast.worldgen.feature.BiomeGenerator;
import net.frozenblock.liukrast.worldgen.feature.WorldSurfaceGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.List;

public class Common implements ModInitializer {
    public static final String MOD_ID = "frozenblock";
    public static final WorldSurfaceGenerator WSG = new WorldSurfaceGenerator(DefaultFeatureConfig.CODEC);
    public static final ConfiguredFeature<DefaultFeatureConfig, WorldSurfaceGenerator> WSG_CF = new ConfiguredFeature<>(WSG, new DefaultFeatureConfig());
    public static final PlacedFeature WSG_PLACED = new PlacedFeature(RegistryEntry.of(WSG_CF), List.of(SquarePlacementModifier.of()));
    @Override
    public void onInitialize() {
        Identifier worldgenid = new Identifier(MOD_ID, "wsg");
        Registry.register(Registry.FEATURE, worldgenid, WSG);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, worldgenid, WSG_CF);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, worldgenid, WSG_PLACED);

        BiomeGenerator.register(new MesaBiome());
        BiomeGenerator.register(new SnowyPlains());

        BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.TOP_LAYER_MODIFICATION, RegistryKey.of(Registry.PLACED_FEATURE_KEY, worldgenid));
    }
}
