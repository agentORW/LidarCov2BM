package no.agentorw.lidarCov2BM;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import no.agentorw.lidarCov2BM.pojos.FullDatasetObj;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FromJson {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<FullDatasetObj> getObjFromFile(String jsonURL) throws IOException {

        return objectMapper.readValue(new File(jsonURL), new TypeReference<List<FullDatasetObj>>() {});
    }
}
