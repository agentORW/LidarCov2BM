package no.agentorw.lidarCov2BM;

import no.agentorw.lidarCov2BM.pojos.fullDatasetObj;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class readDirectories {

    public static List<fullDatasetObj> fullDataset;

    public readDirectories(File f) {

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

}
