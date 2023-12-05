package com.example.app.fragment;

import android.widget.ImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.app.R;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Fragment生命周期开始时初始化
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page5, container, false);

        // 设置加号的点击事件
        ImageView ivAdd = view.findViewById(R.id.ivAdd);
        ivAdd.setOnClickListener(v -> navigateToNewPage(AddPageActivity.class));

        // 设置箭头的点击事件
        view.findViewById(R.id.tvMyPosts).setOnClickListener(v -> navigateToNewPage(MyPostsActivity.class));
        view.findViewById(R.id.tvMyFavorites).setOnClickListener(v -> navigateToNewPage(MyFavoritesActivity.class));
        view.findViewById(R.id.ivArrowRight).setOnClickListener(v -> navigateToNewPage(DetailsActivity.class));

        return view;
    }

    private void navigateToNewPage(Class<?> activityClass) {
        Intent intent = new Intent(getActivity(), activityClass);
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 当包含Fragment的Activity完成onCreate()时调用
    }

    // 其他的生命周期方法...
}




