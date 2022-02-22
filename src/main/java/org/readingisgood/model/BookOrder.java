package org.readingisgood.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class BookOrder extends PanacheEntity {
    @ManyToOne
    public User user;
    @ManyToOne
    public Book book;
}
