package no.agentorw.lidarCov2BM;

import java.util.HashMap;
import java.util.Map;

public class TileToCoordinates {
    
    public static Map<String, Double> getLatLon(int x, int y, int z) {

        double x1, x2, y1, y2;

        double margin = 0.05;

        x1  = lon(x, z)-margin;
        x2  = lon(x + 1, z)+margin;
        y1 = lat(y, z)-margin;
        y2 = lat(y + 1, z)+margin;

        return new HashMap<>()
        {
            {
                put("x1", x1);
                put("x2", x2);
                put("y1", y1);
                put("y2", y2);
            }
        };
    }

    private static double lon(int x, int z) {
        return (double) x / Math.pow(2, z) * 360.0 - 180.0;
    }

    private static double lat(int y, int z) {
        double n = Math.PI - (2.0 * Math.PI * y) / Math.pow(2, z);
        return Math.toDegrees(Math.atan(Math.sinh(n)));
    }

}
