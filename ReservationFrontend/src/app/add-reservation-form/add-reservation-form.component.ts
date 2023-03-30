import { Component, Input } from '@angular/core';
import axios from 'axios';
import { ReservationDTO } from 'src/models/Reservation';

@Component({

  selector: 'app-add-reservation-form',
  templateUrl: './add-reservation-form.component.html',
  styleUrls: ['./add-reservation-form.component.css']
})
export class AddReservationFormComponent {

  name = '';
  startTime = '';
  endTime = '';
  password = '';
  message='';
  matDialogClose=false;
  async addReservation() {
    //30/3/2023 10:00 Am
    let reservationDTO: ReservationDTO = {
      name: this.name,
      startTime: this.startTime,
      endTime: this.endTime,
      password:this.password
    };
    axios.post("http://localhost:8080/addReservation", reservationDTO).then((data) => {
        console.log(data);
        window.location.reload()

      }).catch((err) => {
        console.log(err)
      })
  }
  isValed() {
    let regex=/(\d{2}|\d{1})\/(\d{2}|\d{1})\/\d{4} (\d{2}|\d{1}):(\d{2}|\d{1}) (Am|Pm)/
    if(!regex.test(this.startTime)||!regex.test(this.endTime)){
      
      this.message="the time input not match dd/mm/yyyy hh:mm Am"
      return false;
    }
    let startDatetimeArr: Array<string> = this.startTime.split(' ');
    let startDateArr: Array<string> = startDatetimeArr[0].split('/');
    startDateArr[0] = startDateArr[0].length == 1 ? ("0" + startDateArr[0]) : startDateArr[0]
    startDateArr[1] = startDateArr[1].length == 1 ? ("0" + startDateArr[1]) : startDateArr[1]
    let startTimeArr: Array<string> = startDatetimeArr[1].split(':');
    startTimeArr[1] = startTimeArr[1].length == 1 ? ("0" + startTimeArr[1]) : startTimeArr[1]
    let startAmPm: string = startDatetimeArr[2];
    let startHour: string = startAmPm == "Pm" ? (parseInt(startTimeArr[0]) + 12).toString() : parseInt(startTimeArr[0]).toString();
    startHour = startHour.length == 1 ? ("0" + startHour) : startHour


    let endDatetimeArr: Array<string> = this.endTime.split(' ');
    let endDateArr: Array<string> = endDatetimeArr[0].split('/');
    endDateArr[0] = endDateArr[0].length == 1 ? ("0" + endDateArr[0]) : endDateArr[0]
    endDateArr[1] = endDateArr[1].length == 1 ? ("0" + endDateArr[1]) : endDateArr[1]
    let endTimeArr: Array<string> = endDatetimeArr[1].split(':');
    endTimeArr[1] = endTimeArr[1].length == 1 ? ("0" + endTimeArr[1]) : endTimeArr[1]
    let endAmPm: string = endDatetimeArr[2];
    let endHour: string = endAmPm == "Pm" ? (parseInt(endTimeArr[0]) + 12).toString() : parseInt(endTimeArr[0]).toString();
    endHour = endHour.length == 1 ? ("0" + endHour) : endHour

    var eventStartTime = new Date(startDateArr[2] + "-" + startDateArr[1] + "-" + startDateArr[0] + "T" + startHour + ":" + startTimeArr[1] + ":00")
    var eventEndTime = new Date(endDateArr[2] + "-" + endDateArr[1] + "-" + endDateArr[0] + "T" + endHour + ":" + endTimeArr[1] + ":00")
    // console.log("start: " + eventStartTime + "/" + startDateArr[2] + "-" + startDateArr[1] + "-" + startDateArr[0] + "T" + startHour + ":" + startTimeArr[1] + ":00");

    // console.log("duration: " + eventEndTime + "/" + endDateArr[2] + "-" + endDateArr[1] + "-" + endDateArr[0] + "T" + endHour + ":" + endTimeArr[1] + ":00");
    var duration = (eventEndTime.getTime() - eventStartTime.getTime()) / 36e5; // safe to use

    console.log("duration: " + duration);
    if(duration<0){
      this.message="the end date you set is before the start date"
      return false;
    }
    if (duration > 2) {
      if(this.password!=''){
        this.message='';
        return true;
      }
      this.message="the time is more then 2 Hours Please insert the password"
      return false;
    }
    this.message='';
    return true;
  }
}

