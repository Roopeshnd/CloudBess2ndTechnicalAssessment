package com.CloudBees.TrainTicketBooking.dto;

public class TicketDTO {
    private Long id;
    private String fromLocation;
    private String toLocation;
    private Double price;

    public TicketDTO(Long id, String fromLocation, String toLocation, Double price) {
        this.id = id;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.price = price;
    }

    // Getters and setters for id, fromLocation, toLocation, and price fields
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
}
