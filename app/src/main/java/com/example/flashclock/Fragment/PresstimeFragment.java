package com.example.flashclock.Fragment;

import static android.widget.Toast.makeText;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.flashclock.Activity.CreateTimeActivity;
import com.example.flashclock.R;


public class PresstimeFragment extends Fragment {

    private Button btnStop, btnStart,btnPause;
    private  TextView txtPressTime;
    private  long lStartTime, lPauseTime, lSystemTime = 0L;
    private long lUpdateTime,secs, mins, milliseconds ;
    Handler handler = new Handler();
    boolean isRun;
    private View view;


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_start) {
                if (isRun)
                    return;
                isRun = true;
                lStartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                btnStop.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.INVISIBLE);

            }
            if (view.getId() == R.id.btn_stop) {
                if(!isRun){
                    return;
                }else {
                    isRun = false;
                    lPauseTime += lSystemTime;
                    handler.removeCallbacks(runnable);
                }
                btnStop.setVisibility(View.INVISIBLE);
                btnStart.setVisibility(View.VISIBLE);

            }
            if (view.getId() == R.id.btn_reset) {

                lSystemTime  = 0L ;
                lStartTime = 0L ;
                secs = 0L ;
                lUpdateTime  = 0L ;
                secs = 0 ;
                mins = 0 ;
                milliseconds= 0 ;

                txtPressTime.setText("00:00:00");

            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_presstime, container, false);

        onStart();
        PressTimeControll();
        return view;

    }

    private void PressTimeControll() {

        btnStart = view.findViewById(R.id.btn_start);
        btnStart.setOnClickListener(onClickListener);

        btnStop = view.findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(onClickListener);

        btnPause = view.findViewById(R.id.btn_reset);
        btnPause.setOnClickListener(onClickListener);

        txtPressTime = view.findViewById(R.id.txt_presstime);
    }


    Runnable runnable = new Runnable() {
            @Override
            public void run() {
                lSystemTime = SystemClock.uptimeMillis() - lStartTime;
                 lUpdateTime = lPauseTime + lSystemTime;
                 secs = lUpdateTime/1000;
                 mins= secs/60;
                secs = secs %60;
                milliseconds = lUpdateTime%1000;
                txtPressTime.setText(""+mins+":" + String.format("%02d",secs) + ":" + String.format("%03d",milliseconds));
                handler.postDelayed(this,0);
            }
        };
}
