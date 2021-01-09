package com.example.demopostgresql.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demopostgresql.dto.OrderDetailsDto;
import com.example.demopostgresql.dto.OrderDto;
import com.example.demopostgresql.dto.ProductResponseDto;
import com.example.demopostgresql.entity.Customers;
import com.example.demopostgresql.entity.Employees;
import com.example.demopostgresql.entity.OrderDetails;
import com.example.demopostgresql.entity.Orders;
import com.example.demopostgresql.entity.Products;
import com.example.demopostgresql.entity.Shippers;
import com.example.demopostgresql.repository.CustomersRepository;
import com.example.demopostgresql.repository.EmployeesRepository;
import com.example.demopostgresql.repository.OrderDetailsRepository;
import com.example.demopostgresql.repository.OrdersRepository;
import com.example.demopostgresql.repository.ProductsRepository;
import com.example.demopostgresql.repository.ShippersRepository;
import com.example.demopostgresql.services.SqlService;

/**
 * @author rishi - created on 01/01/21
 **/
@Service
public class SqlServiceImpl implements SqlService {

  @Autowired
  private CustomersRepository customersRepository;

  @Autowired
  private EmployeesRepository employeesRepository;

  @Autowired
  private OrdersRepository ordersRepository;

  @Autowired
  private OrderDetailsRepository orderDetailsRepository;

  @Autowired
  private ProductsRepository productsRepository;

  @Autowired
  private ShippersRepository shippersRepository;

  @Override
  public void order(OrderDto orderDto) {
    Customers customer = customersRepository.findCustomer(orderDto.getCustomerName());
    if (customer == null) {
      System.out.println("Customer name " + orderDto.getCustomerName() + "does not exist");
      throw new RuntimeException();
    }
    List<Products> products = productsRepository.findProduct(orderDto.getProductIdToQuantityMap().keySet());
    if (products == null) {
      System.out.println("Product with ids: " + orderDto.getProductIdToQuantityMap().keySet() + "does not exist");
      throw new RuntimeException();
    }
    orderProduct(customer, products, orderDto);
  }

  @Override
  public List<OrderDetailsDto> getOrderDetails(String customerId) {
    Customers customer = customersRepository.findOut(customerId);
    Set<Orders> ordersSet = customer.getOrders();
    List<OrderDetailsDto> orderDetailsDtos = new ArrayList<>();
    for (Orders orders : ordersSet) {
      OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
      BeanUtils.copyProperties(customer, orderDetailsDto);
      BeanUtils.copyProperties(orders, orderDetailsDto);
      Employees employees = orders.getEmployees();
      BeanUtils.copyProperties(employees, orderDetailsDto);
      Shippers shippers = orders.getShippers();
      BeanUtils.copyProperties(shippers, orderDetailsDto);
      Set<OrderDetails> orderDetailsSet = orders.getOrderDetails();
      List<ProductResponseDto> productResponseDtos = new ArrayList<>();
      for (OrderDetails orderDetails : orderDetailsSet) {
        BeanUtils.copyProperties(orderDetails, orderDetailsDto);
        Products products = orderDetails.getProducts();
        ProductResponseDto productResponseDto = new ProductResponseDto();
        BeanUtils.copyProperties(products, productResponseDto);
        productResponseDto.setQuantity(orderDetails.getQuantity());
        productResponseDtos.add(productResponseDto);
      }
      orderDetailsDto.setProductResponseDtoList(productResponseDtos);
      orderDetailsDtos.add(orderDetailsDto);
    }
    return orderDetailsDtos;
  }

  public void orderProduct(Customers customer, List<Products> productsList, OrderDto orderDto) {
    Employees employees = employeesRepository.getEmployeeWithLeastTask().get(0);
    Shippers shippers = shippersRepository.getShipper(orderDto.getSpeedPriority());
    if (shippers == null) {
      System.out.println("Shipper with priority " + orderDto.getSpeedPriority() + "does not exist");
      throw new RuntimeException();
    }
    Orders orders = Orders.builder()
        .orderDate(new Date())
        .customers(customer)
        .employees(employees)
        .shippers(shippers)
        .id(UUID.randomUUID().toString())
        .build();
    List<OrderDetails> orderDetails = new ArrayList<>();
    Orders savedOrder = ordersRepository.save(orders);
    for (Products products : productsList) {
      OrderDetails createdOrderDetails =
          OrderDetails.builder()
              .quantity(orderDto.getProductIdToQuantityMap().get(products.getId()))
              .products(products)
              .orders(savedOrder)
              .build();
      orderDetails.add(createdOrderDetails);
    }
    orderDetailsRepository.save(orderDetails);
    employees.setTask(employees.getTask() + 1);
    employeesRepository.save(employees);
  }
}
