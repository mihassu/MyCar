package ru.mihassu.mycar.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mihassu.mycar.domain.repository.SparePartsRepository;
import ru.mihassu.mycar.domain.repository.SparePartsRepositoryImpl;
import ru.mihassu.mycar.ui.activity.MainActivityViewModel;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private SparePartsRepository spRepository;

    public MainViewModelFactory(SparePartsRepository spRepository) {
        this.spRepository = spRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == MainActivityViewModel.class) {
            return (T) new MainActivityViewModel(spRepository);
        }

        return null;
    }
}
