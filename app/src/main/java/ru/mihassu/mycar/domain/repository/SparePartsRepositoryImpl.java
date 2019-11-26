package ru.mihassu.mycar.domain.repository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmResults;
import ru.mihassu.mycar.domain.model.SpDetails;
import ru.mihassu.mycar.data.entity.SpDetailsRealmData;
import ru.mihassu.mycar.data.entity.SparePartRealmData;

public class SparePartsRepositoryImpl implements SparePartsRepository {


    @Override
    public Single<List<SparePartRealmData>> getSpareParts() {

        return Single.create(emitter -> {

            try (Realm realm = Realm.getDefaultInstance()) {

                final RealmResults<SparePartRealmData> results = realm
                        .where(SparePartRealmData.class).findAll();

                List<SparePartRealmData> items = realm.copyFromRealm(results);

                emitter.onSuccess(items);
            }

        });
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable addNewSparePart(String name, String date, String mileage) {

        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                try (Realm realm = Realm.getDefaultInstance()) {

                    realm.beginTransaction();

                    SparePartRealmData newSparePart = realm.createObject(SparePartRealmData.class);
                    newSparePart.setName(name);

                    SpDetailsRealmData newDetails = realm.createObject(SpDetailsRealmData.class);
                    newDetails.setChangeDate(date);
                    newDetails.setMileage(mileage);

                    newSparePart.getSpDetails().add(newDetails);

                    realm.commitTransaction();
                    emitter.onComplete();
                }
            }
        });
    }

    @Override
    public Single<List<SpDetails>> getSparePartNotes(String name) {

        return Single.create(emitter -> {

            try (Realm realm = Realm.getDefaultInstance()) {

                final SparePartRealmData result = realm
                        .where(SparePartRealmData.class).equalTo("name", name).findFirst();

                List<SpDetails> items = SpDetailsRealmData.convertListToEntity(result.getSpDetails());

                emitter.onSuccess(items);
            }
        });
    }

    @Override
    public Completable addNewNote(String name, String newDate, String newMileage) {

        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                try (Realm realm = Realm.getDefaultInstance()) {

                    realm.beginTransaction();

                    final SparePartRealmData result = realm
                            .where(SparePartRealmData.class).equalTo("name", name).findFirst();

                    SpDetailsRealmData newDetails = realm.createObject(SpDetailsRealmData.class);
                    newDetails.setChangeDate(newDate);
                    newDetails.setMileage(newMileage);

                    result.getSpDetails().add(newDetails);

                    realm.commitTransaction();
                    emitter.onComplete();
                }
            }
        });
    }

    @Override
    public Completable clearBase() {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                try (Realm realm = Realm.getDefaultInstance()) {

                    realm.beginTransaction();
                    realm.deleteAll();
                    realm.commitTransaction();
                    emitter.onComplete();
                }
            }
        });
    }
}
