package ru.mihassu.mycar.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.mihassu.mycar.R;

import static ru.mihassu.mycar.ui.activity.AddNewPartActivity.PART_NAME;


public class AddNewChangeActivity extends AppCompatActivity {

    public static final String NEW_CHANGE_DATE = "newChangeDate";
    public static final String NEW_MILEAGE = "newMileage";

    private String currentName;
    private EditText newChangeDate;
    private EditText newMileage;
    private Button buttonAddNewChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_change);

        //кнопка Назад/ Ее id: android.R.id.home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        newChangeDate = findViewById(R.id.edit_new_change_date);
        newMileage = findViewById(R.id.edit_new_mileage);

        currentName = getIntent().getStringExtra(PART_NAME);

        buttonAddNewChange = findViewById(R.id.button_add_new_change);
        buttonAddNewChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(PART_NAME, currentName);
                intent.putExtra(NEW_CHANGE_DATE, newChangeDate.getText().toString());
                intent.putExtra(NEW_MILEAGE, newMileage.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
