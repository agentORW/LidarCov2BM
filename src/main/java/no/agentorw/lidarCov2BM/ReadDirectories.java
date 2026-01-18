package no.agentorw.lidarCov2BM;

import no.agentorw.lidarCov2BM.pojos.FullDatasetObj;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadDirectories {

    public List<FullDatasetObj> fullDataset;

    public ReadDirectories(File f) {

        {
            try {
                File jsonFile = new File(
                        f,
                        "terraplusplus/config/heights/heights.json"
                );

                fullDataset = FromJson.getObjFromFile(jsonFile.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
