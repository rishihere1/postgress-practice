package com.example.demopostgresql.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author rishi - created on 13/02/21
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Category.TABLE)
public class Category {
  public static final String TABLE = "category";

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid", strategy = "uuid2")
  @Column(name = "ID")
  private String id;

  @Column(name = "namehere")
  private String name;

  @Column(name = "designation")
  private String designation;

  @Column(name = "address")
  private String address;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "parent_id")
  private Category parent;

  @Builder.Default
  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Category> children = new ArrayList<>();
}
