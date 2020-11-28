package com.pinxv.hackathon2020_backend.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author njuselhx
 */
@Entity
@Table(name = "Cargo", schema = "pinxv")
public class Cargo {
    private int id;
    private String serialNumber;
    private String description;
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
    @Column(name = "serialNumber")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
        Cargo that = (Cargo) o;
        return id == that.id &&
                Objects.equals(serialNumber, that.serialNumber) &&
                Objects.equals(description, that.description) &&
                Objects.equals(creator, that.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serialNumber, description, creator);
    }
}
