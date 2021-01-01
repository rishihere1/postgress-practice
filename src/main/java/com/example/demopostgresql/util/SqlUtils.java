package com.example.demopostgresql.util;

import com.example.demopostgresql.dto.SqlDto;
import com.example.demopostgresql.entity.Customers;
import com.example.demopostgresql.entity.Employees;
import com.example.demopostgresql.entity.OrderDetails;
import com.example.demopostgresql.entity.Orders;
import com.example.demopostgresql.entity.Products;
import com.example.demopostgresql.entity.Shippers;

/**
 * @author rishi - created on 01/01/21
 **/
public class SqlUtils {

  public static Customers setCustomers(SqlDto sqlDto) {
    return Customers.builder()
        .customerName(sqlDto.getCustomerName())
        .address(sqlDto.getCustomerAddress())
        .city(sqlDto.getCustomerCity())
        .contactName(sqlDto.getContactName())
        .postalCode(sqlDto.getCustomerPostalCode())
        .country(sqlDto.getCustomerCountry())
        .build();
  }

  public static Employees setEmployees(SqlDto sqlDto) {
    return Employees.builder()
        .birthDate(sqlDto.getEmployeeBirthDate())
        .firstName(sqlDto.getEmployeeFirstName())
        .lastName(sqlDto.getEmployeeLastName())
        .notes(sqlDto.getEmployeeNotes())
        .photo(sqlDto.getEmployeePhoto())
        .build();
  }

  public static OrderDetails setOrderDetails(SqlDto sqlDto) {
    return OrderDetails.builder()
        .quantity(sqlDto.getOrderDetailsQuantity())
        .build();
  }

  public static Orders setOrders(SqlDto sqlDto) {
    return Orders.builder()
        .orderDate(sqlDto.getOrdersDate())
        .build();
  }

  public static Products setProducts(SqlDto sqlDto) {
    return Products.builder()
        .productName(sqlDto.getProductName())
        .unit(sqlDto.getProductUnit())
        .price(sqlDto.getProductPrice())
        .build();
  }

  public static Shippers setShippers(SqlDto sqlDto) {
    return Shippers.builder()
        .shipperName(sqlDto.getShipperName())
        .phoneNumber(sqlDto.getShipperPhoneNumber())
        .build();
  }

  /**
   *
   * Request body for addData api
   {
   "customerName" : "Cactus Comidas para llevar",
   "contactName" : "Patricio Simpson",
   "customerAddress" : "Cerrito 333",
   "customerCity" : "Buenos Aires",
   "customerPostalCode" : "1010",
   "customerCountry" : "Argentina",
   "employeeLastName" : "West",
   "employeeFirstName" : "Adam",
   "employeeBirthDate" : "2019-09-02T02:10:00.000Z",
   "employeePhoto" : "EmpID10.pic",
   "employeeNotes" : "An old chum.",
   "orderDetailsQuantity" : 2,
   "ordersDate" : "2019-07-25T22:00:00.000Z",
   "productName" : "Queso Manchego La Pastora",
   "productUnit" : "1 kg pkg.",
   "productPrice" : 40,
   "shipperName" : "Federal Shipping",
   "shipperPhoneNumber" : "(503) 555-9931"
   }
   */
}
