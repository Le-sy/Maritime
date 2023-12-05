package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.adapter.MyAdapter;
import com.example.app.fragment.SquareFragment;
import com.example.app.fragment.MessageFragment;
import com.example.app.fragment.ProfileFragment;
import com.example.app.util.MyDatabaseHelper;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout myTabLayout;
    private ViewPager2 myPager2;

    List<String> titles = new ArrayList<>();
    public List<Fragment> fragments=new ArrayList<>();

    int[] icons = {R.drawable.message_gray_mf, R.drawable.square_gray_mf, R.drawable.personal_gray_mf};
    String[] tabs = {"消息", "广场", "个人信息"};


    private SquareFragment squareFragment;
    private MessageFragment messageFragment;
    private ProfileFragment profileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题
        setContentView(R.layout.activity_main);

        //导入数据库
        openDatabase();

        myTabLayout = findViewById(R.id.myTabLayout);
        myPager2 = findViewById(R.id.myPager2);


        //初始化导航栏
        titles.add("Tab 1");
        titles.add("Tab 2");
        titles.add("Tab 3");

        for (int i = 0; i < 2; i++) {
            myTabLayout.addTab(myTabLayout.newTab());
        }


        //初始化fragment
        InitFragment initFragment = new InitFragment();
        initFragment.init();
        squareFragment = initFragment.getPageSquare();
        messageFragment = initFragment.getPageMessage();
        profileFragment = initFragment.getPageProfile();

        //添加Fragment进去
        fragments.add(messageFragment);
        fragments.add(squareFragment);
        fragments.add(profileFragment);

        //传参
        Intent intent=getIntent();
        String account = intent.getStringExtra("account");
        String name = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        String sex = intent.getStringExtra("sex");

        // 向fragment传递数据
        Bundle bundle=new Bundle();
        bundle.putString("account",account);
        bundle.putString("username",name);
        bundle.putString("password",password);
        bundle.putString("sex",sex);

        profileFragment.setArguments(bundle);


        //实例化适配器,将tab和fragments联系起来
        MyAdapter myAdapter=new MyAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        //设置适配器
        myPager2.setAdapter(myAdapter);

        //TabLayout和Viewpager2进行关联
        new TabLayoutMediator(myTabLayout, myPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));
                tab.setCustomView(getViewAtI(position));
            }
        }).attach();
    }
    private View getViewAtI(int position){
        View view = getLayoutInflater().inflate(R.layout.bottom_layout, null, false);
        ImageView imageView = view.findViewById(R.id.icon);
        TextView textView = view.findViewById(R.id.text);
        // 这个icons就是一个简单的图片保存的数组，保存如R.drawable.touxiang
        imageView.setImageResource(icons[position]);
        textView.setText(tabs[position]);

        return view;
    }

    public void openDatabase(){
        MyDatabaseHelper myHelper = new MyDatabaseHelper(MainActivity.this);
        try {
            myHelper.CopyDBFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}