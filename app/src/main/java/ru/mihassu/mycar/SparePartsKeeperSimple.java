package ru.mihassu.mycar;

import java.util.ArrayList;
import java.util.List;

public class SparePartsKeeperSimple implements SparePartsKeeper {

    private List<SparePart> spareParts;

    //Конструктор
    public SparePartsKeeperSimple() {
        spareParts = new ArrayList<>();
    }

    //Геттер на массив данных
    public List<SparePart> getSpareParts() {
        return spareParts;
    }

    //Дата последней замены
    @Override
    public String getChangeDateByName(String name) {
        for (SparePart s: spareParts) {
            if (s.getName().equals(name)) {
                return s.getLastChangeDate();
            }
        }
        return "";
    }

    //Пробег
    @Override
    public int getMileageByName(String name) {
        for (SparePart s: spareParts) {
            if (s.getName().equals(name)) {
                return s.getLastMileage();
            }
        }
        return 0;
    }

    //Создать новую заменяемую часть
    @Override
    public void createNewSparePart(String name) {
        SparePart newPart = new SparePart(name);
        spareParts.add(newPart);

    }

    //Создать запись о замене
    @Override
    public void createNewChangeEvent(String name, String newChangeDate, int newMileage) {
        for (SparePart s: spareParts) {
            if (s.getName().equals(name)) {
                s.addNewChange(newChangeDate, newMileage);
            }
        }
    }
}
