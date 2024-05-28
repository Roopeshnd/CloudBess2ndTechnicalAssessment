package com.CloudBees.TrainTicketBooking.models;

import jakarta.persistence.*;

@Entity
public class TrainSeat
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String section;
    private int seatNumber;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public TrainSeat() {
    }

    public TrainSeat(Long id,String section, int seatNumber, User user)
    {
        this.id = id;
        this.section = section;
        this.seatNumber = seatNumber;
        this.user = user;
    }

    @OneToOne
    private User user;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
