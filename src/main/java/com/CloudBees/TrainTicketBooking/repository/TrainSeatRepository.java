package com.CloudBees.TrainTicketBooking.repository;

import com.CloudBees.TrainTicketBooking.models.TrainSeat;
import com.CloudBees.TrainTicketBooking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainSeatRepository extends JpaRepository<TrainSeat,Long>
{
    boolean existsBySectionAndSeatNumber(String section, int seatNumber);
    List<TrainSeat> findBySection(String section);


    List<TrainSeat> findBySectionOrderBySeatNumberAsc(String section);

}
