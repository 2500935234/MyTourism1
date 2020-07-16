package com.example.mytourism;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener {

    private ViewPager vp_guide;
    private int[] imgs = {R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3, R.mipmap.guide4};
    private List<View> mImagesViews;
    private GuideAdapter adapter;
    private RadioButton rb_1;
    private RadioButton rb_2;
    private RadioButton rb_3;
    private RadioButton rb_4;
    private RadioGroup radioGroup;
    private Button btn_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();
    }

    private void initView() {
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        vp_guide.setOnPageChangeListener(this);
        rb_1 = (RadioButton) findViewById(R.id.rb_1);
        rb_1.setOnClickListener(this);
        rb_2 = (RadioButton) findViewById(R.id.rb_2);
        rb_2.setOnClickListener(this);
        rb_3 = (RadioButton) findViewById(R.id.rb_3);
        rb_3.setOnClickListener(this);
        rb_4 = (RadioButton) findViewById(R.id.rb_4);
        rb_4.setOnClickListener(this);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        btn_go = (Button) findViewById(R.id.btn_go);
        btn_go.setOnClickListener(this);
    }

    public void initData() {
        mImagesViews = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgs[i]);
            mImagesViews.add(imageView);
        }
        adapter = new GuideAdapter(mImagesViews, this);
        vp_guide.setAdapter(adapter);

        vp_guide.setCurrentItem(0);
        rb_1.setChecked(true);
        vp_guide.setPageTransformer(true,new DepthPageTransformer());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go:
                startActivity(new Intent(GuideActivity.this,LoginActivity.class));
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i=0;i<group.getChildCount();i++){
            if (group.getChildAt(i).getId()==checkedId){
                vp_guide.setCurrentItem(i);
                return;
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position==imgs.length-1){
            btn_go.setVisibility(View.VISIBLE);
            AlphaAnimation a=new AlphaAnimation(0,1f);
            a.setDuration(1000);
            btn_go.startAnimation(a);
        }else {
            btn_go.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rb_1.setChecked(true);
                break;
            case 1:
                rb_2.setChecked(true);
                break;
            case 2:
                rb_3.setChecked(true);
                break;
            case 3:
                rb_4.setChecked(true);
                break;
        }


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class GuideAdapter extends PagerAdapter {
        private List<View> views;
        private Context context;

        public GuideAdapter(List<View> views, Context context) {
            this.views = views;
            this.context = context;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(views.get(position));

        }
    }
    class DepthPageTransformer implements ViewPager.PageTransformer{
        private  static final  float MIN_SCALE =0.75f;

        @Override
        public void transformPage(@NonNull View view, float position) {
            int pageWidth = view.getWidth();
            if (position < -1) {
                view.setAlpha(0);
            } else if (position <= 0) {
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);
            } else if (position <= 1) {
                view.setAlpha(1 - position);
                view.setTranslationX(pageWidth * -position);
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else {
                view.setAlpha(0);
            }
        }
    }
}
