package com.example.mytourism;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_Password;
    private Button btn_register;
    private Button btn_return;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_Password = (EditText) findViewById(R.id.et_Password);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_return = (Button) findViewById(R.id.btn_return);

        btn_register.setOnClickListener(this);
        btn_return.setOnClickListener(this);
        sharedPreferences=getSharedPreferences("hnyd",MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:

                Toast.makeText(this,"注册功能暂未开放",Toast.LENGTH_LONG).show();
                submit();
                startActivity(new Intent(this,LoginActivity.class));
                finish();



                break;
            case R.id.btn_return:
                startActivity(new Intent(this,LoginActivity.class));
                finish();

                break;
        }
    }

    private void submit() {
        // validate
        String name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "会员账号", Toast.LENGTH_SHORT).show();
            return;
        }

        String Password = et_Password.getText().toString().trim();
        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "会员密码", Toast.LENGTH_SHORT).show();
            return;
        }
        editor.putString("name", name);
        editor.putString("Password", Password);
        editor.commit();




        // TODO validate success, do something


    }
}
