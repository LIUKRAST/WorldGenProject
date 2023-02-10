package net.frozenblock.liukrast.worldgen.feature;

import java.awt.geom.Point2D;

public class WorldgenHeightCalculator {
    public static double interpolate(double a, double b, double x) {
        return (1-Math.cos(x*Math.PI))/2 * (b-a) + a;
    }

    public static double loop(double x, double chunk) {
        return (x-getChunk(x,chunk))/chunk;
    }

    public static double getChunk(double n, double chunksize) {
        return Math.floor(n/chunksize)*chunksize;
    }


    public static Point2D rotate(double alpha, Point2D real) {
        double fx = real.getX()*Math.cos(alpha)-real.getY()*Math.sin(alpha);
        double fz = real.getX()*Math.sin(alpha)+real.getY()*Math.cos(alpha);
        return new Point2D.Double(fx, fz);
    }

    public static double height(double x, double z, double c, double seed) {
        double s = seed/1000;
        double height1 = clcY(x,z,c,s)/10; // H From 0 to 0.1
        Point2D r = rotate(1, new Point2D.Double(x,z));
        double height2 = clcY(r.getX()/10, r.getY()/10, c, s); // H From 0 to 1
        Point2D r2 = rotate(10, new Point2D.Double(x,z));
        double height3 = clcY(r2.getX()/4, r2.getY()/4, c, s)/5; // H From 0 to 0.5
        return (height1 + height2 + height3)/1.6; // Must match: From 0 to 1
    }

    private static double clcY(double x, double z, double c, double s) {
        double y1 = AdvancedMath.seed2d(getChunk(x,c),getChunk(z,c),s);
        double y2 = AdvancedMath.seed2d(getChunk(x,c)+c,getChunk(z,c),s);
        double y3 = AdvancedMath.seed2d(getChunk(x,c),getChunk(z,c)+c,s);
        double y4 = AdvancedMath.seed2d(getChunk(x,c)+c,getChunk(z,c)+c,s);

        double int1 = interpolate(y1,y2,loop(x,c));
        double int2 = interpolate(y3,y4,loop(x,c));
        return interpolate(int1,int2,loop(z,c));
    }
}
