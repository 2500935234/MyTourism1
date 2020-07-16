package com.example.mytourism;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyInfoFragment extends Fragment {

    private TextView TV_name;
    private SharedPreferences sharedPreferences;

    public MyInfoFragment() {
        // Required empty public constructor
    }

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_info, container, false);
        initView();
//        TV_name.setText(" " + "账号：" + sharedPreferences.getString("name",  ));
        return view;
    }

    private void initView() {
        sharedPreferences = getActivity().getSharedPreferences( "hnyd", Context.MODE_PRIVATE);
        TV_name = view.findViewById(R.id.et_name);
    }

}
