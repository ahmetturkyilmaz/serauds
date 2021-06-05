package com.serauds.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class InformationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    /*
    @OneToOne
    private Address address;
    */
    @OneToMany
    private List<CurrentStatus> statusList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CurrentStatus> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<CurrentStatus> statusList) {
        this.statusList = statusList;
    }
}
