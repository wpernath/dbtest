package org.wanja.entity;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Cacheable
public class County extends PanacheEntity{
    @Column(unique = true, nullable = false)
    public String name;

    @Column(nullable = false, name = "state_name")
    public String stateName;    

    public static List<County> findByState(String state) {
        String filter="%"+state+"%";
        return County.find("upper(state_name) like ?1", filter.toUpperCase()).list();
    }

    public static List<County> findByName(String name) {
        String filter = "%" + name + "%";
        return County.find("upper(name) like ?1", filter.toUpperCase()).list();
    }

}
