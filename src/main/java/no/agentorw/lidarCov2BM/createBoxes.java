package no.agentorw.lidarCov2BM;

import no.agentorw.lidarCov2BM.pojos.fullDatasetObj;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class createBoxes {

    public static List<List<String>> getBoxes(String f) {

        URI uri = URI.create(f);
        Path zoomPath = Path.of(uri).getParent().getParent();

        try (Stream<Path> xDirs = Files.list(zoomPath)) {
            xDirs
            .filter(Files::isDirectory)
            .forEach(xDir -> {
                try (Stream<Path> zFiles = Files.list(xDir)) {
                    zFiles
                    .filter(p -> p.toString().endsWith(".png"))
                    .forEach(z -> {
                        // HERE: you have x/z tile
                        // xDir.getFileName(), z.getFileName()
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

}
