package no.agentorw.lidarCov2BM.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class fullDatasetObj {

    public datasetField dataset;

}
