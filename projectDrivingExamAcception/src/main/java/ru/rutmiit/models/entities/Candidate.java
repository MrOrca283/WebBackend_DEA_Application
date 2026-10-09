package ru.rutmiit.models.entities;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Candidates")
public class Candidate {

    @Id
    @Column(nullable = false,unique = true)
    private String applicationId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private boolean theoryPassed;


    @Column(nullable = false)
    private String contact;

    @Column(unique = true,nullable = false)
    private String passportSeriesAndNumber;

    @Column(nullable = false)
    private String licenceType;


    public Candidate() {}


    public String getFullName() {return fullName;}
    public void setFullName(String fullName) {this.fullName=fullName;}


    public String getPassportSeriesAndNumber() {return passportSeriesAndNumber;}
    public void setPassportSeriesAndNumber(String passportSeriesAndNumber) {this.passportSeriesAndNumber=passportSeriesAndNumber;}

    public String getContact() {return contact;}
    public void setContact(String contact) {this.contact = contact;}

    public String getLicenceType() {return licenceType;}
    public void setLicenceType(String licenceType) {this.licenceType=licenceType;}

    public String getApplicationId() {return applicationId;}
    public void setApplicationId(String applicationId) {this.applicationId=applicationId;}

    public boolean isTheoryPassed() {
        return theoryPassed;
    }

    public void setTheoryPassed(boolean theoryPassed) {
        this.theoryPassed = theoryPassed;
    }
}
