package com.example.pretest.service;
import com.example.pretest.dto.Order;
import com.example.pretest.dto.RequestOrder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class OrderService {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Order getOrderStatus(RequestOrder orderobj) throws ParseException {

        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("Order1", "Prod1", 2.0));

        System.out.println("List Order "+ orderList.toString());

        Predicate<Order> getOrder = a->a.getOrderId().equalsIgnoreCase(orderobj.getOrderId());
        return orderList.stream().filter(getOrder).findFirst().orElseThrow(RuntimeException::new);

    }
}


