package ru.mihassu.mycar.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import ru.mihassu.mycar.R;


public class AddNewPartActivity extends AppCompatActivity {

    public static final String PART_NAME = "partName";
    public static final String CHANGE_DATE = "changeDate";
    public static final String MILEAGE = "mileage";

    private EditText partName;
    private EditText changeDate;
    private EditText mileage;
    private Button buttonAddPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_part);


        //кнопка Назад/ Ее id: android.R.id.home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        partName = findViewById(R.id.edit_part_name);
        changeDate = findViewById(R.id.edit_change_date);
        mileage = findViewById(R.id.edit_mileage);



//        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                if (!emitter.isDisposed()) {
//                    emitter.onNext(partName.getText().toString());
//                }
//
//                if (!emitter.isDisposed()) {
//                    emitter.onNext(changeDate.getText().toString());
//                }
//
//                if (!emitter.isDisposed()) {
//                    emitter.onNext(mileage.getText().toString());
//                }
//
//                emitter.onComplete();
//            }
//        });

        buttonAddPart = findViewById(R.id.button_add_part);
        buttonAddPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(PART_NAME, partName.getText().toString());
                intent.putExtra(CHANGE_DATE, changeDate.getText().toString());
                intent.putExtra(MILEAGE, mileage.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
