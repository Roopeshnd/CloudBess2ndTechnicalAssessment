package com.CloudBees.TrainTicketBooking.service;


import com.CloudBees.TrainTicketBooking.Exception.SeatAlreadyBookedException;
import com.CloudBees.TrainTicketBooking.models.TrainSeat;
import com.CloudBees.TrainTicketBooking.models.User;
import com.CloudBees.TrainTicketBooking.repository.TrainSeatRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
@Validated
public class TrainSeatService
{

    private static final int TOTAL_SEATS_PER_SECTION = 50;
    @Autowired
    private TrainSeatRepository trainSeatRepository;

    public TrainSeat allocateSeat(String section, int seatNumber,@Valid User user)
    {
        if(trainSeatRepository.existsBySectionAndSeatNumber(section,seatNumber))
        {
            throw new SeatAlreadyBookedException("Seat number " + seatNumber + " in section " + section + " is already booked.");
        }
        TrainSeat seat = new TrainSeat();
        seat.setSection(section);
        seat.setSeatNumber(seatNumber);
        seat.setUser(user);
        return trainSeatRepository.save(seat);
    }

    public List<TrainSeat> getSeatsBySection(String section) {
        return trainSeatRepository.findAll().stream()
                .filter(seat -> seat.getSection().equalsIgnoreCase(section))
                .toList();
    }

    public void removeUserFromTrain(Long seatId) {

        trainSeatRepository.deleteById(seatId);
    }

    public TrainSeat modifySeat(Long seatId, int newSeatNumber)
    {
        TrainSeat seat = trainSeatRepository.findById(seatId).orElseThrow(()-> new SeatAlreadyBookedException("Seat not found with id: " + seatId));

        if(trainSeatRepository.existsBySectionAndSeatNumber(seat.getSection(),newSeatNumber))
        {
            throw new SeatAlreadyBookedException("Seat number " + newSeatNumber + " in section " + seat.getSection() + " is already booked.");
        }

            seat.setSeatNumber(newSeatNumber);
            return trainSeatRepository.save(seat);
    }

//    // To find the available seats
    public int findNextAvailableSeat(String section) {
        List<TrainSeat> seats = trainSeatRepository.findBySectionOrderBySeatNumberAsc(section);
        int seatNumber = 1;

        for (TrainSeat seat : seats) {
            if (seat.getSeatNumber() != seatNumber) {
                break;
            }
            seatNumber++;
        }

        return seatNumber;
    }

    public Map<String, List<Integer>> findAvailableSeats()
    {
        Map<String, List<Integer>> availableSeats = new HashMap<>();

        List<TrainSeat> seatsA = trainSeatRepository.findBySectionOrderBySeatNumberAsc("A");
        List<TrainSeat> seatsB = trainSeatRepository.findBySectionOrderBySeatNumberAsc("B");

        availableSeats.put("A", getAvailableSeatNumbers(seatsA));
        availableSeats.put("B", getAvailableSeatNumbers(seatsB));

        return availableSeats;
    }

    private List<Integer> getAvailableSeatNumbers(List<TrainSeat> seats) {
        boolean[] occupied = new boolean[TOTAL_SEATS_PER_SECTION + 1]; // index 0 is not used
        for (TrainSeat seat : seats) {
            occupied[seat.getSeatNumber()] = true;
        }

        List<Integer> availableSeats = new ArrayList<>();
        for (int i = 1; i <= TOTAL_SEATS_PER_SECTION; i++) {
            if (!occupied[i]) {
                availableSeats.add(i);
            }
        }
        return availableSeats;
    }
}

