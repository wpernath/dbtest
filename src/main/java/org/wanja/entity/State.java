package org.wanja.entity;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Cacheable
public class State extends PanacheEntity {
    @Column(unique = true, nullable = false)
    public String name;


    public static List<State> findByName(String name) {
        String filter = "%" + name + "%";
        return State.find("upper(name) like ?1", filter.toUpperCase()).list();
    }

}
