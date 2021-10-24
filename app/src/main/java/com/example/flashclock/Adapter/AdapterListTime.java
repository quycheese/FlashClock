package com.example.flashclock.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashclock.Model.ListTime;
import com.example.flashclock.R;

import java.util.List;

public class AdapterListTime extends RecyclerView.Adapter<AdapterListTime.ListTimeHolder> {



    private List<ListTime> mListTime;

    public AdapterListTime() { }

    public void setData(List<ListTime> list){
        this.mListTime = list;
        notifyDataSetChanged();
    }

    public AdapterListTime(List<ListTime> mListTime) {
        this.mListTime = mListTime;
    }

    @NonNull
    @Override
    public ListTimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_timer,parent, false);

        return new ListTimeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTimeHolder holder, int position) {

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
        TextView txtTime;

        public ListTimeHolder(@NonNull View itemView) {
            super(itemView);

            txtTime = itemView.findViewById(R.id.txt_time);
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
