package ru.mihassu.mycar.data.entity;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import ru.mihassu.mycar.domain.model.SpDetails;

public class SpDetailsRealmData extends RealmObject {

    private String changeDate;
    private String mileage;


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

    public static SpDetails convertToEntity(SpDetailsRealmData item) {
        return new SpDetails(item.getChangeDate(), item.getMileage());
    }

    public static List<SpDetails> convertListToEntity(List<SpDetailsRealmData> itemList) {

        List<SpDetails> resultList = new ArrayList<>();
        for (SpDetailsRealmData item: itemList) {
            resultList.add(new SpDetails(item.getChangeDate(), item.getMileage()));
        }
        return resultList;
    }}
