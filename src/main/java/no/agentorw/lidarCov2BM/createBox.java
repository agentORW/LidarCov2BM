package no.agentorw.lidarCov2BM;

import net.buildtheearth.terraminusminus.generator.EarthGeneratorSettings;
import net.buildtheearth.terraminusminus.projection.GeographicProjection;
import org.locationtech.jts.geom.GeometryFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class createBox {

    private static final GeometryFactory gFactoru = new GeometryFactory();

    private static final EarthGeneratorSettings bteSettings = EarthGeneratorSettings.parse(EarthGeneratorSettings.BTE_DEFAULT_SETTINGS);

    private static final GeographicProjection projection = bteSettings.projection();

    public static List<List<String>> getBox(String fullPath) {

        String f = getClearPath(fullPath);

        URI uri = URI.create(f);
        Path zoomPath = Path.of(uri).getParent().getParent();
        int z = Integer.parseInt(zoomPath.getFileName().toString());

        try (Stream<Path> xDirs = Files.list(zoomPath)) {

            List<List<String>> boxes = new ArrayList<>();

            xDirs
            .filter(Files::isDirectory)
            .forEach(xDir -> {
                try (Stream<Path> yFiles = Files.list(xDir)) {
                    yFiles
                    .filter(p -> p.toString().endsWith(".png"))
                    .forEach(yFile -> {
                        // HERE: you have x/z tile
                        // xDir.getFileName(), z.getFileName()
                        int x = Integer.parseInt(xDir.getFileName().toString());
                        int y = Integer.parseInt(yFile.getFileName().toString().substring(0, yFile.getFileName().toString().indexOf(".png")));

                        double[] coordinates = tileToCoordinates.getLatLon(x, y, z);


                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private static String getClearPath(String f) {
        return f.substring(0, f.indexOf("${x}/${z}.png"));
    }

}
