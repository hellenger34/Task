package com.infopolus.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Person {

    private String id;
    private String name;
    private List<String> ownedCarIds;

}
