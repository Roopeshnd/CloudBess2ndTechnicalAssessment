package com.CloudBees.TrainTicketBooking.dto;

public class ReceiptDTO {
    private UserDTO user;
    private TicketDTO ticket;

    public ReceiptDTO(UserDTO user, TicketDTO ticket) {
        this.user = user;
        this.ticket = ticket;
    }

    public ReceiptDTO() {

    }

    // Getters and setters for user and ticket fields
    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public TicketDTO getTicket() {
        return ticket;
    }

    public void setTicket(TicketDTO ticket) {
        this.ticket = ticket;
    }
}

