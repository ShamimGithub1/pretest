package com.example.pretest.service;

import com.example.pretest.dto.RequestOrder;
import com.example.pretest.dto.Order;
import com.example.pretest.dto.Shipment;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class ShipmentService {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Shipment getShipment(RequestOrder orderobj) throws ParseException {

        System.out.println("Control to the Service Class Shipment !! "+orderobj );
        List<Shipment> shipmentList = new ArrayList<>();
        shipmentList.add(new Shipment("Order1", "Ship1", "Prod1", sdf.parse("2021-02-19"), 2.0));
        System.out.println("List Order "+ shipmentList.toString());
        Predicate<Shipment> getShipment = a->a.getOrderId().equalsIgnoreCase(orderobj.getOrderId());
        return shipmentList.stream().filter(getShipment).findFirst().orElseThrow(RuntimeException::new);

    }
}


