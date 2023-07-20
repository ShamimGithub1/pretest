package com.example.pretest.service;
import com.example.pretest.dto.RequestSupplyDemand;
import com.example.pretest.dto.Supply;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class SupplyService {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Supply getSupplyStatus(RequestSupplyDemand orderobj) throws ParseException {

        List<Supply> supplyList = new ArrayList<>();
        supplyList.add(new Supply("Product1",10));
        supplyList.add(new Supply("Product2",5));

        System.out.println("List Order "+ supplyList.toString());

        Predicate<Supply> getSupply = a->a.getProductId().equalsIgnoreCase(orderobj.getProductId());
        return supplyList.stream().filter(getSupply).findFirst().orElseThrow(RuntimeException::new);
    }
}


