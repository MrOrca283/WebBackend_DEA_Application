package ru.rutmiit.dto;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ShowExamInfoDto {

    private String examSetBy;
    private LocalDate date;
    private LocalTime time;
    private String vehicleName;




    public String getExamSetBy() {return examSetBy;}
    public String setExamSetBy(String examSetBy) {return this.examSetBy=examSetBy;}

    public LocalDate getDate(){return date;}
    public void setDate(LocalDate date){this.date=date;}

    public LocalTime getTime(){return time;}
    public void setTime(LocalTime time){this.time=time;}

    public String getVehicleName(){return vehicleName;}
    public void setVehicleName(String vehicleName){this.vehicleName=vehicleName;}


}
