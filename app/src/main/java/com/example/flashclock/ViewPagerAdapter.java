package com.example.flashclock;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.flashclock.Fragment.PresstimeFragment;
import com.example.flashclock.Fragment.TimenationFragment;
import com.example.flashclock.Fragment.TimerFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TimerFragment();

            case 1:
                return new TimenationFragment();

            case 2:
                return new PresstimeFragment();

            default:
                return new TimerFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
