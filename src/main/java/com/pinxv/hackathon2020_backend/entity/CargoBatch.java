package com.pinxv.hackathon2020_backend.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author njuselhx
 */
@Entity
@Table(name = "CargoBatch", schema = "pinxv")
public class CargoBatch {
    private int id;
    private String batchNumber;
    private String description;
    private Integer sum;
    private String creator;
    private String destination;
    private Boolean isSafe;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "sum")
    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Basic
    @Column(name = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "destination")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Basic
    @Column(name = "isSafe")
    public Boolean getIsSafe() {
        return isSafe;
    }

    public void setIsSafe(Boolean isSafe) {
        this.isSafe = isSafe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CargoBatch that = (CargoBatch) o;
        return id == that.id &&
                sum.equals(that.sum) &&
                Objects.equals(batchNumber, that.batchNumber) &&
                Objects.equals(description, that.description) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(isSafe, that.isSafe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, batchNumber, description, sum, creator, destination, isSafe);
    }
}
