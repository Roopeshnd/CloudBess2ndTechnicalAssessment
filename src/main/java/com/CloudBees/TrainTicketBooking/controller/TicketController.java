package com.CloudBees.TrainTicketBooking.controller;

import com.CloudBees.TrainTicketBooking.dto.ReceiptDTO;
import com.CloudBees.TrainTicketBooking.dto.TicketRequest;
import com.CloudBees.TrainTicketBooking.models.Ticket;
import com.CloudBees.TrainTicketBooking.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@Validated
public class TicketController
{
    @Autowired
    private TicketService ticketService;

    @PostMapping("/purchase")
    public ResponseEntity<Ticket> purchaseTicket(@Validated @RequestBody TicketRequest request) {
        Ticket ticket = ticketService.purchaseTicket(request);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/{ticketId}")
    public Ticket getTicket(@PathVariable Long ticketId) {

        return ticketService.getTicket(ticketId);
    }

    @DeleteMapping("/removeUser/{id}")
    public String removeUserFromTrain(@PathVariable Long id)
    {
        ticketService.RemoveUser(id);
        return "removed user Successfully";
    }

    @GetMapping("/receipt/{userId}")
    public ResponseEntity<ReceiptDTO> getReceiptDetails(@PathVariable Long userId) {
        ReceiptDTO receiptDTO = ticketService.getReceiptDetails(userId);
        return ResponseEntity.ok(receiptDTO);
    }



}
