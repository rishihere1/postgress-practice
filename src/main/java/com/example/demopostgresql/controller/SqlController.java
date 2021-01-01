package com.example.demopostgresql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demopostgresql.dto.SqlDto;
import com.example.demopostgresql.services.SqlService;

/**
 * @author rishi - created on 01/01/21
 **/
@RestController
@RequestMapping("/sql")
public class SqlController {

  @Autowired
  private SqlService sqlService;

  @PostMapping("/addData")
  public String addData(@RequestBody SqlDto sqlDto) {
    sqlService.addData(sqlDto);
    return "Data added successfully!";
  }

  @GetMapping("/sqlJoinQuery")
  public String getJoinQueryData() {
    sqlService.getJoinQueryData();
    return "Query ran successfully!";
  }
}
