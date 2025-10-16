package no.agentorw.lidarCov2BM;

import de.bluecolored.bluemap.api.BlueMapAPI;
import de.bluecolored.bluemap.api.BlueMapMap;
import de.bluecolored.bluemap.api.markers.MarkerSet;
import de.bluecolored.bluemap.api.markers.POIMarker;
import org.bukkit.plugin.java.JavaPlugin;

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
        POIMarker marker = POIMarker.builder()
                .label("My Marker")
                .position(20.0, 65.0, -23.0)
                .minDistance(100)
                .maxDistance(1000)
                .build();

        MarkerSet markerSet = MarkerSet.builder()
                .label("Test")
                .build();

        markerSet.getMarkers()
                .put("my-marker-id", marker);

        api.getWorld("world").ifPresent(world -> {
            for (BlueMapMap map : world.getMaps()) {
                map.getMarkerSets().put("my-marker-set-id", markerSet);
            }
        });
    }
}
