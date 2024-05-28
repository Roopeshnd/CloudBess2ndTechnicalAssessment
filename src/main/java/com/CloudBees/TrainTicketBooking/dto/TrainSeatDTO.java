package com.CloudBees.TrainTicketBooking.dto;

public class TrainSeatDTO
{
    private Long id;
    private String section;
    private int seatNumber;

    public TrainSeatDTO(Long id, String section, int seatNumber) {
        this.id = id;
        this.section = section;
        this.seatNumber = seatNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
