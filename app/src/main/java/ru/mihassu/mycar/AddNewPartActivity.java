package ru.mihassu.mycar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class AddNewPartActivity extends AppCompatActivity {

    public static final String PART_NAME = "partName";
    public static final String CHANGE_DATE = "changeDate";
    public static final String MILEAGE = "mileage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_part);

        final EditText partName = findViewById(R.id.edit_part_name);
        final EditText changeDate = findViewById(R.id.edit_change_date);
        final EditText mileage = findViewById(R.id.edit_mileage);

        Button buttonAddPart = findViewById(R.id.button_add_part);
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


}
