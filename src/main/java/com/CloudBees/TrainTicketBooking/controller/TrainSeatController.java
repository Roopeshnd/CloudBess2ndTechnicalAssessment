package com.CloudBees.TrainTicketBooking.controller;

import com.CloudBees.TrainTicketBooking.Exception.SeatAlreadyBookedException;
import com.CloudBees.TrainTicketBooking.models.TrainSeat;
import com.CloudBees.TrainTicketBooking.service.TrainSeatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seats")
@Validated
public class TrainSeatController
{
    @Autowired
    private TrainSeatService trainSeatService;

    @GetMapping("/section/{section}")
    public List<TrainSeat> getSeatsBySection(@PathVariable String section) {
        return trainSeatService.getSeatsBySection(section);
    }

    @PostMapping("/allocate")
    public TrainSeat allocateSeat(@RequestBody TrainSeat seat) {
        return trainSeatService.allocateSeat(seat.getSection(), seat.getSeatNumber(), seat.getUserT());
    }





    @DeleteMapping("/remove/{seatId}")
    public String  removeUserFromTrain(@PathVariable Long seatId) {
        trainSeatService.removeUserFromTrain(seatId);
        return "Deleted Successfully";
    }

    @PutMapping("/modify/{seatId}")
    public TrainSeat modifySeat(@PathVariable Long seatId, @RequestParam int newSeatNumber) {
        return trainSeatService.modifySeat(seatId, newSeatNumber);
    }

    @GetMapping("/available")
    public ResponseEntity<Map<String, List<Integer>>> findAvailableSeats() {
        Map<String, List<Integer>> availableSeats = trainSeatService.findAvailableSeats();
        return ResponseEntity.ok(availableSeats);
    }

}
