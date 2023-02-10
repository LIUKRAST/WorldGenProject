package net.frozenblock.liukrast.worldgen.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

public abstract class WGBiome {
    public abstract void generate(StructureWorldAccess world, BlockPos pos);
}
