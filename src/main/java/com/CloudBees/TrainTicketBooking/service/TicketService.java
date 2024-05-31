package com.CloudBees.TrainTicketBooking.service;

import com.CloudBees.TrainTicketBooking.Exception.TicketNotFoundException;
import com.CloudBees.TrainTicketBooking.Exception.UserNotFoundException;
import com.CloudBees.TrainTicketBooking.dto.*;
import com.CloudBees.TrainTicketBooking.models.Ticket;


import com.CloudBees.TrainTicketBooking.models.TrainSeat;
import com.CloudBees.TrainTicketBooking.models.UserT;
import com.CloudBees.TrainTicketBooking.repository.TicketRepository;
import com.CloudBees.TrainTicketBooking.repository.TrainSeatRepository;
import com.CloudBees.TrainTicketBooking.repository.UserRepository;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainSeatRepository trainSeatRepository;

    @Autowired
    private TrainSeatService trainSeatService;




    public Ticket purchaseTicket(TicketRequest request) {
        UserT user = new UserT();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        user = userRepository.save(user);

        Ticket ticket = new Ticket();
        ticket.setUserT(user);
        ticket.setFromLocation(request.getFromLocation());
        ticket.setToLocation(request.getToLocation());
        ticket.setPrice(20.0); // Assuming a fixed price for simplicity

       return ticketRepository.save(ticket);
//        String section = "A"; // You can decide how to determine the section, here it's hardcoded to "A"
//        int seatNumber = trainSeatService.findNextAvailableSeat(section);
//        TrainSeat seat = trainSeatService.allocateSeat(section, seatNumber, user);
//
//        return ticket;
    }

    public Ticket getTicket(Long ticketId)
    {

        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + ticketId));
    }


    public void RemoveUser( Long id)
    {
        ticketRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        ticketRepository.deleteById(id);
    }

    public ReceiptDTO getReceiptDetails(Long userId) {
        UserT user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Ticket ticket = ticketRepository.findByUser(user);
        if (ticket == null) {
            throw new TicketNotFoundException("Ticket not found for user with ID: " + userId);
        }

        TrainSeat seat = trainSeatService.getSeatByUser(user);

        UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        TicketDTO ticketDTO = new TicketDTO(ticket.getId(), ticket.getFromLocation(), ticket.getToLocation(), ticket.getPrice());
        TrainSeatDTO seatDTO = new TrainSeatDTO(seat.getId(), seat.getSection(), seat.getSeatNumber());

        // Create ReceiptDTO and populate with user, ticket, and seat details
        ReceiptDTO receiptDTO = new ReceiptDTO(userDTO, ticketDTO, seatDTO);
        return receiptDTO;
    }


    public List<Ticket> getAllUsers(Ticket ticket)
    {
       return ticketRepository.findAll();
    }



}




