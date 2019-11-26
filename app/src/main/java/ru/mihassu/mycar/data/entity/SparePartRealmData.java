package ru.mihassu.mycar.data.entity;

import io.realm.RealmList;
import io.realm.RealmObject;

public class SparePartRealmData extends RealmObject {


    private String name;
    private RealmList<SpDetailsRealmData> spDetails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<SpDetailsRealmData> getSpDetails() {
        return spDetails;
    }

    public void setSpDetails(RealmList<SpDetailsRealmData> spDetails) {
        this.spDetails = spDetails;
    }

    public SpDetailsRealmData getLastSpNote() {
        if (spDetails.size() != 0) {
            return spDetails.get(spDetails.size() - 1);
        } else {
            return null;
        }
    }
}
