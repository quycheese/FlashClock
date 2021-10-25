package com.example.flashclock.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashclock.Adapter.TimeNamtionAdapter;
import com.example.flashclock.Model.TimeNation;
import com.example.flashclock.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TimenationFragment extends Fragment {


    private List<TimeNation> list;
    private ListView listView;
    private TimeNamtionAdapter timeNamtionAdapter;
    private View view;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timenation,container, false);
        addTimeNation();
        return view;
    }

    public void addTimeNation() {

//        listView = (ListView) view.findViewById(R.id.rcv_ListTime_Nation);
//
//        list = new ArrayList<>();
//        list.add(new TimeNation("GTM + 7G", "Tp. Ben Tre","18:30"));
//        list.add(new TimeNation("GTM + 7G", "Tp. Can Tho","18:30"));
//        list.add(new TimeNation("GTM + 7G", "Tp. My Tho","18:30"));
//        list.add(new TimeNation("GTM + 7G", "Tp. Tan An","18:30"));
//
//        timeNamtionAdapter = new TimeNamtionAdapter(list);
//        listView.setAdapter(timeNamtionAdapter);
    }
}

