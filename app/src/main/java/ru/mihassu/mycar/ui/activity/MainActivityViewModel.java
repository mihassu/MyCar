package ru.mihassu.mycar.ui.activity;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.mihassu.mycar.data.entity.SparePartRealmData;
import ru.mihassu.mycar.domain.repository.SparePartsRepository;
import ru.mihassu.mycar.domain.repository.SparePartsRepositoryImpl;

public class MainActivityViewModel extends ViewModel /*implements LifecycleObserver*/ {

    private SparePartsRepository spRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MutableLiveData<String> sparePartLiveData =
            new MutableLiveData<>();


    public MainActivityViewModel(SparePartsRepository spRepository) {
        this.spRepository = spRepository;
    }

    public void addNewSparePart(String name, String date, String mileage) {
        Disposable addPart = spRepository.addNewSparePart(name, date, mileage)
                .subscribe(() -> sparePartLiveData.setValue("Добавлено"),
                throwable -> sparePartLiveData.setValue("Ошибка при добавлении"));

        compositeDisposable.add(addPart);

    }

    public void clearBase() {
        Disposable clear = spRepository.clearBase()
                .subscribe(() -> System.out.println("Очищено"),
                        throwable -> System.out.println("Ошибка"));

        compositeDisposable.add(clear);
    }

    public void addNewNote(String name, String date, String mileage) {
        Disposable addNote = spRepository.addNewNote(name, date, mileage)
                .subscribe(() -> sparePartLiveData.setValue("Добавлено"),
                        throwable -> {sparePartLiveData.setValue("Ошибка при добавлении");
                            System.out.println("\n\n Ошибка: " + throwable);});

        compositeDisposable.add(addNote);

    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    public void getSparePartsList() {
//        compositeDisposable.add(spRepository.getSpareParts()
//                .subscribe(result -> sparePartLiveData.setValue(result),
//                        throwable -> System.out.println(throwable)));
//    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }

}
