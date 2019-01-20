package com.demo.spain.app;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Output  implements Serializable {
    private String name;
    private String city;
    private String id;
}
