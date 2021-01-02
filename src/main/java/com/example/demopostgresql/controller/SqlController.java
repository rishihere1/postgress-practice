package com.example.demopostgresql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demopostgresql.dto.OrderDetailsDto;
import com.example.demopostgresql.dto.OrderDto;
import com.example.demopostgresql.services.SqlService;

/**
 * @author rishi - created on 01/01/21
 **/
@RestController
@RequestMapping("/sql")
public class SqlController {

  @Autowired
  private SqlService sqlService;

  @PostMapping("/order")
  public String order(@RequestBody OrderDto orderDto) {
    sqlService.order(orderDto);
    return "Ordered successfully!!";
  }

  @GetMapping("/getOrderDetail")
  public List<OrderDetailsDto> getOrderDetails(@RequestParam String customerId) {
    return sqlService.getOrderDetails(customerId);
  }
}
