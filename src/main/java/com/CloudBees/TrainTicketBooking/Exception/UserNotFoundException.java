package com.CloudBees.TrainTicketBooking.Exception;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String message)
    {
        super((message));
    }
}
