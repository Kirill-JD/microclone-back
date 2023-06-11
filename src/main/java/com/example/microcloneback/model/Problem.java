package com.example.microcloneback.model;

import com.example.microcloneback.model.contexts.Contexts;
import com.example.microcloneback.model.exception.Exception;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Problem {
    @JsonProperty("event_id")
    private String eventId;
    private Long project;
    private String release; //стоял null, пока что выставлю String
    private String dist;   //стоял null, пока что выставлю String
    private String platform;
    private String message;
    private String datetime;
    private Map<String, String> tags; //стоял List<List<String>>, пока что выставлю Map<String, String>
    @JsonProperty("_metrics")
    private Metrics metrics;
    private List<Value> breadcrumbs;
    private Contexts contexts;
    private String culprit;
    private String environment;
    private List<Error> errors;
    private Exception exception;
    private List<String> fingerprint;
    @JsonProperty("grouping_config")
    private GroupingConfig groupingConfig;
    private List<String> hashes;
    @JsonProperty("ingest_path")
    private List<IngestPath> ingestPath;
    @JsonProperty("key_id")
    private String keyId;
    private String level;
    private String location;
    private String logger;
    private Metadata metadata;
    @JsonProperty("nodestore_insert")
    private Double nodestoreInsert;
    private Double received;
    private Request request;
    private Sdk sdk;
    private Double timestamp;
    private String title;
    private String type;
    private User user;
    private String version;
}
