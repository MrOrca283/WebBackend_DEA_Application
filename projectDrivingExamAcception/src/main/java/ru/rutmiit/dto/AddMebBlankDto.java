package ru.rutmiit.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class AddMebBlankDto {

    private String blankCode; //MOS_GOV-CLINIC7777_123456789012 КЛЮЧ
    private String passportSeriesAndNumber;
    private LocalDate dateOfHealthCheck;
    private String drivingCategory;
    private String nervSystemDiagnosis;
    private String eyeCheckDiagnosis;
    private String psyhicConditionDiagnosis;
    private boolean acceptedOnDrivingCategory;


    @Size(min=1,max=10,message="Внести только 10 символов")
    public String getDrivingCategory(){return drivingCategory;}
    public void setDrivingCategory(String drivingCategory) {this.drivingCategory=drivingCategory;}

    @Size(min=10,max=30,message="Код бланка не может быть короче 10 и длинее 30 символов")
    public String getBlankCode() {return blankCode;}
    public void setBlankCode(String blankCode) {this.blankCode=blankCode;}
    
    @NotNull(message="Дата мед. освед. не может быть пустой")
    @PastOrPresent(message="Дата мед. освед. не может быть в будущем")
    public LocalDate getDateOfHealthCheck() {return dateOfHealthCheck;}
    public void setDateOfHealthCheck(LocalDate dateOfHealthCheck) {this.dateOfHealthCheck=dateOfHealthCheck;}

    public String getPassportSeriesAndNumber() {return passportSeriesAndNumber;}
    public void setPassportSeriesAndNumber(String passportSeriesAndNumber) {this.passportSeriesAndNumber = passportSeriesAndNumber;}

    @NotEmpty()
    public String getNervSystemDiagnosis() {return nervSystemDiagnosis;}
    public void setNervSystemDiagnosis(String nervSystemDiagnosis) {this.nervSystemDiagnosis=nervSystemDiagnosis;}

    @NotEmpty()
    public String getEyeCheckDiagnosis() {return eyeCheckDiagnosis;}
    public void setEyeCheckDiagnosis(String eyeCheckDiagnosis) {this.eyeCheckDiagnosis=eyeCheckDiagnosis;}

    @NotEmpty()
    public String getPsyhicConditionDiagnosis() {return psyhicConditionDiagnosis;}
    public void setPsyhicConditionDiagnosis(String psyhicConditionDiagnosis) {this.psyhicConditionDiagnosis=psyhicConditionDiagnosis;}

    public boolean isAcceptedOnDrivingCategory() {return acceptedOnDrivingCategory;}
    public void setAcceptedOnDrivingCategory(boolean acceptedOnDrivingCategory) {this.acceptedOnDrivingCategory = acceptedOnDrivingCategory;}

    

/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Candidate candidate;
*/

    




}
