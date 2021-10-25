package com.example.flashclock.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashclock.Activity.CreateTimeActivity;
import com.example.flashclock.Adapter.AdapterListTime;
import com.example.flashclock.ItemTouchHelperListener;
import com.example.flashclock.Model.ListTime;
import com.example.flashclock.R;
import com.example.flashclock.RecyclerViewItemTouchHelper;
import com.example.flashclock.SQLite.MyDatabaseHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class TimerFragment extends Fragment implements ItemTouchHelperListener {

    private View view;
    private RecyclerView rcvTime;
    private AdapterListTime adapter;
    private List<ListTime> timeList;
    private LinearLayout fragmentTimer;
    private TextView txtAdd;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timer, container, false);
        addControls();
        addRecycleView();
        return view;
    }

    private void addControls() {
        txtAdd = (TextView) view.findViewById(R.id.txt_add);
        txtAdd.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.txt_add){
                Intent intent = new Intent(getContext(), CreateTimeActivity.class);
                startActivity(intent);
            }
        }
    };



    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<ListTime> getTimeList(){
        List<ListTime> list = new ArrayList<>();
        MyDatabaseHelper myDB = new MyDatabaseHelper(getContext());
        list.addAll(myDB.selectAllAlarm());
        return list;
    }
    


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addRecycleView() {
        fragmentTimer = (LinearLayout) view.findViewById(R.id.fragment_timer);
        rcvTime = (RecyclerView) view.findViewById(R.id.rcv_listtime);
        rcvTime.setHasFixedSize(true);
        rcvTime.setLayoutManager(new LinearLayoutManager(view.getContext()));

        timeList = getTimeList();
        adapter = new AdapterListTime(timeList, this);
        rcvTime.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rcvTime.addItemDecoration(itemDecoration);

        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerViewItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(rcvTime);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {

        if(viewHolder instanceof AdapterListTime.ListTimeHolder) {
            String nameTimeDelete = timeList.get(viewHolder.getAdapterPosition()).getTime();

            ListTime timeDelete = timeList.get(viewHolder.getAdapterPosition());
            int indexDelete = viewHolder.getAdapterPosition();
            MyDatabaseHelper myDB = new MyDatabaseHelper (getContext());
            myDB.deleteAlarm(timeDelete.getId());
            //remove item
            adapter.removeItem(indexDelete);
            Snackbar snackbar = Snackbar.make(fragmentTimer, nameTimeDelete + " removed.!", Snackbar.LENGTH_LONG );
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    adapter.undoItem(timeDelete, indexDelete );
                    if (indexDelete == 0 || indexDelete == timeList.size() -1){
                        rcvTime.scrollToPosition(indexDelete);
                    }
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}