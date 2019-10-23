package ru.mihassu.mycar;

import java.util.List;

public interface SparePartsKeeper {
    String getChangeDateByName(String name);
    int getMileageByName(String name);
    void createNewSparePart(String name);
    void createNewChangeEvent(String name, String newChangeDate, int newMileage);
    List<SparePart> getSpareParts();

}
