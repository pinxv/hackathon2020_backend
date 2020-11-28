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

    @Id
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
                Objects.equals(creator, that.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, batchNumber, description, sum, creator);
    }
}
