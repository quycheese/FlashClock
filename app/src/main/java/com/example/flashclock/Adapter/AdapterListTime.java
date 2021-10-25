package com.example.flashclock.Adapter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashclock.Fragment.TimerFragment;
import com.example.flashclock.Model.ListTime;
import com.example.flashclock.R;
import com.example.flashclock.Receiver.AlarmReceiver;

import java.util.Calendar;
import java.util.List;

public class AdapterListTime extends RecyclerView.Adapter<AdapterListTime.ListTimeHolder> {

    private Calendar calendar = Calendar.getInstance();
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimerFragment fragment;
    private List<ListTime> mListTime;

    public AdapterListTime() { }

    public void setData(List<ListTime> list){
        this.mListTime = list;
        notifyDataSetChanged();
    }

    public AdapterListTime(List<ListTime> mListTime, TimerFragment fragment) {
        this.mListTime = mListTime;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ListTimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_timer,parent, false);
        return new ListTimeHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ListTimeHolder holder, int position) {

//        Intent intent = new Intent(fragment.getContext(), AlarmReceiver.class);
        ListTime listTime = mListTime.get(position);
        String timeAlarm = listTime.getTime();
        int hour = Integer.parseInt(timeAlarm.split(":")[0]);
        int minute = Integer.parseInt(timeAlarm.split(":")[1].substring(0, 2));
        holder.aSwitch.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if (isChecked) {
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);
                        Intent intent = new Intent(buttonView.getContext(), AlarmReceiver.class);
                        alarmManager = (AlarmManager) buttonView.getContext().getSystemService(Context.ALARM_SERVICE);
                        pendingIntent = PendingIntent.getBroadcast(buttonView.getContext(), 24444, intent, 0);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    } else {
                        alarmManager.cancel(pendingIntent);
                    }
                });

        ListTime time =mListTime.get(position);
        if (time == null){
            return;
        }

        holder.txtTime.setText(time.getTime());
        holder.txtNotice.setText(time.getNotice());
    }

    @Override
    public int getItemCount() {
        if (mListTime != null){
            return mListTime.size();
        }
        return 0;
    }

    public class ListTimeHolder extends RecyclerView.ViewHolder {

        public View layoutForeGround;
        private TextView txtNotice;
        private TextView txtTime;
        private Switch aSwitch;

        public ListTimeHolder(@NonNull View itemView) {
            super(itemView);

            txtTime = itemView.findViewById(R.id.txt_time);
            aSwitch = itemView.findViewById(R.id.switchTimeId);
            layoutForeGround = itemView.findViewById(R.id.layout_foreground);

            txtTime = itemView.findViewById(R.id.txt_time);
            txtNotice = itemView.findViewById(R.id.txt_note);
        }
    }
    public  void  removeItem (int index){
        mListTime.remove(index);
        notifyItemRemoved(index);

    }
    public void undoItem (ListTime listTime, int index){
        mListTime.add(index, listTime);
        notifyItemInserted(index);
    }
}
