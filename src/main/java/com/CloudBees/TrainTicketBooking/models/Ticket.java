package com.CloudBees.TrainTicketBooking.models;

import jakarta.persistence.*;


@Entity
public class Ticket
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromLocation;
    private String toLocation;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserT user;

    public Ticket() {
    }

    public Ticket(Long id,String fromLocation, String toLocation, Double price, UserT user) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.price = price;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UserT getUserT() {
        return user;
    }

    public void setUserT(UserT user) {
        this.user = user;
    }
}
