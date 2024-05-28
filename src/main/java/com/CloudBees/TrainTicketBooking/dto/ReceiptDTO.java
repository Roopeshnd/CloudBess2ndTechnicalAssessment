package com.CloudBees.TrainTicketBooking.dto;

public class ReceiptDTO {
    private UserDTO user;
    private TicketDTO ticket;

    private TrainSeatDTO trainSeatDTO;

    public ReceiptDTO() {
    }

    public ReceiptDTO(UserDTO user, TicketDTO ticket, TrainSeatDTO trainSeatDTO) {
        this.user = user;
        this.ticket = ticket;
        this.trainSeatDTO = trainSeatDTO;
    }

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

    public TrainSeatDTO getTrainSeatDTO() {
        return trainSeatDTO;
    }

    public void setTrainSeatDTO(TrainSeatDTO trainSeatDTO) {
        this.trainSeatDTO = trainSeatDTO;
    }
}

