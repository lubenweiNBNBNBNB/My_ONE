package com.example.my_one;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity{
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox remeberPass;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //pref = PreferenceManager.getDefaultSharedPreferences(this);
        pref = getSharedPreferences("your_preference_name", Context.MODE_PRIVATE);


        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        remeberPass = (CheckBox) findViewById(R.id.remember_pass);
        login = (Button)findViewById(R.id.login);

        boolean isRemember = pref.getBoolean("remember_password",false);
        if(isRemember){
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            remeberPass.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                //这里只有一个合法输入：2022150054 我的学号，密码同
                if(account.equals("2022150054")&&password.equals("2022150054"))
                {
                    editor = pref.edit();
                    if(remeberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else{
                        editor.clear();
                    }
                    editor.apply();

                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    MainActivity.have_signed = 1;
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"account or password is invalid",Toast.LENGTH_LONG).show();
                }

            }

        });

        if(findViewById(R.id.login_image) != null)
        {
            ImageView imageView = (ImageView) findViewById(R.id.login_image);
            imageView.setImageResource(R.drawable.img);
        }
        else
        {
            ImageView imageView = (ImageView) findViewById(R.id.szu_image);
            imageView.setImageResource(R.drawable.img);
        }
    }
}
