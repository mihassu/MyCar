package ru.mihassu.mycar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static ru.mihassu.mycar.AddNewPartActivity.PART_NAME;
import static ru.mihassu.mycar.AddNewPartActivity.CHANGE_DATE;
import static ru.mihassu.mycar.AddNewPartActivity.MILEAGE;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    public static final int REQUEST_ACCES_TYPE = 1;

    private RecyclerView recyclerView;
    private ItemCardAdapter adapter;
    private MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initFab();
        presenter = new MainPresenter();
        presenter.attachView(this);
        initRecyclerView();
    }

    private void initFab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent = new Intent(MainActivity.this, AddNewPartActivity.class);
                startActivityForResult(intent, REQUEST_ACCES_TYPE);
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_ACCES_TYPE){
            if(resultCode == RESULT_OK){
                try {
                    presenter.add(data.getStringExtra(PART_NAME));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.mRecyclerView);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }

        LinearLayoutManager layoutLinear = new LinearLayoutManager(this);
        layoutLinear.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutLinear);

        //создать адаптер
//        adapter = new ItemCardAdapter(createDataList());
        adapter = new ItemCardAdapter(presenter.getSpKeeper().getSpareParts());
        recyclerView.setAdapter(adapter);

        //Разделитель
//        recyclerView.addItemDecoration(new ItemDivider(getActivity()));
    }

    private List<SparePart> createDataList() {

        List<SparePart> data = new ArrayList<>();

        SparePart oil = new SparePart("Масло");
        oil.addNewChange("12.10.2019", 86000);

        SparePart brakeShoe = new SparePart("Колодки");
        brakeShoe.addNewChange("24.04.2019", 83000);

        data.add(oil);
        data.add(brakeShoe);

        return data;
    }

    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
