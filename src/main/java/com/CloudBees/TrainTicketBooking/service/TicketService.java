package com.CloudBees.TrainTicketBooking.service;

import com.CloudBees.TrainTicketBooking.Exception.TicketNotFoundException;
import com.CloudBees.TrainTicketBooking.Exception.UserNotFoundException;
import com.CloudBees.TrainTicketBooking.dto.ReceiptDTO;
import com.CloudBees.TrainTicketBooking.dto.TicketDTO;
import com.CloudBees.TrainTicketBooking.dto.TicketRequest;
import com.CloudBees.TrainTicketBooking.dto.UserDTO;
import com.CloudBees.TrainTicketBooking.models.Ticket;
import com.CloudBees.TrainTicketBooking.models.TrainSeat;
import com.CloudBees.TrainTicketBooking.models.User;
import com.CloudBees.TrainTicketBooking.repository.TicketRepository;
import com.CloudBees.TrainTicketBooking.repository.TrainSeatRepository;
import com.CloudBees.TrainTicketBooking.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    private TrainSeatRepository trainSeatRepository;



    public Ticket purchaseTicket(TicketRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        user = userRepository.save(user);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setFromLocation(request.getFromLocation());
        ticket.setToLocation(request.getToLocation());
        ticket.setPrice(20.0); // Assuming a fixed price for simplicity

        return  ticketRepository.save(ticket);
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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Ticket ticket = ticketRepository.findByUser(user);
        if (ticket == null) {
            throw new TicketNotFoundException("Ticket not found for user with ID: " + userId);
        }

        // Create DTO objects and populate data
        UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        TicketDTO ticketDTO = new TicketDTO(ticket.getId(), ticket.getFromLocation(), ticket.getToLocation(), ticket.getPrice());

        // Create ReceiptDTO and populate with user and ticket details
        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setUser(userDTO);
        receiptDTO.setTicket(ticketDTO);

        return receiptDTO;
    }

}




