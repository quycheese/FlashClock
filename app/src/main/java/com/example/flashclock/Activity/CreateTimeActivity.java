package com.example.flashclock.Activity;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashclock.Adapter.AdapterListTime;
import com.example.flashclock.Model.ListTime;
import com.example.flashclock.R;
import com.example.flashclock.SQLite.MyDatabaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CreateTimeActivity extends AppCompatActivity {

    private int Hour, Minute;
    private EditText edtTimer;
    private EditText edtNotice;
    private Button bntSave;

    private MyDatabaseHelper mbDB;

    private AdapterListTime adapterListTime;
    private List<ListTime> mListTime;


    String[] items = {"Luôn Thứ Hai", "Luôn Thứ Ba", "Luôn Thứ Tư", "Luôn Thứ Năm", "Luôn Thứ Sáu", "Luôn Thứ Bảy", "Luôn Chủ Nhật", "Luôn Luôn"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_time);
        addTime();
        AutoComplete();
        initUi();
//        SaTime ();

    }

    public void addTime() {

        edtTimer = findViewById(R.id.edt_timer);
        edtTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        CreateTimeActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfday, int minute) {
                                Hour = hourOfday;
                                Minute = minute;
                                String timeHourMinute = Hour + ":" + Minute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24Hours.parse(timeHourMinute);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("HH:mm aa");
                                    edtTimer.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
                timePickerDialog.updateTime(Hour,Minute);
                timePickerDialog.show();
            }
        });
    }

    private void AutoComplete (){
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

    //BTN_SAVE
    private void initUi (){
       edtTimer = findViewById(R.id.edt_timer);
       edtNotice = findViewById(R.id.edt_notice);

       bntSave = findViewById(R.id.btn_save);
       bntSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               MyDatabaseHelper myDB = new MyDatabaseHelper (CreateTimeActivity.this);
               myDB.btnSaveTime(
                       Integer.valueOf((edtTimer.getText().toString().trim())),
                       edtNotice.getText().toString().trim());
           }
       });
    }

}