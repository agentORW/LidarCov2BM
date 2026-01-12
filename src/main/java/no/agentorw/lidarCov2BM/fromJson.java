package no.agentorw.lidarCov2BM;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import no.agentorw.lidarCov2BM.pojos.fullDatasetObj;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class fromJson {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<fullDatasetObj> getObjFromFile(String jsonURL) throws IOException {

        return objectMapper.readValue(new File(jsonURL), new TypeReference<List<fullDatasetObj>>() {});
    }
}
