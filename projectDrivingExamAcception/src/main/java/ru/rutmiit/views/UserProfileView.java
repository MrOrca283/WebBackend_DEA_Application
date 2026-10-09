package ru.rutmiit.views;

import ru.rutmiit.dto.ShowDetailedExamInfoDto;

import java.util.List;

public class UserProfileView {
    private String username;

    private String email;

    private String fullName;

    private String passportSerAndNum;

    private List<ShowDetailedExamInfoDto> userExams;

    public UserProfileView() {
    }

    public UserProfileView(String username, String email, String fullName, String passportSerAndNum, List<ShowDetailedExamInfoDto> userExams) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.passportSerAndNum=passportSerAndNum;
        this.userExams=userExams;
    }

    public List<ShowDetailedExamInfoDto> getUserExams(){return userExams;}
    public void setUserExams(List<ShowDetailedExamInfoDto> userExams) {
        this.userExams = userExams;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassportSerAndNum() {return passportSerAndNum;}

    public void setPassportSerAndNum(String passportSerAndNum) {this.passportSerAndNum = passportSerAndNum;}
}
