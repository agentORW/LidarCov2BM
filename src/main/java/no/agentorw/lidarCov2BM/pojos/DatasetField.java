package no.agentorw.lidarCov2BM.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatasetField {

    public List<String> urls;

}
