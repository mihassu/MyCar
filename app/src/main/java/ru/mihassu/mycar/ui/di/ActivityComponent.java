package ru.mihassu.mycar.ui.di;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import dagger.Component;
import ru.mihassu.mycar.ui.activity.MainActivity;

@ActivityScope
@Component (modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(MainActivity activity);

//    FragmentManager getFragmentManager();
//    FragmentActivity getMainActivity();
}
