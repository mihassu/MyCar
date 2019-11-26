package ru.mihassu.mycar.domain.repository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.mihassu.mycar.domain.model.SpDetails;
import ru.mihassu.mycar.data.entity.SparePartRealmData;

public interface SparePartsRepository {

    Single<List<SparePartRealmData>> getSpareParts();
    Completable addNewSparePart(String name, String date, String mileage);
    Single<List<SpDetails>> getSparePartNotes(String name);
    Completable addNewNote(String name, String newDate, String newMileage);
    Completable clearBase();
}
