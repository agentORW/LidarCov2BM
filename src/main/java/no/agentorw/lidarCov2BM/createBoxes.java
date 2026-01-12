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

    public createBoxes(File f) {

        URI uri = URI.create(obj.dataset.urls.get(0));
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
        }

    }

    public int getBoxes() {
        return 0;
    }

    public Set<String> listFilesUsingFilesList(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

    public Set<String> getFiles(File f, String link) {

        File xValsPath = new File(
                f,
                link
        );

        String xFullPath = xValsPath.getAbsolutePath();

        Set<String> vals;
        try {
            vals = listFilesUsingFilesList(xFullPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return vals;
    }
}
