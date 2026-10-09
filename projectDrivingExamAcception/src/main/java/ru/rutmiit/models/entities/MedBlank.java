package ru.rutmiit.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "MedBlanks")
public class MedBlank {

    @Id
    @Column(nullable = false)
    private String blankCode;

    @Column(unique = true,nullable = false)
    private String passportSeriesAndNumber;

    @Column(nullable = false)
    private String drivingCategory;

    @Column(nullable = false)
    private LocalDate dateOfHealthCheck;

    @Column(nullable = false)
    private String nervSystemDiagnosis;

    @Column(nullable = false)
    private String eyeCheckDiagnosis;

    @Column(nullable = false)
    private String psyhicConditionDiagnosis;
    
    @Column(nullable = false)
    private boolean acceptedOnDrivingCategory;
    
    

/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Candidate candidate;
*/


    public String getPassportSeriesAndNumber() {return passportSeriesAndNumber;}
    public void setPassportSeriesAndNumber(String passportSeriesAndNumber) {this.passportSeriesAndNumber=passportSeriesAndNumber;}


    public String getBlankCode() {return blankCode;}
    public void setBlankCode(String blankCode) {this.blankCode=blankCode;}


    public String getDrivingCategory() {return drivingCategory;}
    public void setDrivingCategory(String drivingCategory) {this.drivingCategory=drivingCategory;}


    public LocalDate getDateOfHealthCheck() {return dateOfHealthCheck;}
    public void setDateOfHealthCheck(LocalDate dateOfHealthCheck) {this.dateOfHealthCheck=dateOfHealthCheck;}


    public String getNervSystemDiagnosis() {return nervSystemDiagnosis;}
    public void setNervSystemDiagnosis(String nervSystemDiagnosis) {this.nervSystemDiagnosis=nervSystemDiagnosis;}


    public String getEyeCheckDiagnosis() {return eyeCheckDiagnosis;}
    public void setEyeCheckDiagnosis(String eyeCheckDiagnosis) {this.eyeCheckDiagnosis=eyeCheckDiagnosis;}


    public String getPsyhicConditionDiagnosis() {return psyhicConditionDiagnosis;}
    public void setPsyhicConditionDiagnosis(String psyhicConditionDiagnosis) {this.psyhicConditionDiagnosis=psyhicConditionDiagnosis;}


    public boolean isAcceptedOnDrivingCategory() {return acceptedOnDrivingCategory;}

    public void setAcceptedOnDrivingCategory(boolean acceptedOnDrivingCategory) {this.acceptedOnDrivingCategory = acceptedOnDrivingCategory;}
}
