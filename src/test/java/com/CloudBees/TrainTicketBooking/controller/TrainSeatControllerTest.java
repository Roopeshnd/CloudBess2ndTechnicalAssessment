package com.CloudBees.TrainTicketBooking.controller;

import com.CloudBees.TrainTicketBooking.models.TrainSeat;
import com.CloudBees.TrainTicketBooking.models.UserT;
import com.CloudBees.TrainTicketBooking.service.TrainSeatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(TrainSeatController.class)
public class TrainSeatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainSeatService trainSeatService;

//    @Test
//    void testGetSeatsBySection_Success() throws Exception {
//        String section = "A";
//        UserT user = new UserT(1L, "John", "Doe", "john.doe@example.com");
//        TrainSeat seat1 = new TrainSeat(1L, section, 1, user);
//        TrainSeat seat2 = new TrainSeat(2L, section, 2, user);
//        List<TrainSeat> seats = Arrays.asList(seat1, seat2);
//
//        when(trainSeatService.getSeatsBySection(section)).thenReturn(seats);
//
//        mockMvc.perform(get("/api/seats/section/{section}", section))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(seat1.getId().intValue())))
//                .andExpect(jsonPath("$[0].section", is(seat1.getSection())))
//                .andExpect(jsonPath("$[0].seatNumber", is(seat1.getSeatNumber())))
//                .andExpect(jsonPath("$[0].user.id", is(seat1.getUserT().getId().intValue())))
//                .andExpect(jsonPath("$[1].id", is(seat2.getId().intValue())))
//                .andExpect(jsonPath("$[1].section", is(seat2.getSection())))
//                .andExpect(jsonPath("$[1].seatNumber", is(seat2.getSeatNumber())))
//                .andExpect(jsonPath("$[1].user.id", is(seat2.getUserT().getId().intValue())));
//    }

//    @Test
//    void testAllocateSeat_Success() throws Exception {
//        UserT user = new UserT(1L, "John", "Doe", "john.doe@example.com");
//        TrainSeat seat = new TrainSeat(1L, "A", 1, user);
//
//        when(trainSeatService.allocateSeat(any(String.class), any(int.class), any(UserT.class))).thenReturn(seat);
//
//        mockMvc.perform(post("/api/seats/allocate")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(seat)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(seat.getId().intValue())))
//                .andExpect(jsonPath("$.section", is(seat.getSection())))
//                .andExpect(jsonPath("$.seatNumber", is(seat.getSeatNumber())))
//                .andExpect(jsonPath("$.user.id", is(seat.getUserT().getId().intValue())))
//                .andExpect(jsonPath("$.user.firstName", is(seat.getUserT().getFirstName())))
//                .andExpect(jsonPath("$.user.lastName", is(seat.getUserT().getLastName())))
//                .andExpect(jsonPath("$.user.email", is(seat.getUserT().getEmail())));
//    }

    @Test
    void testRemoveUserFromTrain_Success() throws Exception {
        Long seatId = 1L;

        mockMvc.perform(delete("/api/seats/remove/{seatId}", seatId))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Successfully"));

        Mockito.verify(trainSeatService, Mockito.times(1)).removeUserFromTrain(seatId);
    }

//    @Test
//    void testModifySeat_Success() throws Exception {
//        Long seatId = 1L;
//        int newSeatNumber = 2;
//        UserT user = new UserT(1L, "John", "Doe", "john.doe@example.com");
//        TrainSeat seat = new TrainSeat(seatId, "A", newSeatNumber, user);
//
//        when(trainSeatService.modifySeat(seatId, newSeatNumber)).thenReturn(seat);
//
//        mockMvc.perform(put("/api/seats/modify/{seatId}", seatId)
//                        .param("newSeatNumber", String.valueOf(newSeatNumber)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(seat.getId().intValue())))
//                .andExpect(jsonPath("$.section", is(seat.getSection())))
//                .andExpect(jsonPath("$.seatNumber", is(seat.getSeatNumber())))
//                .andExpect(jsonPath("$.user.id", is(seat.getUserT().getId().intValue())));
//    }

    @Test
    void testFindAvailableSeats_Success() throws Exception {
        Map<String, List<Integer>> availableSeats = Map.of("A", Arrays.asList(1, 2, 3), "B", Arrays.asList(1, 2));

        when(trainSeatService.findAvailableSeats()).thenReturn(availableSeats);

        mockMvc.perform(get("/api/seats/available"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.A", hasSize(3)))
                .andExpect(jsonPath("$.B", hasSize(2)));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
