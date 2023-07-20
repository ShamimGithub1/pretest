package com.example.pretest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ResponseOrderShipment {

    private Order order = new Order();
    private Shipment shipment = new Shipment();
    @Data
    @NoArgsConstructor
    public static class Order {
        private String orderId;
        private String productId;
        private double quantity;
    }
    @Data
    @NoArgsConstructor
    public static class Shipment {
        private String orderId;
        private String shipmentId;
        private String productId;
        private String shipmentDate;
        private double qty;
    }


}
