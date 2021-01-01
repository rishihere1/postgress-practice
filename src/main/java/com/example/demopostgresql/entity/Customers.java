package com.example.demopostgresql.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
@Table(name = Customers.TABLE)
public class Customers implements Serializable {
  public static final String TABLE = "customers";

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid", strategy = "uuid2")
  @Column(name = "ID")
  private String id;

  @Column(name = "customer_name")
  private String customerName;

  @Column(name = "contact_name")
  private String contactName;

  @Column(name = "address")
  private String address;

  @Column(name = "city")
  private String city;

  @Column(name = "postal_code")
  private String postalCode;

  @Column(name = "country")
  private String country;

  @JsonManagedReference
  @OneToMany(mappedBy = "customers",
             fetch = FetchType.LAZY,
             cascade = CascadeType.ALL)
  private Set<Orders> orders = new HashSet<>();

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(customerName).append(" ").append(address);
    return stringBuilder.toString();
  }
}
