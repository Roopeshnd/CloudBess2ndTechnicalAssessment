package com.CloudBees.TrainTicketBooking.controller;

import com.CloudBees.TrainTicketBooking.dto.TicketRequest;
import com.CloudBees.TrainTicketBooking.models.Ticket;
import com.CloudBees.TrainTicketBooking.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController)
                .build();
    }

//    @Test
//    void testPurchaseTicket_Success() throws Exception {
//        TicketRequest request = new TicketRequest("John", "Doe", "john.doe@example.com", "New York", "Boston");
//        Ticket ticket = new Ticket(1L, "New York", "Boston", 100.0, null);
//
//        when(ticketService.purchaseTicket(any(TicketRequest.class))).thenReturn(ticket);
//
//        mockMvc.perform(post("/api/tickets/purchase")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"john.doe@example.com\", \"fromLocation\": \"New York\", \"toLocation\": \"Boston\" }"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{ \"id\": 1, \"fromLocation\": \"New York\", \"toLocation\": \"Boston\", \"price\": 100.0 }"));
//    }
//
//    @Test
//    void testGetTicket_Success() throws Exception {
//        Ticket ticket = new Ticket(1L, "New York", "Boston", 100.0, null);
//
//        when(ticketService.getTicket(1L)).thenReturn(ticket);
//
//        mockMvc.perform(get("/api/tickets/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{ \"id\": 1, \"fromLocation\": \"New York\", \"toLocation\": \"Boston\", \"price\": 100.0 }"));
//    }

    @Test
    void testRemoveUserFromTrain_Success() throws Exception {
        mockMvc.perform(delete("/api/tickets/removeUser/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("removed user Successfully"));
    }
}
