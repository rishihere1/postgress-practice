package com.example.demopostgresql.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author rishi - created on 01/01/21
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Orders.TABLE)
public class Orders implements Serializable {

  public static final String TABLE = "orders";

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid", strategy = "uuid2")
  @Column(name = "ID")
  private String id;

  @Column(name = "order_date")
  private Date orderDate;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "employees_fk")
  private Employees employees;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "shipper_fk")
  private Shippers shippers;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customers_fk")
  private Customers customers;

  @JsonManagedReference
  @OneToMany(mappedBy = "orders",
             fetch = FetchType.LAZY,
             cascade = CascadeType.ALL)
  private Set<OrderDetails> orderDetails = new HashSet<>();

  @Override
  public String toString() {
    return id;
  }
}
