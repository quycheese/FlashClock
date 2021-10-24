package com.example.flashclock;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashclock.Adapter.AdapterListTime;

public class RecyclerViewItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private ItemTouchHelperListener listener;
    public RecyclerViewItemTouchHelper(int dragDirs, int swipeDirs, ItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        if (listener != null){
            listener.onSwiped((viewHolder));
        }
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null){
            View foreGround = ((AdapterListTime.ListTimeHolder)viewHolder).layoutForeGround;
        getDefaultUIUtil().onSelected(foreGround);
        }
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foreGround = ((AdapterListTime.ListTimeHolder)viewHolder).layoutForeGround;
        getDefaultUIUtil().onDrawOver(c, recyclerView, foreGround, dX,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foreGround = ((AdapterListTime.ListTimeHolder)viewHolder).layoutForeGround;
        getDefaultUIUtil().onDraw(c, recyclerView, foreGround, dX,dY,actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        View foreGround = ((AdapterListTime.ListTimeHolder)viewHolder).layoutForeGround;
        getDefaultUIUtil().clearView(foreGround);
    }
}
