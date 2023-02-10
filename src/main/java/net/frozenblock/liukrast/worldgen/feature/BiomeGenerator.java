package net.frozenblock.liukrast.worldgen.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BiomeGenerator {
    public static ArrayList<WGBiome> BIOMES = new ArrayList<>();

    public static void register(WGBiome biome) {
        BIOMES.add(biome);
    }

    public static void generateBiome(StructureWorldAccess world, double x, double z, double c, double s) {
        double y = WorldgenHeightCalculator.height(x,z,c,s + 20);
        int min = 20;
        int max = 200;
        double rY = AdvancedMath.range(min, max, y);
        BlockPos pos = new BlockPos(x,rY, z);

        double rh = WorldgenHeightCalculator.height(x,z,c,s);
        WGBiome biome = BIOMES.get((int) Math.round((rh * (BIOMES.size()-1))));
        biome.generate(world, pos);

    }
}
