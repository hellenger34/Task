package com.infopolus.task.domain;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
public class Car {

    private String id;
    @JsonProperty("name")
    private String model;
}
