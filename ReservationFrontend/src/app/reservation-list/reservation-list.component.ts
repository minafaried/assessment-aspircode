import { Component, OnInit } from '@angular/core';
import axios from 'axios';
import { Reservation } from 'src/models/Reservation';
@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
  
  public reservationList: Array<Reservation> = [ ];
  ngOnInit(): void {
    axios.get("http://localhost:8080/getWeekReservations").then((d)=>{
      this.reservationList=d.data
      console.log(this.reservationList);
    }).catch((err)=>{
      console.log(err)
    })
  }
  deleteReservation(id:Number){
    axios.delete("http://localhost:8080/deleteReservation/"+id,).then((data)=>{
      console.log(data);
      this.reservationList=this.reservationList.filter(r=>r.id!=id)
    }).catch((err)=>{
      console.log(err)
    })
  }
}