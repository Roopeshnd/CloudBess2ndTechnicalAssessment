package com.CloudBees.TrainTicketBooking.repository;

import com.CloudBees.TrainTicketBooking.models.UserT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserT,Long>
{

}
