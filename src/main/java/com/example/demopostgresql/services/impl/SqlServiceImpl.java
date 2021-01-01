package com.example.demopostgresql.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demopostgresql.dto.SqlDto;
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
import com.example.demopostgresql.util.SqlUtils;

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
  public void addData(SqlDto sqlDto) {
    Customers customers = customersRepository.save(SqlUtils.setCustomers(sqlDto));
    Employees employee = employeesRepository.save(SqlUtils.setEmployees(sqlDto));
    Shippers shippers = shippersRepository.save(SqlUtils.setShippers(sqlDto));
    Orders orders = SqlUtils.setOrders(sqlDto);
    orders.setCustomers(customers);
    orders.setEmployees(employee);
    orders.setShippers(shippers);
    Orders savedOrders = ordersRepository.save(orders);
    Products products = SqlUtils.setProducts(sqlDto);
    products.setOrders(savedOrders);
    Products savedProducts = productsRepository.save(products);
    OrderDetails orderDetails = SqlUtils.setOrderDetails(sqlDto);
    orderDetails.setOrders(savedOrders);
    orderDetails.setProducts(savedProducts);
    orderDetailsRepository.save(orderDetails);
  }

  @Override
  public void getJoinQueryData() {
    List<Customers> value = customersRepository.findOut("7d55c316-c911-4a79-8ab5-80329ef7b077");
    System.out.println(value.get(0).getCustomerName());
    System.out.println(value.get(0).getAddress());
    System.out.println(value.get(0).getOrders());
  }
}
