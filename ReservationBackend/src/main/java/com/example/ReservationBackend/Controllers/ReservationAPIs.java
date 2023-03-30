package com.example.ReservationBackend.Controllers;

import com.example.ReservationBackend.Models.Reservation;
import com.example.ReservationBackend.Models.ReservationDTO;
import com.example.ReservationBackend.Services.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ReservationAPIs {
    @Autowired
    ReservationServices reservationServices;
    @GetMapping("/getReservations")
    public List<Reservation> getReservations(){
        return reservationServices.getReservations();
    }
    @GetMapping("/getWeekReservations")
    public List<Reservation> getWeekReservations(){
        return reservationServices.getWeekReservations();
    }
    @PostMapping("/addReservation")
    public Reservation addReservation(@RequestBody ReservationDTO reservation){
        return reservationServices.addReservation(reservation);
    }
    @DeleteMapping("/deleteReservation/{id}")
    public Reservation deleteReservation(@PathVariable Integer id){
        return reservationServices.deleteReservation(id);
    }

}
