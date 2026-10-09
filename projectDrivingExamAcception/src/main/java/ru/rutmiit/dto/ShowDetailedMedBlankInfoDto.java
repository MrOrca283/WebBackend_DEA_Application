package ru.rutmiit.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ShowDetailedMedBlankInfoDto {

    private String blankCode; //MOS_GOV-CLINIC7777_123456789012
    private String passportSeriesAndNumber;
    private LocalDate dateOfHealthCheck;
    private String drivingCategory;
    private String nervSystemDiagnosis;
    private String eyeCheckDiagnosis;
    private String psyhicConditionDiagnosis;
    private boolean acceptedOnDrivingCategory;


    public String getDrivingCategory() {return drivingCategory;}
    public void setDrivingCategory(String drivingCategory) {this.drivingCategory = drivingCategory;}

    public String getBlankCode() {return blankCode;}
    public void setBlankCode(String blankCode) {this.blankCode=blankCode;}

    public LocalDate getDateOfHealthCheck() {return dateOfHealthCheck;}
    public void setDateOfHealthCheck(LocalDate dateOfHealthCheck) {this.dateOfHealthCheck=dateOfHealthCheck;}

    public String getPassportSeriesAndNumber() {return passportSeriesAndNumber;}
    public void setPassportSeriesAndNumber(String passportSeriesAndNumber) {this.passportSeriesAndNumber = passportSeriesAndNumber;}

    public String getNervSystemDiagnosis() {return nervSystemDiagnosis;}
    public void setNervSystemDiagnosis(String nervSystemDiagnosis) {this.nervSystemDiagnosis=nervSystemDiagnosis;}

    public String getEyeCheckDiagnosis() {return eyeCheckDiagnosis;}
    public void setEyeCheckDiagnosis(String eyeCheckDiagnosis) {this.eyeCheckDiagnosis=eyeCheckDiagnosis;}

    public String getPsyhicConditionDiagnosis() {return psyhicConditionDiagnosis;}
    public void setPsyhicConditionDiagnosis(String psyhicConditionDiagnosis) {this.psyhicConditionDiagnosis=psyhicConditionDiagnosis;}

    public boolean isAcceptedOnDrivingCategory() {return acceptedOnDrivingCategory;}
    public void setAcceptedOnDrivingCategory(boolean acceptedOnDrivingCategory) {this.acceptedOnDrivingCategory = acceptedOnDrivingCategory;}


}
