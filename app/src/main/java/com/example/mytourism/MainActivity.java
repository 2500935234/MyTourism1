package com.example.mytourism;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener  {
    private ViewPager myViewPager;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    private RadioButton radio4;
    private RadioGroup radio;
    private List<Fragment> fragmentList;
    private long exitTime = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTap();


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("你确定要退出吗？");
            alertDialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertDialog.setPositiveButton("取消", null);
            alertDialog.setTitle("提示");
            alertDialog.show();
        } else {
            finish();
            System.exit(0);
        }
    }


    private void initTap() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new BlankFragment());
        fragmentList.add(new BlankFragment());
        fragmentList.add(new BlankFragment());
        fragmentList.add(new MyInfoFragment());
        myViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), fragmentList));
    }

    private void initView() {
        myViewPager = (ViewPager) findViewById(R.id.myViewPager);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);
        radio4 = (RadioButton) findViewById(R.id.radio4);
        radio = (RadioGroup) findViewById(R.id.radio);
        myViewPager.setCurrentItem(0);
        radio1.setChecked(true);
        myViewPager.addOnPageChangeListener(this);
        radio.setOnCheckedChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radio1.setChecked(true);
                break;
            case 1:
                radio2.setChecked(true);
                break;
            case 2:
                radio3.setChecked(true);
                break;
            case 3:
                radio4.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount(); i++) {
            if (group.getChildAt(i).getId() == checkedId) {
                myViewPager.setCurrentItem(i);
                return;
            }
        }
    }

    private class MyFragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;

        public MyFragmentAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }




}

