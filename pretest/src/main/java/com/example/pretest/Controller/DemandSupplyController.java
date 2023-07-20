package com.example.pretest.Controller;

import com.example.pretest.dto.*;
import com.example.pretest.service.DemandService;
import com.example.pretest.service.ShipmentService;
import com.example.pretest.service.SupplyService;
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
public class DemandSupplyController {

    @Autowired
    DemandService demandService;
    @Autowired
    SupplyService supplyService;
    @PostMapping(value = "/getAvailability")
    public ResponseEntity getOrderDetailsResponse(@RequestBody RequestSupplyDemand request, ResponseSupplyDemand response) throws ParseException, ExecutionException, InterruptedException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future demandDetails = executorService.submit(getDemandDetails(request,demandService));
        Future supplyDetails = executorService.submit(getSupplyDetails(request, supplyService));

        Demand demandRef = (Demand) demandDetails.get();
        Supply supplyRef = (Supply) supplyDetails.get();

        executorService.shutdown();

        Double demand = demandRef.getQuantity();
        Double supply = supplyRef.getQuantity();
        Double availiability = supply - demand;
        System.out.println("Availaibility" + availiability);
     if (availiability>0.0){
         response.setAvailability(availiability);
         response.setProductId(demandRef.getProductId());
         return ResponseEntity.status(HttpStatus.OK).body(response);
     }
     else{
         System.out.println("Availability Not Available");
         throw new ResponseStatusException(HttpStatus.valueOf(204));
     }

    }

    private Callable getDemandDetails(RequestSupplyDemand request, DemandService demandService) {
        return new Callable() {

            @Override
            public Object call() throws Exception {
                return demandService.getDemandStatus(request);

            }
        };
    }

    private Callable getSupplyDetails(RequestSupplyDemand request, SupplyService supplyService) {
        return new Callable() {

            @Override
            public Object call() throws Exception {
                return supplyService.getSupplyStatus(request);
            }
        };
    }

}
