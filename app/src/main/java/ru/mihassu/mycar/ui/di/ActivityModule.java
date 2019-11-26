package ru.mihassu.mycar.ui.di;


import android.app.Activity;

import androidx.core.app.ComponentActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {

    private FragmentActivity context;

    public ActivityModule(FragmentActivity context) {
        this.context = context;
    }

    @ActivityScope
    @Provides
    FragmentActivity provideActivity() {
        return context;
    }

    @ActivityScope
    @Provides
    FragmentManager provideFragmentManager(FragmentActivity context){
        return context.getSupportFragmentManager();
    }


}
