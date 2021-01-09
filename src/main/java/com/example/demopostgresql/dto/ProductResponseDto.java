package com.example.demopostgresql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rishi - created on 10/01/21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

  private String productName;
  private long quantity;
  private String unit;
  private double price;
}
