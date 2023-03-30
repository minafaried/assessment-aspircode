package com.example.ReservationBackend.Models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class DateTime {
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    public String AMPM;//true Am false Pm

    public DateTime(int year, int month, int day, int hour, int minute,String AMPM) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.AMPM=AMPM;
    }
    public DateTime(String dataTime) {//"dd/mm/yyyy hh:mm Am" "dd/mm/yyyy hh:mm Pm"
        String[] startDateTimeArr= dataTime.split(" ");
        String[] startDateArr=startDateTimeArr[0].split("/");
        String[] startTimeArr=startDateTimeArr[1].split(":");
        this.year = Integer.parseInt( startDateArr[2]);
        this.month = Integer.parseInt( startDateArr[1]);
        this.day =  Integer.parseInt( startDateArr[0]);
        this.hour =  Integer.parseInt( startTimeArr[0]);
        this.minute = Integer.parseInt( startTimeArr[1]);
        this.AMPM=startDateTimeArr[2];
    }


    public boolean after(DateTime dateTime){
        String month=(this.month+"").length()==1?(this.month+"0"):this.month+"";
        String day=(this.day+"").length()==1?(this.day+"0"):this.day+"";
        String date1=""+this.year+""+month+""+day;

        month=(dateTime.month+"").length()==1?(dateTime.month+"0"):dateTime.month+"";
        day=(dateTime.day+"").length()==1?(dateTime.day+"0"):dateTime.day+"";
        String date2=""+dateTime.year+""+month+""+day;

        if(Integer.parseInt(date1)>Integer.parseInt(date2)){
            return true;
        }
        else if (Integer.parseInt(date1)<Integer.parseInt(date2)){
            return false;
        }
        else {
            if(Objects.equals(this.AMPM, "Pm") && Objects.equals(dateTime.AMPM, "Am")){
                return true;
            }
            else if(Objects.equals(this.AMPM, "Am") && Objects.equals(dateTime.AMPM, "Pm")){
                return false;
            }
            else {
                String time1=""+this.hour+this.minute;
                String time2=""+dateTime.hour+dateTime.minute;
                if(Integer.parseInt(time1)>=Integer.parseInt(time2)){
                    return true;
                }
                else {
                    return false;
                }

            }
        }
    }
    public boolean before(DateTime dateTime){
        return !this.after(dateTime);
    }

    public boolean equals(DateTime dateTime){
        return (this.day + "/" + this.month + "/" + this.year + " " + this.hour + ":" + this.minute + " " + this.AMPM)
                .equals(dateTime.day + "/" + dateTime.month + "/" + dateTime.year + " " + dateTime.hour + ":" + dateTime.minute + " " + dateTime.AMPM);
    }
    public boolean equalsDate(DateTime dateTime){
        return (this.day + "/" + this.month + "/" + this.year )
                .equals(dateTime.day + "/" + dateTime.month + "/" + dateTime.year);
    }
    public static DateTime getToday(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String[] startDateArr=dateFormat.format(date).split("/");
        return new DateTime(Integer.parseInt(startDateArr[0]),Integer.parseInt(startDateArr[1]),Integer.parseInt(startDateArr[2]),0,0,"Am");
    }
    public static DateTime addDaysFromToday(int days){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Using today's date
        c.add(Calendar.DATE, days); // Adding 5 days
        String[] startDateArr= sdf.format(c.getTime()).split("/");
        return new DateTime(Integer.parseInt(startDateArr[0]),Integer.parseInt(startDateArr[1]),Integer.parseInt(startDateArr[2]),0,0,"Am");
    }
    public static Date convertToDate(DateTime dateTime){
        String month=(dateTime.month+"").length()==1?("0"+dateTime.month):dateTime.month+"";
        String day=(dateTime.day+"").length()==1?("0"+dateTime.day):dateTime.day+"";
        int timeHour= dateTime.hour +(( dateTime.AMPM).equals("Pm") ?12:0);
        String hour=(timeHour+"").length()==1?("0"+timeHour):timeHour+"";
        String minute=(dateTime.minute+"").length()==1?("0"+dateTime.minute):dateTime.minute+"";

        String stringDate=dateTime.year+"/"+ month+"/"+day+" "+hour+":"+minute+":00";

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date=null;
        try {
            date = format.parse(stringDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
    @Override
    public String toString() {
        return day+"/"+month+"/"+year+" "+hour+":"+minute+" "+AMPM;
    }
}
