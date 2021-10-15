package com.example.flashclock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTimer extends Fragment {
    private View view;

    private int Hour, Minute;
    private  TextView txtTimer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_create_timer, container, false);

        txtTimer = (TextView) view.findViewById(R.id.txt_timer);
        txtTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        CreateTimer.this.getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfday, int minute) {
                                Hour = hourOfday;
                                Minute = minute;
                                String time = Hour + ":" + Minute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("HH:mm aa");
                                    txtTimer.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(Hour,Minute);
                timePickerDialog.show();
            }
        });
        return view;
    }
}

