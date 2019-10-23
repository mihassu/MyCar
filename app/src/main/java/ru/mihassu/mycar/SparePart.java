package ru.mihassu.mycar;

import java.util.ArrayList;
import java.util.List;

public class SparePart {

    private String name;
    private List<String> changeDates;
    private List<Integer> mileages;

    public SparePart(String name) {
        this.name = name;
        this.changeDates = new ArrayList<>();
        this.mileages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getChangeDates() {
        return changeDates;
    }

    public List<Integer> getMileages() {
        return mileages;
    }

    public void addNewChange(String changeDate, int mileage){
        this.changeDates.add(changeDate);
        this.mileages.add(mileage);
    }

    public String getLastChangeDate() {
        if (changeDates.size() != 0) {
            return changeDates.get(changeDates.size() - 1);
        }
        return "No changes";
    }

    public int getLastMileage() {
        if (mileages.size() != 0) {
            return mileages.get(mileages.size() - 1);
        }
        return 0;
    }

}
