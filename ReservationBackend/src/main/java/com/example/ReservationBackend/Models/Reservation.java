package com.example.ReservationBackend.Models;


public class Reservation {
    public int id;
    public DateTime startTime;
    public DateTime endTime;
    public String name;

    public Reservation(int index,ReservationDTO reservationDTO) {
        this.id=index;
        this.startTime=new DateTime(reservationDTO.startTime);
        this.endTime=new DateTime(reservationDTO.endTime);
        this.name = reservationDTO.name;
    }

}
