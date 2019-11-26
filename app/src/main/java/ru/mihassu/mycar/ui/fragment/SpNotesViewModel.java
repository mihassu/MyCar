package ru.mihassu.mycar.ui.fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import ru.mihassu.mycar.domain.model.SpDetails;
import ru.mihassu.mycar.domain.repository.SparePartsRepository;

public class SpNotesViewModel extends ViewModel /*implements LifecycleObserver*/ {

    private SparePartsRepository spRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MutableLiveData<List<SpDetails>> sparePartLiveData =
            new MutableLiveData<>();


    public SpNotesViewModel(SparePartsRepository spRepository) {
        this.spRepository = spRepository;
    }


//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void getSpNotesByName(String name) {
        compositeDisposable.add(spRepository.getSparePartNotes(name)
                .subscribe(result -> sparePartLiveData.setValue(result),
                        throwable -> System.out.println("Ошибка получения данных!!!!" + throwable)));
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
