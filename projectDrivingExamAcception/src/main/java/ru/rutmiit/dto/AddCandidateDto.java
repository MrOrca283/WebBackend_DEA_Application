package ru.rutmiit.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddCandidateDto {



    private String fullName;
    private String applicationId;//ключ
    private String passportSeriesAndNumber;
    private String contact;
    private String licenceType;
    private boolean theoryPassed;

    @NotEmpty(message="Полное имя не должно быть пустым")
    @Size(min=10,max=50,message="Полное имя не может быть короче 10 и длинее 50 символов")
    public String getFullName() {return fullName;}
    public void setFullName(String fullName) {this.fullName=fullName;}

    @NotEmpty(message="Поле номер заявления не должно быть пустым")
    @Size(max=50,message="Номер заявления не может иметь более 50ти символов")
    public String getApplicationId() {return applicationId;}
    public void setApplicationId(String applicationId) {this.applicationId=applicationId;}

    @NotEmpty()
    @Size(max=30,message="Контактные данные должны содержать не более 30 символов без пробелов")
    public String getContact() {return contact;}
    public void setContact(String contact) {this.contact = contact;}

    @NotEmpty()
    @Size(min=10,max=10,message="Серия и номер паспорта должны содержать 10 символов без пробелов")
    public String getPassportSeriesAndNumber() {return passportSeriesAndNumber;}
    public void setPassportSeriesAndNumber(String passportSeriesAndNumber) {this.passportSeriesAndNumber=passportSeriesAndNumber;}

    @NotEmpty()
    @Size(min=1,max=10,message="Типы получаемых за экзамен лицензий не могут превышать 10 символов")
    public String getLicenceType() {return licenceType;}
    public void setLicenceType(String licenceType) {this.licenceType=licenceType;}

    @NotNull(message="Поле не должно быть не заполненным")
    public boolean isTheoryPassed() {return theoryPassed;}
    public void setTheoryPassed(boolean theoryPassed) {this.theoryPassed=theoryPassed;}



}
