package ru.mihassu.mycar.ui.fragment;

import android.util.Log;

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

public class SparePartViewModel extends ViewModel /*implements LifecycleObserver*/ {

    private final String TAG = "SparePartViewModel";

    private SparePartsRepository spRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MutableLiveData<List<SparePartRealmData>> sparePartLiveData =
            new MutableLiveData<>();


    public SparePartViewModel(SparePartsRepository spRepository) {
        this.spRepository = spRepository;
    }


//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void getSparePartsList() {
        compositeDisposable.add(spRepository.getSpareParts()
                .subscribe(result -> {
                    sparePartLiveData.setValue(result);
//                    Log.w(TAG, "setValue()");
                        },
                        throwable -> System.out.println("Ошибка получения данных!!!!" + throwable)));

//        Log.w(TAG, "getSparePartsList()");
    }


    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
