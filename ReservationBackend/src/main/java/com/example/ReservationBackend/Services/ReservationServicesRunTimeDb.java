package com.example.ReservationBackend.Services;

import com.example.ReservationBackend.Database.RunTimeDatabase;
import com.example.ReservationBackend.Models.DateTime;
import com.example.ReservationBackend.Models.Reservation;
import com.example.ReservationBackend.Models.ReservationDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class ReservationServicesRunTimeDb implements ReservationServices{
    @Override
    public List<Reservation> getReservations() {
        return RunTimeDatabase.reservationList;
    }

    @Override
    public List<Reservation> getWeekReservations() {
        DateTime today=DateTime.getToday();
        DateTime WeekdateTime =DateTime.addDaysFromToday(8);
        return RunTimeDatabase.reservationList.stream().filter(
                r ->r.startTime.before(WeekdateTime)&&r.startTime.after(today)
        ).toList();
    }

    @Override
    public Reservation addReservation(ReservationDTO reservation) {

        DateTime startTemp=new DateTime(reservation.startTime);
        DateTime endTemp=new DateTime(reservation.endTime);
        List<Reservation>f=RunTimeDatabase.reservationList.stream().filter(
                r ->
                        (
                            (startTemp.before(r.startTime)&&endTemp.after(r.startTime)&&endTemp.before(r.endTime))||
                            (startTemp.after(r.startTime)&&endTemp.before(r.endTime)&&endTemp.after(r.endTime))||
                            (startTemp.equals(r.startTime)&&endTemp.equals(r.endTime))||
                            (startTemp.before(r.startTime)&&endTemp.after(r.startTime))
                        )
        ).toList();
        //System.out.println(reservation.password);
        if(f.size()>0){
            return null;
        }
        if( startTemp.after(endTemp)){
            return null;
        }
        long diff =   DateTime.convertToDate(endTemp).getTime() -DateTime.convertToDate(startTemp) .getTime();//as given

        long Hours = TimeUnit.MILLISECONDS.toHours(diff);
        //System.out.println(Hours);
        if(Hours<0){
            return null;
        }
        if(Hours>2){
            if(!Objects.equals(reservation.password, RunTimeDatabase.password)){
                return null;
            }
        }
        int lastindex=0;
        if(RunTimeDatabase.reservationList.size()!=0)
           lastindex=RunTimeDatabase.reservationList.get(RunTimeDatabase.reservationList.size()-1).id;
        Reservation newReservation=new Reservation(lastindex+1,reservation);
        RunTimeDatabase.reservationList.add(newReservation);
        return newReservation;
    }

    @Override
    public Reservation deleteReservation(Integer id) {
        int index=-1;
        for (int i = 0; i < RunTimeDatabase.reservationList.size(); i++)
            if (RunTimeDatabase.reservationList.get(i).id == id)
            {
                index= i;
                break;
            }
        Reservation deleted=null;
        if(index!=-1){
            deleted=RunTimeDatabase.reservationList.get(index);
            RunTimeDatabase.reservationList.remove(index);
        }
        return deleted;

    }
}
