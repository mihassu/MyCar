package ru.mihassu.mycar.ui.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mihassu.mycar.domain.repository.SparePartsRepository;
import ru.mihassu.mycar.ui.activity.MainActivityViewModel;

public class SparePartViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private SparePartsRepository spRepository;

    public SparePartViewModelFactory(SparePartsRepository spRepository) {
        this.spRepository = spRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == SparePartViewModel.class) {
            return (T) new SparePartViewModel(spRepository);
        }

        if (modelClass == SpNotesViewModel.class) {
            return (T) new SpNotesViewModel(spRepository);
        }

        return null;
    }
}
