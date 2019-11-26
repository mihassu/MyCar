package ru.mihassu.mycar.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;

import ru.mihassu.mycar.R;
import ru.mihassu.mycar.domain.repository.SparePartsRepository;
import ru.mihassu.mycar.domain.repository.SparePartsRepositoryImpl;

import ru.mihassu.mycar.ui.MainViewModelFactory;
import ru.mihassu.mycar.ui.di.ActivityComponent;
import ru.mihassu.mycar.ui.di.ActivityModule;
import ru.mihassu.mycar.ui.di.DaggerActivityComponent;
import ru.mihassu.mycar.ui.fragment.SpNotesFragment;
import ru.mihassu.mycar.ui.fragment.SparePartFragment;
//import ru.mihassu.mycar.ui.MainContract;
//import ru.mihassu.mycar.ui.MainPresenter;

import static ru.mihassu.mycar.ui.activity.AddNewPartActivity.CHANGE_DATE;
import static ru.mihassu.mycar.ui.activity.AddNewPartActivity.MILEAGE;
import static ru.mihassu.mycar.ui.activity.AddNewPartActivity.PART_NAME;
import static ru.mihassu.mycar.ui.activity.AddNewChangeActivity.NEW_CHANGE_DATE;
import static ru.mihassu.mycar.ui.activity.AddNewChangeActivity.NEW_MILEAGE;



public class MainActivity extends AppCompatActivity implements MyListener
        /*implements MainContract.View*/ {

    public static final int REQUEST_NEW_PART = 1;
    public static final int REQUEST_NEW_CHANGE = 2;

    public static final String CHANGE_PART_NAME = "changePartName";
    public static final String CHANGE_PART = "changePart";
    private static final String TAG = "MainActivity";

    private Toolbar toolbar;
    private FloatingActionButton fab;
    @Inject
    FragmentManager fragmentManager;
    private MainActivityViewModel mainViewModel;
    public static ActivityComponent activityComponent;


//    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActivityComponent();
        initToolbar();
        initFab();
        replaceSparePartFragment();
        initViewModel();

        mainViewModel.sparePartLiveData.observe(this, data -> {
            Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
            replaceSparePartFragment();
        });

//        presenter = new MainPresenter();
//        presenter.attachView(this);
    }

    private void initActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build();
        activityComponent.inject(this);
    }

    //показать фрагмент SparePartFragment
    @Override
    public void replaceSparePartFragment() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container_main, SparePartFragment.newInstance());
        ft.commit();
    }

    @Override
    public void replaceSpNotesFragment(String name) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container_main,
                SpNotesFragment.newInstance(name));
        ft.addToBackStack("");
        ft.commit();
    }

    private void initFab() {
        fab = findViewById(R.id.fab);
        fab.setOnClickListener((v) -> showAddNewPartActivity());
    }
    private void showAddNewPartActivity() {
        Intent intent = new Intent(MainActivity.this, AddNewPartActivity.class);
        startActivityForResult(intent, REQUEST_NEW_PART);
    }

    @Override
    public void showAddNewChangeActivity(String name) {
        Intent intent = new Intent(this, AddNewChangeActivity.class);
        intent.putExtra(PART_NAME, name);
        startActivityForResult(intent, REQUEST_NEW_CHANGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_NEW_PART){
            if(resultCode == RESULT_OK){
                addNewPart(data);
            }

        } else if (requestCode == REQUEST_NEW_CHANGE){
            if(resultCode == RESULT_OK){
                addNewChange(data);
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void addNewPart(@Nullable Intent data) {
        try {
            if (data != null) {
//                        presenter.add(data.getStringExtra(PART_NAME));
//                        presenter.addNote(data.getStringExtra(PART_NAME),
//                                data.getStringExtra(CHANGE_DATE),
//                                        Integer.parseInt(data.getStringExtra(MILEAGE)));

                mainViewModel.addNewSparePart(
                        data.getStringExtra(PART_NAME),
                        data.getStringExtra(CHANGE_DATE),
                        data.getStringExtra(MILEAGE));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void addNewChange(@Nullable Intent data) {
        mainViewModel.addNewNote(data.getStringExtra(PART_NAME),
                data.getStringExtra(NEW_CHANGE_DATE),
                data.getStringExtra(NEW_MILEAGE));
    }


    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //кнопка Назад/ Ее id: android.R.id.home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initViewModel() {
//        mainViewModel = ViewModelProviders.of(this)
//                .get(MainActivityViewModel.class);
        SparePartsRepository sparePartsRepository= new SparePartsRepositoryImpl();
        mainViewModel = ViewModelProviders.of(this,
                new MainViewModelFactory(sparePartsRepository)).get(MainActivityViewModel.class);
//        getLifecycle().addObserver(mainViewModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.action_delete_all:
                mainViewModel.clearBase();

            case android.R.id.home:
                fragmentManager.popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //    @Override
//    public void notifyAdapter() {
//        adapter.notifyDataSetChanged();
//    }

    @Override
    protected void onDestroy() {
//        presenter.detachView();
        super.onDestroy();
    }
}
