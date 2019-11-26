package ru.mihassu.mycar.domain.model;

public class SpDetails {

    private String changeDate;
    private String mileage;

    public SpDetails(String changeDate, String mileage) {
        this.changeDate = changeDate;
        this.mileage = mileage;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
}
