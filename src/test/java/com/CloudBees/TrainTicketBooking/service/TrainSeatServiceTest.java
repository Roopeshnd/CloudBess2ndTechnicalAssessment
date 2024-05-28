package com.CloudBees.TrainTicketBooking.service;

import com.CloudBees.TrainTicketBooking.Exception.SeatAlreadyBookedException;
import com.CloudBees.TrainTicketBooking.models.TrainSeat;
import com.CloudBees.TrainTicketBooking.models.UserT;
import com.CloudBees.TrainTicketBooking.repository.TrainSeatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainSeatServiceTest {

    @Mock
    private TrainSeatRepository trainSeatRepository;

    @InjectMocks
    private TrainSeatService trainSeatService;



    @Test
    void testAllocateSeat_Success() {
        String section = "A";
        int seatNumber = 1;
        UserT user = new UserT(1L, "John", "Doe", "john.doe@example.com");
        TrainSeat seat = new TrainSeat(1L, section, seatNumber, user);

        when(trainSeatRepository.existsBySectionAndSeatNumber(section, seatNumber)).thenReturn(false);
        when(trainSeatRepository.save(any(TrainSeat.class))).thenReturn(seat);

        TrainSeat result = trainSeatService.allocateSeat(section, seatNumber, user);

        assertNotNull(result);
        assertEquals(seat.getId(), result.getId());
        assertEquals(seat.getSection(), result.getSection());
        assertEquals(seat.getSeatNumber(), result.getSeatNumber());
        assertEquals(seat.getUserT().getId(), result.getUserT().getId());
    }

    @Test
    void testAllocateSeat_SeatAlreadyBooked() {
        String section = "A";
        int seatNumber = 1;
        UserT user = new UserT(1L, "John", "Doe", "john.doe@example.com");

        when(trainSeatRepository.existsBySectionAndSeatNumber(section, seatNumber)).thenReturn(true);

        assertThrows(SeatAlreadyBookedException.class, () -> trainSeatService.allocateSeat(section, seatNumber, user));
    }

    @Test
    void testRemoveUserFromTrain_Success() {
        Long seatId = 1L;

        trainSeatService.removeUserFromTrain(seatId);

        verify(trainSeatRepository, times(1)).deleteById(seatId);
    }

    @Test
    void testModifySeat_Success() {
        Long seatId = 1L;
        int newSeatNumber = 2;
        String section = "A";
        UserT user = new UserT(1L, "John", "Doe", "john.doe@example.com");
        TrainSeat seat = new TrainSeat(seatId, section, newSeatNumber, user);

        when(trainSeatRepository.findById(seatId)).thenReturn(java.util.Optional.of(seat));
        when(trainSeatRepository.save(any(TrainSeat.class))).thenReturn(seat);

        TrainSeat result = trainSeatService.modifySeat(seatId, newSeatNumber);

        assertNotNull(result);
        assertEquals(seat.getId(), result.getId());
        assertEquals(seat.getSection(), result.getSection());
        assertEquals(seat.getSeatNumber(), result.getSeatNumber());
    }


}
