package com.example.pretest.service;
import com.example.pretest.dto.Demand;
import com.example.pretest.dto.Order;
import com.example.pretest.dto.RequestOrder;
import com.example.pretest.dto.RequestSupplyDemand;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class DemandService {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Demand getDemandStatus(RequestSupplyDemand request) throws ParseException {

        List<Demand> demandList = new ArrayList<>();
        demandList.add(new Demand("Product1",2));
        demandList.add(new Demand("Product2",5));

        System.out.println("List Order "+ demandList.toString());

        Predicate<Demand> getDemand = a->a.getProductId().equalsIgnoreCase(request.getProductId());
        return demandList.stream().filter(getDemand).findFirst().orElseThrow(RuntimeException::new);

    }
}


