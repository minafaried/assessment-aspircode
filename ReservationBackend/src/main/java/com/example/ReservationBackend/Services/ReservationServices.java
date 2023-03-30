package com.example.ReservationBackend.Services;

import com.example.ReservationBackend.Database.RunTimeDatabase;
import com.example.ReservationBackend.Models.Reservation;
import com.example.ReservationBackend.Models.ReservationDTO;

import java.util.List;

public interface ReservationServices {

    public List<Reservation>getReservations();
    public List<Reservation>getWeekReservations();
    public Reservation addReservation(ReservationDTO reservation);
    public Reservation deleteReservation(Integer  id);
}
