package no.agentorw.lidarCov2BM;

import com.flowpowered.math.vector.Vector2d;
import de.bluecolored.bluemap.api.BlueMapAPI;
import de.bluecolored.bluemap.api.BlueMapMap;
import de.bluecolored.bluemap.api.markers.MarkerSet;
import de.bluecolored.bluemap.api.markers.POIMarker;
import de.bluecolored.bluemap.api.markers.ShapeMarker;
import de.bluecolored.bluemap.api.math.Shape;
import no.agentorw.lidarCov2BM.pojos.FullDatasetObj;
import org.bukkit.plugin.java.JavaPlugin;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public final class LidarCov2BM extends JavaPlugin {
    public final Logger log =  getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic
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
                .put("my-shape-id", shapeMarker);

        markerSet.getMarkers()
                .put("my-marker-id", marker);

        File f = getServer().getWorldContainer();

        //  readDirectories readDirectories = new readDirectories(f);
        List<FullDatasetObj> stuff = new ReadDirectories(f).fullDataset;

        for (FullDatasetObj obj : stuff) {
            log.info(obj.dataset.urls.getFirst());
            Geometry box = CreateBox.getBox(obj.dataset.urls.getFirst());

            URI link = URI.create(obj.dataset.urls.getFirst());
            Path placePath = Path.of(link).getParent().getParent().getParent();
            String name = placePath.getFileName().toString();

            //ShapeMarker box = new ShapeMarker()

            Collection<Vector2d> coordsCollection = new ArrayList<>();

            for (Coordinate coord : box.getCoordinates()) {
                coordsCollection.add(new Vector2d(coord.getX(), coord.getY()));
            }

            Shape boxShape = new Shape(coordsCollection);

            ShapeMarker bmOutline = new ShapeMarker(name, boxShape, 50);

            markerSet.getMarkers()
                    .put(name, bmOutline);

        }

        api.getWorld("world").ifPresent(world -> {
            for (BlueMapMap map : world.getMaps()) {
                map.getMarkerSets().put("my-marker-set-id", markerSet);
            }
        });
    }
}
