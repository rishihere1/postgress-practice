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
   *
    Order api request body
   {
     "customerName" : "White Clover Markets",
     "productIdToQuantityMap" : {
         "2" : 20,
         "4" : 30,
         "6" : 40,
         "8" : 50
     },
     "speedPriority" : 1
   }




   Create sql tables

   CREATE TABLE customers (
   ID VARCHAR(255) NOT NULL PRIMARY KEY,
   customer_name varchar(255),
   contact_name varchar(255),
   address varchar(255),
   city varchar(255),
   postal_code VARCHAR(255),
   country VARCHAR(255)
   );
   ----

   CREATE TABLE employees (
   ID VARCHAR(255) NOT NULL PRIMARY KEY,
   last_name varchar(255),
   first_name varchar(255),
   birth_date date,
   photo varchar(255),
   notes VARCHAR(255)
   );

   ----
   CREATE TABLE order_details (
   ID VARCHAR(255) NOT NULL PRIMARY KEY,
   quantity bigint,
   orders_fk varchar(255),
   products_fk varchar(255)
   );

   -----

   CREATE TABLE orders (
   ID VARCHAR(255) NOT NULL PRIMARY KEY,
   order_date DATE,
   employees_fk varchar(255),
   shipper_fk varchar(255),
   customers_fk VARCHAR(255)
   );

   -----

   CREATE TABLE products (
   ID VARCHAR(255) NOT NULL PRIMARY KEY,
   product_name varchar(255),
   unit varchar(255),
   price double precision,
   orders_fk varchar(255)
   );

   ----

   CREATE TABLE shippers (
   ID VARCHAR(255) NOT NULL PRIMARY KEY,
   shipper_name varchar(255),
   shipper_phone_number varchar(255)
   );


   INSERT into products

   insert into products VALUES ('1'    ,'Ipoh Coffee', 'boxes x 20 bags', 60);
   insert into products VALUES ('2'	,'Northwoods Cranberry Sauce'	,'12 - 12 oz jars',40);
   insert into products VALUES ('3'	,'Teatime Chocolate Biscuits'	,'10 boxes x 12 pieces',50);
   insert into products VALUES ('4'	,'Raclette Courdavault'	,'5 kg pkg.',47);
   insert into products VALUES ('5'	,'Mozzarella di Giovanni'	,'24 - 200 g pkgs.',34.8);
   insert into products VALUES ('6'	,'Louisiana Fiery Hot Pepper Sauce'	,'32 - 8 oz bottles',21.05);
   insert into products VALUES ('7'	,'Guaraná Fantástica'	,'12 - 355 ml cans',4.5);
   insert into products VALUES ('8'	,'Alice Mutton'	,'20 - 1 kg tins',39);
   insert into products VALUES ('9'	,'Uncle Bobs Organic Dried Pears'	,'12 - 1 lb pkgs.',80.5);
   insert into products VALUES ('10'	,'Aniseed Syrup'	,'12 - 550 ml bottles',10.5);
   */
}
