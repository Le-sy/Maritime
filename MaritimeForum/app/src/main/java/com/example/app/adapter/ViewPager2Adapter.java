package com.example.app.adapter;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.annotation.NonNull;

import com.example.app.fragment.Page1Fragment;
import com.example.app.fragment.Page2Fragment;
class ViewPager2Adapter extends FragmentStateAdapter{

    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // 每个页面对应的fragment
        switch (position){
            // 这里我随便写了两个Fragment【改】
            case 0:return new Page1Fragment();
            case 1:return new Page2Fragment();
        }

        return null;
    }

    @Override
    public int getItemCount() {
        // 有几个页面就返回几
        return 2;
    }
}
