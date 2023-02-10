package net.frozenblock.liukrast.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class WorldSurfaceGenerator extends Feature<DefaultFeatureConfig> {
    public WorldSurfaceGenerator(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        int CX = (int) WorldgenHeightCalculator.getChunk(context.getOrigin().getX(), 16);
        int CZ = (int) WorldgenHeightCalculator.getChunk(context.getOrigin().getZ(), 16);

        for(int x = 0; x <= 15; x++) {
            for(int z = 0; z <= 15; z++) {
                int ox = CX + x;
                int oz = CZ + z;

                BiomeGenerator.generateBiome(world,ox,oz,16,20);
            }
        }
        return true;
    }

    public static void decorate(BlockPos pos, StructureWorldAccess world) {
        double seed = AdvancedMath.seed3d(pos.getX(), pos.getY(), pos.getZ(), 20);
        double seed2 = AdvancedMath.seed3d(pos.getX(), pos.getY(), pos.getZ(), 20 + 1209);
        if(seed2 > 0.1) {
            world.setBlockState(pos, Blocks.GRASS.getDefaultState(), 3);
        }
        /*if(seed > 0.995) {
            for(int i = 0; i < AdvancedMath.range(3, 7, seed); i++) {
                world.setBlockState(pos.up(i), Blocks.OAK_LOG.getDefaultState(), 3);
            }
        }*/
    }
}
