package com.example.mytourism;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_password;
    private CheckBox cb_autoLogin;
    private CheckBox cb_saveAccount;
    private Button btn_register;
    private Button btn_Login;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        cb_autoLogin = (CheckBox) findViewById(R.id.cb_autoLogin);
        cb_saveAccount = (CheckBox) findViewById(R.id.cb_saveAccount);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_Login = (Button) findViewById(R.id.btn_Login);

        btn_register.setOnClickListener(this);
        btn_Login.setOnClickListener(this);
        et_name.setOnClickListener(this);
        et_password.setOnClickListener(this);

        sharedPreferences=getSharedPreferences("hnyd",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        cb_saveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_saveAccount.isChecked()){
                    editor.putBoolean("savePassword",true);

                }else {
                    editor.putBoolean("savePassword",false);

                }
                editor.commit();
            }
        });
        cb_autoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_autoLogin.isChecked()){
                    editor.putBoolean("autoLogin",true);
                    cb_saveAccount.setChecked(true);
                }else {
                    editor.putBoolean("autoLogin",false);
                    cb_saveAccount.setChecked(false);
                }
                editor.commit();

            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();


                break;
            case R.id.btn_Login:
                submit();

                break;
        }
    }

    private void submit() {
        // validate
        String name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "账号与密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "账号与密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        savePassword(name,password);


        // TODO validate success, do something


    }

    private void savePassword(String name,String password) {
        if (sharedPreferences.getString("name", "").equals(name) && sharedPreferences.getString("Password", "").equals(password)) {
            //记住账号
            if (cb_saveAccount.isChecked()) {
                editor.putString("name", name);
                editor.putString("password", password);
                editor.putBoolean("savePassword", true);

            } else {
                editor.putBoolean("savePassword", false);
            }
            //自动登录
            if (cb_autoLogin.isChecked()) {
                editor.putBoolean("autoLogin", true);

            } else {
                editor.putBoolean("autoLogin", false);
            }
            editor.commit();

            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "账号或者密码不正确", Toast.LENGTH_SHORT).show();
        }
    }

    private void initData() {
//        Log.i("TAG", "judge: "+sharedPreferences.getBoolean("autoLogin",false));
        if (sharedPreferences.getBoolean("autoLogin", false)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        if (sharedPreferences.getBoolean("savePassword", false)) {
            et_name.setText(sharedPreferences.getString("name", null));
            et_password.setText(sharedPreferences.getString("Password", null));
            cb_saveAccount.setChecked(true);
        }
    }
}
