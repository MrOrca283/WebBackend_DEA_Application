package ru.rutmiit.dto;

public class ShowDetailedCandidateInfoDto {
    private String fullName;
    private String applicationId; //ключ
    private String passportSeriesAndNumber;
    private String contact;
    private String licenceType;
    private boolean theoryPassed;

    public String getFullName() {return fullName;}
    public void setFullName(String firstName) {this.fullName=firstName;}

    public String getApplicationId() {return applicationId;}
    public void setApplicationId(String applicationId) {this.applicationId=applicationId;}

    public String getContact() {return contact;}
    public void setContact(String contact) {this.contact = contact;}

    public String getPassportSeriesAndNumber() {return passportSeriesAndNumber;}
    public void setPassportSeriesAndNumber(String passportSeriesAndNumber) {this.passportSeriesAndNumber=passportSeriesAndNumber;}

    public String getLicenceType() {return licenceType;}
    public void setLicenceType(String licenceType) {this.licenceType=licenceType;}

    public boolean isTheoryPassed() {return theoryPassed;}
    public void setTheoryPassed(boolean theoryPassed) {this.theoryPassed=theoryPassed;}

}
