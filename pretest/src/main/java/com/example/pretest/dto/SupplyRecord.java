package com.example.pretest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplyRecord {

    private String productId;
    private Date updateTimeStamp;
    private double quantity;
    private String status;

}
