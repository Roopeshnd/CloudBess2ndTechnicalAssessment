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
    @OneToOne
    private UserT user;

    public TrainSeat() {
    }

    public TrainSeat(Long id,String section, int seatNumber, UserT user)
    {
        this.id = id;
        this.section = section;
        this.seatNumber = seatNumber;
        this.user = user;
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

    public UserT getUserT() {
        return user;
    }

    public void setUserT(UserT user) {
        this.user = user;
    }
}
