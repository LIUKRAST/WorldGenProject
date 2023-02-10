package net.frozenblock.liukrast.worldgen.biome;

import net.frozenblock.liukrast.worldgen.feature.WGBiome;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

public class SnowyPlains extends WGBiome {
    @Override
    public void generate(StructureWorldAccess world, BlockPos pos) {
        for(int i = 0; i < 10; i++) {
            world.setBlockState(pos.down(i), Blocks.SNOW_BLOCK.getDefaultState(), 3);
        }
        world.setBlockState(pos, Blocks.SNOW_BLOCK.getDefaultState(), 3);
    }
}
