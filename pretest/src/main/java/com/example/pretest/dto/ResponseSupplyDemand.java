package com.example.pretest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseSupplyDemand {

    private String productId;
    private double availability;

}
