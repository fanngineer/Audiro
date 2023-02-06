package com.a402.audiro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Spot {

    @Id
    @Column(name = "spot_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "spot_name")
    private String spotName;
    @Column(name = "spot_addr")
    private String spotAddr;

    @Override
    public String toString() {
        return "Spot{" +
                "spotId=" + id +
                ", spotName='" + spotName + '\'' +
                ", spotAddr='" + spotAddr + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getSpotName() {
        return spotName;
    }

    public String getSpotAddr() {
        return spotAddr;
    }
}
