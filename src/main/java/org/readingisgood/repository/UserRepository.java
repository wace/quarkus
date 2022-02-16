package org.readingisgood.repository;

import javax.enterprise.context.ApplicationScoped;

import org.readingisgood.model.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{

    
}
