package no.agentorw.lidarCov2BM;

import no.agentorw.lidarCov2BM.pojos.fullDatasetObj;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class readDirectories {

    private final File f;
    Logger log;

    List<fullDatasetObj> fullDataset;

    public readDirectories(File f) {
        this.f = f;

        {
            try {
                File jsonFile = new File(
                        f,
                        "terraplusplus/config/heights/heights.json"
                );

                fullDataset = fromJson.getObjFromFile(jsonFile.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<fullDatasetObj> getFullDataset() {
        return fullDataset;
    }

}
