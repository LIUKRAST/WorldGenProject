package net.frozenblock.liukrast.worldgen.feature;

public class AdvancedMath {
    public static double range(float min, float max, double number) {
        return (number * (max - min)) + min;
    }

    private static double rawSeed(double seed) {
        double f = Math.PI * 51.49;
        double l = (seed+f)*f;
        double base = Math.floor(l);
        return l-base;
    }

    public static double seed(double seed) {
        return rawSeed(rawSeed(rawSeed(seed)));
    }

    public static double seed2d(double x, double z, double seed) {
        return seed(rawSeed(x)*rawSeed(z)*rawSeed(seed));
    }

    public static double seed3d(double x, double y, double z, double seed) {
        return seed(rawSeed(x)*rawSeed(y)*rawSeed(z)*rawSeed(seed));
    }
}
