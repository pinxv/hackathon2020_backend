package com.pinxv.hackathon2020_backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ChangeCargoInfo", schema = "pinxv")
public class ChangeCargoInfo {
    private int id;
    private String place;
    private Integer sum;
    private String batchNumber;
    private String description;
    private String destination;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "sum")
    public Integer getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Basic
    @Column(name = "batchNumber")
    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "destination")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChangeCargoInfo that = (ChangeCargoInfo) o;
        return id == that.id &&
                sum.equals(that.sum) &&
                Objects.equals(place, that.place) &&
                Objects.equals(batchNumber, that.batchNumber) &&
                Objects.equals(description, that.description) &&
                Objects.equals(destination, that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place, sum, batchNumber, description, destination);
    }

}
