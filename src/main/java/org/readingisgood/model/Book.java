package org.readingisgood.model;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Book extends PanacheEntity {

  public String title;
  public Integer stock;
}
