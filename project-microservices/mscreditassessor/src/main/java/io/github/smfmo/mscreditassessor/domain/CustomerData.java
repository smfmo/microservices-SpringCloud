package io.github.smfmo.mscreditassessor.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomerData {

    private UUID id;
    private String name;
    private Integer age;
}
