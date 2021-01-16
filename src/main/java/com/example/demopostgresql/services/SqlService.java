package com.example.demopostgresql.services;

import java.util.List;

import com.example.demopostgresql.dto.OrderDetailsDto;
import com.example.demopostgresql.dto.OrderDto;

/**
 * @author rishi - created on 01/01/21
 **/
public interface SqlService {

  void order(OrderDto orderDto);

  List<OrderDetailsDto> getOrderDetails(String customerId);

  void cancelOrder(String orderId);
}
