package com.example.demopostgresql.services;

import com.example.demopostgresql.dto.SqlDto;

/**
 * @author rishi - created on 01/01/21
 **/
public interface SqlService {

  void addData(SqlDto sqlDto);

  void getJoinQueryData();
}
