import { DateTime } from "./DateTime";

export interface Reservation {
    id: Number,
    startTime: DateTime,
    endTime: DateTime,
    name: String
  }
export interface ReservationDTO {
    name: String,
    startTime: String;
    endTime: String;
    password:String;
  }