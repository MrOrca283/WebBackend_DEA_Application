package ru.rutmiit.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AddExamDto {

    private String examId;
    private String examSetBy;
    private LocalDate date;
    private LocalTime time;
    private String vehicleName;

    @CreatedDate
    private LocalDateTime createdAt;
    private String applicationId;




    public String getExamId(){return examId;}
    public void setExamId(String examId){this.examId=examId;}


    public String getExamSetBy() {return examSetBy;}
    public String setExamSetBy(String examSetBy) {return this.examSetBy=examSetBy;}

    public LocalDate getDate(){return date;}
    public void setDate(LocalDate date){this.date=date;}

    public LocalTime getTime(){return time;}
    public void setTime(LocalTime time){this.time=time;}

    public String getVehicleName(){return vehicleName;}
    public void setVehicleName(String vehicleName){this.vehicleName=vehicleName;}


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getApplicationId() {return applicationId;}
    public void setApplicationId(String applicationId){this.applicationId=applicationId;}



}
