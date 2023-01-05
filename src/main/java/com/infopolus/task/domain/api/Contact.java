package com.infopolus.task.domain.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Contact {

    private String name;
    private List<String> ownedCars;
}
