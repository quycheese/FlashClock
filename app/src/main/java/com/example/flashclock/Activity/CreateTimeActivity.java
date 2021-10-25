package com.example.flashclock.Activity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flashclock.Adapter.AdapterListTime;
import com.example.flashclock.Fragment.TimerFragment;
import com.example.flashclock.Model.ListTime;
import com.example.flashclock.R;
import com.example.flashclock.SQLite.MyDatabaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class CreateTimeActivity extends AppCompatActivity {

    private int hourChoice, minuteChoice;
    private EditText edtTimer;
    private EditText edtNotice;
    private Button btnSave;
    private String timeAlarm;


    String[] items = {"Luôn Thứ Hai", "Luôn Thứ Ba", "Luôn Thứ Tư", "Luôn Thứ Năm", "Luôn Thứ Sáu", "Luôn Thứ Bảy", "Luôn Chủ Nhật", "Luôn Luôn"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_time);
        btnSave = findViewById(R.id.btn_save);
        edtNotice = findViewById(R.id.edt_notice);
        addTime();
        autoComplete();
        saveAlarm();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addTime() {
        edtTimer = findViewById(R.id.edt_timer);
        edtTimer.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    CreateTimeActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    (timePicker, hourOfday, minute) -> {
                        hourChoice = hourOfday;
                        minuteChoice = minute;
                        String timeHourMinute = hourChoice + ":" + minuteChoice;
                        SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                        try {
                            Date date = f24Hours.parse(timeHourMinute);
                            SimpleDateFormat f12Hours = new SimpleDateFormat("HH:mm aa");
                            timeAlarm = f12Hours.format(date);
                            edtTimer.setText(timeAlarm);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }, LocalDateTime.now().getHour(),LocalDateTime.now().getHour(),false
            );
            timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
            timePickerDialog.updateTime(hourChoice,minuteChoice);
            timePickerDialog.show();
        });
    }

    public void saveAlarm() {
        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(v -> {
            if (edtTimer == null || edtTimer.equals("")) {
                Toast.makeText(CreateTimeActivity.this, "Please select time before save!", Toast.LENGTH_SHORT).show();
            } else {
                MyDatabaseHelper myDB = new MyDatabaseHelper (CreateTimeActivity.this);
                myDB.btnSaveTime(edtTimer.getText().toString().trim(), edtNotice.getText().toString());
                Intent intent = new Intent(this, TimerFragment.class);
                startActivity(intent);
            }
        });
    }

    private void autoComplete (){
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        adapterItems = new ArrayAdapter<String>(this, R.layout.menuday_dropdown,items);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(),""+item,Toast.LENGTH_SHORT).show();
            }
        });
    }

}