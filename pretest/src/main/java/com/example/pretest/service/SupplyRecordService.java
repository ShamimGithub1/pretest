package com.example.pretest.service;
import com.example.pretest.dto.RequestSupplyDemand;
import com.example.pretest.dto.Supply;
import com.example.pretest.dto.SupplyRecord;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class SupplyRecordService {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public SupplyRecord getSupplyRecord(RequestSupplyDemand orderobj) throws ParseException {

        List<Supply> supplyDetailsList = new ArrayList<>();
//        supplyDetailsList.add(new SupplyRecord("Product1",10));
//        supplyDetailsList.add(new SupplyRecord("Product2",5));

        System.out.println("List Order "+ supplyDetailsList.toString());

//        Predicate<Supply> getSupply = a->a.getProductId().equalsIgnoreCase(orderobj.getProductId());
//        return supplyDetailsList.stream().filter(getSupply).findFirst().orElseThrow(RuntimeException::new);
        return null;
    }
}


