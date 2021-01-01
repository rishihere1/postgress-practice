package com.example.demopostgresql.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rishi - created on 01/01/21
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SqlDto {

  private String customerName;
  private String contactName;
  private String customerAddress;
  private String customerCity;
  private String customerPostalCode;
  private String customerCountry;
  private String employeeLastName;
  private String employeeFirstName;
  private Date employeeBirthDate;
  private String employeePhoto;
  private String employeeNotes;
  private long orderDetailsQuantity;
  private Date ordersDate;
  private String productName;
  private String productUnit;
  private double productPrice;
  private String shipperName;
  private String shipperPhoneNumber;
}
