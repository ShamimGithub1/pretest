package com.example.pretest.Controller;

import com.example.pretest.dto.*;
import com.example.pretest.dto.ResponseOrderShipment;
import com.example.pretest.service.OrderService;
import com.example.pretest.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.*;

@RestController
@Component
public class OrderShipmentController {

    @Autowired
    OrderService orderService;
    @Autowired
    ShipmentService shipmentService;
    @PostMapping(value = "/getOrderDetails")
    public ResponseEntity getOrderDetailsResponse(@RequestBody RequestOrder orderobj, ResponseOrderShipment response) throws ParseException, ExecutionException, InterruptedException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future orderDetails = executorService.submit(getOrderDetails(orderobj, orderService));
        Future shipmentDetails = executorService.submit(getShipmentDetails(orderobj, shipmentService));

        Order orderRef = (Order) orderDetails.get();
        Shipment shipRef = (Shipment) shipmentDetails.get();

        if(shipRef.getOrderId().equalsIgnoreCase(orderRef.getOrderId())){
            ResponseOrderShipment.Order responseOrder = response.getOrder();
            ResponseOrderShipment.Shipment responseShipment = response.getShipment();
            responseOrder.setOrderId(orderRef.getOrderId());
            responseOrder.setQuantity(orderRef.getQuantity());
            responseOrder.setProductId(orderRef.getProductId());
            responseShipment.setShipmentId(shipRef.getShipmentId());
            responseShipment.setOrderId(shipRef.getOrderId());
            responseShipment.setShipmentDate(sdf.format(shipRef.getShipmentDate()));
            responseShipment.setProductId(shipRef.getProductId());
            responseShipment.setQty(shipRef.getQty());
        }
        executorService.shutdown();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private Callable getOrderDetails(RequestOrder orderObj, OrderService orderService) {
        return new Callable() {

            @Override
            public Object call() throws Exception {
                return orderService.getOrderStatus(orderObj);

            }
        };
    }

    private Callable getShipmentDetails(RequestOrder orderObj, ShipmentService shipmentService) {
        return new Callable() {

            @Override
            public Object call() throws Exception {
                return shipmentService.getShipment(orderObj);
            }
        };
    }

}
