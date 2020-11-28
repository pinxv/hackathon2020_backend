package com.pinxv.hackathon2020_backend.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ChangeCargoInfo", schema = "pinxv")
public class ChangeCargoInfo implements Comparable<ChangeCargoInfo> {
    private int id;
    private String place;
    private Integer sum;
    private String batchNumber;
    private String description;
    private String destination;
    private String creator;
    private Timestamp timestamp;

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

    @Basic
    @Column(name = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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
                Objects.equals(destination, that.destination) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place, sum, batchNumber, description, destination, creator, timestamp);
    }

    @Override
    public int compareTo(ChangeCargoInfo o) {
        return (int) (this.timestamp.getTime() - o.timestamp.getTime());
    }
}
