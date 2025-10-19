package no.agentorw.lidarCov2BM;

import com.flowpowered.math.vector.Vector2d;
import de.bluecolored.bluemap.api.BlueMapAPI;
import de.bluecolored.bluemap.api.BlueMapMap;
import de.bluecolored.bluemap.api.markers.MarkerSet;
import de.bluecolored.bluemap.api.markers.POIMarker;
import de.bluecolored.bluemap.api.markers.ShapeMarker;
import de.bluecolored.bluemap.api.math.Shape;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Vector;
import java.util.logging.Logger;

public final class LidarCov2BM extends JavaPlugin {
    public final Logger log =  getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logig
        log.info("LidarCov2BM enabled");
        BlueMapAPI.onEnable(this::onBlueMapEnable);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log.info("LidarCov2BM disabled");
    }

    private void onBlueMapEnable(BlueMapAPI api) {
        log.info("LidarCov2BM onBlueMapEnable");
        POIMarker marker = POIMarker.builder()
                .label("My Marker")
                .position(3494494.0, 65.0, -6387419)
                .maxDistance(1000)
                .build();

        Shape rectangle = Shape.builder()
                .addPoints(new Vector2d(10.0, 10.0), new Vector2d(-10.0, 10.0), new Vector2d(10.0, 20.0))
                .build();

        ShapeMarker shapeMarker = ShapeMarker.builder()
                .label("Whats up")
                .shape(rectangle, 50)
                .build();

        MarkerSet markerSet = MarkerSet.builder()
                .label("Test")
                .build();

        markerSet.getMarkers()
                .put("my-marker-id", marker);

        markerSet.getMarkers()
                .put("my-shape-id", shapeMarker);

        api.getWorld("world").ifPresent(world -> {
            for (BlueMapMap map : world.getMaps()) {
                map.getMarkerSets().put("my-marker-set-id", markerSet);
            }
        });
    }
}
