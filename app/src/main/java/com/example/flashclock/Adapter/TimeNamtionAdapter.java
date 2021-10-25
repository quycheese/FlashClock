package com.example.flashclock.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.flashclock.Model.TimeNation;
import com.example.flashclock.R;

import java.util.List;

public class TimeNamtionAdapter extends BaseAdapter {

    private List<TimeNation> list;

    public TimeNamtionAdapter(List<TimeNation> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View item ;
        if(view == null){
            item = View.inflate(viewGroup.getContext(), R.layout.item_timenation,null);
        } else {
            item = view;
        }

        TimeNation timeNation = (TimeNation) getItem(i);
        TextView txt_GTM = item.findViewById(R.id.txt_GTM);
        TextView txt_Location = item.findViewById(R.id.txt_Location);
        TextView txt_Time= item.findViewById(R.id.txt_Time);
        txt_GTM.setText(String.format(timeNation.getGTM()));
        txt_Location.setText(String.format(timeNation.getLocation()));
        txt_Time.setText(String.format(timeNation.getTime()));

        return item;
    }
}
