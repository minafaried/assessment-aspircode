import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import axios from 'axios';
import { AddReservationFormComponent } from './add-reservation-form/add-reservation-form.component';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{


  constructor(public dialog: MatDialog) {}
  title = 'Room Reservation';

  openDialog() {
    const dialogRef = this.dialog.open(AddReservationFormComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}