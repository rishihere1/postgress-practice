package com.example.demopostgresql.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rishi - created on 02/01/21
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

  private String customerName;
  private Map<String, Integer> productIdToQuantityMap;
  private int speedPriority;
}
