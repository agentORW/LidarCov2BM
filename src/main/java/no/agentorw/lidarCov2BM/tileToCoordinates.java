package no.agentorw.lidarCov2BM;

public class tileToCoordinates {
    
    public void getLatLon(int x, int y, int z) {

        double west, east, north, south;

        west  = lon(x, z);
        east  = lon(x + 1, z);
        north = lat(y, z);
        south = lat(y + 1, z);
        

        return west, east, north, south;
    }

    private double lon(int x, int z) {
        return (double) x / Math.pow(2, z) * 360.0 - 180.0;
    }

    private double lat(int y, int z) {
        double n = Math.PI - (2.0 * Math.PI * y) / Math.pow(2, z);
        return Math.toDegrees(Math.atan(Math.sinh(n)));
    }

}
