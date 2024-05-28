package com.CloudBees.TrainTicketBooking.service;

import com.CloudBees.TrainTicketBooking.dto.TicketRequest;
import com.CloudBees.TrainTicketBooking.models.Ticket;

import com.CloudBees.TrainTicketBooking.models.UserT;
import com.CloudBees.TrainTicketBooking.repository.TicketRepository;
import com.CloudBees.TrainTicketBooking.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void testPurchaseTicket_Success() {
        TicketRequest request = new TicketRequest("John", "Doe", "john.doe@example.com", "NYC", "LA");

        UserT user = new UserT(1L, "John", "Doe", "john.doe@example.com");
        Ticket ticket = new Ticket(1L, "NYC", "LA", 100.0, user);

        when(userRepository.save(any(UserT.class))).thenReturn(user);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket result = ticketService.purchaseTicket(request);

        assertNotNull(result);
        assertEquals(ticket.getId(), result.getId());
        assertEquals(ticket.getFromLocation(), result.getFromLocation());
        assertEquals(ticket.getToLocation(), result.getToLocation());
        assertEquals(ticket.getPrice(), result.getPrice());
        assertEquals(ticket.getUserT().getId(), result.getUserT().getId());
    }

    @Test
    void testGetTicket_Success() {
        Long ticketId = 1L;
        UserT user = new UserT(1L, "John", "Doe", "john.doe@example.com");
        Ticket ticket = new Ticket(1L, "NYC", "LA", 100.0, user);

        when(ticketRepository.findById(ticketId)).thenReturn(java.util.Optional.of(ticket));

        Ticket result = ticketService.getTicket(ticketId);

        assertNotNull(result);
        assertEquals(ticket.getId(), result.getId());
        assertEquals(ticket.getFromLocation(), result.getFromLocation());
        assertEquals(ticket.getToLocation(), result.getToLocation());
        assertEquals(ticket.getPrice(), result.getPrice());
        assertEquals(ticket.getUserT().getId(), result.getUserT().getId());
    }

//    @Test
//    void testRemoveUser_Success() {
//        Long userId = 1L;
//
//        ticketService.RemoveUser(userId);
//
//        verify(ticketRepository, times(1)).deleteById(userId);
//    }
}
