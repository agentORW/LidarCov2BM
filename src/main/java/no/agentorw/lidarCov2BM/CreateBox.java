package no.agentorw.lidarCov2BM;

import net.buildtheearth.terraminusminus.generator.EarthGeneratorSettings;
import net.buildtheearth.terraminusminus.projection.GeographicProjection;
import net.buildtheearth.terraminusminus.projection.OutOfProjectionBoundsException;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.operation.polygonize.Polygonizer;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CreateBox {

    private static final GeometryFactory gFactory = new GeometryFactory();

    private static final EarthGeneratorSettings bteSettings = EarthGeneratorSettings.parse(EarthGeneratorSettings.BTE_DEFAULT_SETTINGS);

    private static final GeographicProjection projection = bteSettings.projection();

    private static final String[][] coordpairs = new String[][]{
            {"x1", "y1}"},
            {"x2", "y1}"},
            {"x2", "y2}"},
            {"x1", "y2"},
            {"x1", "y1"}
    };

    public static Geometry getBox(String fullPath) {

        String f = getClearPath(fullPath);

        URI uri = URI.create(f);
        Path zoomPath = Path.of(uri).getParent().getParent();
        int z = Integer.parseInt(zoomPath.getFileName().toString());

        List<Geometry> boxes = new ArrayList<>();

        try (Stream<Path> xDirs = Files.list(zoomPath)) {

            xDirs
            .filter(Files::isDirectory)
            .forEach(xDir -> {
                try (Stream<Path> yFiles = Files.list(xDir)) {
                    yFiles
                    .filter(p -> p.toString().endsWith(".png"))
                    .forEach(yFile -> {

                        int x = Integer.parseInt(xDir.getFileName().toString());
                        int y = Integer.parseInt(yFile.getFileName().toString().substring(0, yFile.getFileName().toString().indexOf(".png")));

                        Polygonizer polygonizer = new Polygonizer();

                        Map<String, Double> coordinates = TileToCoordinates.getLatLon(x, y, z);
                        Collection<Geometry> lines = getLines(coordinates);
                        polygonizer.add(lines);
                        Collection polygons = polygonizer.getPolygons();
                        Polygon box = (Polygon) polygons.toArray()[0];

                        boxes.add(box);

                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Geometry[] geometryList = boxes.toArray(new Geometry[0]);
        GeometryCollection boxCollection = gFactory.createGeometryCollection(geometryList);

        return boxCollection.buffer(0);
    }

    private static String getClearPath(String f) {
        return f.substring(0, f.indexOf("${x}/${z}.png"));
    }

    private static Collection<Geometry> getLines(Map<String, Double> coordinates) {
        Collection<Geometry> lines = new ArrayList<>();
        for (int i = 0; i < coordpairs.length - 1; i++) {

            double[] point1, point2;

            try {
                point1 = projection.fromGeo(
                        coordinates.get(coordpairs[i][1]),
                        coordinates.get(coordpairs[i][0])
                );
                point2 = projection.fromGeo(
                        coordinates.get(coordpairs[i + 1][1]),
                        coordinates.get(coordpairs[i + 1][0])
                );

            } catch (OutOfProjectionBoundsException e) {
                throw new RuntimeException(e);
            }

            Coordinate[] coordinates1 = new Coordinate[]{
                    new Coordinate(
                            point1[0],
                            point1[1]
                    ),

                    new Coordinate(
                            point2[0],
                            point2[1]
                    )
            };
            Geometry line = gFactory.createLineString(coordinates1);

            lines.add(line);
        }

        return lines;
    }

}
