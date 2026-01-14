package no.agentorw.lidarCov2BM;

public class tileToCoordinates {
    
    public static double[] getLatLon(int x, int y, int z) {

        double x1, x2, y1, y2;

        x1  = lon(x, z);
        x2  = lon(x + 1, z);
        y1 = lat(y, z);
        y2 = lat(y + 1, z);

        return new double[]{x1, y1, x2, y2};
    }

    private static double lon(int x, int z) {
        return (double) x / Math.pow(2, z) * 360.0 - 180.0;
    }

    private static double lat(int y, int z) {
        double n = Math.PI - (2.0 * Math.PI * y) / Math.pow(2, z);
        return Math.toDegrees(Math.atan(Math.sinh(n)));
    }

}
