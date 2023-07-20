package com.example.pretest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {

    private String orderId;
    private String shipmentId;
    private String productId;
    private Date shipmentDate;
    private double qty;


}
